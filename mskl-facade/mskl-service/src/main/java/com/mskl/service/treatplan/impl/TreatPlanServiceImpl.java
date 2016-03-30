package com.mskl.service.treatplan.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatPlanDto;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.*;
import com.mskl.dao.treatplan.TreatPlanDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.medicinebox.MedicineBoxService;
import com.mskl.service.msklmedicine.MsklMedicineService;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.treatplan.TreatPlanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Service(value = "treatPlan.treatPlanService")
public class TreatPlanServiceImpl extends BaseServiceImpl<MsklTreatPlan, Serializable> implements TreatPlanService {

    private Log logger = LogFactory.getLog(TreatPlanServiceImpl.class);

    private TreatPlanDao treatPlanDao;

    @Resource(name = "treatPlan.treatPlanDao")
    public void setTreatPlanDao(TreatPlanDao treatPlanDao) {
        this.treatPlanDao = treatPlanDao;
        super.setBaseDaoImpl(treatPlanDao);
    }

    @Resource(name = "msklmedicine.msklMedicineService")
    private MsklMedicineService msklMedicineService;

    @Resource(name = "mskluser.msklUserService")
    private MsklUserService msklUserService;

    @Resource(name = "medicineBox.medicineBoxService")
    private MedicineBoxService medicineBoxService;

    @Resource(name = "treatLog.treatLogService")
    private TreatLogService treatLogService;



