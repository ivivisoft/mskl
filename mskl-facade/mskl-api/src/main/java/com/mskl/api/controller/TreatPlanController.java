package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatPlanDto;
import com.mskl.service.treatplan.TreatPlanService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/treatPlan")
public class TreatPlanController {

    private Log logger = LogFactory.getLog(TreatPlanController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "treatPlan.treatPlanService")
    private TreatPlanService treatPlanService;

    @RequestMapping("/insertOrUpdate/{token}")
    public RestServiceResult<Boolean> insertTreatPlan(@RequestBody TreatPlanDto treatPlanDto, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入添加或服药计划controller类", false);
        if (!verificationService.verification(treatPlanDto, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        if (null == treatPlanDto.getMsklTreatplanId()){
            return treatPlanService.insertTreatPlan(treatPlanDto,token);
        }else {
            return treatPlanService.updateTreatPlan(treatPlanDto,token);
        }

    }

}
