package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        return bookService.createBook(bookCreateDTO);
    }
    @PutMapping("{id}")
    public BookDTO updateBook(@RequestBody @Valid BookUpdateDTO bookUpdateDTO, @PathVariable Long id) {
        return bookService.updateBook(bookUpdateDTO, id);
    }
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    // END
}
