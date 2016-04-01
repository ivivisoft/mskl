package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/treat")
public class TakeMedicineController {

    private Log logger = LogFactory.getLog(TakeMedicineController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "treatLog.treatLogService")
    private TreatLogService treatLogService;

    @RequestMapping("takeMedicine/{time}/{md5str}/{token}")
    public RestServiceResult<Boolean> takeMedicine(@RequestBody TakeMedicineDto takeMedicineDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入服药信息的controller类", false);
        if (!verificationService.verification(takeMedicineDto, token, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        return treatLogService.updateTreatLog(takeMedicineDto, token);
    }
}
