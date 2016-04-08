package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatInfoDto;
import com.mskl.common.vo.TreatInfoVo;
import com.mskl.service.treatinfo.TreatInfoService;
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
@RequestMapping("/treatInfo")
public class TreatInfoController {

    private Log logger = LogFactory.getLog(TreatInfoController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "treatInfo.treatInfoService")
    private TreatInfoService treatInfoService;

    @RequestMapping("/select/{token}")
    public RestServiceResult<List<TreatInfoVo>> getAllTreatInfo(@RequestBody TreatInfoDto treatInfoDto, @PathVariable String token){
        RestServiceResult<List<TreatInfoVo>> result = new RestServiceResult<List<TreatInfoVo>>("进入统计信息Controller类", true);
        if (!verificationService.verification(treatInfoDto, token,result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return treatInfoService.getAllTreatInfo(treatInfoDto,token);
    }

}
