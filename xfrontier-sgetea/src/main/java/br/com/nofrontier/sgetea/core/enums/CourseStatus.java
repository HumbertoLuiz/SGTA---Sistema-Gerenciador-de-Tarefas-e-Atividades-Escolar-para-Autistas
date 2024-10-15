package br.com.nofrontier.sgetea.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum CourseStatus {
	
	ACTIVE("active"),
	INACTIVE("inactive"),
	GRADUATED("graduated");
	
	CourseStatus(String name) {
		this.name = name;
	}
	
	private String name;
	private String value;

}
