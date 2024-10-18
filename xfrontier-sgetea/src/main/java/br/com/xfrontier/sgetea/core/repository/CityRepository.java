package br.com.xfrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
