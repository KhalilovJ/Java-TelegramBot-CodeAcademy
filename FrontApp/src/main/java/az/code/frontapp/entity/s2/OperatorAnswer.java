package az.code.frontapp.entity.s2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OperatorAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "operatorId")
    private OperatorUser operatorId;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "answerId")
    private MainSession mainSession;


    /**
     * status 1 accepted
     * status -1 declined

     */
    private Integer status;
}
