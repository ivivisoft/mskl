package com.mskl.service.mskluser.impl;


import com.mskl.dao.model.MsklUserExt;
import com.mskl.dao.mskluser.MsklUserExtDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.mskluser.MsklUserExtService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "mskluser.msklUserExtService")
public class MsklUserExtServiceImpl extends BaseServiceImpl<MsklUserExt, String> implements MsklUserExtService {
    private Log logger = LogFactory.getLog(MsklUserServiceImpl.class);

    private MsklUserExtDao msklUserExtDao;

    @Resource(name = "mskluser.msklUserExtDao")
    public void setMsklUserExtDao(MsklUserExtDao msklUserExtDao) {
        this.msklUserExtDao = msklUserExtDao;
        super.setBaseDaoImpl(msklUserExtDao);
    }

}
