package com.creditsuisse.LoggerFile.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int index;
	private String eventId;
	private long timestampDifference;
	private String type;
	private String host;
	private boolean longEventFlag;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int index, String eventId, long timestampDifference, String type, String host, boolean longEventFlag) {
		super();
		this.index = index;
		this.eventId = eventId;
		this.timestampDifference = timestampDifference;
		this.type = type;
		this.host = host;
		this.longEventFlag = longEventFlag;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public long getTimestampDifference() {
		return timestampDifference;
	}

	public void setTimestampDifference(long timestampDifference) {
		this.timestampDifference = timestampDifference;
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

	public boolean isLongEventFlag() {
		return longEventFlag;
	}

	public void setLongEventFlag(boolean longEventFlag) {
		this.longEventFlag = longEventFlag;
	}

	@Override
	public String toString() {
		return "Event [index=" + index + ", eventId=" + eventId + ", timestampDifference=" + timestampDifference
				+ ", type=" + type + ", host=" + host + ", longEventFlag=" + longEventFlag + "]";
	}

}
