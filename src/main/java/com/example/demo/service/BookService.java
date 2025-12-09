package com.example.demo.service;

import com.example.demo.dto.CreateBookRequestDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book save(CreateBookRequestDto bookDto) {
        Book book = mapDtoToBook(bookDto);
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    public Book update(Long id, CreateBookRequestDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setIsbn(bookDto.getIsbn());
        existingBook.setPrice(bookDto.getPrice());
        existingBook.setDescription(bookDto.getDescription());
        existingBook.setCoverImage(bookDto.getCoverImage());

        return bookRepository.save(existingBook);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    private Book mapDtoToBook(CreateBookRequestDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());
        book.setCoverImage(bookDto.getCoverImage());
        return book;
    }
}
