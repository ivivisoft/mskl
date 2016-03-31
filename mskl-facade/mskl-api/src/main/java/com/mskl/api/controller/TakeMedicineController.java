package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TakeMedicineDto;
import com.mskl.common.vo.UserInfoVo;
import com.mskl.service.treatplan.TreatLogService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/treat")
public class TakeMedicineController {

    private Log logger = LogFactory.getLog(TakeMedicineController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "treatLog.treatLogService")
    private TreatLogService treatLogService;

    @RequestMapping("/{token}")
    public RestServiceResult<Boolean> takeMedicine(@RequestBody TakeMedicineDto takeMedicineDto, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入服药信息的controller类", false);
        if (!verificationService.verification(token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        return treatLogService.updateTreatLog(takeMedicineDto,token);
    }
}
