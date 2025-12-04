package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.lab.service.specification.FieldFilterSpecification.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> find(String title, Long authorId, Long genreId, Double minRating) {
        Specification<Book> specification = Specification.allOf(
                filterContainsText("title", title),
                filterEquals("author.id", authorId),
                filterEquals("genre.id", genreId),
                filterGreaterThanOrEqual("averageRating", minRating)
        );

        return bookRepository.findAll(specification);
    }

    @Override
    public List<Book> searchBooks(String text, double rating) {
      
        return find(text, null, null, rating);
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findBooksByAuthorId(Long authorId) {

        return find(null, authorId, null, null);
    }

    @Override
    public List<Book> findBooksByGenreId(Long genreId) {

        return find(null, null, genreId, null);
    }

    @Override
    public List<Book> findBooksByAuthorIdAndGenre(Long authorId, Long genreId) {

        return find(null, authorId, genreId, null);
    }

    @Override
    public Book add(String title, Genre genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = new Book(title, genre, averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, Genre genre, Double averageRating, Long authorId) {
        Book book = findBook(id);
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}