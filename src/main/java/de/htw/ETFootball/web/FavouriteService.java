package de.htw.ETFootball.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    // Methode, um alle Favoriten zu erhalten
    public Flux<Favourite> getAllFavourites() {
        return favouriteRepository.findAll(); // Dies sollte bereits ein Flux zurückgeben, wenn das Repository reaktiv ist
    }

    // Methode, um einen Favoriten hinzuzufügen
    public Mono<Favourite> addFavourite(Favourite favourite) {
        return favouriteRepository.save(favourite); // Dies sollte bereits ein Mono<Favourite> zurückgeben, wenn das Repository reaktiv ist
    }

    // Methode, um einen Favoriten zu entfernen
    public Mono<Void> removeFavourite(String teamName) {
        return favouriteRepository.findByTeamName(teamName)
                .flatMap(favourite -> favouriteRepository.delete(favourite))
                .then(); // Mono<Void> für API-Aufrufe, die keinen Wert zurückgeben
    }
}