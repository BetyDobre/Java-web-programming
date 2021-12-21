package ro.unibuc.springlab8example1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.springlab8example1.domain.Points;

import java.util.List;

@Repository
public interface PointsRepository extends JpaRepository<Points, Long> {

    List<Points> findAllByUsername(String username);

}
