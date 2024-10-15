package br.com.nofrontier.sgetea.core.services.checkcity.adapters;

import br.com.nofrontier.sgetea.core.services.checkcity.dtos.CityResponse;
import br.com.nofrontier.sgetea.core.services.checkcity.exceptions.CheckCityServiceException;

public interface CheckCityService {

    CityResponse findCityByIbgeCode(String codigoIbge) throws CheckCityServiceException;

}