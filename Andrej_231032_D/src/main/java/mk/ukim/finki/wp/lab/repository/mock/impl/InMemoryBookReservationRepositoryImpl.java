package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryBookReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepositoryImpl implements InMemoryBookReservationRepository {
    @Override
    public BookReservation save(BookReservation bookReservation) {
        DataHolder.reservations.add(bookReservation);
        return bookReservation;
    }
}
