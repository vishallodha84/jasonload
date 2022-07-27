package com.crc.commonservice.notification.dto;

public abstract class LogEvents {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LogEvent [id=" + id + "]";
	}

}
