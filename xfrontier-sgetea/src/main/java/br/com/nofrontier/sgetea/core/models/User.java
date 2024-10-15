package br.com.nofrontier.sgetea.core.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.nofrontier.sgetea.core.enums.UserType;
import br.com.nofrontier.sgetea.core.repository.RoleRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "users")
public class User extends AbstractPerson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "complete_name", nullable = false)
	private String completeName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

    @Column(name = "user_type", length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "document_picture", nullable = true)
	private Picture documentPicture;

	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "user_picture", nullable = true)
	private Picture userPicture;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "users", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Phone> phones = new HashSet<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "users", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Address> addresses = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles = new HashSet<>();

	public User(Long userId) {
	}

	public Boolean isAdmin() {
		return roles.equals(RoleRepository.findByName("ROLE_ADMIN"));
	}

	public Boolean isUser() {
		return roles.equals(RoleRepository.findByName("ROLE_USER"));
	}
	
	public String getCpf() {
		return getCpf();
	}

	public String getEmail() {
		return getEmail();
	}

}