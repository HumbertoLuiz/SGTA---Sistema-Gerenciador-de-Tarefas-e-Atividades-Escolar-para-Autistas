package br.com.xfrontier.sgetea.web.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.xfrontier.sgetea.core.enums.Position;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AdministrativeStaffDto {
	
	@NotBlank(message="Campo matricula obrigatorio")
	private Integer registration;	
	
	@NotBlank(message="Campotipo usuario obrigatorio")
	@Enumerated(EnumType.STRING) 
	private Position position;	

}
