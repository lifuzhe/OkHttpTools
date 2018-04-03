package com.example.okhttptools_demo.bean;

public class LoginResponse {

	public int code;
	public String message;
	public String fileUrlPath;

	@Override
	public String toString() {
		return "LoginResponse [code=" + code + ", message=" + message
				+ ", extend=" + fileUrlPath + "]";
	}

}
