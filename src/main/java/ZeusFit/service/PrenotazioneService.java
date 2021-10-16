package ZeusFit.service;

import ZeusFit.controller.dto.PrenotazioneDto;
import ZeusFit.model.Prenotazione;

import java.util.ArrayList;

public interface PrenotazioneService {
    Prenotazione save(PrenotazioneDto prenotazioneDto);
    boolean prenotazione_exist(long utente,long lezione);
    ArrayList<Prenotazione> loadPrenotazionebyid(Long id);

}
