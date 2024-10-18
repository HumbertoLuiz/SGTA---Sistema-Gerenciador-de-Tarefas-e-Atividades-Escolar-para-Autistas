package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractPerson implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstName;

	protected String lastName;

	protected String cpf;

	protected String email;

	protected LocalDate birthDate;

	protected String keyPix;

	protected Integer age;

	protected String genre;

	protected String nationality;

	protected String maritalStatus;

	protected String profession;

	protected String emergencyContactName;

	protected String emergencyContactPhone;

	protected String medicalInformation;

	protected String personalInformation;

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public Integer getAge() {
		age = (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
		return age;
	}

	public AbstractPerson(String email, String cpf) {}


//	@JsonManagedReference
//	@OneToMany(mappedBy = "persons", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<User> users = new HashSet<>();

//	@JsonManagedReference
//	@OneToMany(mappedBy = "persons", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<Phone> phones = new HashSet<>();

//	@OneToOne(mappedBy = "persons", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Student students;
//
//	@OneToOne(mappedBy = "persons", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Guardian guardians;
//
//	@OneToOne(mappedBy = "persons", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Employee employees;

}
