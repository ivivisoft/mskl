package com.mskl.api.controller;

import com.mskl.common.dto.PushMsgDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.service.pushmsg.PushMsgService;
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
@RequestMapping("/pushMsg")
public class PushMsgController {

    private Log logger = LogFactory.getLog(PushMsgController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "pushMsg.pushMsgService")
    private PushMsgService pushMsgService;


    @RequestMapping("/select/{time}/{md5str}/{token}")
    public RestServiceResult<List<MsklPushMsg>> getPushMsgByDateAndUserId(@RequestBody PushMsgDto pushMsgDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token) {
        RestServiceResult<List<MsklPushMsg>> result = new RestServiceResult<List<MsklPushMsg>>("进入推送消息Controller类", true);
        if (!verificationService.verification(pushMsgDto, token, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return pushMsgService.getPushMsgByDateAndUserId(pushMsgDto,token);
    }
}
