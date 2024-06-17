package de.htw.ETFootball.web;


import de.htw.ETFootball.web.API.Comment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = {"https://etfootball-frontend.onrender.com/", "http://localhost:5173/", "http://localhost:8080"})
public class Controller {

    @GetMapping("/post")
    public List<Comment> index() {
        //test
        Comment entry = new Comment(3, "Test", "Test", "Test25", null);
        Comment sentry = new Comment(2, "Tests", "Tesat", "Testd25", null); //a


        return List.of(entry, sentry);
    } //a

}

