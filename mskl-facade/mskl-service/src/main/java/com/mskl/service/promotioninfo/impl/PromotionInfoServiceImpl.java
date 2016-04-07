package com.mskl.service.promotioninfo.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.promotioninfo.PromotionInfoDao;
import com.mskl.dao.model.MsklPromotionInfo;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.promotioninfo.PromotionInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "promotionInfo.promotionInfoService")
public class PromotionInfoServiceImpl extends BaseServiceImpl<MsklPromotionInfo, String> implements PromotionInfoService {

    private Log logger = LogFactory.getLog(PromotionInfoServiceImpl.class);

    private PromotionInfoDao promotionInfoDao;

    @Resource(name = "promotionInfo.promotionInfoDao")
    public void setPromotionInfoDao(PromotionInfoDao promotionInfoDao) {
        this.promotionInfoDao = promotionInfoDao;
        super.setBaseDaoImpl(promotionInfoDao);
    }

    public RestServiceResult<List<MsklPromotionInfo>> getPromotionInfos() {
        RestServiceResult<List<MsklPromotionInfo>> result = new RestServiceResult<List<MsklPromotionInfo>>("推广活动", false);
        try {
            List<MsklPromotionInfo> promotionInfos = promotionInfoDao.getPromotionInfos();
            result.setSuccess(true);
            result.setData(promotionInfos);
            return result;
        } catch (Exception e) {
            result.setMessage("获取推广活动异常!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
    }
}
