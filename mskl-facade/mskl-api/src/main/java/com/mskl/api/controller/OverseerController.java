package com.mskl.api.controller;

import com.mskl.common.dto.OverseerDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.service.overseer.OverseerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/overseer")
public class OverseerController {

    @Resource(name = "overseer.overseerService")
    private OverseerService overseerService;

    @RequestMapping("/insert")
    public RestServiceResult<Boolean> insertOverseer(@RequestBody OverseerDto overseerDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if (checkOverseerParam(overseerDto)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("非法参数!");
            return result;
        }
        return overseerService.insertOverseer(overseerDto);

    }

    @RequestMapping("/{userId}")
    public RestServiceResult<List<MsklOverseer>> getOverseersByUserId(@PathVariable String userId){
        RestServiceResult<List<MsklOverseer>> result = new RestServiceResult<List<MsklOverseer>>();
        if (StringUtils.isBlank(userId)){
            result.setSuccess(false);
            result.setMessage("userId不能为空！");
            return result;
        }
        return overseerService.getOverseersByUserId(userId);

    }


    private boolean checkOverseerParam(OverseerDto overseerDto) {
        return null == overseerDto || StringUtils.isBlank(overseerDto.getOverseerMobile()) || StringUtils.isBlank(overseerDto.getOverseer()) || StringUtils.isBlank(overseerDto.getUserId());
    }


}
