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
            updateTreatPlan(treatPlanDto, result, msklTreatPlan);

            //2.更新药箱
            updateMedbox(treatPlanDto, result, msklMedbox);

            //3.处理服药记录
            dealTreatLog(treatPlanDto, result, msklTreatPlan, userId);

            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        } else {


            //1.保存服药计划
            saveTreatPlan(treatPlanDto, result, msklMedicine, msklUser);

            //2.个人药箱
            savaMedbox(treatPlanDto, result, userId, msklMedicine, msklUser);

            //3.服药记录
            saveTreatLog(treatPlanDto, result, userId, msklMedicine, msklUser);
            //4.服药信息
            saveTreatInfo(treatPlanDto, result, userId);

            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        }
    }

    private void saveTreatLog(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, Long userId, MsklMedicine msklMedicine, MsklUser msklUser) {
        if (isLateByCurrentDate(treatPlanDto.getMorningAlarm())) {
            insertTreatLog(treatPlanDto.getMorningAlarm(), userId, treatPlanDto, msklUser, msklMedicine, result);
        }
        if (isLateByCurrentDate(treatPlanDto.getNoonAlarm())) {
            insertTreatLog(treatPlanDto.getNoonAlarm(), userId, treatPlanDto, msklUser, msklMedicine, result);
        }
        if (isLateByCurrentDate(treatPlanDto.getNightAlarm())) {
            insertTreatLog(treatPlanDto.getNightAlarm(), userId, treatPlanDto, msklUser, msklMedicine, result);
        }
    }

    private void saveTreatInfo(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, Long userId) {
        try {
            MsklTreatInfo msklTreatInfo = new MsklTreatInfo();
            msklTreatInfo.setUserId(userId);
            msklTreatInfo.setMedicineId(Long.parseLong(treatPlanDto.getMsklMedicineId()));
            msklTreatInfo.setTakenTimes(0);
            msklTreatInfo.setDailyTimes(Integer.parseInt(treatPlanDto.getDailyTimes()));
            msklTreatInfo.setTreatDate(DateUtil.getCurrDateOfDate("yyyy-MM-dd"));
            treatInfoService.saveObject(msklTreatInfo);
        } catch (Exception e) {
            result.setMessage("增加服药信息到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return;
        }
    }

    private void savaMedbox(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, Long userId, MsklMedicine msklMedicine, MsklUser msklUser) {

        //个人药箱
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("medicineId", treatPlanDto.getMsklMedicineId());
        MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicineIdAndUserId(map);
        if (null == msklMedbox) {

            msklMedbox = new MsklMedbox();
            msklMedbox.setUserId(userId);
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
                return;
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
                return;
            }
        }
    }

    private void saveTreatPlan(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklMedicine msklMedicine, MsklUser msklUser) {
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
            msklTreatPlan.setMedicineUnit(treatPlanDto.getMedicineUnit());
            msklTreatPlan.setUserId(msklUser.getUserId());
            saveObject(msklTreatPlan);

        } catch (Exception e) {
            result.setMessage("增加服药计划到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return;
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
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("更新服药计划服务服务", false);

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
        updateTreatPlan(treatPlanDto, result, msklTreatPlan);

        //2.更新药箱
        updateMedbox(treatPlanDto, result, msklMedbox);

        //3.处理服药记录
        dealTreatLog(treatPlanDto, result, msklTreatPlan, userId);
        result.setSuccess(true);
        result.setData(Boolean.TRUE);
        return result;
    }

    private void dealTreatLog(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklTreatPlan msklTreatPlan, Long userId) {
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
            return;
        }
    }

    private void updateMedbox(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklMedbox msklMedbox) {
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
        }
    }

    private void updateTreatPlan(TreatPlanDto treatPlanDto, RestServiceResult<Boolean> result, MsklTreatPlan msklTreatPlan) {
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
            updateObject(msklTreatPlan);
        } catch (Exception e) {
            result.setMessage("更新用药计划信息失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return;
        }
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
        msklTreatLog.setDose(treatPlan.getDose());
        treatLogService.saveObject(msklTreatLog);
    }


    private void insertTreatLog(String alarm, Long userId, TreatPlanDto treatPlanDto, MsklUser msklUser, MsklMedicine msklMedicine, RestServiceResult<Boolean> result) {

        try {
            Date alarmTime = DateUtil.stringToDate(DateUtil.getCurrDate("yyyy-MM-dd") + " " + alarm, "yyyy-MM-dd HH:mm:ss");
            MsklTreatLog msklTreatLog = new MsklTreatLog();
            msklTreatLog.setUserId(userId);
            msklTreatLog.setUserMobile(msklUser.getMobile());
            msklTreatLog.setMsklMedicineId(msklMedicine.getMsklMedicineId());
            msklTreatLog.setMedicalName(msklMedicine.getMedicalName());
            msklTreatLog.setNormalName(msklMedicine.getNormalName());
            msklTreatLog.setTakenStatus(1);
            msklTreatLog.setSetAlarm(alarmTime);
            msklTreatLog.setDose(new Double(treatPlanDto.getDose()));
            treatLogService.saveObject(msklTreatLog);
        } catch (Exception e) {
            result.setMessage("增加服药记录到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return;
        }
    }
}
