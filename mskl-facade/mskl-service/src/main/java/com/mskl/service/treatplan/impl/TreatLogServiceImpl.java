package com.mskl.service.treatplan.impl;

import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.treatplan.TreatLogDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.treatplan.TreatPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service("treatLog.treatLogService")
public class TreatLogServiceImpl extends BaseServiceImpl<MsklTreatLog,Serializable> implements TreatLogService{

    private TreatLogDao treatLogDao;
    @Resource(name = "treatLog.treatLogDao")
    public void setTreatLogDao(TreatLogDao treatLogDao) {
        this.treatLogDao = treatLogDao;
        super.setBaseDaoImpl(treatLogDao);
    }
}
