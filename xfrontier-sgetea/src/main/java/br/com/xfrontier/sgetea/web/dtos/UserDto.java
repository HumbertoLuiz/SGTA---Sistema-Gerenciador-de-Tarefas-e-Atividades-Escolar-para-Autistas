package br.com.xfrontier.sgetea.web.dtos;

import java.util.Set;

import br.com.xfrontier.sgetea.core.interfaces.IConfirmPassword;
import br.com.xfrontier.sgetea.core.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements IConfirmPassword {

	@NotNull
	@Size(min = 3, max = 255)
	private String firstName;
	
	@NotNull
	@Size(min = 3, max = 255)
	private String lastName;

	@NotNull
	@Size(min = 3, max = 255)
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String confirmPassword;
	
	private int active;
	
	private boolean isEnabled;

	private String imagePath;

	@NotNull
	private Set<Role> roles;

}