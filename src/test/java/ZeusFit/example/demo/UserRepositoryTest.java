package ZeusFit.example.demo;

import ZeusFit.model.*;
import ZeusFit.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.HashSet;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UtenteRepository repo;

    @Autowired
    private CorsoRepository repoc;

    @Autowired
    private AbbonamentoRepository repoa;

    @Autowired
    private PrenotazioneRepository repop;

    @Autowired
    private RuoloRepository repor;

    @Autowired
    private LezioneRepository repol;

    @Autowired
    private TestEntityManager entityManager;



   //Obiettivo del Test: Aggiungere correttamente un utente, con annesso abbonamento e prenotazione, ed il corso a cui egli è abbonato al database
   @Test
   public void testCreateUser(){
       //Pre condizioni: Non ci sono informazioni riguardo l'utente e il corso inseriti all'interno del database.
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       Ruolo admin = new Ruolo("ADMIN");
       Ruolo utente = new Ruolo("UTENTE");

       Collection collection = new HashSet();
       collection.add(utente);

       Collection collection1 = new HashSet();
       collection1.add(admin);
       collection1.add(utente);

       Utente user = new Utente();
       user.setCf("SNTSVT98P21E79U1");
       user.setNome("Salvatore");
       user.setCognome("Santella");
       user.setEmail("zeus2115@gmail.com");
       user.setGenere("M");
       user.setData_nascita("21-09-1998");
       user.setIndirizzo("Via ponte dei cani 58");
       user.setTelefono("3314027048");
       user.setSaldo(100);
       user.setPassword(passwordEncoder.encode("Wakamagia98*"));
       user.setRuoli(collection);
       user.setRuolo("UTENTE");

       Utente user2 = new Utente();
       user2.setCf("TSTALS98I07T839K");
       user2.setNome("Alessandro");
       user2.setCognome("Testa");
       user2.setEmail("ale.testa@studenti.unina.it");
       user2.setGenere("M");
       user2.setData_nascita("19-07-1998");
       user2.setIndirizzo("Via magica 58");
       user2.setTelefono("3349402852");
       user2.setSaldo(100);
       user2.setPassword(passwordEncoder.encode("Wakamagia98*"));
       user2.setRuoli(collection);
       user2.setRuolo("UTENTE");

       Utente user3 = new Utente();
       user3.setCf("FGGALS98P06E789U");
       user3.setNome("Alessio");
       user3.setCognome("Foggia");
       user3.setEmail("ale.foggia@studenti.unina.it");
       user3.setGenere("M");
       user3.setData_nascita("15-12-1998");
       user3.setIndirizzo("Via ponte dei cani 58");
       user3.setTelefono("3398303785");
       user3.setSaldo(100);
       user3.setPassword(passwordEncoder.encode("Wakamagia98*"));
       user3.setRuoli(collection);
       user3.setRuolo("UTENTE");

       Utente user1 = new Utente();
       user1.setCf("AMMINISTRATORE");
       user1.setNome("AMMINISTRATORE");
       user1.setCognome("AMMINISTRATORE");
       user1.setEmail("admin@admin.admin");
       user1.setGenere("N");
       user1.setData_nascita("AMMINISTRATORE");
       user1.setIndirizzo("AMMINISTRATORE");
       user1.setTelefono("AMMINISTRATORE");
       user1.setSaldo(9999999);
       user1.setPassword(passwordEncoder.encode("admin"));
       user1.setRuoli(collection1);
       user1.setRuolo("ADMIN");


       Corso PL = new Corso();
       PL.setNome("Powerlifting");
       PL.setDescrizione("Corso in cui vi saranno insegnate le tre alzate di forza del powelifting: Squat, Panca e Stacco");
       PL.setUrlimm("pl.jpeg");

       Corso ZUMBA = new Corso();
       ZUMBA.setNome("Zumba");
       ZUMBA.setDescrizione("Corso in cui vi divertirete a ritmo di musica");
       ZUMBA.setUrlimm("zumba.jpeg");

       Corso Krav = new Corso();
       Krav.setNome("Krav Magia");
       Krav.setDescrizione("Corso in cui imparerete a difendervi dagli attacchi utilizzando la Krav Magia");
       Krav.setUrlimm("krav.jpeg");



       Lezione PL1 = new Lezione();
       PL1.setCorso(PL);
       PL1.setDurata(120);
       PL1.setGiorno("Lunedì");
       PL1.setOrario("17:30");
       PL1.setSala(3);
       PL1.setNum_posti_disponibili(10);
       PL1.setCosto(10);

       Lezione PL2 = new Lezione();
       PL2.setCorso(PL);
       PL2.setDurata(120);
       PL2.setGiorno("Mercoledì");
       PL2.setOrario("17:30");
       PL2.setSala(3);
       PL2.setNum_posti_disponibili(10);
       PL2.setCosto(10);

       Lezione PL3 = new Lezione();
       PL3.setCorso(PL);
       PL3.setDurata(120);
       PL3.setGiorno("Venerdì");
       PL3.setOrario("17:30");
       PL3.setSala(3);
       PL3.setNum_posti_disponibili(10);
       PL3.setCosto(10);

       Lezione ZU1 = new Lezione();
       ZU1.setCorso(ZUMBA);
       ZU1.setDurata(90);
       ZU1.setGiorno("Martedì");
       ZU1.setOrario("11:30");
       ZU1.setSala(1);
       ZU1.setNum_posti_disponibili(20);
       ZU1.setCosto(10);

       Lezione ZU2 = new Lezione();
       ZU2.setCorso(ZUMBA);
       ZU2.setDurata(90);
       ZU2.setGiorno("Giovedì");
       ZU2.setOrario("11:30");
       ZU2.setSala(1);
       ZU2.setNum_posti_disponibili(20);
       ZU2.setCosto(10);

       Lezione KM1 = new Lezione();
       KM1.setCorso(Krav);
       KM1.setDurata(90);
       KM1.setGiorno("Lunedì");
       KM1.setOrario("19:30");
       KM1.setSala(2);
       KM1.setNum_posti_disponibili(15);
       KM1.setCosto(10);

       Lezione KM2 = new Lezione();
       KM2.setCorso(Krav);
       KM2.setDurata(90);
       KM2.setGiorno("Giovedì");
       KM2.setOrario("19:30");
       KM2.setSala(2);
       KM2.setNum_posti_disponibili(15);
       KM2.setCosto(10);

       Prenotazione P1 = new Prenotazione();
       P1.setUtente(user);
       P1.setOra("14:00:00");
       P1.setStato("valido");
       P1.setLezione(PL1);



       Abbonamento A1 = new Abbonamento();
       A1.setCorso(PL);
       A1.setUtente(user);
       A1.setDurata("mensile");
       A1.setStato("valido");
       A1.setData(new Date(121,9,22));


       Ruolo savedRuolo = repor.save(admin);
       Ruolo savedRuolo1 = repor.save(utente);
       Utente savedAdmin = repo.save(user1);
       Utente savedUser = repo.save(user);
       Utente saved1 = repo.save(user2);
       Utente saved2 = repo.save(user3);
       Corso savedCorso = repoc.save(PL);
       Corso savedCorso2 = repoc.save(ZUMBA);
       Corso savedCorso3 = repoc.save(Krav);
       Abbonamento savedAbbonamento = repoa.save(A1);
       Lezione savedLezione = repol.save(PL1);
       Lezione save1 = repol.save(PL2);
       Lezione save2 = repol.save(PL3);
       Lezione save3 = repol.save(ZU1);
       Lezione save4 = repol.save(ZU2);
       Lezione save5 = repol.save(KM1);
       Lezione save6 = repol.save(KM2);
       Prenotazione savedPrenotazione = repop.save(P1);


      Ruolo existRuolo = entityManager.find(Ruolo.class,savedRuolo.getId());
      Ruolo existRuolo1 = entityManager.find(Ruolo.class,savedRuolo1.getId());
      Utente existAdmin = entityManager.find(Utente.class,savedAdmin.getId());
      Utente existUser = entityManager.find(Utente.class,savedUser.getId());
      Corso existCorso = entityManager.find(Corso.class,savedCorso.getId());
      Abbonamento existAbbonamento = entityManager.find(Abbonamento.class,savedAbbonamento.getId());
      Prenotazione existPrenotazione = entityManager.find(Prenotazione.class,savedPrenotazione.getId());
      Lezione existLezione = entityManager.find(Lezione.class,savedLezione.getId());


      assert(existRuolo.getNome().equals(admin.getNome()));
      assert(existRuolo1.getNome().equals(utente.getNome()));
      assert(existAdmin.getId().equals(admin.getId()));
      assert(existUser.getEmail()).equals(user.getEmail());
      assert(existAbbonamento.getId().equals(A1.getId()));
      assert(existCorso.getId().equals(PL.getId()));
      assert(existPrenotazione.getUtente().equals(P1.getUtente()));
      assert(existLezione.getId().equals(PL1.getId()));





   }
}
