package br.com.xfrontier.sgetea.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum Shift {	

	MORNING("Morning"), 
	AFTERNOON("Afternoon"), 
	EVENING("Evening"), 
	FULLTIME("fulltime");
	
	Shift(String name) {
		this.name = name;
	}
	
	private String name;
	private String value;	

}
