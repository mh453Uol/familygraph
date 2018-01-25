package com.neoworks.interviewtests.graph;

public class Relationship {
	private Person to;
	private Person from;
	private RelationshipType type;
	
	public Relationship(Person from,Person to, RelationshipType type) {
		this.to = to;
		this.from = from;
		this.type = type;
	}
	
	public Person getTo() {
		return to;
	}
	public void setTo(Person to) {
		this.to = to;
	}
	public Person getFrom() {
		return from;
	}
	public void setFrom(Person from) {
		this.from = from;
	}
	public RelationshipType getType() {
		return type;
	}
	public void setType(RelationshipType type) {
		this.type = type;
	}
	
}
