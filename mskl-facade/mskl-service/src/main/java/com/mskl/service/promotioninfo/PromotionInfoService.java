package com.mskl.service.promotioninfo;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklPromotionInfo;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;

public interface PromotionInfoService extends BaseService<MsklPromotionInfo,Serializable> {
    /**
     * 获取所有的推广信息
     * @return
     */
    RestServiceResult<List<MsklPromotionInfo>> getPromotionInfos();
}
