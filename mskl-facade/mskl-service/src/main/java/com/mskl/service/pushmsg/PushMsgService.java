package com.mskl.service.pushmsg;

import com.mskl.common.dto.PushMsgDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;

public interface PushMsgService extends BaseService<MsklPushMsg,Serializable>{

    /**
     * 根据日期和userId查询消息
     * @param pushMsgDto
     * @param token
     * @return
     */
    RestServiceResult<List<MsklPushMsg>> getPushMsgByDateAndUserId(PushMsgDto pushMsgDto, String token);

    /**
     * 生成推送消息
     * @param msklPushMsg
     * @return
     */
    int generatorPushMsg(MsklPushMsg msklPushMsg);
}
