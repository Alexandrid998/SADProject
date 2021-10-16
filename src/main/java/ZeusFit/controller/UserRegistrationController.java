package ZeusFit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ZeusFit.service.UserService;

import ZeusFit.controller.dto.UserRegistrationDto;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService utenteService;

    public UserRegistrationController(UserService userService) {
        super();
        this.utenteService = userService;
    }

    @ModelAttribute("utente")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("utente") UserRegistrationDto registrationDto) {

        registrationDto.setRuoli("UTENTE");

        //Verifico che la mail inserita non sia già associata ad un account
        if (utenteService.email_exists(registrationDto)) {
            return "redirect:/registration?error3";
        }
        int ok_tel=1;

        //Verifico che il numero di telefono inserito è composto solo da numeri

        for (int i = 0; i < registrationDto.getTelefono().length() && (ok_tel==1); i++) {
            if(!Character.isDigit(registrationDto.getTelefono().charAt(i))){
                ok_tel=0;
            }
        }
        if(ok_tel==0) {
            return "redirect:/registration?error2";
        }
        int ok_mail=0;
        int j=0;

        //Verifico che nella mail ci siano almeno una @ e un .

        while (j != registrationDto.getEmail().length() && registrationDto.getEmail().charAt(j) != '@') {
            j = j + 1;
        }
        if (j != registrationDto.getEmail().length()) {
            ok_mail = 1;
        }
        while(j != registrationDto.getEmail().length() && registrationDto.getEmail().charAt(j) != '.') {
            j = j + 1;
        }
        if (j == registrationDto.getEmail().length()) {
            ok_mail = 0;
        }
        if (ok_mail == 0) {
            return "redirect:/registration?error1";
        }
        int Digit=0;
        int Upper=0;
        int Lower=0;
        int Symbol=0;

        //Verifico che la password contenga almeno 1 carattere minuscolo, 1 carattere maiuscolo, 1 carattere speciale e 1 numero
        for (int i = 0; i < registrationDto.getPassword().length() && (Digit==0 || Upper==0 || Lower==0 || Symbol==0); i++) {
            if(Character.isDigit(registrationDto.getPassword().charAt(i))){
                Digit=1;
            }
            else if(Character.isUpperCase(registrationDto.getPassword().charAt(i))){
                Upper=1;
            }
            else if(Character.isLowerCase(registrationDto.getPassword().charAt(i))) {
                Lower=1;
            }
            else {
                Symbol=1;
            }
        }
        String  psw = registrationDto.getPassword();
        String cpsw = registrationDto.getPswconfirm();

        if(Digit==0 || Upper==0 || Lower==0 || Symbol==0) {
            return "redirect:/registration?error0";
        }

        //Verifico che le password inserite corrispondano

        if(!psw.equals(cpsw)) {
            return "redirect:/registration?error4";
        }
        utenteService.save(registrationDto);
        return "redirect:/login?success0";

    }
}
