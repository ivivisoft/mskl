package com.mskl.api.controller;

import com.mskl.common.dto.OverseerDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.service.overseer.OverseerService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/overseer")
public class OverseerController {

    private Log logger = LogFactory.getLog(OverseerController.class);

    @Resource(name = "overseer.overseerService")
    private OverseerService overseerService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @RequestMapping("/insert/{time}/{md5str}/{token}")
    public RestServiceResult<Boolean> insertOverseer(@RequestBody OverseerDto overseerDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入添加监督人Controller类",true);
        if(!verificationService.verification(overseerDto,token,time,md5str,result)){
            if(logger.isInfoEnabled()){
                logger.info(result.toString());
            }
        }

        return overseerService.insertOverseer(overseerDto,token);

    }

    @RequestMapping("/{time}/{md5str}/{token}")
    public RestServiceResult<List<MsklOverseer>> getOverseersByUserId(@PathVariable Long time, @PathVariable String md5str, @PathVariable String token){
        RestServiceResult<List<MsklOverseer>> result = new RestServiceResult<List<MsklOverseer>>("进入查询监督人Controller类",true);
        return overseerService.getOverseersByUserId(token);

    }

}
