package com.mskl.api.controller;

import com.mskl.common.dto.FeedbackDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.service.feedback.FeedbackService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private Log logger = LogFactory.getLog(FeedbackController.class);

    @Resource(name = "feedback.feedbackService")
    private FeedbackService feedbackService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;


    @RequestMapping("/insert/{token}")
    public RestServiceResult<Boolean> insertFeedback(@RequestBody FeedbackDto feedbackDto, @PathVariable String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入意见反馈Controller类", true);
        if (!verificationService.verification(feedbackDto, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return feedbackService.insertFeedback(feedbackDto, token);
    }

}
