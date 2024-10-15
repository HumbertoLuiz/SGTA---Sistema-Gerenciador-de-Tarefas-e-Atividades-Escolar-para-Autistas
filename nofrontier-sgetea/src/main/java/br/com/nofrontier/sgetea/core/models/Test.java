package br.com.nofrontier.sgetea.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "test")
public class Test extends IdBaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String title;

    @Column(name = "approval_percentage", nullable = false)
    private BigDecimal percentage;
    
    @Column(nullable = false)
    private LocalDate date; // Data da prova
    
    @OneToMany(mappedBy = "test")
    private List<Grade> grades; // Notas associadas à prova

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_question",
            joinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question > questions = new HashSet<>();
}