    public RestServiceResult<Boolean> insertTreatPlan(TreatPlanDto treatPlanDto, String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("添加服药计划服务服务", false);

        Long userId = TokenUtil.getUserIdFromToken(token);

        MsklMedicine msklMedicine = msklMedicineService.getObjectById(Long.parseLong(treatPlanDto.getMsklMedicineId()));
        if (null == msklMedicine) {
            result.setMessage("没有对应的药品信息!");
            return result;
        }

        MsklUser msklUser = msklUserService.getObjectById(userId);
        if (null == msklUser) {
            result.setMessage("没有用户信息!");
            return result;
        }
        //个人药箱
        MsklMedbox msklMedbox = new MsklMedbox();

        msklMedbox.setUserId(userId);
        msklMedbox.setUserMobile(msklUser.getMobile());
        msklMedbox.setUserRealName(msklUser.getUserRealName());
        msklMedbox.setMsklMedicineId(msklMedicine.getMsklMedicineId());
        msklMedbox.setMedicalName(msklMedicine.getMedicalName());
        msklMedbox.setNormalName(msklMedicine.getNormalName());
        msklMedbox.setTotalAmount(new BigDecimal(treatPlanDto.getPackageAmount()));
        msklMedbox.setDose(new BigDecimal(treatPlanDto.getDose()));
        msklMedbox.setDailyTimes(Short.parseShort(treatPlanDto.getDailyTimes()));
        msklMedbox.setUpdateDatetime(new Date());

        try {
            medicineBoxService.saveObject(msklMedbox);
        } catch (Exception e) {
            result.setMessage("增加个人药箱到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }

        //服药记录
        if (null!= treatPlanDto.getMorningAlarm()) {
            insertTreatLog(treatPlanDto.getMorningAlarm(),userId,treatPlanDto,msklUser,msklMedicine,result);
        }
        if (null!= treatPlanDto.getMorningAlarm()) {
            insertTreatLog(treatPlanDto.getNoonAlarm(),userId,treatPlanDto,msklUser,msklMedicine,result);
        }
        if (null!= treatPlanDto.getMorningAlarm()) {
            insertTreatLog(treatPlanDto.getNightAlarm(),userId,treatPlanDto,msklUser,msklMedicine,result);
        }



        //服药计划
        MsklTreatPlan msklTreatPlan = new MsklTreatPlan();

        msklTreatPlan.setDailyTimes(Short.parseShort(treatPlanDto.getDailyTimes()));
        msklTreatPlan.setDose(new BigDecimal(treatPlanDto.getDose()));
        msklTreatPlan.setPackageAmount(new BigDecimal(treatPlanDto.getPackageAmount()));
        msklTreatPlan.setMorningAlarm(treatPlanDto.getMorningAlarm() == null ? null : Integer.parseInt(treatPlanDto.getMorningAlarm()));
        msklTreatPlan.setNightAlarm(treatPlanDto.getNightAlarm() == null ? null : Integer.parseInt(treatPlanDto.getNightAlarm()));
        msklTreatPlan.setNoonAlarm(treatPlanDto.getNoonAlarm() == null ? null : Integer.parseInt(treatPlanDto.getNoonAlarm()));
        msklTreatPlan.setTakenAmount(new BigDecimal(treatPlanDto.getTakenAmount()));
        msklTreatPlan.setMsklMedicineId(msklMedicine.getMsklMedicineId());
        msklTreatPlan.setUpdateDatetime(new Date());
        msklTreatPlan.setMedicalName(msklMedicine.getMedicalName());
        msklTreatPlan.setNormalName(msklMedicine.getNormalName());
        msklTreatPlan.setMedicineUnit(msklMedicine.getMedicineUnit());

        try {
            saveObject(msklTreatPlan);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
        } catch (Exception e) {
            result.setMessage("增加服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }


        return result;
    }

    private void insertTreatLog(String alarm, Long userId, TreatPlanDto treatPlanDto, MsklUser msklUser, MsklMedicine msklMedicine, RestServiceResult<Boolean> result) {

        MsklTreatLog msklTreatLog = new MsklTreatLog();

        msklTreatLog.setUserId(userId);
        msklTreatLog.setUserMobile(msklUser.getMobile());
        msklTreatLog.setMsklMedicineId(msklMedicine.getMsklMedicineId());
        msklTreatLog.setMedicalName(msklMedicine.getMedicalName());
        msklTreatLog.setNormalName(msklMedicine.getNormalName());
        msklTreatLog.setTakenStatus(new Short("1"));
        msklTreatLog.setSetAlarm(Integer.parseInt(alarm));
        msklTreatLog.setDose(new BigDecimal(treatPlanDto.getDose()));

        try {
            treatLogService.saveObject(msklTreatLog);
        } catch (Exception e) {
            result.setMessage("增加服药记录到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }
    }


    public RestServiceResult<Boolean> updateTreatPlan(TreatPlanDto treatPlanDto, String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("更新服药计划服务服务", false);

        Long userId = TokenUtil.getUserIdFromToken(token);

        MsklMedicine msklMedicine = msklMedicineService.getObjectById(Long.parseLong(treatPlanDto.getMsklMedicineId()));
        if (null == msklMedicine) {
            result.setMessage("没有对应的药品信息!");
            return result;
        }
        MsklTreatPlan msklTreatPlan = new MsklTreatPlan();

        msklTreatPlan.setDailyTimes(Short.parseShort(treatPlanDto.getDailyTimes()));
        msklTreatPlan.setDose(new BigDecimal(treatPlanDto.getDose()));
        msklTreatPlan.setPackageAmount(new BigDecimal(treatPlanDto.getPackageAmount()));
        msklTreatPlan.setMorningAlarm(treatPlanDto.getMorningAlarm() == null ? null : Integer.parseInt(treatPlanDto.getMorningAlarm()));
        msklTreatPlan.setNightAlarm(treatPlanDto.getNightAlarm() == null ? null : Integer.parseInt(treatPlanDto.getNightAlarm()));
        msklTreatPlan.setNoonAlarm(treatPlanDto.getNoonAlarm() == null ? null : Integer.parseInt(treatPlanDto.getNoonAlarm()));
        msklTreatPlan.setTakenAmount(new BigDecimal(treatPlanDto.getTakenAmount()));
        msklTreatPlan.setMsklMedicineId(msklMedicine.getMsklMedicineId());
        msklTreatPlan.setUpdateDatetime(new Date());
        msklTreatPlan.setMedicalName(msklMedicine.getMedicalName());
        msklTreatPlan.setNormalName(msklMedicine.getNormalName());
        msklTreatPlan.setMedicineUnit(msklMedicine.getMedicineUnit());
        msklTreatPlan.setMsklTreatplanId(Long.parseLong(treatPlanDto.getMsklTreatplanId()));

        try {
            updateObject(msklTreatPlan);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
        } catch (Exception e) {
            result.setMessage("更新服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }

        return result;
    }
}
