package br.com.nofrontier.sgetea.web.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.nofrontier.sgetea.core.models.Phone;
import br.com.nofrontier.sgetea.core.models.Picture;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class StudentDto {

	@NotBlank(message = "{campo.nome.obrigatorio}")
	@Size(min = 3, max = 255, message = "Primeiro Nome deve conter {min} ate {max} caracteres")
	private String firstName;

	@NotBlank(message = "{campo.nome.obrigatorio}")
	@Size(min = 3, max = 255, message = "Último Nome deve conter {min} ate {max} caracteres")
	private String lastName;

	@NotBlank(message = "Campo matricula obrigatorio")
	private Integer registration;

	@NotBlank(message = "Campo email obrigatorio")
	@Email(message = "Entre com um email válido")
	private String email;

	@CPF
	@NotBlank(message = "Campo cpf obrigatorio")
	private String cpf;

	@NotBlank(message = "Campo idade cpf obrigatorio")
	private Integer age;

	@NotBlank(message = "Campo gênero obrigatorio")
	private String genre;

	@NotBlank(message = "Campo nacionalidade cpf obrigatorio")
	@Size(min = 3, max = 50)
	private String nationality;

	@NotBlank(message = "Campo data de Nascimento cpf obrigatorio")
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

	@NotBlank(message = "Campo estado civil obrigatorio")
	private String maritalStatus;

	@NotBlank(message = "Campo profissão obrigatorio")
	private String profession;

	@NotBlank(message = "Campo nome de contato de emergencia cpf obrigatorio")
	private String emergencyContactName;

	@NotBlank(message = "Campo telefone de contato de emergencia obrigatorio")
	private String emergencyContactPhone;

	@NotBlank(message = "Campo informações médicas obrigatorio")
	private String medicalInformation;

	@NotBlank(message = "Campo informações pessoais obrigatorio")
	private String personalInformation;

	private Set<Address> addresses = new HashSet<>();

	private Set<Phone> phones = new HashSet<>();
	
	@NotBlank(message = "Campo foto obrigatorio")
    private Picture userPicture;

	public String getFullName() {
		return firstName + " " + lastName;
	}

}
