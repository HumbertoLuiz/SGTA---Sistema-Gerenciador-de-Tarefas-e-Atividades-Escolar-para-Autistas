package br.com.xfrontier.sgetea.web.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.xfrontier.sgetea.core.models.AbstractPerson;
import br.com.xfrontier.sgetea.core.models.City;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AddressDto {

	@NotBlank(message="Campo endereço1 obrigatorio")
	protected String addressLine1;

	@NotBlank(message="Campo endereço2 obrigatorio")
	protected String addressLine2;

	@NotBlank(message="Campo endereço de envio obrigatorio")
	private boolean defaultForShipping;

	@NotBlank(message="Campo cep obrigatorio")
	protected String postalCode;

	private AbstractPerson persons;

	private City cities;

}
