package com.abhishek.personService.domain;

public class Person {

	private final String id;
	private final String firstName;
	private final String familyName;
	private final int age;

	public Person(String id, String firstName, String familyName, int age) {
		this.id = id;
		this.firstName = firstName;
		this.familyName = familyName;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public int getAge() {
		return age;
	}

}
