package br.com.nofrontier.sgetea.web.dtos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.nofrontier.sgetea.core.models.State;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CountryDto {

	@NotBlank(message="Campo nome obrigatorio")
	private String name;

	@NotBlank(message="Campo código obrigatorio")
	private String code;


	private Set<State> states = new HashSet<>();
}
