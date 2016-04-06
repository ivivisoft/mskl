package com.mskl.service.treatplan.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.common.dto.TreatLogDto;
import com.mskl.common.util.DateUtil;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklMedbox;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.treatplan.TreatLogDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.medicinebox.MedicineBoxService;
import com.mskl.service.msklmedicine.MsklMedicineService;
import com.mskl.service.treatinfo.TreatInfoService;
import com.mskl.service.treatplan.TreatLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("treatLog.treatLogService")
public class TreatLogServiceImpl extends BaseServiceImpl<MsklTreatLog, Serializable> implements TreatLogService {

    private Log logger = LogFactory.getLog(TreatLogServiceImpl.class);

    private TreatLogDao treatLogDao;

    @Resource(name = "treatLog.treatLogDao")
    public void setTreatLogDao(TreatLogDao treatLogDao) {
        this.treatLogDao = treatLogDao;
        super.setBaseDaoImpl(treatLogDao);
    }

    @Resource(name = "medicineBox.medicineBoxService")
    private MedicineBoxService medicineBoxService;

    @Resource(name = "treatInfo.treatInfoService")
    private TreatInfoService treatInfoService;

    @Resource(name = "msklmedicine.msklMedicineService")
    private MsklMedicineService msklMedicineService;

    @Transactional
    public RestServiceResult<Boolean> updateTreatLog(TakeMedicineDto takeMedicineDto, String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("服药服务", false);
        result.setData(Boolean.FALSE);

        MsklTreatLog msklTreatLog = this.getObjectById(takeMedicineDto.getMsklTreatlogId());

        if (null == msklTreatLog) {
            result.setMessage("没有获取到服药记录!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        Map map = new HashMap();
        map.put("userId", msklTreatLog.getUserId());
        map.put("medicineId", msklTreatLog.getMsklMedicineId());

        MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicineIdAndUserId(map);
        if (null == msklMedbox) {
            result.setMessage("没有获取药箱记录!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        try {
            //更新新药箱
            if (msklMedbox.getStartDay() == null) {
                msklMedbox.setStartDay(new Date());
            }
            msklMedbox.setTakenAmount(msklMedbox.getTakenAmount() + msklMedbox.getDose());
            msklMedbox.setRemainingAmount(msklMedbox.getTotalAmount() - msklMedbox.getTakenAmount());
            int addDate = (int) Math.ceil(msklMedbox.getRemainingAmount() / (msklMedbox.getDose() * msklMedbox.getDailyTimes()));
            msklMedbox.setFinishDay(DateUtil.addDate(new Date(), addDate));
            msklMedbox.setUpdateDatetime(new Date());
            medicineBoxService.updateObject(msklMedbox);

            //更新统计
            treatInfoService.updateTakeAmountByUserIDAndMedicineID(msklMedbox.getUserId(), msklMedbox.getMsklMedicineId());


            msklTreatLog.setTakenStatus(2);
            msklTreatLog.setFinishAt(new Date());
            msklTreatLog.setUpdateDatetime(new Date());
            msklTreatLog.setTakenMood(Integer.parseInt(takeMedicineDto.getTakenMood()));
            msklTreatLog.setTakenWords(takeMedicineDto.getTakenWords());

            updateObject(msklTreatLog);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
        } catch (Exception e) {
            result.setMessage("更新服药到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }
        return result;
    }

    public RestServiceResult<List<MsklTreatLog>> getTreatLogs(TreatLogDto treatLogDto, String token) {

        RestServiceResult<List<MsklTreatLog>> result = new RestServiceResult<List<MsklTreatLog>>("查询服药服务", false);

        Long userId = TokenUtil.getUserIdFromToken(token);
        Map param = new HashMap();
        if (StringUtils.isNotBlank(treatLogDto.getDate())) {
            String beginTimeStr = treatLogDto.getDate() + " 00:00:00";
            String endTimeStr = treatLogDto.getDate() + " 23:59:59";
            Date beginTime;
            Date endTime;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                beginTime = sdf.parse(beginTimeStr);
                endTime = sdf.parse(endTimeStr);
            } catch (ParseException e) {
                result.setMessage("日期解析失败!");
                if (logger.isErrorEnabled()) {
                    logger.error(result.toString());
                }
                return result;
            }

            param.put("beginTime", beginTime);
            param.put("endTime", endTime);
        }

        param.put("userId", userId);

        try {
            List<MsklTreatLog> lists = treatLogDao.getTreatLogByUserIdAndDate(param);
            result.setSuccess(true);
            result.setData(lists);
            result.setMessage("查询成功!");

        } catch (Exception e) {
            result.setMessage("查询数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }

        return result;
    }

    public List<MsklTreatLog> getTreatLogsByDate(Map paramToday) {

        return treatLogDao.getTreatLogsByDate(paramToday);
    }

    public List<MsklTreatLog> getTreatLogsByDateAndPlanId(Map yestodayParam) {

        return treatLogDao.getTreatLogsByDateAndPlanId(yestodayParam);
    }

    public void deleteCurrentTreatLogByPlanId(Map param) {
        treatLogDao.deleteCurrentTreatLogByPlanId(param);
    }

    public RestServiceResult<MsklTreatLog> getTreatLog(String msklTreatlogId) {
        RestServiceResult<MsklTreatLog> result = new RestServiceResult<MsklTreatLog>("查询服药详情服务", false);
        try {
            MsklTreatLog msklTreatLog = treatLogDao.getObjectById(Long.parseLong(msklTreatlogId));
            result.setSuccess(true);
            result.setData(msklTreatLog);
        } catch (Exception e) {
            result.setMessage("查询数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }
        return result;
    }
}
