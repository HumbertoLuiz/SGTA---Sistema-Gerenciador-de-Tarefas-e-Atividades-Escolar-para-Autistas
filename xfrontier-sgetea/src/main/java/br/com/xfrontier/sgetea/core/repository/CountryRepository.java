package br.com.xfrontier.sgetea.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.xfrontier.sgetea.core.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	@Query("SELECT c FROM Country c WHERE c.name = :name")
	public Country findByName(String name);

	public List<Country> findAllByOrderByNameAsc();

	@Query("SELECT c FROM Country c WHERE c.code = ?1")
	public Country findByCode(String code);
}
