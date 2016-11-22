package jpa_spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="persons")

@NamedQueries({
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p"),
@NamedQuery(name="Person.findById",
query="SELECT p FROM Person p WHERE p.id = :id")
})

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int age;
	private String name;
	private String sex;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}