package az.code.frontapp.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MainSessionDto {

    Long id;
    List<String> AnswersList;
}
