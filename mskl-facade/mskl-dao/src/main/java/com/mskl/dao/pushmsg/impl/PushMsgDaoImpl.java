package com.mskl.dao.pushmsg.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.dao.pushmsg.PushMsgDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository(value = "pushMsg.pushMsgDao")
public class PushMsgDaoImpl extends MsklBaseDao<MsklPushMsg,Serializable> implements PushMsgDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklPushMsgMapper";
    }

    public List<MsklPushMsg> getPushMsgByDateAndUserId(Map param) {
        return queryForList("getPushMsgByDateAndUserId",param);
    }
}
