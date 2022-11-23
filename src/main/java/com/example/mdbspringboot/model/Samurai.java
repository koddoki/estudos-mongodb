package com.example.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("samurai")
public class Samurai {

	@Id
	private String name;
	private String familyName;
	private String school;
	private String clan;
	private int honor;
	private int glory;
	private int status;

	public Samurai(String name, String familyName, String school, String clan, int honor, int glory, int status) {
		super();
		this.name = name;
		this.familyName = familyName;
		this.school = school;
		this.clan = clan;
		this.honor = honor;
		this.glory = glory;
		this.status = status;
	}

	public Samurai() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public int getHonor() {
		return honor;
	}

	public void setHonor(int honor) {
		this.honor = honor;
	}

	public int getGlory() {
		return glory;
	}

	public void setGlory(int glory) {
		this.glory = glory;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
