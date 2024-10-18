package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.xfrontier.sgetea.core.enums.Position;
import br.com.xfrontier.sgetea.core.enums.Seniority;
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
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@PrimaryKeyJoinColumn(name = "id_teacher")
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "teachers")
public class Teacher extends AbstractPerson implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

	@Column(nullable = false)
	private Integer registration;

	@Column(nullable = false)
	private String specialization;

	@Column(nullable = false)
	private LocalDate hiringDate;

	@Column(nullable = false)
	private String salary;

    @Column(length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
	private Seniority seniority; // ex: Junior, Senior, Head Teacher

	@Column(nullable = false)
	@Enumerated(EnumType.STRING) 
	private Position position;
    
	@Column(nullable = false)
	private String academicBackground;

	@Column(nullable = false)
	private String professionalExperience;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_discipline", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
			@JoinColumn(name = "discipline_id") })
	private Set<Subject> subjects = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_course_class", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_class_id") })
	private Set<ClassGroup> classes = new HashSet<>();
	
}