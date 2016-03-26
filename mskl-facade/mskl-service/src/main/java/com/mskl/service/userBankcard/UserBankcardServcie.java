package com.mskl.service.userBankcard;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserBankcardDto;
import com.mskl.dao.model.MsklUserBankcard;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;

public interface UserBankcardServcie extends BaseService<MsklUserBankcard,Serializable>{

    /**
     * 根据userId查询银行卡
     * @param userId
     * @return
     */
    RestServiceResult<List<MsklUserBankcard>> getBankcardByUserId(String userId);

    /**
     * 添加银行卡
     * @param userBankcardDto
     * @return
     */
    RestServiceResult<Boolean> insertBankcard(UserBankcardDto userBankcardDto);
}
