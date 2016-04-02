package com.mskl.dao.pushmsg;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklPushMsg;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PushMsgDao extends Dao<MsklPushMsg,Serializable>{
    MsklPushMsg getMsgsByTreatLogId(Long msklTreatlogId);

    List<MsklPushMsg> getPushMsgByDateAndUserId(Map param);
}
