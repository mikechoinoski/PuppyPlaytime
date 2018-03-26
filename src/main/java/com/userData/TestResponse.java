package com.userData;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TestResponse{

	@JsonProperty("password")
	private String password;

	@JsonProperty("packName")
	private String packName;

	@JsonProperty("login")
	private String login;

	@JsonProperty("packNumber")
	private int packNumber;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPackName(String packName){
		this.packName = packName;
	}

	public String getPackName(){
		return packName;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
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
			"TestResponse{" + 
			"password = '" + password + '\'' + 
			",packName = '" + packName + '\'' + 
			",login = '" + login + '\'' + 
			",packNumber = '" + packNumber + '\'' + 
			"}";
		}
}