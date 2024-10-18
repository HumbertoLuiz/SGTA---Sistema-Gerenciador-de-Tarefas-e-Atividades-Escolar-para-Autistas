package br.com.xfrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.TokenBlackList;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    boolean existsByToken(String token);

}
