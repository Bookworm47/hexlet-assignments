package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found.", id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable long id) {
        Product productInData = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found.", id)));
        productInData.setPrice(product.getPrice());
        productInData.setTitle(product.getTitle());
        return ResponseEntity.ok().body(productRepository.save(productInData));
    }

    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
