package tn.esprit.twin.springboot1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.twin.springboot1.services.ChambreService;
import tn.esprit.twin.springboot1.services.ChambreServiceImpl;
@AllArgsConstructor
@RestController
public class ChambreController {
    ChambreService chambreService;
}
