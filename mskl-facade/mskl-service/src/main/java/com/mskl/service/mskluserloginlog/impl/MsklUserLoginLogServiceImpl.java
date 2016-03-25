package com.mskl.service.mskluserloginlog.impl;

import com.mskl.dao.model.MsklUserLoginLog;
import com.mskl.dao.mskluserloginlog.MsklUserLoginLogDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.mskluserloginlog.MsklUserLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "mskluserloginlog.msklUserLoginLogService")
public class MsklUserLoginLogServiceImpl extends BaseServiceImpl<MsklUserLoginLog, String> implements MsklUserLoginLogService {
    private MsklUserLoginLogDao msklUserLoginLogDao;

    @Resource(name = "mskluserloginlog.msklUserLoginLogDao")
    public void setMsklUserLoginLogDao(MsklUserLoginLogDao msklUserLoginLogDao) {
        this.msklUserLoginLogDao = msklUserLoginLogDao;
        super.setBaseDaoImpl(msklUserLoginLogDao);
    }
}
