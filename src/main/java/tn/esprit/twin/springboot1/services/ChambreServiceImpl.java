package tn.esprit.twin.springboot1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.repository.ChambreRepository;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements  ChambreService{
    ChambreRepository chambreRepository;
}
