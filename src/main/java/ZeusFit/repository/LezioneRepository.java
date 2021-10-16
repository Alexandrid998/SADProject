package ZeusFit.repository;

import ZeusFit.model.Lezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
@Repository("lezionerepository")
public interface LezioneRepository extends JpaRepository<Lezione, Long> {
    ArrayList<Lezione>findByGiorno(String giorno);
    ArrayList<Lezione>findByOrario(String orario);
    ArrayList<Lezione>findBySala(Integer sala);

    @Query("SELECT p FROM Lezione p WHERE p.corso.id=?1")
    ArrayList<Lezione>findByCorso(Long id_corso);

    @Query("SELECT p FROM Lezione p WHERE p.giorno=?1 AND p.sala=?2")
    ArrayList<Lezione> findSovrapponi(String giorno,Integer sala);

    @Query("SELECT p FROM Lezione p WHERE p.id=?1")
    Lezione findBy_Id(Long id);

}
