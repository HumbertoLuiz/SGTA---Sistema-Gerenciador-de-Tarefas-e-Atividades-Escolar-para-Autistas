package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "guardians")
@PrimaryKeyJoinColumn(name = "id_guardian")
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Guardian extends AbstractPerson implements Serializable{

	private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
    
    
	@Column(nullable = false)
	private String relationship;
	
	
	@Column(nullable = false)
	private String workPlace;

	@Column(nullable = false)
	private String Observation;

	
	public Guardian(String email, String cpf, String relationship, String workPlace, String observation,
			Set<Student> students) {
		super(email, cpf);
		this.relationship = relationship;
		this.workPlace = workPlace;
		Observation = observation;
		this.students = students;
	}



	@JsonManagedReference
	@OneToMany(mappedBy = "guardians", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<>();
}
