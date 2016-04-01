package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatPlanDto;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.service.treatplan.TreatPlanService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/treatPlan")
public class TreatPlanController {

    private Log logger = LogFactory.getLog(TreatPlanController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "treatPlan.treatPlanService")
    private TreatPlanService treatPlanService;

    @RequestMapping("/insert/{time}/{md5str}/{token}")
    public RestServiceResult<Boolean> insertTreatPlan(@RequestBody TreatPlanDto treatPlanDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入添加或服药计划controller类", false);
        if (!verificationService.verification(treatPlanDto, token, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return treatPlanService.insertTreatPlan(treatPlanDto, token);

    }

    @RequestMapping("/all/{token}")
    public RestServiceResult<List<MsklTreatPlan>> getAllTreatPlan(@PathVariable String token) {
        RestServiceResult<List<MsklTreatPlan>> result = new RestServiceResult<List<MsklTreatPlan>>("进入查询服药计划Controller类!",true);
        if (!verificationService.verification(token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return treatPlanService.getAllTreatPlan(token);
    }


}
