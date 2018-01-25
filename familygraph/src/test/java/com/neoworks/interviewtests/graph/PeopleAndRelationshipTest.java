package com.neoworks.interviewtests.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PeopleAndRelationshipTest {
	
	private Network people;
	
	@Before
	public void init() {
		people = new Network();
		people.importPeopleFromCSV("src/test/resources/people.csv");
		people.importRelationshipFromCSV("src/test/resources/relationships.csv");
	}
	
	@Test
	public void correctNumberOfPeopleImported() {
		int size = people.getPeople().size();
		String message = "people.csv has 12 rows however collection has: "+ size;
		assertTrue(message, 12 == size);
	}
	
	@Test
	public void correctRelationshipLoaded() {
		Person bob = people.getPersonByKey("bob@bob.com");
		Person jenny = people.getPersonByKey("jenny@toys.com");
		Person nigel = people.getPersonByKey("nigel@marketing.com");
		Person alan = people.getPersonByKey("alan@lonely.org");
		
		assertTrue(4 == bob.getRelationships().size());
		assertTrue(3 == jenny.getRelationships().size());
		assertTrue(2 == nigel.getRelationships().size());
		assertTrue(0 == alan.getRelationships().size());
	}
	
	@Test
	public void correctExtendedFamily() {
		Person jenny = people.getPersonByKey("jenny@toys.com");
		Person bob = people.getPersonByKey("bob@bob.com");
		Person derek = people.getPersonByKey("derek@bob.com");
		
		assertTrue(4 == people.sizeOfExtendedFamily(jenny));
		assertTrue(4 == people.sizeOfExtendedFamily(bob));
		assertTrue(1 == people.sizeOfExtendedFamily(derek));
	}
}
