package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre findById(Long id);
}
