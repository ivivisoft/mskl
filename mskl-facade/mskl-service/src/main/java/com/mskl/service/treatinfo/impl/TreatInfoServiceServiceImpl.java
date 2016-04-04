package com.mskl.service.treatinfo.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.TreatInfoDto;
import com.mskl.common.util.DateUtil;
import com.mskl.common.util.TokenUtil;
import com.mskl.common.vo.TreatInfoVo;
import com.mskl.dao.model.MsklTreatInfo;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.dao.treatinfo.TreatInfoDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.treatinfo.TreatInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service(value = "treatInfo.treatInfoService")
public class TreatInfoServiceServiceImpl extends BaseServiceImpl<MsklTreatInfo, Serializable> implements TreatInfoService {

    private Log logger = LogFactory.getLog(TreatInfoServiceServiceImpl.class);

    private TreatInfoDao treatInfoDao;

    @Resource(name = "treatInfo.treatInfoDao")
    public void setTreatInfoDao(TreatInfoDao treatInfoDao) {
        this.treatInfoDao = treatInfoDao;
        super.setBaseDaoImpl(treatInfoDao);
    }


    public List<MsklTreatInfo> getTreatInfoByDate(Date date) {

        return queryForList("getTreatInfoByDate", date);
    }

    public void generatorCurrentInfo(MsklTreatPlan treatPlan) {
        MsklTreatInfo msklTreatInfo = new MsklTreatInfo();
        msklTreatInfo.setTreatDate(DateUtil.getCurrDateOfDate("yyyy-MM-dd"));
        msklTreatInfo.setDailyTimes(treatPlan.getDailyTimes());
        msklTreatInfo.setTakenTimes(0);
        msklTreatInfo.setUserId(treatPlan.getUserId());
        msklTreatInfo.setMedicineId(treatPlan.getMsklMedicineId());
        treatInfoDao.saveObject(msklTreatInfo);
    }

    public void updateTakeAmountByUserIDAndMedicineID(Long userId, Long msklMedicineId) {
        MsklTreatInfo msklTreatInfo = new MsklTreatInfo();
        msklTreatInfo.setUserId(userId);
        msklTreatInfo.setMedicineId(msklMedicineId);
        treatInfoDao.updateObject("updateTakeAmountByUserIDAndMedicineID", msklTreatInfo);

    }

    public RestServiceResult<List<TreatInfoVo>> getAllTreatInfo(TreatInfoDto treatInfoDto, String token) {
        RestServiceResult<List<TreatInfoVo>> result = new RestServiceResult<List<TreatInfoVo>>("进入统计用药信息服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);
        try {
            List<TreatInfoVo> lists = treatInfoDao.getAllTreatInfo(treatInfoDto, userId);

            result.setData(lists);
            result.setMessage("查询成功!");
            result.setSuccess(true);
        } catch (Exception e) {

            if (logger.isInfoEnabled()) {
                logger.error(result.toString());
            }
            result.setMessage("查询数据库失败!");
        }

        return result;
    }
}
