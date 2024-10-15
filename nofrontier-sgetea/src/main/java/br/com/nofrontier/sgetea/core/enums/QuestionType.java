package br.com.nofrontier.sgetea.core.enums;

public enum QuestionType {
	
	TEST("test"),
	WORK("work");
	
	private String name;
	private String value;
	
	private QuestionType() {}

	private QuestionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
}
