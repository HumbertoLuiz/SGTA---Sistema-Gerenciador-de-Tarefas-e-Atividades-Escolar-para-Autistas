package br.com.nofrontier.sgetea.web.dtos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.nofrontier.sgetea.core.enums.TaskStatus;
import br.com.nofrontier.sgetea.core.models.Subject;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class TaskDto {

	@NotBlank(message="Campo titulo obrigatorio")
	@Size(min = 3, max = 65)
	private String title;

	@NotBlank(message="Campo descrição obrigatorio")
	@Size(min = 3, max = 255)
	private String description;

	@Size(max = 5)
	private String priority;

	@NotBlank(message="Campo data de criação obrigatorio")
	@Size(max = 10)
	private String taskCreationDate;

	@NotBlank(message="Campo data término obrigatorio")
	@Size(max = 10)
	private String taskEndDate;

	@Size(max = 11)
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	private Set<Subject> subjects = new HashSet<>();
}
