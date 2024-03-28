package com.example.demo.dto;

import java.util.Map;

public class EmployeeListRequest {
	String token;
	Map<String, String> data;
	String reqid;
	String _client_ts;
	String _client_type;
	String filter;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String get_client_ts() {
		return _client_ts;
	}
	public void set_client_ts(String _client_ts) {
		this._client_ts = _client_ts;
	}
	public String get_client_type() {
		return _client_type;
	}
	public void set_client_type(String _client_type) {
		this._client_type = _client_type;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
}
