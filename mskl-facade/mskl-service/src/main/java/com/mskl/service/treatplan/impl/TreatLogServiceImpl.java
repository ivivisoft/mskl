package com.mskl.service.treatplan.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.dao.model.MsklMedbox;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.treatplan.TreatLogDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.medicinebox.MedicineBoxService;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.treatplan.TreatPlanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Service("treatLog.treatLogService")
public class TreatLogServiceImpl extends BaseServiceImpl<MsklTreatLog,Serializable> implements TreatLogService{

    private Log logger = LogFactory.getLog(TreatLogServiceImpl.class);

    private TreatLogDao treatLogDao;
    @Resource(name = "treatLog.treatLogDao")
    public void setTreatLogDao(TreatLogDao treatLogDao) {
        this.treatLogDao = treatLogDao;
        super.setBaseDaoImpl(treatLogDao);
    }

    @Resource(name = "medicineBox.medicineBoxService")
    private MedicineBoxService medicineBoxService;

    public RestServiceResult<Boolean> updateTreatLog(TakeMedicineDto takeMedicineDto, String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("服药服务", false);
        result.setData(Boolean.FALSE);

        MsklTreatLog msklTreatLog = this.getObjectById(takeMedicineDto.getMsklTreatlogId());

        if(null == msklTreatLog){
            result.setMessage("没有获取到服药记录!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        MsklMedbox msklMedbox = medicineBoxService.getBoxByMedicine(msklTreatLog.getMsklMedicineId());
        if (null == msklMedbox){
            result.setMessage("没有获取药箱记录!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        if( new Integer(msklMedbox.getRemainingAmount()+"")<=0){
            result.setMessage("药箱药量不足!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
        }
        //待完善
        msklMedbox.setTakenAmount(new BigDecimal("2"));
        try {
            medicineBoxService.updateObject(msklMedbox);
        } catch (Exception e) {
            result.setMessage("更新医药箱到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }



        msklTreatLog.setTakenStatus(new Short("2"));
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
}
