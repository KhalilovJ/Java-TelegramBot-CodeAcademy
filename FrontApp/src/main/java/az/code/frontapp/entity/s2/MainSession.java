package az.code.frontapp.entity.s2;

import az.code.frontapp.entity.Session;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class MainSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "sessionId")
    private Session session;

    /**
     if stage -1 then session is inactive
     if 0 then session is ready to accept bids
     */
    private Integer stage;

    private LocalDate date;

    @JsonIgnore
    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OperatorAnswer> offersList;
}
