package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostsController {

    PostRepository postRepository;
    CommentRepository commentRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPosts() throws Exception {
        var posts = postRepository.findAll();
        return posts.stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPost(@PathVariable long id) throws Exception {
        var postDTO = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %d not found", id)));
        return this.toDTO(postDTO);
    }

    private PostDTO toDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(commentToDTO(commentRepository.findByPostId(post.getId())));
        return dto;
    }

    private List<CommentDTO> commentToDTO(List<Comment> comments) {
        List<CommentDTO> resultComments = new ArrayList<>();
        for (Comment comment : comments) {
            var commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setBody(comment.getBody());
            resultComments.add(commentDTO);
        }
        return resultComments;
    }
}
// END
