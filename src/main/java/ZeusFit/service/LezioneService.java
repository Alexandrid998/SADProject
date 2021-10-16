package ZeusFit.service;

import ZeusFit.controller.dto.LezioneDto;
import ZeusFit.model.Lezione;

import java.util.ArrayList;

public interface LezioneService {

    Lezione save(LezioneDto lezioneDto);
    boolean exist(LezioneDto lezioneDto);
    Lezione aggiungi_prenotazione(LezioneDto lezioneDto);
    ArrayList<Lezione> loadLezionebyCorso(Long idcorso);
    boolean is_valid(LezioneDto lezioneDto);


}
