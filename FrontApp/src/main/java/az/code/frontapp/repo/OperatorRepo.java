package az.code.frontapp.repo;

import az.code.frontapp.entity.s2.OperatorUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OperatorRepo {

    private final EntityManager em;

    public OperatorUser getOperatorUser(Long Id){
        return em.find(OperatorUser.class, Id);
    }

}
