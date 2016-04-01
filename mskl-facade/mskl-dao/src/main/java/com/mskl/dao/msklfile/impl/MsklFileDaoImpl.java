package com.mskl.dao.msklfile.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklFile;
import com.mskl.dao.msklfile.MsklFileDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "msklFile.msklFileDao")
public class MsklFileDaoImpl extends MsklBaseDao<MsklFile,Serializable> implements MsklFileDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklFileMapper";
    }
}
