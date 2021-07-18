package it.polito.tdp.newufosightings.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
	public enum Type{
		AVVISTAMENTO,
		CESSA_ALLERTA
	}
	private String state;
	private LocalDateTime dateTime;
	private Type type;
	
	public Event(String state, LocalDateTime dateTime, Type type) {
		super();
		this.state = state;
		this.dateTime = dateTime;
		this.type = type;
	}
	public Event(String state, LocalDateTime date_posted) {
		super();
		this.state = state;
		this.dateTime = date_posted;
	}
	public String getState() {
		return state;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public void setState(String state) {
		this.state = state;
	}
	public LocalDateTime getdateTime() {
		return dateTime;
	}
	public void setdateTime(LocalDateTime date_posted) {
		this.dateTime = date_posted;
	}
	@Override
	public int compareTo(Event other) {
		return this.getdateTime().compareTo(other.getdateTime());
	}
	@Override
	public String toString() {
		return "Event [state=" + state + ", dateTime=" + dateTime + ", type=" + type + "]\n";
	}
	
}
