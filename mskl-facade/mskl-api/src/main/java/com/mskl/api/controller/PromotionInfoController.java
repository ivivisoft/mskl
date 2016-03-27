package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklFeedback;
import com.mskl.dao.model.MsklPromotionInfo;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.promotioninfo.PromotionInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/promotionInfo")
public class PromotionInfoController {

    @Resource(name = "promotionInfo.promotionInfoService")
    private PromotionInfoService promotionInfoService;

    @RequestMapping("/all")
    public
    @ResponseBody
    RestServiceResult<List<MsklPromotionInfo>> getPromotionInfos() {

        return promotionInfoService.getPromotionInfos();
    }

}