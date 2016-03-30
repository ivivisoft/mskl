package com.mskl.service.overseer;

import com.mskl.common.dto.OverseerDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.List;


public interface OverseerService extends BaseService<MsklOverseer,Serializable>  {

    /**
     * 添加监督人
     * @param overseerDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> insertOverseer(OverseerDto overseerDto, String token);

    RestServiceResult<List<MsklOverseer>> getOverseersByUserId(String userId);
}
