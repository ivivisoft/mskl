package com.mskl.service.medicinebox;

import com.mskl.dao.model.MsklMedbox;
import com.mskl.service.base.BaseService;

import java.io.Serializable;
import java.util.Map;

public interface MedicineBoxService extends BaseService<MsklMedbox,Serializable>{

    /**
     *  通过药品id和userId查询药箱
     * @param map
     */
    MsklMedbox getBoxByMedicineIdAndUserId(Map map);

    /**
     * 更新药箱预计完成日期通过药品id和userId
     * @param msklMedbox
     * @return
     */
   int updateBoxFinishDayByMedicineIdAndUserId(MsklMedbox msklMedbox);
}
