package ZeusFit.service;

import ZeusFit.controller.dto.LezioneDto;
import ZeusFit.model.Lezione;
import ZeusFit.repository.CorsoRepository;
import ZeusFit.repository.LezioneRepository;
import javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Service
public class LezioneServiceImpl implements LezioneService{

    @Autowired
    private CorsoRepository corsoRepository;
    @Autowired
    private LezioneRepository lezioneRepository;

    public Lezione save(LezioneDto lezioneDto){
        Lezione lezione = new Lezione(lezioneDto.getGiorno(), lezioneDto.getOrario(), lezioneDto.getDurata(),lezioneDto.getSala(),lezioneDto.getNum_posti_disponibili(),lezioneDto.getCosto(),corsoRepository.findbyId(lezioneDto.getCorso()), Collections.emptyList());
        return lezioneRepository.save(lezione);
    }
    public boolean exist(LezioneDto lezioneDto){
        //if(lezioneRepository.findBySala(lezioneDto.getSala()) != null && lezioneRepository.findByGiorno(lezioneDto.getGiorno()) != null && lezioneRepository.findByOrario(lezioneDto.getOrario()) !=null){
        //Carico tutte le lezioni nella sala specificata per un determinato giorno
        ArrayList<Lezione> listlezioni = lezioneRepository.findSovrapponi(lezioneDto.getGiorno(),lezioneDto.getSala());
        if(!listlezioni.isEmpty()){
            String[] hourMin1 = lezioneDto.getOrario().split(":");
            int hour1 = Integer.parseInt(hourMin1[0]);
            int mins1 = Integer.parseInt(hourMin1[1]);
            int hoursInMins1 = hour1 * 60;
            int value1 = hoursInMins1+mins1;
            //int dur1 = lezioneDto.getDurata();
            int sum = value1+lezioneDto.getDurata();
            //Controllo se l'orario inserito si sovrappone con qualche lezione esistente
            boolean sovrappone = false;
            for(int counter =0;counter<listlezioni.size();counter++){
                String[] hourMin = listlezioni.get(counter).getOrario().split(":");
                int hour = Integer.parseInt(hourMin[0]);
                int mins = Integer.parseInt(hourMin[1]);
                int hoursInMins = hour * 60;
                int value = hoursInMins+mins;
                //int dur = listlezioni.get(counter).getDurata();
                int conf = value+listlezioni.get(counter).getDurata();

                if(value1>value && value1<conf || sum>value && sum<conf ){
                    sovrappone = true;
                }

            }
            return sovrappone;
        }
            return false;

    }

    public Lezione aggiungi_prenotazione(LezioneDto lezioneDto){
        return null;
    }

    public ArrayList<Lezione> loadLezionebyCorso(Long idcorso){
        return lezioneRepository.findByCorso(idcorso);
    }

    public  boolean is_valid(LezioneDto lezioneDto){
        boolean valid = true;
        String time = lezioneDto.getOrario();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        try {
            dateFormat.parse(time);
        }catch (ParseException p){
            valid = false;
        };
        if(lezioneDto.getDurata()<30 || lezioneDto.getDurata()>=150 || lezioneDto.getSala()<1 || lezioneDto.getSala()>5 || lezioneDto.getCosto()<=0) {
            valid = false;
        }
        return valid;

    }
}
