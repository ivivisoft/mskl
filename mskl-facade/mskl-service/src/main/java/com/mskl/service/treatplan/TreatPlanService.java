package com.mskl.service.treatplan;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatPlanDto;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface TreatPlanService extends BaseService<MsklTreatPlan,Serializable>{

    /**
     * 添加服药计划
     * @param treatPlanDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> insertTreatPlan(TreatPlanDto treatPlanDto, String token);

    /**
     * 更新服药计划
     * @param treatPlanDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> updateTreatPlan(TreatPlanDto treatPlanDto, String token);
}
