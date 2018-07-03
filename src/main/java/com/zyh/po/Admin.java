package com.zyh.po;

import java.util.Date;

public class Admin {
	private int admin_id;
	private String admin_name;
	private String admin_password;
	private String admin_gender;
	private String admin_phone;
	private String admin_role;
	private String admin_note;
	private String admin_email;
	private Date ctime;   
	
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date date) {
		this.ctime = date;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getAdmin_gender() {
		return admin_gender;
	}
	public void setAdmin_gender(String admin_gender) {
		this.admin_gender = admin_gender;
	}
	public String getAdmin_phone() {
		return admin_phone;
	}
	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}
	public String getAdmin_role() {
		return admin_role;
	}
	public void setAdmin_role(String admin_role) {
		this.admin_role = admin_role;
	}
	public String getAdmin_note() {
		return admin_note;
	}
	public void setAdmin_note(String admin_note) {
		this.admin_note = admin_note;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	
	
}
