package com.mskl.api.controller;

import com.mskl.common.dto.FeedbackDto;
import com.mskl.common.dto.RegisterDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.service.feedback.FeedbackService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource(name = "feedback.feedbackService")
    private FeedbackService feedbackService;


    @RequestMapping("/insert")
    public RestServiceResult<Boolean> insertFeedback(@RequestBody FeedbackDto feedbackDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if (checkFeedbackParam(feedbackDto)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法!");
            return result;
        }
        return feedbackService.insertFeedback(feedbackDto);
    }

    private boolean checkFeedbackParam(FeedbackDto feedbackDto) {
        return null == feedbackDto || StringUtils.isBlank(feedbackDto.getUserId()) || StringUtils.isBlank(feedbackDto.getUserMobile()) || StringUtils.isBlank(feedbackDto.getFeedbackMsg());
    }
}
