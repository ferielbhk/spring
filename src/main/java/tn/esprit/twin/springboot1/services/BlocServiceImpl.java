package tn.esprit.twin.springboot1.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.entity.*;
import tn.esprit.twin.springboot1.repository.BlocRepository;
import tn.esprit.twin.springboot1.repository.ChambreRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
@Slf4j
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

    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        return blocRepository.nbChambreParTypeEtBloc(type , idBloc);
    }

    //@Scheduled(fixedRate = 120000)
    public void  listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();
        for (int i=0; i<blocs.size();i++) {
            log.info(blocs.get(i).getNomBloc() + ": " + blocs.get(i).getCapaciteBloc());
            Set<Chambre> chambres =blocs.get(i).getChambre();
            if (chambres!=null){
            chambres.stream().forEach(
                    chambre -> {
                        log.info("num chambre est : "+chambre.getNumeroChambre()+"est de type "+chambre.getTypeC());
                    }
            );}

        }
    }






        @Override
        public byte[] generatePdfForBloc(Bloc bloc) throws IOException {
            // Génère le PDF en utilisant la méthode auxiliaire
            return generatePdf(bloc);
        }

        public byte[] generatePdf(Bloc bloc) throws IOException {
            // Crée un nouveau document PDF
            PDDocument document = new PDDocument();

            // Ajoute une nouvelle page au document
            PDPage page = new PDPage();
            document.addPage(page);

            // Utilise un flux de contenu pour ajouter du texte à la page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Titre du PDF
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(20, 700);
                contentStream.showText("Chambres du Bloc : " + bloc.getNomBloc());
                contentStream.newLineAtOffset(0, -20);
                contentStream.endText();

                // Liste des chambres
                List<Chambre> chambres = List.copyOf(bloc.getChambre());

                for (Chambre chambre : chambres) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(20, 700);
                    contentStream.showText("Chambre numéro : " + chambre.getNumeroChambre());
                    contentStream.newLineAtOffset(0, -20);

                    if (chambre.getReservations() != null && !chambre.getReservations().isEmpty()) {
                        // La chambre est réservée
                        contentStream.showText("Statut : Réservée");
                    } else {
                        // La chambre n'est pas réservée
                        contentStream.showText("Statut : Non Réservée");
                    }

                    contentStream.endText();
                }
            }

            // Convertit le document en tableau d'octets
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            // Retourne le tableau d'octets représentant le fichier PDF
            return byteArrayOutputStream.toByteArray();
        }
    }





