package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UploadFileDto;
import com.mskl.service.msklfile.MsklFileService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {

    private Log logger = LogFactory.getLog(UploadFileController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "msklFile.msklFileService")
    private MsklFileService msklFileService;

    @RequestMapping("/identity/{token}")
    public RestServiceResult<Boolean> uploadIdentityFile(@RequestBody UploadFileDto uploadFileDto, @PathVariable String token){
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入上传实名认证图片Controller类!",true);
        if (!verificationService.verification(uploadFileDto, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklFileService.uploadIdentityFile(uploadFileDto,token);
    }

    @RequestMapping("/case/{token}")
    public RestServiceResult<Boolean> uploadCaseFile(@RequestBody UploadFileDto uploadFileDto, @PathVariable String token){
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入上传病例图片Controller类!",true);
        if (!verificationService.verification(uploadFileDto, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklFileService.uploadCaseFile(uploadFileDto,token);
    }
}
