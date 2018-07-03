package com.zyh.po;

import java.util.Date;

public class Info {
	private int info_id;//主键（自增长）
	private String info_title;//信息的标题
	private String info_category;//信息的分类 
	private int user_id;//发布的用户ID
	private String info_time;//信息发布的时间
	private int info_status;// 发布状态（1：已发布；2：已下架）
	private String info_detail;
	
	public String getInfo_detail() {
		return info_detail;
	}
	public void setInfo_detail(String info_detail) {
		this.info_detail = info_detail;
	}
	public int getInfo_id() {
		return info_id;
	}
	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}
	public String getInfo_title() {
		return info_title;
	}
	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}
	public String getInfo_category() {
		return info_category;
	}
	public void setInfo_category(String info_category) {
		this.info_category = info_category;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getInfo_time() {
		return info_time;
	}
	public void setInfo_time(String info_time) {
		this.info_time = info_time;
	}
	public int getInfo_status() {
		return info_status;
	}
	public void setInfo_status(int info_status) {
		this.info_status = info_status;
	}

	

}
