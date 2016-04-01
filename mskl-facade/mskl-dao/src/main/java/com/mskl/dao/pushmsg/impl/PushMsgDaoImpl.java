package com.mskl.dao.pushmsg.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.dao.pushmsg.PushMsgDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "pushMsg.pushMsgDao")
public class PushMsgDaoImpl extends MsklBaseDao<MsklPushMsg,Serializable> implements PushMsgDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklPushMsgMapper";
    }
}
