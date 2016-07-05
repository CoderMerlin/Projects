package com.yc.hdmedia.entity;

public class MyData {

	private int value;
	private String label;
	private String formatted;
	
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFormatted() {
		return formatted;
	}
	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}
	
	
	
	@Override
	public String toString() {
		return "Test [value=" + value + ", label=" + label + ", formatted="
				+ formatted + "]";
	}
	
	
}
