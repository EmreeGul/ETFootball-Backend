package de.htw.ETFootball.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/favourites")
@CrossOrigin(origins = {"http://localhost:3000", "https://etfootball-frontend.onrender.com", "https://etfootball-backend.onrender.com"})
public class FavouritesController {

    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private FootballDataService footballDataService;

    @GetMapping
    public Flux<Favourite> getFavourites() {
        return favouriteService.getAllFavourites();
    }

    @PostMapping
    public Mono<Favourite> addFavourite(@RequestBody Favourite fav) {
        return footballDataService.getTeamData(fav.getTeamName())
                .flatMap(teamData -> {
                    System.out.println("Team Data: " + teamData);
                    return favouriteService.addFavourite(fav);
                });
    }

    @DeleteMapping("/{teamName}")
    public Mono<Void> removeFavourite(@PathVariable String teamName) {
        return favouriteService.removeFavourite(teamName);
    }
}
