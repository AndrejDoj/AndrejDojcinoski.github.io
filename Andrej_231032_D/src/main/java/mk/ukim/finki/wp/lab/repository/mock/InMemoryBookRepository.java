package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;

import java.util.List;

public interface InMemoryBookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, double rating);
    List<Book> findAllByAuthor_Id(Long authorId);

    Book findBook(Long id);
    Book add(String title, Genre genre, Double averageRating, Author author);
    Book update(Long id, String title, Genre genre, Double averageRating, Author author);
    void delete(Long id);
}
