package ZeusFit.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

        @GetMapping("/login")
        public String login() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

                return "redirect:/home-admin";
            }
            else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

                return "redirect:/homepage";
            }
            else
                return "login";
        }

        @GetMapping("/")
        public String homepage() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

                return "redirect:/home-admin";
            }
            else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

                return "redirect:/homepage";
            }
            else
                return "login";
        }


        /*
        @GetMapping("/about-us")
        public String showabout(){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

                return "about-us";
            }
            else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("UTENTE"))) {

                return "about-us";
            }
            else
                return "rediret:/login";
        }

         */


}
