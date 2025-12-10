package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookRequestDto;
import com.example.demo.mapper.BookMapper; // <-- Новый импорт
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.toDto(book);
    }

    public BookDto update(Long id, CreateBookRequestDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        bookMapper.updateBook(bookDto, existingBook);
        return bookMapper.toDto(bookRepository.save(existingBook));
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
