package br.com.nofrontier.sgetea.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nofrontier.sgetea.core.exceptions.TokenBlackListException;
import br.com.nofrontier.sgetea.core.models.TokenBlackList;
import br.com.nofrontier.sgetea.core.repository.TokenBlackListRepository;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository repository;

    public void checkToken(String token) {
        if (repository.existsByToken(token)) { throw new TokenBlackListException(); }
    }

    public void putTokenOnBlackList(String token) {
        if (!repository.existsByToken(token)) {
            var tokenBlackList= new TokenBlackList();
            tokenBlackList.setToken(token);
            repository.save(tokenBlackList);
        }
    }

}
