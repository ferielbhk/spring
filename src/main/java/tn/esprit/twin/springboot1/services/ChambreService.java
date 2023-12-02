package tn.esprit.twin.springboot1.services;

import org.springframework.data.repository.query.Param;
import tn.esprit.twin.springboot1.entity.Bloc;
import tn.esprit.twin.springboot1.entity.Chambre;
import tn.esprit.twin.springboot1.entity.TypeChambre;
import tn.esprit.twin.springboot1.entity.TypeChambrePourcentage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
    List<Chambre> getChambresParNomBloc(String nomBloc);
    //void pourcentageChambreParTypeChambre();

    //String calculerPourcentageChambreParTypeChambre(boolean estValide);

    HashSet<TypeChambrePourcentage> calculerPourcentageChambreParTypeChambre1(boolean estValide);

}
