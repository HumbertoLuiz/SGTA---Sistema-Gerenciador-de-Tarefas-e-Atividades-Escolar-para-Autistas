package br.com.xfrontier.sgetea.web.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.xfrontier.sgetea.core.models.ClassGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CourseDto {
	
	@NotBlank(message="Campo nome obrigatorio")
	private String name;
	
	private ClassGroup classes;
}
