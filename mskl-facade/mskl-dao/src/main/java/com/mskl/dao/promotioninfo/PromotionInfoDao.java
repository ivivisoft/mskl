package com.mskl.dao.promotioninfo;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklPromotionInfo;

import java.io.Serializable;
import java.util.List;

public interface PromotionInfoDao  extends Dao<MsklPromotionInfo,Serializable> {
    List<MsklPromotionInfo> getPromotionInfos();
}
