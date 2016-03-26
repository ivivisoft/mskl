package com.mskl.service.overseer.impl;

import com.mskl.common.dto.OverseerDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.overseer.OverseerDao;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.overseer.OverseerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service(value = "overseer.overseerService")
public class OverseerServiceImpl extends BaseServiceImpl<MsklOverseer, Serializable> implements OverseerService {

    private Log logger = LogFactory.getLog(OverseerServiceImpl.class);
    private OverseerDao overseerDao;

    @Resource(name = "overseer.overseerDao")
    public void setOverseerDao(OverseerDao overseerDao) {
        this.overseerDao = overseerDao;
        super.setBaseDaoImpl(overseerDao);
    }

    public RestServiceResult<Boolean> insertOverseer(OverseerDto overseerDto) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        result.setSuccess(false);
        result.setData(Boolean.FALSE);

        MsklOverseer msklOverseer = new MsklOverseer();

        msklOverseer.setOverseer(overseerDto.getOverseer());
        msklOverseer.setOverseerMobile(overseerDto.getOverseerMobile());
        msklOverseer.setUserMobile(overseerDto.getUserMobile());
        msklOverseer.setUserId(Long.parseLong(overseerDto.getUserId()));
        msklOverseer.setUpdateDatetime(new Date());

        if (saveObject(msklOverseer) > 0) {
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            if (logger.isInfoEnabled()) {
                logger.info("添加监督人" + result.toString());
            }
            return result;
        }
        return result;
    }

    public RestServiceResult<List<MsklOverseer>> getOverseersByUserId(String userId) {
        RestServiceResult<List<MsklOverseer>> result = new RestServiceResult<List<MsklOverseer>>();
        result.setSuccess(false);

        List<MsklOverseer> lists = overseerDao.getOverseersByUserId(Long.parseLong(userId));
        if (null == lists) {
            result.setMessage("查询监督人异常！");
            if (logger.isInfoEnabled()) {
                logger.info("查询监督人" + result.toString());
            }
            return result;
        }
        result.setSuccess(true);
        result.setData(lists);

        return result;
    }
}
