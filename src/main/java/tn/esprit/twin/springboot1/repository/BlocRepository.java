package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Bloc;
import tn.esprit.twin.springboot1.entity.TypeChambre;

@Repository
public interface BlocRepository extends JpaRepository<Bloc , Long> {
    Bloc findByNomBloc (String nomBloc);
    @Query("SELECT COUNT(c) FROM Chambre c WHERE c.typeC = :type AND c.bloc.idBloc = :idBloc")
    long nbChambreParTypeEtBloc(@Param("type") TypeChambre type, @Param("idBloc") long idBloc);


}
