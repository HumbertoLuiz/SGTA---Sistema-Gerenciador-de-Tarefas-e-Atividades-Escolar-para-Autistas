package br.com.nofrontier.sgetea.web.dtos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.nofrontier.sgetea.core.models.Phone;
import br.com.nofrontier.sgetea.core.models.Picture;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class GuardianDto {
	
	@NotBlank(message="Campo nome obrigatorio")	
	private String firstName;
	
	@NotBlank(message="Campo nome obrigatorio")	
	private String lastName;
	
	@NotBlank(message = "Campo email obrigatorio")
	@Email(message = "Entre com um email válido")
	private String email;
	
	
	private Set<Address> addresses = new HashSet<>();
	
    private Set<Phone> phones = new HashSet<>();
    
    @NotBlank(message = "Campo foto obrigatorio")
    private Picture userPicture;
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	

}
