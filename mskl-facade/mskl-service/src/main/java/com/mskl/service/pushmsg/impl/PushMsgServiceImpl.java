package com.mskl.service.pushmsg.impl;

import com.mskl.dao.model.MsklPushMsg;
import com.mskl.service.base.BaseService;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.promotioninfo.PromotionInfoService;
import com.mskl.service.pushmsg.PushMsgService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service(value = "pushMsg.pushMsgService")
public class PushMsgServiceImpl extends BaseServiceImpl<MsklPushMsg,Serializable> implements PushMsgService {
}
