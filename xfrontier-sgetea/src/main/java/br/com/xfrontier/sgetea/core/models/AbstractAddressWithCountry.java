package br.com.xfrontier.sgetea.core.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AbstractAddressWithCountry extends AbstractAddress {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	protected Country country;

}
