package com.mitrais.rms.model;

public class MasterData {
	private String codeType;
	private String codeId;
	private String description;
	
	public static enum CODE_TYPE {
		user_type
	}
	
	public MasterData(String codeType, String codeId, String description) {
		this.codeType = codeType;
		this.codeId = codeId;
		this.description = description;
	}
	
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	public String getCodeType() {
		return this.codeType;
	}
	
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	public String getCodeId() {
		return this.codeId;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
