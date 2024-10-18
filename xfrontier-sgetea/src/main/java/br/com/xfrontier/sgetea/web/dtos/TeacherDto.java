package br.com.xfrontier.sgetea.web.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.xfrontier.sgetea.core.models.ClassGroup;
import br.com.xfrontier.sgetea.core.models.Picture;
import br.com.xfrontier.sgetea.core.models.Subject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class TeacherDto {

	@NotBlank(message = "Campo matrícula obrigatorio")
	private Integer registration;

	@NotBlank(message = "Campo especialização obrigatorio")
	private String specialization;

	@NotBlank(message = "Campo data de contratação cpf obrigatorio")
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate hiringDate;

	@NotBlank(message = "Campo salário obrigatorio")
	private String salary;

	@NotBlank(message = "Campo email obrigatorio")
	@Email(message = "Entre com um email válido")
	private String email;

	@NotBlank(message = "Campo especialização obrigatorio")
	private String academicBackground;

	@NotBlank(message = "Campo experiência profissional obrigatorio")
	private String professionalExperience;

	@NotBlank(message = "Campo foto obrigatorio")
    private Picture userPicture;

	private Set<Subject> subjects = new HashSet<>();

	private Set<ClassGroup> classes = new HashSet<>();
}
