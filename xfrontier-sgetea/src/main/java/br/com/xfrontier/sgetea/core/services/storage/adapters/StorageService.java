package br.com.xfrontier.sgetea.core.services.storage.adapters;

import org.springframework.web.multipart.MultipartFile;

import br.com.xfrontier.sgetea.core.models.Picture;
import br.com.xfrontier.sgetea.core.services.storage.exceptions.StorageServiceException;

public interface StorageService {

    Picture save(MultipartFile file) throws StorageServiceException;

    void remove(String filename) throws StorageServiceException;

}