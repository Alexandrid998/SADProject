package ZeusFit.repository;

import ZeusFit.model.Abbonamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("abbonamentoRepository")
public interface AbbonamentoRepository extends JpaRepository<Abbonamento,Long> {
}
