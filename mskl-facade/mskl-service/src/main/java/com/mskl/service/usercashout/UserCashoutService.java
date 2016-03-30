package com.mskl.service.usercashout;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserCashoutDto;
import com.mskl.dao.model.MsklUserCashoutApplication;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface UserCashoutService extends BaseService<MsklUserCashoutApplication,Serializable>{
    RestServiceResult<Boolean> applyCashout(UserCashoutDto userCashoutDto, String token);
}
