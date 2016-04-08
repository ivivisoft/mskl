package com.mskl.service.treatinfo;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatInfoDto;
import com.mskl.common.vo.TreatInfoVo;
import com.mskl.dao.model.MsklTreatInfo;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TreatInfoService extends BaseService<MsklTreatInfo,Serializable> {
    /**
     * 根据日期查询服药日期
     * @param date
     * @return
     */
    List<MsklTreatInfo> getTreatInfoByDate(Date date);

    /**
     * 按照服药计划生成统计信息
     * @param treatPlan
     */
    void generatorCurrentInfo(MsklTreatPlan treatPlan);

    /**
     * 根据用户ID和药品ID更新使用次数
     * @param userId
     * @param msklMedicineId
     */
    void updateTakeAmountByUserIDAndMedicineID(Long userId, Long msklMedicineId);

    /**
     * 根据用户id药品id 查询统计信息
     * @param treatInfoDto
     * @param token
     * @return
     */
    RestServiceResult<List<TreatInfoVo>> getAllTreatInfo(TreatInfoDto treatInfoDto, String token);

    /**
     * 按照日期,用户ID和药品ID删除统计信息
     * @param param
     */
    void deleteTreatInfoByUserIdDateAndMedicineId(Map param);
}
