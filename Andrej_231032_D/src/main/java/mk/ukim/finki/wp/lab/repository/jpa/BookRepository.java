package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaSpecificationRepository<Book, Long> {
    List<Book> findAllByAuthor_Id(Long authorId);
    List<Book> findAllByGenre_Id(Long genreId);
    List<Book> findAllByAuthor_IdAndGenre_Id(Long authorId, Long genreId);
}