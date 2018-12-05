package br.com.company.entity;

import br.com.company.application.Entity;

import java.io.Serializable;
import java.util.Date;

public class UserEntity extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
