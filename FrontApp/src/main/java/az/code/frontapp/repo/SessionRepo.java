package az.code.frontapp.repo;

import az.code.frontapp.entity.Session;
import az.code.frontapp.entity.s2.MainSession;
import az.code.frontapp.entity.s2.OperatorAnswer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionRepo {

    private final EntityManager em;


    public List<MainSession> getSessionsList(){
        Query q = em.createQuery("select a from MainSession a where a.stage >= 0", Session.class);

        List<MainSession> msList = q.getResultList();
        return msList;
    }

    @Transactional
    public MainSession saveSession(MainSession mainSession){
        em.merge(mainSession);
        return mainSession;
    }

    public MainSession getSession(Long id){
        return em.find(MainSession.class, id);
    }

    @Transactional
    public OperatorAnswer saveAnswer(OperatorAnswer answer){

        return em.merge(answer);
    }


}
