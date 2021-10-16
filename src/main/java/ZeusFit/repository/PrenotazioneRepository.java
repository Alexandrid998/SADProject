package ZeusFit.repository;

import ZeusFit.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("prenotazioneRepository")
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    @Query("SELECT p FROM Prenotazione p WHERE p.utente.id=?1")
    ArrayList<Prenotazione> findByIdUtente(Long id);

    @Query("SELECT p FROM Prenotazione p WHERE p.utente.id=?1 and p.lezione.id=?2")
    Prenotazione alreadyprenotato(long utente, long lezione);

    @Query("SELECT p FROM Prenotazione  p WHERE p.lezione.id=?1")
    ArrayList<Prenotazione> findByLezione(long id);
}
