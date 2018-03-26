package com.userData;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PackResponse{

	@JsonProperty("password")
	private Object password;

	@JsonProperty("members")
	private List<Object> members;

	@JsonProperty("roles")
	private List<Object> roles;

	@JsonProperty("packName")
	private Object packName;

	@JsonProperty("login")
	private Object login;

	@JsonProperty("packNumber")
	private int packNumber;

	public void setPassword(Object password){
		this.password = password;
	}

	public Object getPassword(){
		return password;
	}

	public void setMembers(List<Object> members){
		this.members = members;
	}

	public List<Object> getMembers(){
		return members;
	}

	public void setRoles(List<Object> roles){
		this.roles = roles;
	}

	public List<Object> getRoles(){
		return roles;
	}

	public void setPackName(Object packName){
		this.packName = packName;
	}

	public Object getPackName(){
		return packName;
	}

	public void setLogin(Object login){
		this.login = login;
	}

	public Object getLogin(){
		return login;
	}

	public void setPackNumber(int packNumber){
		this.packNumber = packNumber;
	}

	public int getPackNumber(){
		return packNumber;
	}

	@Override
 	public String toString(){
		return 
			"PackResponse{" + 
			"password = '" + password + '\'' +
			",members = '" + members + '\'' +
			",roles = '" + roles + '\'' +
			",packName = '" + packName + '\'' + 
			",login = '" + login + '\'' + 
			",packNumber = '" + packNumber + '\'' + 
			"}";
		}
}