package az.code.frontapp.controller;

import az.code.frontapp.entity.Session;
import az.code.frontapp.entity.dto.OfferDTO;
import az.code.frontapp.entity.s2.MainSession;
import az.code.frontapp.entity.s2.OperatorAnswer;
import az.code.frontapp.entity.s2.OperatorUser;
import az.code.frontapp.repo.OperatorRepo;
import az.code.frontapp.repo.SessionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.repository.support.SimpleJdbcRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class MainController {


    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;


    @Autowired
    private final RabbitTemplate template;
    @Autowired
    private final ApplicationContext ac;

    SessionRepo sessionRepo;
    OperatorRepo operatorRepo;


    @PostConstruct
    private void init(){
        sessionRepo = ac.getBean(SessionRepo.class);
        operatorRepo = ac.getBean(OperatorRepo.class);
    }



    public List<MainSession> getSessions(){

        return sessionRepo.getSessionsList();

    }


    public OperatorAnswer setAnswer(Long mainSessionId, Long OperatorId, String answer){

//        OperatorUser operator = operatorRepo.getOperatorUser(OperatorId);

        MainSession mainSession = sessionRepo.getSession(mainSessionId);


        OperatorAnswer answer1 = OperatorAnswer.builder()
//                .operatorId(operator)
                .mainSession(mainSession).status(0).answer(answer).build();

//        mainSession.getOffersList().add(answer1);

        answer1 = sessionRepo.saveAnswer(answer1);

//        OperatorAnswer answerOut = sessionRepo.saveSession(mainSession);

        sendToQueue(answer1);

        return answer1;

    }

    //producer
    public void sendToQueue(OperatorAnswer answer){//produce
        log.info("okayiz");
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr;

        OfferDTO offer = OfferDTO.builder()
                .offerId(answer.getId()).offer(answer.getAnswer()).chatId(Long.valueOf(answer.getMainSession().getSession().getUser().getChatId()))
                .build();

        try {
            jsonStr = Obj.writeValueAsString(offer);
            System.out.println(jsonStr);
            template.convertAndSend(exchangeName,routingName,jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Converted and send");

    }



//    @PostConstruct
//    public void test(){
//        OperatorAnswer answer=OperatorAnswer.builder().answer("helllo").build();
//        sendToQueue(answer);
//        System.out.println(answer.toString()+"********");
//    }

//    @PostConstruct
//    private void test(){
//        operatorRepo = ac.getBean(OperatorRepo.class);
//        sessionRepo = ac.getBean(SessionRepo.class);
//
//        setAnswer(2l, 1l, "500 manata Tbilisiyə səyahət");
//    }

}
