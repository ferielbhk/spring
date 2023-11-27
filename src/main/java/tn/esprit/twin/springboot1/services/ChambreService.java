package tn.esprit.twin.springboot1.services;

import org.springframework.data.repository.query.Param;
import tn.esprit.twin.springboot1.entity.Bloc;
import tn.esprit.twin.springboot1.entity.Chambre;
import tn.esprit.twin.springboot1.entity.TypeChambre;

import java.util.List;

public interface ChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
    List<Chambre> getChambresParNomBloc(String nomBloc);
    //void pourcentageChambreParTypeChambre();

}
