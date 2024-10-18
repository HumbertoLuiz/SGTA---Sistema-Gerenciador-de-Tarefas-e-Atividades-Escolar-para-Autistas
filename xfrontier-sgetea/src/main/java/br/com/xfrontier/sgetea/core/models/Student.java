package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.xfrontier.sgetea.core.enums.CourseStatus;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "students")
public class Student extends AbstractPerson implements Serializable {

	private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
    
	@Column(nullable = false)
	private Integer enrollmentNumber;

	@Column(nullable = false)
	private LocalDate enrollmentDate;

	@Column(length = 11, nullable = false)
	@Enumerated(EnumType.STRING)
	private CourseStatus courseStatus;

	@Column(nullable = false)
	private Double averageScore; // Média de todas as disciplinas do aluno
	
	@Column(nullable = false)
	private String academicYear;

	@Column(nullable = false)
	private String frequency;
	
	@OneToOne(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Course course;

    @ManyToMany
    @JoinTable(
        name = "student_subjects_enrolled",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjectsEnrolled = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "student_subjects_completed",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjectsCompleted = new HashSet<>();
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_class_group", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "class_group_id") })
	private Set<ClassGroup> classes = new HashSet<>();
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();
	
    @ManyToMany
    @JoinTable(
        name = "student_activities", 
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activities = new HashSet<>();
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guardian_id")
	private Guardian guardians;

}
