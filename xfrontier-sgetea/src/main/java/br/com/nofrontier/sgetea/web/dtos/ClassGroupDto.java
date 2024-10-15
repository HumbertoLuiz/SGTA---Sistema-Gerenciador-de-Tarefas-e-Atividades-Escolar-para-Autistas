package br.com.nofrontier.sgetea.web.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.nofrontier.sgetea.core.models.Subject;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ClassGroupDto {
	
	@NotBlank(message = "Campo nome obrigatorio")
	private String name;

	private Subject subjects;

}
