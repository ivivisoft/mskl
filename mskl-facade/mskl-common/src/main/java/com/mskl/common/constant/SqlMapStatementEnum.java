package com.mskl.common.constant;

/**
 * statement 枚举类
 * @author andy
 *
 */
public enum SqlMapStatementEnum {

	GET_BY_ID(".selectByPrimaryKey","根据ID去查询的通用statement"),
	INSERT_OBJECT(".insertSelective","插入对象的通用statement"),
	UPDATE_OBJECT(".updateByPrimaryKeySelective","更新对象的通用statement"),
	DELETE_OBJECT(".deleteByPrimaryKey","删除对象的通用statement"),
	LIST_OBJECT(".list","查询对象的通用statement"),
	LIST_PAGE_OBJECT(".listPage","查询对象的通用statement"),
	COUNT_OBJECT(".count","查询总数的通用statement"),
	SELECT_ONE_OBJECT(".selectOneObject","返回一个对象的通用statement");
		
    private String statementId;   
    private String desc;
	
	private SqlMapStatementEnum(String statementId, String desc){
		this.statementId = statementId;
		this.desc = desc;
	}
	
    public static String getExtNameByCode(String statementId) {   
        for (SqlMapStatementEnum e : SqlMapStatementEnum.values()) {   
            if (e.getStatementId().equals(statementId)) {   
                return e.desc;   
            }   
        }   
        return null;   
    }

    

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
