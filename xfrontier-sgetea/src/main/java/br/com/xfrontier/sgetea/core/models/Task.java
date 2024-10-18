package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.xfrontier.sgetea.core.enums.TaskStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
@Table(name = "tasks")
public class Task extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1l;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = true)
	private String priority;

	@Column(nullable = false)
	private String taskCreationDate;

	@Column(nullable = false)
	private String taskEndDate;

	@Column(length = 11, nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tasks", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Subject> subjects = new HashSet<>();


	public static String convertDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

}
