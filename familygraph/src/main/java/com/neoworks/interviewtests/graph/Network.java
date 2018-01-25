package com.neoworks.interviewtests.graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Network {
	//Email -> Person, Email in nature are unique so can be used as a key
	//O(1) for insertion if we know the key
	private HashMap<String, Person> people;
	
	private CSVFileReader reader;

	public Network() {
		reader = new CSVFileReader();
		people = new HashMap<String,Person>();
	}

	public HashMap<String, Person> getPeople() {
		return people;
	}

	public void setPeople(HashMap<String, Person> people) {
		this.people = people;
	}
	
	public boolean addRelationship(String from, String to,RelationshipType type) {
		// get both peoples
		Person personOne = people.get(from);
		Person personTwo = people.get(to);
		
		if(personOne == null || personTwo == null) {
			return false;
		}
		
		// personOne <-(Type)-> PersonTwo
		// personTwo <-(Type)-> personOne
		personOne.addRelationship(personTwo,type);
		personTwo.addRelationship(personOne,type);
		
		return true;
	}
	
	public void importPeopleFromCSV(String path) {
		
		for (String p : reader.Read(path)) {
			
			String[] fields = p.split(",");

			if (fields.length == 3) {

				String name = fields[0];
				String email = fields[1];
				byte age = Byte.parseByte(fields[2]);

				Person person = new Person(name, email, age);
				
				people.put(email, person);
			}
		}
	}
	
	public void importRelationshipFromCSV(String path) {
		for (String r : reader.Read(path)) {	
			String[] fields = r.split(",");

			if (fields.length == 3) {
				String from = fields[0];
				String to = fields[2];
				RelationshipType type = RelationshipType.valueOf(fields[1]);
				
				addRelationship(from, to, type);
			}
		}
	}
	
	public Person getPersonByKey(String key) {
		return people.get(key);
	}
	
	public int sizeOfExtendedFamily(Person p) {
		//This collection will contain duplicates since 
		ArrayList<String> links = computeExtendedFamily(p, new ArrayList<String>());
		// the person is in the array so we don't need do +1
		return (int) links.stream().distinct().count();
	}
	
	//Their extended family includes anyone connected 
	//to **them** by a chain of family relationships of any length 
	private ArrayList<String> computeExtendedFamily(Person person,ArrayList<String> included) {
		//included keep track of already included family members when recursively call method 
		
		//add person to collection so it doesn't end up in a infinite
		//since we dont want to consider relationship we have already explored
		
		included.add(person.getEmail());			
		
		for(Relationship p:person.getRelationships()) {
			
			//only explore relationship if its a family relationship and we havent
			//explored it already
			
			if(p.getType() == RelationshipType.FAMILY && !included.contains(p.getTo().getEmail())) {
				
				//once we have explored the relationship add it to the collection
				//so we dont explore it again
				
				included.addAll(computeExtendedFamily(p.getTo(),included));
			}
		}
		return included;
	}
}
