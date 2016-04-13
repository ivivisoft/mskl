package com.mskl.service.treatplan.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatPlanDto;
import com.mskl.common.util.DateUtil;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.*;
import com.mskl.dao.treatplan.TreatPlanDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.medicinebox.MedicineBoxService;
import com.mskl.service.msklmedicine.MsklMedicineService;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.treatinfo.TreatInfoService;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.treatplan.TreatPlanService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource(name = "treatInfo.treatInfoService")
    private TreatInfoService treatInfoService;


    @Transactional
    public RestServiceResult<Boolean> insertTreatPlan(TreatPlanDto treatPlanDto, String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("添加服药计划服务服务", false);
        //参数业务检查
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
        MsklTreatPlan msklTreatPlan = getMedicineInPlan(treatPlanDto, userId, result);
        if (null != msklTreatPlan) {
            //个人药箱
            Map map = new HashMap();
            map.put("userId", userId);
            map.put("medicineId", treatPlanDto.getMsklMedicineId());
            MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicineIdAndUserId(map);
            if (null == msklMedbox) {
                result.setMessage("个人药箱中无对应的药品信息!");
                return result;
            }

            //1.更新服药计划
            if (!updateTreatPlan(treatPlanDto, result, msklTreatPlan)) {
                return result;
            }

            //2.更新药箱
            if (!updateMedbox(treatPlanDto, result, msklMedbox)) {
                return result;
            }

            //3.处理服药记录
            if (!dealTreatLog(treatPlanDto, result, msklTreatPlan, userId)) {
                return result;
            }

            //4.服药信息
            if (!dealTreatInfo(treatPlanDto, result, userId)) {
                return result;
            }

            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        } else {


            //1.保存服药计划
            Long planId = saveTreatPlan(treatPlanDto, result, msklMedicine, msklUser);
            if (0L == planId) {
                return result;
            }

            //2.个人药箱
            if (!savaMedbox(treatPlanDto, result, msklMedicine, msklUser)) {
                return result;
            }

            //3.服药记录
            if (!saveTreatLog(treatPlanDto, result, msklMedicine, msklUser, planId)) {
                return result;
            }
            //4.服药信息
            if (!saveTreatInfo(treatPlanDto, result, userId)) {
                return result;
            }

            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        }
    }

    private boolean dealTreatInfo(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, Long userId) {
        try {
            //1.删除当天待服药的日志
            Map param = new HashMap();
            param.put("medicineId", treatPlanDto.getMsklMedicineId());
            param.put("treatDate", DateUtil.getCurrDateOfDate("yyyy-MM-dd"));
            param.put("userId", userId);
            treatInfoService.deleteTreatInfoByUserIdDateAndMedicineId(param);
            //2.生产新的服药信息
            saveTreatInfo(treatPlanDto, result, userId);
        } catch (Exception e) {
            result.setMessage("增加服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return false;
        }

        return true;
    }

    private boolean saveTreatLog(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklMedicine msklMedicine, MsklUser msklUser, Long planId) {
        if (isLateByCurrentDate(treatPlanDto.getMorningAlarm())) {
            insertTreatLog(treatPlanDto.getMorningAlarm(), treatPlanDto, msklUser, msklMedicine, result, planId);
        }
        if (isLateByCurrentDate(treatPlanDto.getNoonAlarm())) {
            insertTreatLog(treatPlanDto.getNoonAlarm(), treatPlanDto, msklUser, msklMedicine, result, planId);
        }
        if (isLateByCurrentDate(treatPlanDto.getNightAlarm())) {
            insertTreatLog(treatPlanDto.getNightAlarm(), treatPlanDto, msklUser, msklMedicine, result, planId);
        }
        return true;
    }

    private boolean saveTreatInfo(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, Long userId) {
        try {
            MsklTreatInfo msklTreatInfo = new MsklTreatInfo();
            msklTreatInfo.setUserId(userId);
            msklTreatInfo.setMedicineId(Long.parseLong(treatPlanDto.getMsklMedicineId()));
            msklTreatInfo.setTakenTimes(0);
            msklTreatInfo.setDailyTimes(0);
            if (isLateByCurrentDate(treatPlanDto.getMorningAlarm())) {
                msklTreatInfo.setDailyTimes(msklTreatInfo.getDailyTimes() + 1);
            }
            if (isLateByCurrentDate(treatPlanDto.getNoonAlarm())) {
                msklTreatInfo.setDailyTimes(msklTreatInfo.getDailyTimes() + 1);
            }
            if (isLateByCurrentDate(treatPlanDto.getNightAlarm())) {
                msklTreatInfo.setDailyTimes(msklTreatInfo.getDailyTimes() + 1);
            }
            msklTreatInfo.setTreatDate(DateUtil.getCurrDateOfDate("yyyy-MM-dd"));
            treatInfoService.saveObject(msklTreatInfo);
        } catch (Exception e) {
            result.setMessage("增加服药信息到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return false;
        }
        return true;
    }

    private boolean savaMedbox(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklMedicine msklMedicine, MsklUser msklUser) {

        //个人药箱
        Map map = new HashMap();
        map.put("userId", msklUser.getUserId());
        map.put("medicineId", treatPlanDto.getMsklMedicineId());
        MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicineIdAndUserId(map);
        if (null == msklMedbox) {

            msklMedbox = new MsklMedbox();
            msklMedbox.setUserId(msklUser.getUserId());
            msklMedbox.setUserMobile(msklUser.getMobile());
            msklMedbox.setUserRealName(msklUser.getUserRealName());
            msklMedbox.setMsklMedicineId(msklMedicine.getMsklMedicineId());
            msklMedbox.setMedicalName(msklMedicine.getMedicalName());
            msklMedbox.setNormalName(msklMedicine.getNormalName());
            msklMedbox.setTotalAmount(Integer.parseInt(treatPlanDto.getPackageAmount()));
            msklMedbox.setDose(Double.parseDouble(treatPlanDto.getDose()));
            msklMedbox.setDailyTimes(Integer.parseInt(treatPlanDto.getDailyTimes()));
            msklMedbox.setUpdateDatetime(new Date());
            msklMedbox.setTakenAmount(Double.parseDouble(treatPlanDto.getTakenAmount()));
            msklMedbox.setRemainingAmount(Double.parseDouble(msklMedbox.getTotalAmount() + "") - msklMedbox.getTakenAmount());
            int addDate = (int) Math.ceil(msklMedbox.getRemainingAmount() / (msklMedbox.getDose() * msklMedbox.getDailyTimes()));
            msklMedbox.setFinishDay(DateUtil.addDate(new Date(), addDate));
            try {
                medicineBoxService.saveObject(msklMedbox);
            } catch (Exception e) {
                result.setMessage("增加个人药箱到数据库失败!");
                if (logger.isErrorEnabled()) {
                    logger.error(result.toString());
                }
                return false;
            }
        } else {
            msklMedbox.setTotalAmount(msklMedbox.getTotalAmount() + Integer.parseInt(treatPlanDto.getPackageAmount()));
            msklMedbox.setDose(Double.parseDouble(treatPlanDto.getDose()));
            msklMedbox.setDailyTimes(Integer.parseInt(treatPlanDto.getDailyTimes()));
            msklMedbox.setUpdateDatetime(new Date());
            msklMedbox.setTakenAmount(msklMedbox.getTakenAmount() + Double.parseDouble(treatPlanDto.getTakenAmount()));
            msklMedbox.setRemainingAmount(Double.parseDouble(msklMedbox.getTotalAmount() + "") - msklMedbox.getTakenAmount());
            int addDate = (int) Math.ceil(msklMedbox.getRemainingAmount() / (msklMedbox.getDose() * msklMedbox.getDailyTimes()));
            msklMedbox.setFinishDay(DateUtil.addDate(new Date(), addDate));
            try {
                medicineBoxService.updateObject(msklMedbox);
            } catch (Exception e) {
                result.setMessage("修改个人药箱到数据库失败!");
                if (logger.isErrorEnabled()) {
                    logger.error(result.toString());
                }
                return false;
            }
        }
        return true;
    }

    private Long saveTreatPlan(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklMedicine msklMedicine, MsklUser msklUser) {
        try {
            MsklTreatPlan msklTreatPlan = new MsklTreatPlan();
            msklTreatPlan.setDailyTimes(Integer.parseInt(treatPlanDto.getDailyTimes()));
            msklTreatPlan.setDose(new Double(treatPlanDto.getDose()));
            msklTreatPlan.setPackageAmount(Integer.parseInt(treatPlanDto.getPackageAmount()));
            if (StringUtils.isNotBlank(treatPlanDto.getMorningAlarm())) {
                msklTreatPlan.setMorningAlarm(treatPlanDto.getMorningAlarm());
            }
            if (StringUtils.isNotBlank(treatPlanDto.getNoonAlarm())) {
                msklTreatPlan.setNoonAlarm(treatPlanDto.getNoonAlarm());
            }
            if (StringUtils.isNotBlank(treatPlanDto.getNightAlarm())) {
                msklTreatPlan.setNightAlarm(treatPlanDto.getNightAlarm());
            }
            msklTreatPlan.setTakenAmount(new Double(treatPlanDto.getTakenAmount()));
            msklTreatPlan.setMsklMedicineId(msklMedicine.getMsklMedicineId());
            msklTreatPlan.setUpdateDatetime(new Date());
            msklTreatPlan.setMedicalName(msklMedicine.getMedicalName());
            msklTreatPlan.setNormalName(msklMedicine.getNormalName());
            msklTreatPlan.setMedicineUnit(msklMedicine.getMedicineUnit());
            msklTreatPlan.setUserId(msklUser.getUserId());
            treatPlanDao.insertSelectiveBackId(msklTreatPlan);
            return msklTreatPlan.getMsklTreatplanId();
        } catch (Exception e) {
            result.setMessage("增加服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return 0L;
        }
    }

    private MsklTreatPlan getMedicineInPlan(TreatPlanDto treatPlanDto, Long userId, RestServiceResult<Boolean> result) {
        try {
            return treatPlanDao.getMedicineInPlan(treatPlanDto.getMsklMedicineId(), userId);
        } catch (Exception e) {
            result.setMessage("获取服药计划失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return null;
        }
    }

    @Transactional
    public RestServiceResult<Boolean> updateTreatPlan(TreatPlanDto treatPlanDto, String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("更新服药计划服务", false);

        Long userId = TokenUtil.getUserIdFromToken(token);
        MsklTreatPlan msklTreatPlan = getObjectById(treatPlanDto.getTreatPlanId());
        if (null == msklTreatPlan) {
            result.setMessage("没有对应的服药计划信息!");
            return result;
        }

        //个人药箱
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("medicineId", treatPlanDto.getMsklMedicineId());
        MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicineIdAndUserId(map);
        if (null == msklMedbox) {
            result.setMessage("个人药箱中无对应的药品信息!");
            return result;
        }

        //1.更新服药计划
        if (!updateTreatPlan(treatPlanDto, result, msklTreatPlan)) {
            return result;
        }

        //2.更新药箱
        if (!updateMedbox(treatPlanDto, result, msklMedbox)) {
            return result;
        }

        //3.处理服药记录
        if (!dealTreatLog(treatPlanDto, result, msklTreatPlan, userId)) {
            return result;
        }
        //4.服药信息
        if (!dealTreatInfo(treatPlanDto, result, userId)) {
            return result;
        }
        result.setSuccess(true);
        result.setData(Boolean.TRUE);
        return result;
    }

    public RestServiceResult<Boolean> deleteTreatPlan(String msklTreatplanId) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("删除服药计划服务", false);
        try {
            deleteObjectById(Long.parseLong(msklTreatplanId));
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        } catch (Exception e) {
            result.setMessage("删除服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }

    }

    private boolean dealTreatLog(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklTreatPlan msklTreatPlan, Long userId) {
        try {
            //1.删除当天待服药的日志
            Map param = new HashMap();
            param.put("msklMedicineId", msklTreatPlan.getMsklMedicineId());
            param.put("alarm", new Date());
            param.put("userId", userId);
            treatLogService.deleteCurrentTreatLogByPlanId(param);
            //2.生产新的服药记录
            if (isLateByCurrentDate(treatPlanDto.getMorningAlarm())) {
                insertTreatLog(msklTreatPlan, 1);
            }
            if (isLateByCurrentDate(treatPlanDto.getNoonAlarm())) {
                insertTreatLog(msklTreatPlan, 2);
            }
            if (isLateByCurrentDate(treatPlanDto.getNightAlarm())) {
                insertTreatLog(msklTreatPlan, 3);
            }
        } catch (Exception e) {
            result.setMessage("增加服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return false;
        }
        return true;
    }

    private boolean updateMedbox(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklMedbox msklMedbox) {
        try {
            msklMedbox.setDose(Double.parseDouble(treatPlanDto.getDose()));
            msklMedbox.setDailyTimes(Integer.parseInt(treatPlanDto.getDailyTimes()));
            msklMedbox.setTotalAmount(msklMedbox.getTotalAmount() + Integer.parseInt(treatPlanDto.getPackageAmount()));
            msklMedbox.setTakenAmount(msklMedbox.getTakenAmount() + Double.parseDouble(treatPlanDto.getTakenAmount()));
            msklMedbox.setRemainingAmount(msklMedbox.getTotalAmount() - msklMedbox.getTakenAmount());
            msklMedbox.setUpdateDatetime(new Date());
            int addDate = (int) Math.ceil(msklMedbox.getRemainingAmount() / (msklMedbox.getDose() * msklMedbox.getDailyTimes()));
            msklMedbox.setFinishDay(DateUtil.addDate(new Date(), addDate));

            medicineBoxService.updateObject(msklMedbox);
        } catch (Exception e) {
            result.setMessage("增加个人药箱到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return false;
        }
        return true;
    }

    private boolean updateTreatPlan(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklTreatPlan msklTreatPlan) {
        try {
            msklTreatPlan.setDailyTimes(Integer.parseInt(treatPlanDto.getDailyTimes()));
            msklTreatPlan.setDose(new Double(treatPlanDto.getDose()));
            msklTreatPlan.setPackageAmount(Integer.parseInt(treatPlanDto.getPackageAmount()));
            if (StringUtils.isNotBlank(treatPlanDto.getMorningAlarm())) {
                msklTreatPlan.setMorningAlarm(treatPlanDto.getMorningAlarm());
            }
            if (StringUtils.isNotBlank(treatPlanDto.getNoonAlarm())) {
                msklTreatPlan.setNoonAlarm(treatPlanDto.getNoonAlarm());
            }
            if (StringUtils.isNotBlank(treatPlanDto.getNightAlarm())) {
                msklTreatPlan.setNightAlarm(treatPlanDto.getNightAlarm());
            }
            msklTreatPlan.setUpdateDatetime(new Date());
            updateObject(msklTreatPlan);
        } catch (Exception e) {
            result.setMessage("更新用药计划信息失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return false;
        }
        return true;
    }

    private boolean isLateByCurrentDate(String date) {
        if (StringUtils.isBlank(date)) {
            return false;
        }
        Date current = DateUtil.getCurrDateOfDate(DateUtil.DEFAULT_FORMAT_STR);
        String yymmdd = DateUtil.getCurrDate("yyyy-MM-dd");
        Date someDate = DateUtil.stringToDate(yymmdd + " " + date, DateUtil.DEFAULT_FORMAT_STR);
        return current.before(someDate);
    }

    public RestServiceResult<List<MsklTreatPlan>> getAllTreatPlan(String token) {
        RestServiceResult<List<MsklTreatPlan>> result = new RestServiceResult<List<MsklTreatPlan>>("查询用药计划服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);
        List<MsklTreatPlan> msklTreatPlanList = treatPlanDao.getAllTreatPlanByUserId(userId);
        if (null == msklTreatPlanList) {
            result.setMessage("没有查询到服药计划!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
        result.setSuccess(true);
        result.setData(msklTreatPlanList);
        result.setMessage("查询服药计划成功!");

        return result;
    }

    public List<MsklTreatPlan> getAllTreatPlan() {
        List<MsklTreatPlan> msklTreatPlanList = treatPlanDao.getAllTreatPlan();
        return msklTreatPlanList;
    }

    public void generatorPlanLog(MsklTreatPlan treatPlan) {
        //服药记录
        if (isLateByCurrentDate(treatPlan.getMorningAlarm())) {
            insertTreatLog(treatPlan, 1);
        }
        if (isLateByCurrentDate(treatPlan.getNoonAlarm())) {
            insertTreatLog(treatPlan, 2);
        }
        if (isLateByCurrentDate(treatPlan.getNightAlarm())) {
            insertTreatLog(treatPlan, 3);
        }
    }


    private void insertTreatLog(MsklTreatPlan treatPlan, int type) {
        switch (type) {
            case 1:
                insertTreatLogByDate(treatPlan, treatPlan.getMorningAlarm());
                break;
            case 2:
                insertTreatLogByDate(treatPlan, treatPlan.getNoonAlarm());
                break;
            case 3:
                insertTreatLogByDate(treatPlan, treatPlan.getNightAlarm());
                break;
        }
    }

    private void insertTreatLogByDate(MsklTreatPlan treatPlan, String alarm) {
        Date alarmTime = DateUtil.stringToDate(DateUtil.getCurrDate("yyyy-MM-dd") + " " + alarm, "yyyy-MM-dd HH:mm:ss");
        MsklTreatLog msklTreatLog = new MsklTreatLog();
        msklTreatLog.setUserId(treatPlan.getUserId());
        msklTreatLog.setMsklMedicineId(treatPlan.getMsklMedicineId());
        msklTreatLog.setMedicalName(treatPlan.getMedicalName());
        msklTreatLog.setNormalName(treatPlan.getNormalName());
        msklTreatLog.setTakenStatus(1);
        msklTreatLog.setSetAlarm(alarmTime);
        msklTreatLog.setMedicineUnit(treatPlan.getMedicineUnit());
        msklTreatLog.setDose(treatPlan.getDose());
        msklTreatLog.setTreatPlanId(treatPlan.getMsklTreatplanId());
        msklTreatLog.setUpdateDatetime(new Date());
        treatLogService.saveObject(msklTreatLog);
    }


    private boolean insertTreatLog(String alarm, TreatPlanDto treatPlanDto, MsklUser msklUser, MsklMedicine msklMedicine, RestServiceResult<Boolean> result, Long planId) {

        try {
            Date alarmTime = DateUtil.stringToDate(DateUtil.getCurrDate("yyyy-MM-dd") + " " + alarm, "yyyy-MM-dd HH:mm:ss");
            MsklTreatLog msklTreatLog = new MsklTreatLog();
            msklTreatLog.setUserId(msklUser.getUserId());
            msklTreatLog.setUserMobile(msklUser.getMobile());
            msklTreatLog.setMsklMedicineId(msklMedicine.getMsklMedicineId());
            msklTreatLog.setMedicalName(msklMedicine.getMedicalName());
            msklTreatLog.setNormalName(msklMedicine.getNormalName());
            msklTreatLog.setTakenStatus(1);
            msklTreatLog.setSetAlarm(alarmTime);
            msklTreatLog.setMedicineUnit(msklMedicine.getMedicineUnit());
            msklTreatLog.setDose(new Double(treatPlanDto.getDose()));
            msklTreatLog.setTreatPlanId(planId);
            msklTreatLog.setUpdateDatetime(new Date());
            treatLogService.saveObject(msklTreatLog);
        } catch (Exception e) {
            result.setMessage("增加服药记录到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return false;
        }
        return true;
    }
}
