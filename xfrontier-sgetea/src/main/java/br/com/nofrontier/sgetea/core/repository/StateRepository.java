package br.com.nofrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
