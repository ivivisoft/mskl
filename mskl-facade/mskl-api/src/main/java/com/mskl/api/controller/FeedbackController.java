package com.mskl.api.controller;

import com.mskl.common.dto.FeedbackDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.SignUtil;
import com.mskl.service.feedback.FeedbackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource(name = "feedback.feedbackService")
    private FeedbackService feedbackService;


    @RequestMapping("/insert/{time}/{md5str}/{token}")
    public RestServiceResult<Boolean> insertFeedback(@RequestBody FeedbackDto feedbackDto, @PathVariable Long time, @PathVariable String md5str,@PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if(!StringUtils.equals(md5str, SignUtil.signMethod(feedbackDto,time))){
            result.setSuccess(false);
            result.setMessage("方法签名不正确!");
            return result;
        }
        if (checkFeedbackParam(feedbackDto)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法!");
            return result;
        }

        return feedbackService.insertFeedback(feedbackDto);
    }

    private boolean checkFeedbackParam(FeedbackDto feedbackDto) {
        return null == feedbackDto || StringUtils.isBlank(feedbackDto.getUserMobile()) || StringUtils.isBlank(feedbackDto.getFeedbackMsg());
    }
}
