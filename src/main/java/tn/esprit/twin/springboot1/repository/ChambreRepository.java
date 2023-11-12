package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Chambre;
@Repository
public interface ChambreRepository extends JpaRepository<Chambre , Long> {
    Chambre findByNumeroChambre (Long numChambre);
}
