package br.com.nofrontier.sgetea.core.services.checkaddress.adapters;

import br.com.nofrontier.sgetea.core.services.checkaddress.dtos.AddressResponse;
import br.com.nofrontier.sgetea.core.services.checkaddress.exceptions.AddressServiceException;

public interface AddressService {

	AddressResponse findAddressByzipCode(String zipCode) throws AddressServiceException;



}
