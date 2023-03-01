package az.code.frontapp.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferDTO {

    Long offerId;

    Long chatId;

    String offer;
}
