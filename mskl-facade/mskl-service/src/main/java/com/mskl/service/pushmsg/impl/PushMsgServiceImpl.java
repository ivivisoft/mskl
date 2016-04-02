package com.mskl.service.pushmsg.impl;

import com.mskl.common.dto.PushMsgDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.dao.pushmsg.PushMsgDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.pushmsg.PushMsgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "pushMsg.pushMsgService")
public class PushMsgServiceImpl extends BaseServiceImpl<MsklPushMsg, Serializable> implements PushMsgService {

    private Log logger = LogFactory.getLog(PushMsgServiceImpl.class);
    private PushMsgDao pushMsgDao;

    @Resource(name = "pushMsg.pushMsgDao")
    public void setPushMsgDao(PushMsgDao pushMsgDao) {
        this.pushMsgDao = pushMsgDao;
        super.setBaseDaoImpl(pushMsgDao);
    }

    public MsklPushMsg getMsgsByTreatLogId(Long msklTreatlogId) {
        return pushMsgDao.getMsgsByTreatLogId(msklTreatlogId);
    }

    public RestServiceResult<List<MsklPushMsg>> getPushMsgByDateAndUserId(PushMsgDto pushMsgDto, String token) {

        RestServiceResult<List<MsklPushMsg>> result = new RestServiceResult<List<MsklPushMsg>>("进入查询推送消息服务", false);

        Long userId = TokenUtil.getUserIdFromToken(token);
        Map param = buildDateparam(pushMsgDto, userId, result);

        List<MsklPushMsg> lists = pushMsgDao.getPushMsgByDateAndUserId(param);

        result.setData(lists);
        result.setSuccess(true);

        return result;
    }

    private Map buildDateparam(PushMsgDto pushMsgDto, Long userId, RestServiceResult<List<MsklPushMsg>> result) {

        String beginTimeStr = pushMsgDto.getDate() + " 00:00:00";
        String endTimeStr = pushMsgDto.getDate() + " 23:59:59";

        Date beginTime = null;
        Date endTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            beginTime = sdf.parse(beginTimeStr);
            endTime = sdf.parse(endTimeStr);
        } catch (ParseException e) {
            result.setMessage("日期解析失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }
        Map param = new HashMap();
        param.put("userId", userId);
        param.put("beginTime", beginTime);
        param.put("endTime", endTime);

        return param;

    }
}
