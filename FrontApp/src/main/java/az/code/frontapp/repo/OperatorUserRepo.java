package az.code.frontapp.repo;

import az.code.frontapp.entity.s2.OperatorAnswer;
import az.code.frontapp.entity.s2.OperatorUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OperatorUserRepo{

    private final EntityManager em;

//    public Optional<OperatorUser> findByUsername(String string, OperatorRepoInterface repo) {
//        Optional<OperatorUser> user = repo.findByUsername(string);
//        user.get();
//        return user;
//    }
//
    public Optional<OperatorUser> findByUsername(String string) {
        Optional<OperatorUser> ou = Optional.of(em.createQuery("select a from OperatorUser a where a.username = ?1", OperatorUser.class)
                .setParameter(1, string).getSingleResult());
        return ou;
    }
}
