package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class UpdateEmployeeResponse {
	HttpStatus status;
	int status_code;
	String status_msg;
	String message;
	String reqid;
	LocalDateTime _server_ts;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getStatus_msg() {
		return status_msg;
	}
	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public LocalDateTime get_server_ts() {
		return _server_ts;
	}
	public void set_server_ts(LocalDateTime _server_ts) {
		this._server_ts = _server_ts;
	}
}
