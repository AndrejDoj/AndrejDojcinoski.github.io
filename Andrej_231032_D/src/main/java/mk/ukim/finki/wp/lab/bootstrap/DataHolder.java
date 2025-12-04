package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GenreRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    // Додади ги статичките листи за mock репозиториумите
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Genre> genres = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public DataHolder(BookRepository bookRepository,
                      GenreRepository genreRepository,
                      AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {
        // 1. Креирај автори
        if (authorRepository.count() == 0) {
            authors = List.of(
                    new Author("J.K", "Rowling", "England", "J.K Rowling biography"),
                    new Author("J.R.R", "Tolkien", "England", "J.R.R Tolkien biography"),
                    new Author("George", "Orwell", "England", "George Orwell biography"),
                    new Author("Andy", "Weir", "United States", "Andy Weir biography"),
                    new Author("Douglas", "Adams", "England", "Douglas Adams biography"),
                    new Author("Aldous", "Huxley", "England", "Aldous Huxley biography"),
                    new Author("Mary", "Shelley", "England", "Mary Shelley biography"),
                    new Author("Frank", "Herbert", "United States", "Frank Herbert biography"),
                    new Author("Suzanne", "Collins", "United States", "Suzanne Collins biography"),
                    new Author("Arthur C.", "Clarke", "England", "Arthur C. Clarke biography")
            );
            authorRepository.saveAll(authors);
            System.out.println("=== CREATED " + authors.size() + " AUTHORS ===");
        } else {
            // Ако веќе има, пополни ја листата
            authors = authorRepository.findAll();
        }

        // 2. Креирај жанрови
        if (genreRepository.count() == 0) {
            genres = List.of(
                    new Genre("Fantasy", "Genre for fantasy books"),
                    new Genre("Science Fiction", "Genre for Sci-Fi books"),
                    new Genre("Dystopian", "Genre for dystopian books"),
                    new Genre("Gothic Novel", "Genre for gothic novel books"),
                    new Genre("Novel", "Genre for novel books")
            );
            genreRepository.saveAll(genres);
            System.out.println("=== CREATED " + genres.size() + " GENRES ===");
        } else {
            genres = genreRepository.findAll();
        }

        // 3. Креирај книги со автори и жанрови
        if (bookRepository.count() == 0) {
            // Најди ги жанровите по име
            Genre fantasy = genres.stream()
                    .filter(g -> g.getName().equals("Fantasy"))
                    .findFirst()
                    .orElse(genres.get(0));

            Genre sciFi = genres.stream()
                    .filter(g -> g.getName().equals("Science Fiction"))
                    .findFirst()
                    .orElse(genres.get(0));

            Genre dystopian = genres.stream()
                    .filter(g -> g.getName().equals("Dystopian"))
                    .findFirst()
                    .orElse(genres.get(0));

            Genre gothicNovel = genres.stream()
                    .filter(g -> g.getName().equals("Gothic Novel"))
                    .findFirst()
                    .orElse(genres.get(0));

            books = List.of(
                    new Book("Harry Potter", fantasy, 5.0),
                    new Book("Lord of the Rings", fantasy, 3.2),
                    new Book("1984", dystopian, 4.5),
                    new Book("Project Hail Mary", sciFi, 4.8),
                    new Book("The Hitchhiker's Guide To The Galaxy", sciFi, 4.2),
                    new Book("Brave New World", dystopian, 3.7),
                    new Book("Frankenstein", gothicNovel, 4.6),
                    new Book("Dune", sciFi, 4.3),
                    new Book("The Hunger Games", dystopian, 4.9),
                    new Book("2001: A Space Odyssey", sciFi, 4.5)
            );

            // Додели автори на книги
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                if (i < authors.size()) {
                    book.setAuthor(authors.get(i));
                } else {
                    book.setAuthor(authors.get(i % authors.size()));
                }
            }

            bookRepository.saveAll(books);
            System.out.println("=== CREATED " + books.size() + " BOOKS WITH AUTHORS ===");

            // Провери
            List<Book> savedBooks = bookRepository.findAll();
            for (Book book : savedBooks) {
                System.out.println("Book: " + book.getTitle() +
                        ", Author: " + (book.getAuthor() != null ?
                        book.getAuthor().getName() : "NULL"));
            }
        } else {
            // Ако веќе има книги, пополни ја листата
            books = bookRepository.findAll();
        }
    }
}