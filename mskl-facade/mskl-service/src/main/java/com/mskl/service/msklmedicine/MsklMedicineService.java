package com.mskl.service.msklmedicine;


import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface MsklMedicineService extends BaseService<MsklMedicine,Serializable> {
    /**
     * 根据二维码获取用户信息
     * @param barCode
     * @return
     */
    RestServiceResult<MsklMedicine> getMsklMedicineByBarCode(String barCode);

    /**
     * 根据通用名获取药品信息
     * @param normalName
     * @return
     */
    RestServiceResult<MsklMedicine> getMsklMedicineByNormalName(String normalName);
}
