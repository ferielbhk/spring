package tn.esprit.twin.springboot1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot1.entity.Bloc;
import tn.esprit.twin.springboot1.entity.Chambre;
import tn.esprit.twin.springboot1.entity.Universite;
import tn.esprit.twin.springboot1.services.BlocService;
import tn.esprit.twin.springboot1.services.BlocServiceImpl;
import tn.esprit.twin.springboot1.services.ChambreService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bloc")
public class BlocController {
    BlocService blocService;
    ChambreService chambreService;

    @GetMapping("/getallblocs")
    public List<Bloc> getBlocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }
    @GetMapping("/getbloc/{blocID}")
    public Bloc retrieveBloc(@PathVariable("blocID") Long blocId) {

        return blocService.retrieveBloc(blocId);
    }
    @PostMapping("/addbloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        Bloc  bloc = blocService.addBloc(b);
        return bloc;
    }
    @DeleteMapping("/deletebloc/{blocID}")
    public void removeBloc(@PathVariable("blocID") Long blocId) {

        blocService.removeBloc(blocId);
    }
    @PutMapping("/updatebloc")
    public Bloc updateBloc(@RequestBody Bloc b ){
        Bloc bloc= blocService.updateBloc(b);
        return bloc;
    }
    @PutMapping("/affecter/{nombloc}")
    public Bloc affecter(@RequestBody List<Long> numChambre,@PathVariable("nombloc")  String nomBloc) {
        Bloc bloc = blocService.affecterChambresABloc( numChambre,  nomBloc);
        return bloc;
    }

}
