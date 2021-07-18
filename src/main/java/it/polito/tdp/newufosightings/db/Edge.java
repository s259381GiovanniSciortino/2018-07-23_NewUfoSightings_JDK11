package it.polito.tdp.newufosightings.db;

public class Edge {
	String stateID1;
	String stateID2;
	public Edge(String stateID1, String stateID2) {
		super();
		this.stateID1 = stateID1;
		this.stateID2 = stateID2;
	}
	public String getStateID1() {
		return stateID1;
	}
	public void setStateID1(String stateID1) {
		this.stateID1 = stateID1;
	}
	public String getStateID2() {
		return stateID2;
	}
	public void setStateID2(String stateID2) {
		this.stateID2 = stateID2;
	}
}
