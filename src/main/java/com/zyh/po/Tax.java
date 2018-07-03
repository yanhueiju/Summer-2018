package com.zyh.po;

import java.util.Date;

public class Tax {
	private int id;//主键（自增长）
	private String title;// 信息的标题
	private String ttype;// 信息的分类
	private String userId;// 发布的用户ID
	private Date createDate;// 信息发布的时间
	private int status;
	private String detail;
	
	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}// 发布状态（1：已发布；2：已下架）

}
