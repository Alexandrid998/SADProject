package ZeusFit.controller;

import ZeusFit.controller.dto.CorsoDto;
import ZeusFit.controller.dto.CorsoLezioneDto;
import ZeusFit.controller.dto.LezioneDto;
import ZeusFit.model.Corso;
import ZeusFit.model.Lezione;
import ZeusFit.model.Prenotazione;
import ZeusFit.model.Utente;
import ZeusFit.repository.CorsoRepository;
import ZeusFit.repository.LezioneRepository;
import ZeusFit.repository.PrenotazioneRepository;
import ZeusFit.repository.UtenteRepository;
import ZeusFit.service.CorsoService;
import ZeusFit.service.LezioneService;
import ZeusFit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/home-admin")
public class AdminHomepageController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    LezioneRepository lezioneRepository;

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private LezioneService lezioneService;

    public AdminHomepageController() {
        super();
    }

    public AdminHomepageController(UtenteRepository utenteRepository, CorsoRepository corsoRepository, LezioneRepository lezioneRepository, UserService userService, CorsoService corsoService, LezioneService lezioneService) {
        super();
        this.utenteRepository = utenteRepository;
        this.corsoRepository = corsoRepository;
        this.lezioneRepository = lezioneRepository;
        this.userService = userService;
        this.corsoService = corsoService;
        this.lezioneService = lezioneService;
    }
    //Model Attribute
    @ModelAttribute("corso")
    public CorsoDto corsoDto() {
        return new CorsoDto();
    }

    @ModelAttribute("corsolezione")
    public CorsoLezioneDto corsoLezioneDto(){
        return new CorsoLezioneDto();
    }

    @ModelAttribute("lezione")
    public LezioneDto lezioneDto(){
        return new LezioneDto();
    }


    //Caricamento Homepage
    @GetMapping
    public String home_admin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {


            //Resituisce la lista dei corsi e le relative liste di lezioni
            //Basterebbe solo la lista dei corsi per come abbiamo implementato il resto
            ArrayList<Corso> listaCorsi = (ArrayList<Corso>) corsoRepository.findAll();

            ArrayList<ArrayList<Lezione>> lista_suprema = new ArrayList<ArrayList<Lezione>>();

            for(int i=0;i<listaCorsi.size();i++){
                lista_suprema.add(lezioneRepository.findByCorso(listaCorsi.get(i).getId()));
            }

            Listissima listissima = new Listissima(listaCorsi,lista_suprema);
            model.addAttribute("listCorsi", listissima);

            return "home-admin";
        } else
            return "redirect:/login";
    }

    //CORSI

    //Inserimento Nuovo Corso
    @GetMapping("/inserisci-corso")
    public String showaddcorso(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return "inserisci-corso";
        }
        else return "redirect:/login";
    }


    @PostMapping("/inserisci-corso")
    public String aggiungicorso(@ModelAttribute("corsolezione") CorsoLezioneDto corsoLezioneDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            //Faccio così perchè altrimenti dava problemi con i campi del dto
            String nom = corsoLezioneDto.getNome();
            String des = corsoLezioneDto.getDescrizione();
            String url = corsoLezioneDto.getUrlimm();
            String giorno = corsoLezioneDto.getGiorno();
            String orario = corsoLezioneDto.getOrario();
            Integer durata = corsoLezioneDto.getDurata();
            Integer sala = corsoLezioneDto.getSala();
            Integer num = corsoLezioneDto.getNum_posti_disponibili();
            float costo = corsoLezioneDto.getCosto();

            CorsoDto corsoDto = new CorsoDto(nom,des,url);

            //Se gia esiste un corso con questo nome ritorno alla pagina con un errore, altrimenti creo il nuovo corso
          if(corsoService.corso_exist(corsoDto)){
              return "redirect:/home-admin/inserisci-corso?error3";
          }

          //Creo la lezione per il corso appena inserito e verifico che non ci siano lezioni di altri corsi
          //coincidenti con quella inserita, se ci sono ritorno alla pagine con un errore, altrimenti creo la nuova lezione

          //Long idcorso = corsoRepository.findByNome(corsoDto.getNome()).getId();
          LezioneDto lezioneDto = new LezioneDto(giorno,orario,durata,sala,num,costo,(long) 0);
          if(lezioneService.exist(lezioneDto)){
              return "redirect:/home-admin/inserisci-corso?error2";
          }
          else if(!lezioneService.is_valid(lezioneDto)) {
              return "redirect:/home-admin/inserisci-corso?error1";
          }
          else
              corsoService.save(corsoDto);
              lezioneDto.setCorso(corsoRepository.findByNome(corsoDto.getNome()).getId());
              lezioneService.save(lezioneDto);

          //Ritorno alla pagine di inserimento corso con una notifica di corretto inserimento
          return "redirect:/home-admin/inserisci-corso?success0";
        }
        else
            return "redirect:/login";
    }


    //Modifica di un corso esistente
    @GetMapping("modifica-corso/{id}")
    public String showmodificacorso(@PathVariable (value="id") long id,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            //Cerco il corso da modificare e lo carico insieme alle sue lezioni
            Corso corso = corsoRepository.findbyId(id);
            ArrayList<Lezione> lezioni = lezioneRepository.findByCorso(id);

            model.addAttribute("corso",corso);
            model.addAttribute("lezioni",lezioni);

            return "modifica-corso";
        }
        else return "redirect:/login";
    }

    @PostMapping("modifica-corso")
    public String modificacorso(@ModelAttribute("corso")CorsoDto corsoDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            Corso corso = corsoRepository.findbyId(corsoDto.getId());
            //Controllo di aver modificato almeno uno dei tre campi del corso (nome,descrizione,urlimm) prima di aggiornarlo
            if(corso.getNome().equals(corsoDto.getNome()) & corso.getDescrizione().equals(corsoDto.getDescrizione()) & corso.getUrlimm().equals(corsoDto.getUrlimm())){
                return "redirect:/home-admin/modifica-corso/"+corsoDto.getId()+"?error3";
            }

            corsoService.aggiorna(corsoDto);
            return "redirect:/home-admin?success0";
        }
        return "redirect:/login";

    }





    @PostMapping("/aggiungi-lezione/{id}")
    public String addlesson1(@ModelAttribute("lezione")LezioneDto lezioneDto,@PathVariable (value="id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            if(lezioneService.exist(lezioneDto)){
                return "redirect:/home-admin/modifica-corso/"+id+"?error2";
            }
            else if(!lezioneService.is_valid(lezioneDto)) {
                return "redirect:/home-admin/modifica-corso/" + id + "?error3";
            }
            else
                lezioneService.save(lezioneDto);
                return "redirect:/home-admin/modifica-corso/"+id+"?success0";
        }
        return "redirect:/login";
    }




    //Eliminazione di un corso esistente
    @GetMapping("/elimina-corso/{id}")
    public String deletecorso(@PathVariable (value="id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){
            //Cerco il corso da eliminare per id, passato dalla pagina web tramite click e lo elimino
            Corso corso = corsoRepository.findbyId(id);
            corsoRepository.delete(corso);

            return  "redirect:/home-admin?success1";
        }
            return "redorect:/login";
    }


    //LEZIONI

    //Aggiunta di una lezione ad un corso esistente o appena creato
    @GetMapping("/aggiungi-lezione/{id}")
    public String addlesson(@ModelAttribute("lezione")LezioneDto lezioneDto,@PathVariable (value="id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            //Controllo se esiste già una lezione che coincide per orario giorno e sala con quella inserita
            if(lezioneService.exist(lezioneDto)){
                return "redirect:/home-admin/modifica-corso/"+id+"?error0";
            }

            else
                lezioneService.save(lezioneDto);
        }
        return "redirect:/home-admin/modifica-corso/"+id+"?succes0";

    }

    //Eliminazione di una lezione di un corso esistente
    @GetMapping("/elimina-lezione/{id}/{idcorso}")
    public String deletelezione(@PathVariable (value="id") long id,@PathVariable (value="idcorso") long idcorso){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){
            //Cerco la lezione da eliminare per id, e ritorno alla pagina di visualizzazione lezioni del corso in questione
            Lezione lezione = lezioneRepository.findBy_Id(id);
            ArrayList<Prenotazione> prenotazioni = prenotazioneRepository.findByLezione(lezione.getId());
            for(int i =0;i<prenotazioni.size();i++){
                Utente temp = prenotazioni.get(i).getUtente();
                float saldo = temp.getSaldo();
                temp.setSaldo(saldo+lezione.getCosto());
                utenteRepository.save(temp);
            }
            lezioneRepository.delete(lezione);

            return  "redirect:/home-admin/visualizza-lezioni/"+idcorso+"?succes0";
        }
        return "redirect:/login";
    }

    //Visualizzazione di tutte le lezioni relative ad un corso esistente
    @GetMapping("/visualizza-lezioni/{id}")
    public String showlessons(@PathVariable (value = "id") long id,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            //Visualizzo l'elenco delle leziioni relative ad un determinato corso, da questa pagina possono eliminare lezioni o modificare il corso
            Corso corso = corsoRepository.findbyId(id);
            ArrayList<Lezione> lezioni = lezioneRepository.findByCorso(corso.getId());

            model.addAttribute("corso",corso);
            model.addAttribute("lezioni",lezioni);
            model.addAttribute("id", id);

            return "visualizza-lezioni";

        } else
            return "redirect:/login";
    }


    //ABBONAMENTI

    @GetMapping("/gestisci-abbonamenti")
    public String showabbonamenti(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){
            // DA IMPLEMENTARE IN FUTURO
            return "gestisci-abbonamenti";
        }
        return "redirect:/login";
    }



}
