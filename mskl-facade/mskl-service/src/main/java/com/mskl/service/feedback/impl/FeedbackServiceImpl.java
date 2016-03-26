package com.mskl.service.feedback.impl;

import com.mskl.common.dto.FeedbackDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.feedback.FeedbackDao;
import com.mskl.dao.model.MsklFeedback;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.feedback.FeedbackService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

@Service(value = "feedback.feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<MsklFeedback, Serializable> implements FeedbackService {

    private Log logger = LogFactory.getLog(FeedbackServiceImpl.class);

    private FeedbackDao feedbackDao;

    @Resource(name = "feedback.feedbackDao")
    public void setFeedbackDao(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
        super.setBaseDaoImpl(feedbackDao);
    }

    public RestServiceResult<Boolean> insertFeedback(FeedbackDto feedbackDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        result.setSuccess(false);
        result.setData(Boolean.FALSE);

        MsklFeedback msklFeedback = new MsklFeedback();
        msklFeedback.setFeedbackMsg(feedbackDto.getFeedbackMsg());
        msklFeedback.setUserId(Long.parseLong(feedbackDto.getUserId()));
        msklFeedback.setUserMobile(feedbackDto.getUserMobile());
        msklFeedback.setUserName(feedbackDto.getUserName());
        msklFeedback.setUpdateDatetime(new Date());
        if(saveObject(msklFeedback)>0){
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            if (logger.isInfoEnabled()){
                logger.info("添加反馈意见"+result.toString());
            }
            return result;
        }

        return result;
    }
}
