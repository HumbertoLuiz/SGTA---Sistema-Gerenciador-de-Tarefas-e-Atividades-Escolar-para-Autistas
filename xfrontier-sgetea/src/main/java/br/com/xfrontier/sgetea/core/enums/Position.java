package br.com.xfrontier.sgetea.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum Position {

	COORDINATOR("Coordinator"), 
	PSYCHOLOGIST("Psichologist"), 
	TEACHER("Teacher");

	Position(String name) {
		this.name = name;
	}

	private String name;
	private String value;

}
