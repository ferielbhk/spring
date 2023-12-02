package tn.esprit.twin.springboot1.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.CapaciteMaxAtteinteException;
import tn.esprit.twin.springboot1.entity.Chambre;
import tn.esprit.twin.springboot1.entity.Etudiant;
import tn.esprit.twin.springboot1.entity.Reservation;
import tn.esprit.twin.springboot1.repository.ChambreRepository;
import tn.esprit.twin.springboot1.repository.EtudiantRepository;
import tn.esprit.twin.springboot1.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ReservationServicaAvancé {
        @Autowired
        private ChambreRepository chambreRepository;

        @Autowired
        private EtudiantRepository etudiantRepository;

        @Autowired
        private ReservationRepository reservationRepository;
         int annee = LocalDate.now().getYear();
        public Reservation ajouterReservation(long idBloc, long cinEtudiant) {

            // Récupérer la chambre et l'étudiant correspondant aux identifiants fournis
            Chambre chambre = chambreRepository.findByBlocIdBloc(idBloc);
            Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

            // Vérifier si la chambre et l'étudiant existent
            if (chambre == null || etudiant == null) {
                throw new EntityNotFoundException("Chambre ou étudiant non trouvé");
            }

            // Vérifier si la capacité maximale de la chambre n'est pas atteinte
            if (capaciteMaxNonAtteinte(chambre)) {

                // Construire le numéro de réservation selon le format spécifié
                String numReservation = construireNumeroReservation(chambre);

                // Créer la réservation avec les conditions spécifiées
                Reservation reservation = new Reservation();
                reservation.setNumReservation(numReservation);
                reservation.setAnneeUniversitaire(new Date(annee));
                reservation.setEstValide(true);

                // Associer la réservation à la chambre et à l'étudiant
                chambre.getReservations().add(reservation);
                etudiant.getReservations().add(reservation);
                return reservationRepository.save(reservation);
            } else {
                throw new CapaciteMaxAtteinteException("La capacité maximale de la chambre est atteinte");
            }
        }

        private boolean capaciteMaxNonAtteinte(Chambre chambre) {
            int capaciteMax = 0;

            // Déterminez la capacité maximale en fonction du type de chambre
            switch (chambre.getTypeC()) {
                case SIMPLE:
                    capaciteMax = 1;
                    break;
                case DOUBLE:
                    capaciteMax = 2;
                    break;
                case TRIPLE:
                    capaciteMax = 3;
                    break;
            }

            // Vérifiez si la capacité maximale n'est pas atteinte
            return chambre.getReservations().size() < capaciteMax;
        }

        private String construireNumeroReservation(Chambre chambre) {
            // Récupérer les informations nécessaires de la chambre
            Long numeroChambre = chambre.getNumeroChambre();
            Long idBloc = chambre.getBloc().getIdBloc();

            // Construire le numéro de réservation selon le format spécifié
            return String.format("%d-%d-%s",
                    numeroChambre, idBloc, new Date(annee)); // Remplacez "2023" par l'année universitaire réelle
        }
    }


