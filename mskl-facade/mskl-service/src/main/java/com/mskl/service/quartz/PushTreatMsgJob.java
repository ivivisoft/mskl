package com.mskl.service.quartz;

import com.mskl.common.util.DateUtil;
import com.mskl.dao.model.MsklMedbox;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.service.medicinebox.MedicineBoxService;
import com.mskl.service.treatinfo.TreatInfoService;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.treatplan.TreatPlanService;
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

    @Resource(name = "treatLog.treatLogService")
    private TreatLogService treatLogService;

    @Resource(name = "treatPlan.treatPlanService")
    private TreatPlanService treatPlanService;

    @Resource(name = "treatInfo.treatInfoService")
    private TreatInfoService treatInfoService;

    @Resource(name = "medicineBox.medicineBoxService")
    private MedicineBoxService medicineBoxService;

    public void pushTreatMsgJob() {

        List<MsklTreatPlan> msklTreatPlanList = treatPlanService.getAllTreatPlan();

        Map yestodayParam = buildParam(DateUtil.dateToString(DateUtil.addDate(new Date(), -1), "yyyy-MM-dd"));

        for (MsklTreatPlan treatPlan : msklTreatPlanList) {
            //1.处理漏服情况
            yestodayParam.put("treatPlanId", treatPlan.getMsklTreatplanId());
            List<MsklTreatLog> yestodayTreatLog = treatLogService.getTreatLogsByDateAndPlanId(yestodayParam);
            for (MsklTreatLog treatLog : yestodayTreatLog) {
                treatLog.setTakenStatus(3);
                treatLogService.updateObject(treatLog);
            }
            //2.更新药箱预计结束时间
            if (yestodayTreatLog.size() > 0) {
                MsklMedbox msklMedbox = new MsklMedbox();
                msklMedbox.setUserId(treatPlan.getUserId());
                msklMedbox.setMsklMedicineId(treatPlan.getMsklMedicineId());
                medicineBoxService.updateBoxFinishDayByMedicineIdAndUserId(msklMedbox);
            }
            //3.生成服药记录
            Map param = new HashMap();
            param.put("medicineId", treatPlan.getUserId());
            param.put("medicineId", treatPlan.getMsklMedicineId());
            MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicineIdAndUserId(param);
            if (null != msklMedbox) {
                Date date = msklMedbox.getFinishDay();
                if (date.after(new Date())) {
                    treatPlanService.generatorPlanLog(treatPlan);
                }
            }
            //4.生成统计信息
            treatInfoService.generatorCurrentInfo(treatPlan);
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
