package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "assessment")
public class Assessment extends IdBaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String title;
    
    private String type;

    @Column(name = "Assessment_date", nullable = false)
    private LocalDate AssessmentDate;

    @Column(name = "max_score", nullable = false)
    private BigDecimal maxScore;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

}
