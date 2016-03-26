package com.mskl.dao.overseer;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklOverseer;

import java.io.Serializable;
import java.util.List;

public interface OverseerDao extends Dao<MsklOverseer,Serializable>{
    List<MsklOverseer> getOverseersByUserId(Long userId);
}
