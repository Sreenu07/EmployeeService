package com.organisation.beans;

public class EmployeeEvent {
	private String uuid;
	private String eventName;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Override
	public String toString() {
		return "EmployeeEvent [uuid=" + uuid + ", eventName=" + eventName + "]";
	}
}
