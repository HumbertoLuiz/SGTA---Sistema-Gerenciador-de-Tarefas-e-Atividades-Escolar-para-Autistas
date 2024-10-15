package br.com.nofrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.Guardian;


public interface GuardianRepository extends JpaRepository<Guardian, Long> {

}
