package com.crc.commonservice.notification.dto;

public class AllLogEvent extends LogEvents{
	private long timestamp;
	private String type;
	private String host;
	private String state;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "AllLogEvent [ " + super.toString() + ", timestamp=" + timestamp + ", type=" + type + ", host=" + host + ", state=" + state
				+ "]";
	}
	
}
