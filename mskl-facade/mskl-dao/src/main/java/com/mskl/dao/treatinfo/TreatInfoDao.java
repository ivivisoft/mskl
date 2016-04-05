package com.mskl.dao.treatinfo;

import com.mskl.common.dto.TreatInfoDto;
import com.mskl.common.vo.TreatInfoVo;
import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklTreatInfo;

import java.io.Serializable;
import java.util.List;

public interface TreatInfoDao extends Dao<MsklTreatInfo,Serializable>{

    List<TreatInfoVo> getAllTreatInfo(TreatInfoDto treatInfoDto, Long userId);
}
