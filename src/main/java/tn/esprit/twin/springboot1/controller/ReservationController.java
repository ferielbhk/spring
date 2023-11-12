package tn.esprit.twin.springboot1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.twin.springboot1.services.ReservationService;
import tn.esprit.twin.springboot1.services.ReservationServiceImpl;
@AllArgsConstructor
@RestController
public class ReservationController {
    ReservationService reservationService;
}
