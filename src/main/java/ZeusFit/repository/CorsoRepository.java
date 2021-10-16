package ZeusFit.repository;

import ZeusFit.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("corsoRepository")
public interface CorsoRepository extends JpaRepository<Corso,Long> {
    Corso findByNome(String nome);

    @Query("SELECT p FROM Corso p WHERE p.id=?1 ")
    Corso findbyId(Long id);



}
