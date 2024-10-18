package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;

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
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded= true, callSuper= false)
@EqualsAndHashCode(onlyExplicitlyIncluded= true, callSuper= false)
public class Address extends AbstractAddress implements Serializable {

	private static final long serialVersionUID = 1L;
    
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;
	
	@Column(name = "default_address")
	private boolean defaultForShipping;
}