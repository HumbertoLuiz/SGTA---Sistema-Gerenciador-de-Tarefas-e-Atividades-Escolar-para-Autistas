package br.com.nofrontier.sgetea.core.models;

import java.io.Serializable;

import br.com.nofrontier.sgetea.core.enums.Position;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "administrative_staff")
public class AdministrativeStaff extends AbstractPerson implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
    
    
	@Column(nullable = false)
	private Integer registration;	

	@Column(nullable = false)
	@Enumerated(EnumType.STRING) 
	private Position position;

}
