package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return authorMapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO authorCreateDTO) {
        var author = authorMapper.map(authorCreateDTO);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO authorUpdateDTO, Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        authorMapper.update(authorUpdateDTO, author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
