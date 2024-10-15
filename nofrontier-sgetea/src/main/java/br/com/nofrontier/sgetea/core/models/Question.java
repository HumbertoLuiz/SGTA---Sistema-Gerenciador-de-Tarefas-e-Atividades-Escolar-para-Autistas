package br.com.nofrontier.sgetea.core.models;

import java.io.Serializable;

import br.com.nofrontier.sgetea.core.enums.QuestionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
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
@ToString(onlyExplicitlyIncluded= true, callSuper= false)
@EqualsAndHashCode(onlyExplicitlyIncluded= true, callSuper= false)
@Table(name = "question")
public class Question extends IdBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Lob
    @Column(nullable = false)
    private String description;
    
    @Column(name = "question_type", length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column(name = "alternative_1", nullable = false, length = 400)
    private String alternative1;

    @Column(name = "alternative_2", nullable = false, length = 400)
    private String alternative2;

    @Column(name = "alternative_3", nullable = false, length = 400)
    private String alternative3;

    @Column(name = "alternative_4", nullable = false, length = 400)
    private String alternative4;

    @Column(name = "alternative_5", nullable = false, length = 400)
    private String alternative5;

    @Column(name = "RESPOSTA", nullable = false)
    private Integer resposta;

}

