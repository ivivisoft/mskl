package com.mskl.service.overseer.impl;

import com.mskl.common.dto.OverseerDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.dao.overseer.OverseerDao;
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

    public RestServiceResult<Boolean> insertOverseer(OverseerDto overseerDto, String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("添加监督人服务", false);
        try {
            Long userId = TokenUtil.getUserIdFromToken(token);
            MsklOverseer msklOverseer = new MsklOverseer();
            msklOverseer.setOverseer(overseerDto.getOverseer());
            msklOverseer.setOverseerMobile(overseerDto.getOverseerMobile());
            msklOverseer.setUserMobile(overseerDto.getUserMobile());
            msklOverseer.setUserId(userId);
            msklOverseer.setUpdateDatetime(new Date());
            saveObject(msklOverseer);
        } catch (Exception e) {
            result.setMessage("保存监护人异常!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
        result.setSuccess(true);
        result.setData(Boolean.TRUE);
        return result;
    }

    public RestServiceResult<List<MsklOverseer>> getOverseersByUserId(String token) {
        RestServiceResult<List<MsklOverseer>> result = new RestServiceResult<List<MsklOverseer>>("查询监督人服务", false);
        try {
            Long userId = TokenUtil.getUserIdFromToken(token);
            List<MsklOverseer> lists = overseerDao.getOverseersByUserId(userId);
            result.setSuccess(true);
            result.setData(lists);
            return result;
        } catch (Exception e) {
            result.setMessage("查询监护人异常!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
    }
}
