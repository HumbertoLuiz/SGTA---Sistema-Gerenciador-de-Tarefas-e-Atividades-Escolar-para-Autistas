package br.com.nofrontier.sgetea.core.enums;

public enum Seniority {

	JUNIOR("junior"), 
	INTERN("intern"),
	SENIOR("senior"), 
	PLENO("pleno"), 
	HEAD_TEACHER("HeadTeacher");
	
	private String name;
	private String value;
		
	private Seniority() {}
	
	private Seniority(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	
}

