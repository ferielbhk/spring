package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Bloc;
@Repository
public interface BlocRepository extends JpaRepository<Bloc , Long> {
    Bloc findByNomBloc (String nomBloc);


}
