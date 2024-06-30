package de.htw.ETFootball.Controller;

import de.htw.ETFootball.repo.FavoriteRepository;
import de.htw.ETFootball.web.API.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = {"https://etfootball-frontend.onrender.com", "http://localhost:5173"})
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping
    public List<Favorite> getFavorites() {
        return (List<Favorite>) favoriteRepository.findAll();
    }

    @PostMapping
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
    }
}
