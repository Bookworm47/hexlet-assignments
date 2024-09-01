package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        var sql = "INSERT INTO products (title, price) VALUES(?, ?)";
        try (var conn = dataSource.getConnection()) {
            var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        var sql = "SELECT * FROM products WHERE id = ?";
        try (var conn = dataSource.getConnection()) {
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            var result = preparedStatement.executeQuery();
            if (result.next()) {
                var title = result.getString("title");
                var price = result.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public static List<Product> getEntities()throws SQLException {
        var sql = "SELECT * FROM products";
        try (var conn = dataSource.getConnection()) {
            var preparedStatement = conn.prepareStatement(sql);
            var result = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            if (result.next()) {
                var id = result.getLong("id");
                var title = result.getString("title");
                var price = result.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }
    // END
}
