package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;


// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO productParamsDTO) {
        Specification<Product> specification = Specification.where(null);
        return specification.and(withCategoryId(productParamsDTO.getCategoryId()))
                .and(withPriceGreaterThan(productParamsDTO.getPriceGt()))
                .and(withPriceLessThan(productParamsDTO.getPriceLt()))
                .and(withRatingGt(productParamsDTO.getRatingGt()))
                .and(withTitleContainsSubstring(productParamsDTO.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> categoryId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }

    private Specification<Product> withPriceGreaterThan(Integer priceGt) {
        return ((root, query, criteriaBuilder) -> priceGt == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("price"), priceGt));
    }
    private Specification<Product> withPriceLessThan(Integer priceLt) {
        return ((root, query, criteriaBuilder) -> priceLt == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.lessThan(root.get("price"), priceLt));
    }
    private Specification<Product> withRatingGt(Double ratingGt) {
        return ((root, query, criteriaBuilder) -> ratingGt == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("rating"), ratingGt));
    }
    private Specification<Product> withTitleContainsSubstring(String titleCont) {
        return ((root, query, criteriaBuilder) -> titleCont == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("title"),
                "%" + titleCont.toLowerCase() + "%"));
    }
}
// END
