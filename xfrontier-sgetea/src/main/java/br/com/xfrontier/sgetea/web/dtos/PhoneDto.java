package br.com.xfrontier.sgetea.web.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.xfrontier.sgetea.core.models.AbstractPerson;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PhoneDto {

	@NotBlank(message="Campo telefone obrigatorio")
	protected String phoneNumber;

	@NotBlank(message="Campo celular obrigatorio")
	protected String mobileNumber;

	private AbstractPerson persons;
}
