package com.neoworks.interviewtests.graph;

import java.util.ArrayList;

public class Person {
	private String name;
	private String email;
	private byte age;
	private ArrayList<Relationship> relationships;
	
	public Person(String name,String email,byte age) {
		this.name = name;
		this.email = email;
		this.age = age;
		relationships = new ArrayList<Relationship>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte getAge() {
		return age;
	}
	public void setAge(byte age) {
		this.age = age;
	}
	public ArrayList<Relationship> getRelationships() {
		return relationships;
	}
	
	public void setRelationships(ArrayList<Relationship> relationships) {
		this.relationships = relationships;
	}
	
	public void addRelationship(Person to,RelationshipType type) {
		relationships.add(new Relationship(this, to, type));
	}
}
