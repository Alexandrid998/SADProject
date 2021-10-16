package ZeusFit.service;
import ZeusFit.controller.dto.UserRegistrationDto;
import ZeusFit.model.Utente;
import ZeusFit.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;



public interface UserService extends UserDetailsService {

    Utente save(UserRegistrationDto registrationDto);
    boolean email_exists(UserRegistrationDto registrationDto);
    Utente loadUserByEmail(String email) throws UsernameNotFoundException;
    Utente loadUserById(Long id);
}
