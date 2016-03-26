package com.mskl.dao.promotioninfo.impl;

import com.mskl.dao.promotioninfo.PromotionInfoDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklPromotionInfo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository(value = "promotionInfo.promotionInfoDao")
public class PromotionInfoDaoImpl extends MsklBaseDao<MsklPromotionInfo, Serializable> implements PromotionInfoDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklPromotionInfoMapper";
    }

    public List<MsklPromotionInfo> getPromotionInfos() {
        return queryForList("list");
    }
}