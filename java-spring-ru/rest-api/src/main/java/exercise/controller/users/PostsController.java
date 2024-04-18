package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;

// BEGIN
@RestController
@RequestMapping("/api/users/")
class PostsController {

    List<Post> postsList = new ArrayList<>();

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable int id) {
        return ResponseEntity.ok().body(postsList);
    }

    @PostMapping("{id}/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable int id) {
        var newPost = new Post();
        newPost.setSlug(post.getSlug());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setUserId(id);
        postsList.add(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }
}

// END
