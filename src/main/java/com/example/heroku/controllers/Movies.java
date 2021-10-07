package com.example.heroku.controllers;

import com.example.heroku.models.Movie;
import com.example.heroku.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Movies {


    @Autowired
    MovieRepository movies;




    @GetMapping("/movies")
    public Iterable<Movie> getMovies(){

        return movies.findAll();
    }


    @GetMapping("/movies/{id}")
    public Movie getMoviesById(@PathVariable Long id){
        return movies.findById(id).get();
    }

    //Adds painting
    @PostMapping("/movies")
    public Movie addMovies(@RequestBody Movie newmovie){

        return movies.save(newmovie);
    }

    //Updates painting by id
    @PatchMapping("/movies/{id}")
    public void patchMoviesById(@PathVariable Long id, @RequestBody Movie movieToUpdate){
        movies.findById(id).map(foundMovie ->{
            if(movieToUpdate.getName()!= null) foundMovie.setName(movieToUpdate.getName());
            if(movieToUpdate != null)foundMovie.setReleaseDate(movieToUpdate.getReleaseDate());
            if(movieToUpdate.getDirector()!= null)foundMovie.setDirector(movieToUpdate.getDirector());
            if(movieToUpdate.getDescription()!= null)foundMovie.setDescription(movieToUpdate.getDescription());

            movies.save(foundMovie);
            return "Movie updated";
        }).orElse("Movie not found");



    }

    @PutMapping("/movies/{id}")
    public String updateMovieById(@PathVariable Long id, @RequestBody Movie artistToUpdate){
        if (movies.existsById(id)) {
            artistToUpdate.setId(id);
            movies.save(artistToUpdate);
            return "Artist was created";
        } else {
            return "Artist not found";
        }
    }

    //Deletes painting by id
    @DeleteMapping("/movies/{id}")
    public void deleteMovieById(@PathVariable Long id){
        movies.deleteById(id);
    }
}
