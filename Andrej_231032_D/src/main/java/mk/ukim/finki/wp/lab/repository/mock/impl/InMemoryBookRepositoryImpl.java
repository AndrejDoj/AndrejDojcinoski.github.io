package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryBookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookRepositoryImpl implements InMemoryBookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, double rating) {
        return DataHolder.books.stream().filter(book -> book.getTitle().toLowerCase().contains(text.toLowerCase()) && book.getAverageRating() >= rating).toList();
    }

    @Override
    public List<Book> findAllByAuthor_Id(Long authorId) {
        return DataHolder.books.stream()
                .filter(book -> book.getAuthor() != null && book.getAuthor().getId().equals(authorId))
                .toList();
    }

    @Override
    public Book findBook(Long id) {
        return DataHolder.books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Book add(String title, Genre genre, Double averageRating, Author author) {
        Book book = new Book(title, genre, averageRating);
        book.setAuthor(author);
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public Book update(Long id, String title, Genre genre, Double averageRating, Author author) {
        int idx = IntStream.range(0, DataHolder.books.size())
                .filter(i -> DataHolder.books.get(i).getId().equals(id))
                .findFirst()
                .orElse(-1);

        if(idx == -1)
            return null;

        Book book = new Book(title, genre, averageRating);
        book.setAuthor(author);
        DataHolder.books.set(idx, book);
        return book;
    }

    @Override
    public void delete(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }
}
