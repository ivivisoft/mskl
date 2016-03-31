package com.mskl.service.medicinebox.impl;

import com.mskl.dao.medicinebox.MedicineBoxDao;
import com.mskl.dao.model.MsklMedbox;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.medicinebox.MedicineBoxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "medicineBox.medicineBoxService")
public class MedicineBoxServiceImpl extends BaseServiceImpl<MsklMedbox,Serializable> implements MedicineBoxService{

    private MedicineBoxDao medicineBoxDao;

    @Resource(name = "medicineBox.medicineBoxDao")
    public void setMedicineBoxDao(MedicineBoxDao medicineBoxDao) {
        this.medicineBoxDao = medicineBoxDao;
        super.setBaseDaoImpl(medicineBoxDao);
    }

    public MsklMedbox getBoxByMedicine(Long msklMedicineId) {
        return medicineBoxDao.getBoxByMedicine(msklMedicineId);
    }
}
