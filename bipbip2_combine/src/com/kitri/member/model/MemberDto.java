package com.kitri.member.model;

public class MemberDto {
	private String id;
	private String pass;
	private String name;
	private String email;
	private String phone;
	private String addr1;
	private String addr2;
	
	private int checklike;
	private int checkbad;
	private int checkhit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public int getChecklike() {
		return checklike;
	}
	public void setChecklike(int checklike) {
		this.checklike = checklike;
	}
	public int getCheckbad() {
		return checkbad;
	}
	public void setCheckbad(int checkbad) {
		this.checkbad = checkbad;
	}
	public int getCheckhit() {
		return checkhit;
	}
	public void setCheckhit(int checkhit) {
		this.checkhit = checkhit;
	}
	
		
}
