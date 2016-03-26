package com.mskl.service.usertrade;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserTradeDto;
import com.mskl.dao.model.MsklUserTrade;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface UserTradeService extends BaseService<MsklUserTrade,Serializable>{

    /**
     * 添加交易密码
     * @param userTradeDto
     * @return
     */
    RestServiceResult<Boolean> insertTradePassword(UserTradeDto userTradeDto);

    /**
     * 修改交易密码
     * @param userTradeDto
     * @return
     */
    RestServiceResult<Boolean> updateTradePassword(UserTradeDto userTradeDto);
}
