package com.mskl.service.treatplan;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.common.dto.TreatLogDto;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface TreatLogService extends BaseService<MsklTreatLog,Serializable> {

    /**
     * 服药
     * @param takeMedicineDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> updateTreatLog(TakeMedicineDto takeMedicineDto, String token);

    /**
     * 查询服药记录
     * @param treatLogDto
     * @param token
     * @return
     */
    RestServiceResult<List<MsklTreatLog>> getTreatLogs(TreatLogDto treatLogDto, String token);

    /**
     * 通过日期查询服药记录
     * @param paramToday
     * @return
     */
    List<MsklTreatLog> getTreatLogsByDate(Map paramToday);

    /**
     * 根据计划id和时间查询用药记录
     * @param yestodayParam
     * @return
     */
    List<MsklTreatLog> getTreatLogsByDateAndPlanId(Map yestodayParam);

    /**
     * 根据计划ID删除当前待服药记录
     * @param param
     */
    void deleteCurrentTreatLogByPlanId(Map param);
}
