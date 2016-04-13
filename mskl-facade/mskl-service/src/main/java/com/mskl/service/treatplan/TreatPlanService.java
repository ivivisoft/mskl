package com.mskl.service.treatplan;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatPlanDto;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;

public interface TreatPlanService extends BaseService<MsklTreatPlan,Serializable>{

    /**
     * 添加服药计划
     * @param treatPlanDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> insertTreatPlan(TreatPlanDto treatPlanDto, String token);

    /**
     *
     * @return
     * @param token
     */
    RestServiceResult<List<MsklTreatPlan>> getAllTreatPlan(String token);

    /**
     * 获取所有的用药计划
     * @return
     */
    List<MsklTreatPlan> getAllTreatPlan();

    void generatorPlanLog(MsklTreatPlan treatPlan);

    RestServiceResult<Boolean> updateTreatPlan(TreatPlanDto treatPlanDto, String token);

    RestServiceResult<Boolean> deleteTreatPlan(String msklTreatplanId);
}
