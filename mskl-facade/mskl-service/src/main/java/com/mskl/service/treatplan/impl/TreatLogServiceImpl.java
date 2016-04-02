package com.mskl.service.treatplan.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.common.dto.TreatLogDto;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklMedbox;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.treatplan.TreatLogDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.medicinebox.MedicineBoxService;
import com.mskl.service.msklmedicine.MsklMedicineService;
import com.mskl.service.treatplan.TreatLogService;
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

    @Resource(name = "msklmedicine.msklMedicineService")
    private MsklMedicineService msklMedicineService;

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
        MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicine(msklTreatLog.getMsklMedicineId());
        if (null == msklMedbox) {
            result.setMessage("没有获取药箱记录!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        MsklMedicine msklMedicine = msklMedicineService.getObjectById(msklTreatLog.getMsklMedicineId());
        if (null == msklMedbox.getRemainingAmount() || msklMedbox.getRemainingAmount() - msklMedicine.getDose() <= 0) {
            result.setMessage("药箱药量不足!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        msklTreatLog.setTakenStatus(1);
        msklTreatLog.setFinishAt(new Date());
        msklTreatLog.setUpdateDatetime(new Date());
        try {
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
        Map param = new HashMap();
        param.put("userId", userId);
        param.put("beginTime", beginTime);
        param.put("endTime", endTime);

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
}
