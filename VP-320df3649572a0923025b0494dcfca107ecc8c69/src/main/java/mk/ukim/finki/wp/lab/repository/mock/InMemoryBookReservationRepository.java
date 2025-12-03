package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.BookReservation;

public interface InMemoryBookReservationRepository {
    BookReservation save(BookReservation bookReservation);
}
