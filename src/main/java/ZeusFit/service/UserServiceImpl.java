package ZeusFit.service;

import ZeusFit.controller.dto.UserRegistrationDto;
import ZeusFit.model.Ruolo;
import ZeusFit.model.Utente;
import ZeusFit.repository.RuoloRepository;
import ZeusFit.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UtenteRepository utenteRepository) {
        super();
        this.utenteRepository = utenteRepository;
    }

    public UserServiceImpl() {
    }

    public Utente save(UserRegistrationDto registrationDto) {
        Utente user = new Utente(registrationDto.getCf(), registrationDto.getNome(), registrationDto.getCognome(), registrationDto.getData_nascita(), registrationDto.getGenere(), registrationDto.getEmail(),registrationDto.getIndirizzo(),registrationDto.getTelefono(), passwordEncoder.encode(registrationDto.getPassword()), 0, Arrays.asList(ruoloRepository.findByNome(registrationDto.getRuolo())),registrationDto.getRuolo());
        return utenteRepository.save(user);
    }

    public boolean email_exists(UserRegistrationDto registrationDto) {
        if (utenteRepository.findByEmail(registrationDto.getEmail()) != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("ciao" + email);
        Utente user = utenteRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRuoli()));

    }

    @Override
    public Utente loadUserByEmail(String email) throws UsernameNotFoundException {

        Utente user = utenteRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return user;
    }

    @Override
    public Utente loadUserById(Long id) {
        return utenteRepository.findbyId_utente(id);
    }



    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Ruolo> ruoli){
        return ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome())).collect(Collectors.toList());
    }
}
