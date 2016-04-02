package com.mskl.service.pushmsg;

import com.mskl.common.dto.PushMsgDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklPushMsg;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;

public interface PushMsgService extends BaseService<MsklPushMsg,Serializable>{
    /**
     * 通过服药记录id查找消息
     * @param msklTreatlogId
     * @return
     */
    MsklPushMsg getMsgsByTreatLogId(Long msklTreatlogId);

    /**
     * 根据日期和userId查询消息
     * @param pushMsgDto
     * @param token
     * @return
     */
    RestServiceResult<List<MsklPushMsg>> getPushMsgByDateAndUserId(PushMsgDto pushMsgDto, String token);
}
