package br.com.nofrontier.sgetea.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum TaskStatus {
	
	FINISHED("Finished"),
	SCHEDULED("Scheduled"),
	CANCELLED("Concelled");
	
	TaskStatus(String name) {
		this.name = name;
	}
	
	private String name;
	private String value;

}
