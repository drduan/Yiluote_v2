package com.yiluote.bean;

/**
 * 广告实体类
 * @author lyp
 *
 */
public class BeanAD {
	
	String id;
	String title;
	String imageUrl;
	String targetUrl;
	
	public BeanAD(String id, String title, String imageUrl, String targetUrl) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.targetUrl = targetUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
}
