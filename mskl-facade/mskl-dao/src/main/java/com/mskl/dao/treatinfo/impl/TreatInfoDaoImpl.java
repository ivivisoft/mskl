package com.mskl.dao.treatinfo.impl;

import com.mskl.common.dto.TreatInfoDto;
import com.mskl.common.vo.TreatInfoVo;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklTreatInfo;
import com.mskl.dao.treatinfo.TreatInfoDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "treatInfo.treatInfoDao")
public class TreatInfoDaoImpl extends MsklBaseDao<MsklTreatInfo, Serializable> implements TreatInfoDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklTreatInfoMapper";
    }

    public List<TreatInfoVo> getAllTreatInfo(TreatInfoDto treatInfoDto, Long userId) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("beginDate", treatInfoDto.getBeginDate());
        map.put("endDate", treatInfoDto.getEndDate());
        return super.getSqlSession().selectList("getAllTreatInfo", map);
    }

    public void deleteTreatInfoByUserIdDateAndMedicineId(Map param) {
        super.deleteByStatementName("deleteTreatInfoByUserIdDateAndMedicineId", param);
    }
}
