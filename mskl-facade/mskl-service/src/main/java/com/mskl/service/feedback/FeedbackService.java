package com.mskl.service.feedback;

import com.mskl.common.dto.FeedbackDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklFeedback;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface FeedbackService extends BaseService<MsklFeedback,Serializable> {
    /**
     * 添加反馈意见
     */
    RestServiceResult<Boolean> insertFeedback(FeedbackDto feedbackDto, String token);
}
