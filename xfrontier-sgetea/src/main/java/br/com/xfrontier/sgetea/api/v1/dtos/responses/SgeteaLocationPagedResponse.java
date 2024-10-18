package br.com.xfrontier.sgetea.api.v1.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class SgeteaLocationPagedResponse {

	private List<SgeteaLocationResponse> housekeeper;

	private Long quantityHousekeeper;

	public SgeteaLocationPagedResponse(List<SgeteaLocationResponse> housekeeper,
			Integer pageSize, Long totalElements) {
		this.housekeeper = housekeeper;
		this.quantityHousekeeper = totalElements > pageSize ? totalElements - pageSize : 0;
	}
}
