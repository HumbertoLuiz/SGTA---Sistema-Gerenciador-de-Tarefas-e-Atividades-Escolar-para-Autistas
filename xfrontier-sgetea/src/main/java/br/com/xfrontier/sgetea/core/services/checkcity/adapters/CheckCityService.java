package br.com.xfrontier.sgetea.core.services.checkcity.adapters;

import br.com.xfrontier.sgetea.core.services.checkcity.dtos.CityResponse;
import br.com.xfrontier.sgetea.core.services.checkcity.exceptions.CheckCityServiceException;

public interface CheckCityService {

    CityResponse findCityByIbgeCode(String codigoIbge) throws CheckCityServiceException;

}