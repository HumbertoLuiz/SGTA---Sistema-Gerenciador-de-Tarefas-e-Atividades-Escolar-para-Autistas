package br.com.xfrontier.sgetea.core.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded= true, callSuper= false)
@EqualsAndHashCode(onlyExplicitlyIncluded= true, callSuper= false)
public class AbstractAddress extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    
	@Column(name = "address_line_1", nullable = false, length = 64)
	protected String addressLine1;

	@Column(name = "address_line_2", length = 64)
	protected String addressLine2;

	@Column(name = "postal_code", nullable = false, length = 10)
	protected String postalCode;
    

	@Column(name = "default_address")
	private boolean defaultForShipping;

}
