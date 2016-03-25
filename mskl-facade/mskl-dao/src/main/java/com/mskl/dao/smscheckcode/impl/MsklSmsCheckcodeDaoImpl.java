package com.mskl.dao.smscheckcode.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.dao.smscheckcode.MsklSmsCheckcodeDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Repository(value = "smsCheckcode.msklSmsCheckcodeDao")
public class MsklSmsCheckcodeDaoImpl extends MsklBaseDao<MsklSmsCheckcode, Serializable> implements MsklSmsCheckcodeDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklSmsCheckcodeMapper";
    }

    /**
     * @see MsklSmsCheckcodeDao#selectByMobileAndSmsBizType(java.lang.String, java.lang.String)
     */
    public MsklSmsCheckcode selectByMobileAndSmsBizType(String mobile, String smsBizType) {
        Map param = new HashMap();
        param.put("mobile", mobile);
        param.put("smsBizType", smsBizType);
        return (MsklSmsCheckcode) selectOneObject("selectByMobileAndSmsBizType", param);
    }
}
