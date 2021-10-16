package ZeusFit.service;

import ZeusFit.controller.dto.CorsoDto;
import ZeusFit.model.Corso;
import ZeusFit.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CorsoServiceImpl implements CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    public CorsoServiceImpl(CorsoRepository corsoRepository) {
        this.corsoRepository = corsoRepository;
    }

    public CorsoServiceImpl() {
    }

    public Corso save(CorsoDto corsoDto){
        Corso corso = new Corso(corsoDto.getNome(),corsoDto.getDescrizione(),corsoDto.getUrlimm(), Collections.emptyList(),Collections.emptyList());
        return corsoRepository.save(corso);
    }

    public Corso aggiorna(CorsoDto corsoDto){
        Corso corso = corsoRepository.findbyId(corsoDto.getId());
        corso.setNome(corsoDto.getNome());
        corso.setUrlimm(corsoDto.getUrlimm());
        corso.setDescrizione(corsoDto.getDescrizione());
        return corsoRepository.save(corso);
    }

    public boolean corso_exist(CorsoDto corsoDto){
        if (corsoRepository.findByNome(corsoDto.getNome()) != null){
            return true;
        }
        return false;
    }

    public Corso aggiugi_lezione(CorsoDto corsoDto){
        return null;
    }

    @Override
    public Corso loadCorsobyid(Long id){
        return corsoRepository.findbyId(id);
    }

    @Override
    public Corso loadCorsobynome(String nome){
        Corso corso = corsoRepository.findByNome(nome);

        if(corso == null){
            throw new UsernameNotFoundException("Invalid name corso");
        }
        return corso;
    }
}
