package com.mskl.dao.feedback.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.feedback.FeedbackDao;
import com.mskl.dao.model.MsklFeedback;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value="feedback.feedbackDao")
public class FeedbackDaoImpl extends MsklBaseDao<MsklFeedback,Serializable> implements FeedbackDao{
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklFeedbackMapper";
    }
}
