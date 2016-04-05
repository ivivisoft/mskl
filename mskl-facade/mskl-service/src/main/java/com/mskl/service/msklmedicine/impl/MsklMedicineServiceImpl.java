package com.mskl.service.msklmedicine.impl;


import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.dao.msklmedicine.MsklMedicineDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.msklmedicine.MsklMedicineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "msklmedicine.msklMedicineService")
public class MsklMedicineServiceImpl extends BaseServiceImpl<MsklMedicine, String> implements MsklMedicineService {
    private Log logger = LogFactory.getLog(MsklMedicineServiceImpl.class);
    private MsklMedicineDao msklMedicineDao;

    @Resource(name = "msklmedicine.msklMedicineDao")
    public void setMsklMedicineDao(MsklMedicineDao msklMedicineDao) {
        this.msklMedicineDao = msklMedicineDao;
        super.setBaseDaoImpl(msklMedicineDao);
    }


    public RestServiceResult<MsklMedicine> getMsklMedicineByBarCode(String barCode) {
        RestServiceResult<MsklMedicine> result = new RestServiceResult<MsklMedicine>("根据药品二维码获取药品信息服务", false);
        MsklMedicine msklMedicine = msklMedicineDao.getMsklMedicineByBarCode(barCode);
        if (null == msklMedicine) {
            result.setMessage("没有对应的药品信息!");
            return result;
        }
        result.setSuccess(true);
        result.setData(msklMedicine);
        return result;
    }

    public RestServiceResult<List<MsklMedicine>> getMsklMedicineByNormalName(String normalName) {
        RestServiceResult<List<MsklMedicine>> result = new RestServiceResult<List<MsklMedicine>>("根据药品通用名获取药品信息服务", false);
        List<MsklMedicine> msklMedicines = msklMedicineDao.getMsklMedicineByNormalName(normalName);
        if (null == msklMedicines) {
            result.setMessage("没有对应的药品信息!");
            return result;
        }
        result.setSuccess(true);
        result.setData(msklMedicines);
        return result;
    }

    public RestServiceResult<List<MsklMedicine>> getAllMedicine() {
        RestServiceResult<List<MsklMedicine>> result = new RestServiceResult<List<MsklMedicine>>("查询所有药品信息服务", false);
        try {
            List<MsklMedicine> lists = msklMedicineDao.getAllMedicine();
            result.setSuccess(true);
            result.setData(lists);
            result.setMessage("查询成功");
        }catch (Exception e){
            if (logger.isInfoEnabled()){
                logger.error("查询所有药品信息失败！",e);
            }
            result.setMessage("查询失败！");
        }

        return result;
    }
}
