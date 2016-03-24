package com.mskl.service.mskluser.impl;

import com.mskl.dao.model.MsklUser;
import com.mskl.dao.mskluser.MsklUserDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.mskluser.MsklUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service(value = "mskluser.msklUserService")
public class MsklUserServiceImpl extends BaseServiceImpl<MsklUser,String> implements MsklUserService {

    private MsklUserDao msklUserDao;
    @Resource(name = "mskluser.msklUserDao")
    public void setMsklUserDao(MsklUserDao msklUserDao) {
        this.msklUserDao = msklUserDao;
        super.setBaseDaoImpl(msklUserDao);
    }
}
