package com.mskl.dao.overseer.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.dao.overseer.OverseerDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository(value = "overseer.overseerDao")
public class OverseerDaoImpl extends MsklBaseDao<MsklOverseer,Serializable> implements OverseerDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklOverseerMapper";
    }

    public List<MsklOverseer> getOverseersByUserId(Long userId) {
        return queryForList("getOverseersByUserId",userId);
    }
}
