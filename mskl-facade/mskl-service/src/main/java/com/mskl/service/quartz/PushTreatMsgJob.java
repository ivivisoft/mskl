package com.mskl.service.quartz;

import com.mskl.common.util.DateUtil;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.service.pushmsg.PushMsgService;
import com.mskl.service.treatplan.TreatLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushTreatMsgJob {

    private Log logger = LogFactory.getLog(PushTreatMsgJob.class);

    @Resource(name = "pushMsg.pushMsgService")
    private PushMsgService pushMsgService;

    @Resource(name = "treatLog.treatLogService")
    private TreatLogService treatLogService;

    public void pushTreatMsgJob() {


        Map todayParam = buildParam(DateUtil.getCurrDate("yyyy-MM-dd"));
        Map yestodayParam = buildParam(DateUtil.dateToString(DateUtil.addDate(new Date(), -1), "yyyy-MM-dd"));

        List<MsklTreatLog> todayTreatLog = treatLogService.getTreatLogsByDate(todayParam);
        List<MsklTreatLog> yestodayTreatLog = treatLogService.getTreatLogsByDate(yestodayParam);

        insertMsklPushMsg(todayTreatLog);
        updateMsklPushMsg(yestodayTreatLog);

    }

    private void updateMsklPushMsg(List<MsklTreatLog> yestodayTreatLog) {

        for (MsklTreatLog treatLog : yestodayTreatLog) {

            MsklPushMsg msklPushMsg = pushMsgService.getMsgsByTreatLogId(treatLog.getMsklTreatlogId());
            if (StringUtils.equals("1",treatLog.getTakenStatus()+"")){
                msklPushMsg.setPushMsgDigest("3");
                pushMsgService.updateObject(msklPushMsg);

                treatLog.setTakenStatus(3);
                treatLogService.updateObject(treatLog);
            }
        }
    }

    private void insertMsklPushMsg(List<MsklTreatLog> todayTreatLog) {
        for (MsklTreatLog treatLog : todayTreatLog) {

            MsklPushMsg pushMsg = new MsklPushMsg();
            pushMsg.setPushModel("0");
            pushMsg.setPushType("01");
            pushMsg.setPushMsgKind("0");
            pushMsg.setPushMsgTitle("服药记录");
            pushMsg.setPushMsgDigest(treatLog.getTakenStatus() + "");
            pushMsg.setMsgFrom(0L);
            pushMsg.setRecvUserId(treatLog.getUserId() + "");
            pushMsg.setCreateDatetime(new Date());
            pushMsg.setTreatLogId(treatLog.getMsklTreatlogId());
            pushMsgService.saveObject(pushMsg);
        }
    }

    private Map buildParam(String date) {
        Map param = new HashMap();
        String beginDateStr = date + " 00:00:00";
        String endDateStr = date + " 23:59:59";
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = DateUtil.string2Date(beginDateStr, "yyyy-MM-dd HH:mm:ss");
            endDate = DateUtil.string2Date(endDateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            if (logger.isInfoEnabled()) {
                logger.error("日期解析异常!");
            }
        }
        param.put("beginDate", beginDate);
        param.put("endDate", endDate);
        return param;
    }
}
