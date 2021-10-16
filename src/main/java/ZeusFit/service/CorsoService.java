package ZeusFit.service;

import ZeusFit.controller.dto.CorsoDto;
import ZeusFit.model.Corso;

public interface CorsoService {

    Corso save(CorsoDto corsoDto);
    Corso aggiorna(CorsoDto corsoDto);
    boolean corso_exist(CorsoDto corsoDto);
    Corso aggiugi_lezione(CorsoDto corsoDto);
    Corso loadCorsobyid(Long id);
    Corso loadCorsobynome(String nome);

}
