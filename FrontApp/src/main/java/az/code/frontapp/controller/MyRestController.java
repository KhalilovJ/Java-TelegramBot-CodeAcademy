package az.code.frontapp.controller;


import az.code.frontapp.entity.SessionReply;
import az.code.frontapp.entity.dto.MainSessionDto;
import az.code.frontapp.entity.dto.OfferDTO;
import az.code.frontapp.entity.s2.MainSession;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class MyRestController {

    @Autowired
    ApplicationContext applicationContext;

    MainController mainController;

    @PostConstruct
    private void init(){
        mainController = applicationContext.getBean(MainController.class);

//        System.out.println(getSessions());

//        sendOffer(OfferDTO.builder().id(202l).offer("apidən gələn təklif").build());
    }



    @GetMapping("/getAllSessions")
    public List<MainSessionDto> getSessions(){
        List<MainSession> sessions = mainController.getSessions();

        List <MainSessionDto> dtoList = new ArrayList<>();

        if (sessions.size()>0){
        sessions.forEach(a-> {
            if (a.getSession() != null){
            dtoList.add(MainSessionDto.builder().id(a.getId())
                    .AnswersList(a.getSession().getUserReplies().stream().map(ms-> ms.getAnswer()).collect(Collectors.toList()))
                    .build());}
        });}


        return dtoList;
    }


    @PostMapping("/sendoffer")
    private void sendOffer(@RequestBody OfferDTO offer){
        mainController.setAnswer(offer.getOfferId(), null, offer.getOffer());
    }

}
