package br.com.xfrontier.sgetea.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

	Optional<Picture> findByFilename(String filename);
}
