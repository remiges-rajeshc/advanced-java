package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class EmployeeResponse {
	HttpStatus status;
	int status_code;
	String status_msg;
	Map<String, String> data;
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
	public void setStatus_code(int i) {
		this.status_code = i;
	}
	public String getStatus_msg() {
		return status_msg;
	}
	public void setStatus_msg(String string) {
		this.status_msg = string;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
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
