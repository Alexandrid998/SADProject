package ZeusFit.service;

import ZeusFit.controller.dto.PrenotazioneDto;
import ZeusFit.model.Prenotazione;
import ZeusFit.repository.LezioneRepository;
import ZeusFit.repository.PrenotazioneRepository;
import ZeusFit.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PrenotazioneServiceImpl implements  PrenotazioneService{
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private LezioneRepository lezioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public PrenotazioneServiceImpl( PrenotazioneRepository prenotazioneRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
    }

    public PrenotazioneServiceImpl() {
    }

    public Prenotazione save(PrenotazioneDto prenotazioneDto){
        Prenotazione p = new Prenotazione(prenotazioneDto.getOra(),prenotazioneDto.getStato());
        p.setLezione(lezioneRepository.findBy_Id(prenotazioneDto.getLezione()));
        p.setUtente(utenteRepository.findbyId_utente(prenotazioneDto.getUtente()));
        return prenotazioneRepository.save(p);
    }

    public boolean prenotazione_exist(long utente, long lezione){
        Prenotazione prenotazione = prenotazioneRepository.alreadyprenotato(utente,lezione);
        if(prenotazione != null)
            return true;
        else
            return false;
    }

    public ArrayList<Prenotazione> loadPrenotazionebyid(Long id){
        return prenotazioneRepository.findByIdUtente(id);
    }


}
