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
@Table(name = "states")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded= true, callSuper= false)
@EqualsAndHashCode(onlyExplicitlyIncluded= true, callSuper= false)
public class State extends IdBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 45)
	private String name;

	//@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country countries;

}