package br.com.nofrontier.sgetea.core.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
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
@Table(name = "activities")
public class Activity extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
    private String name;
    
	@Lob
	@Column(nullable = false)
    private String description;
    
	@Column(nullable = false)
    private String type;
    
	@Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
	@Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
	@Column(nullable = false)
    private String status;
    
    @ManyToMany(mappedBy = "activities")
    private Set<Student> students = new HashSet<>();
}
