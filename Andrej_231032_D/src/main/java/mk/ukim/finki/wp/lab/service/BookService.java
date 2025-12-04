package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;

import java.util.List;

public interface BookService {
    List<Book> listAll();

    // НОВ: Еден метод за сите филтри
    List<Book> find(String title, Long authorId, Long genreId, Double minRating);

    // Стари методи за компатибилност
    List<Book> searchBooks(String text, double rating);
    Book findBook(Long id);
    List<Book> findBooksByAuthorId(Long authorId);
    List<Book> findBooksByGenreId(Long genreId);
    List<Book> findBooksByAuthorIdAndGenre(Long authorId, Long genreId);

    Book add(String title, Genre genre, Double averageRating, Long authorId);
    Book update(Long id, String title, Genre genre, Double averageRating, Long authorId);
    void delete(Long id);
}