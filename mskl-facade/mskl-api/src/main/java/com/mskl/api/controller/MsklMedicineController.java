package com.mskl.api.controller;


import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.service.msklmedicine.MsklMedicineService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/msklmedicine")
public class MsklMedicineController {

    private Log logger = LogFactory.getLog(MsklMedicineController.class);

    @Resource(name = "msklmedicine.msklMedicineService")
    private MsklMedicineService msklMedicineService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;


    @RequestMapping("/{barCode}")
    public RestServiceResult<MsklMedicine> getMsklMedicineByBarCode(@PathVariable String barCode) {
        RestServiceResult<MsklMedicine> result = new RestServiceResult<MsklMedicine>("进入根据药品二维码获取药品信息controller类", true);
        if (!verificationService.verification(barCode, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklMedicineService.getMsklMedicineByBarCode(barCode);
    }

    @RequestMapping("/name/{medicalName}")
    public RestServiceResult<List<MsklMedicine>> getMsklMedicineByNormalName(@PathVariable String normalName){
        RestServiceResult<List<MsklMedicine>> result = new RestServiceResult<List<MsklMedicine>>("进入根据药品通用名获取药品信息controller类", true);
        if (!verificationService.verification(normalName, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklMedicineService.getMsklMedicineByNormalName(normalName);
    }

    @RequestMapping("/all")
    public RestServiceResult<List<MsklMedicine>> getAllMedicine(){
        return msklMedicineService.getAllMedicine();
    }


}
