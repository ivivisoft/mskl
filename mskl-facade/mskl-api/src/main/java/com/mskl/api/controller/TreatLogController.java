package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatLogDto;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.service.treatplan.TreatLogService;
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
@RequestMapping("/treatLog")
public class TreatLogController {

    private Log logger = LogFactory.getLog(TreatLogController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "treatLog.treatLogService")
    private TreatLogService treatLogService;

    @RequestMapping("/select/{time}/{md5str}/{token}")
    public RestServiceResult<List<MsklTreatLog>> getTreatLogs(@RequestBody TreatLogDto treatLogDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token){
        RestServiceResult<List<MsklTreatLog>> result = new RestServiceResult<List<MsklTreatLog>>("进入服药记录Controller类",true);
        if (!verificationService.verification(treatLogDto, token, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return treatLogService.getTreatLogs(treatLogDto,token);
    }
}
