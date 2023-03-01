package az.code.frontapp.repo;

import az.code.frontapp.entity.s2.OperatorUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepoInterface  extends JpaRepository<OperatorUser,Long> {

    Optional<OperatorUser> findByUsername(String string);

    Optional<OperatorUser> findById(String string);
}
