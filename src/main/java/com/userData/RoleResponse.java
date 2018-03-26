package com.userData;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RoleResponse{

	@JsonProperty("updateDate")
	private Object updateDate;

	@JsonProperty("roldId")
	private int roldId;

	@JsonProperty("roleName")
	private Object roleName;

	@JsonProperty("login")
	private Object login;

	@JsonProperty("createDate")
	private Object createDate;

	public void setUpdateDate(Object updateDate){
		this.updateDate = updateDate;
	}

	public Object getUpdateDate(){
		return updateDate;
	}

	public void setRoldId(int roldId){
		this.roldId = roldId;
	}

	public int getRoldId(){
		return roldId;
	}

	public void setRoleName(Object roleName){
		this.roleName = roleName;
	}

	public Object getRoleName(){
		return roleName;
	}

	public void setLogin(Object login){
		this.login = login;
	}

	public Object getLogin(){
		return login;
	}

	public void setCreateDate(Object createDate){
		this.createDate = createDate;
	}

	public Object getCreateDate(){
		return createDate;
	}

	@Override
 	public String toString(){
		return 
			"RoleResponse{" + 
			"updateDate = '" + updateDate + '\'' + 
			",roldId = '" + roldId + '\'' + 
			",roleName = '" + roleName + '\'' + 
			",login = '" + login + '\'' + 
			",createDate = '" + createDate + '\'' + 
			"}";
		}
}