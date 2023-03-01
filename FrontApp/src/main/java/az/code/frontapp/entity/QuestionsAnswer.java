package az.code.frontapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@RequiredArgsConstructor
@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "questionsAnswers")
public class QuestionsAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "questionId")
    private Question questionId;

    /**
     * Dil üzrə sual seçildikdən sonra cavabın mətni burda olacaq
     * DB tablelarında context cavabı başa düşmək üçündür
     */
    private String context;

    @ManyToOne(fetch = FetchType.EAGER)
    private Locale locale;

}
