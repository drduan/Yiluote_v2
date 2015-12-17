package com.yiluote.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 资讯实体类
 * @author lyp
 *
 */

public class BeanInfo {

	private String lable;		//标签
	private int lableColor;		//标签颜色
	private String title;		//资讯标题
	private String content;		//资讯内容
	private String hits;		//点击量
	private String comments;	//评论数
	private String targetUrl;	//目标网址
	private Bitmap imageBmp;	//咨询图片Bitmap
	private String imageUrl;	//咨询图片URL

	public BeanInfo() {
	}

	public BeanInfo(String lable, int lableColor, String title, String content, String hits,
					String comments, String targetUrl, String imageUrl) {
		super();
		this.lable = lable;
		this.lableColor = lableColor;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.comments = comments;
		this.targetUrl = targetUrl;
		this.imageUrl = imageUrl;
	}


	public Bitmap getImageBmp() {
		return imageBmp;
	}

	public void setImageBmp(Bitmap imageBmp) {
		this.imageBmp = imageBmp;
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

	public int getLableColor() {
		return lableColor;
	}

	public void setLableColor(int lableColor) {
		this.lableColor = lableColor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}
}
