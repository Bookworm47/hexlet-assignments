package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.AuthorNotFoundForBookException;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::map).toList();
    }

    public BookDTO getBookById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookMapper.map(book);
    }

    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        var author = authorRepository.findById(bookCreateDTO.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundForBookException("Author not found"));
        Book book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO updateBook(BookUpdateDTO bookUpdateDTO, Long id) {
        try {
            var author = authorRepository.findById(bookUpdateDTO.getAuthorId().get());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Author not found");
        }
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookMapper.update(bookUpdateDTO, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
