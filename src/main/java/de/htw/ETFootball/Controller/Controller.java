package de.htw.ETFootball.Controller;


import de.htw.ETFootball.web.API.Comment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = {"https://etfootball-frontend.onrender.com", "http://localhost:5173", "http://localhost:8080"})
public class Controller {

    @GetMapping("/")
    public List<Comment> index() {
        //test
        Comment entry = new Comment(5, "sdsd", "sd", "df", null);
        Comment sentry = new Comment(12, "dsf", "fds", "Testd32", null); //a


        return List.of(entry, sentry);
    } //a

}
