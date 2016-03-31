package com.mskl.service.treatplan;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface TreatLogService extends BaseService<MsklTreatLog,Serializable> {

    /**
     * 服药
     * @param takeMedicineDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> updateTreatLog(TakeMedicineDto takeMedicineDto, String token);
}
