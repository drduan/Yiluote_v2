package com.yiluote.bean;

public class BeanRecommend {

	private String imageUrl;
	private String text;
	private String lable1;
	private int lableColor1;
	private String lable2;
	private int lableColor2;
	private String targetUrl;

	public BeanRecommend(String imageUrl, String text, String lable1,
			int lableColor1, String lable2, int lableColor2, String targetUrl) {
		super();
		this.imageUrl = imageUrl;
		this.text = text;
		this.lable1 = lable1;
		this.lableColor1 = lableColor1;
		this.lable2 = lable2;
		this.lableColor2 = lableColor2;
		this.targetUrl = targetUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUlr) {
		this.imageUrl = imageUlr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLable1() {
		return lable1;
	}

	public void setLable1(String lable1) {
		this.lable1 = lable1;
	}

	public int getLableColor1() {
		return lableColor1;
	}

	public void setLableColor1(int lableColor1) {
		this.lableColor1 = lableColor1;
	}

	public String getLable2() {
		return lable2;
	}

	public void setLable2(String lable2) {
		this.lable2 = lable2;
	}

	public int getLableColor2() {
		return lableColor2;
	}

	public void setLableColor2(int lableColor2) {
		this.lableColor2 = lableColor2;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
}