package tn.esprit.twin.springboot1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.entity.Bloc;
import tn.esprit.twin.springboot1.entity.Chambre;
import tn.esprit.twin.springboot1.entity.Foyer;
import tn.esprit.twin.springboot1.entity.Universite;
import tn.esprit.twin.springboot1.repository.BlocRepository;
import tn.esprit.twin.springboot1.repository.ChambreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class BlocServiceImpl implements  BlocService {
    BlocRepository blocRepository;
    ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc( Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public  Bloc updateBloc( Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public  Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public void removeBloc(Long idBloc) {

    }

    @Override
    public Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc)
    {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        numChambre.stream().forEach(
                c -> {
                    Chambre chambre = chambreRepository.findByNumeroChambre(c);
                    chambre.setBloc(bloc);
                    chambreRepository.save(chambre);
                }
        );

        return bloc;

    }
}
