package nguyen.vn.bt1_2.dao.impl;

import nguyen.vn.bt1_2.connection.DBConnection;
import nguyen.vn.bt1_2.dao.CategoryDao;
import nguyen.vn.bt1_2.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends DBConnection implements CategoryDao {

    @Override
    public void insert(Category category) {

        String sql =
                "INSERT INTO Category(cate_name, icons) VALUES (?, ?)";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getIcon());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(Category category) {

        String sql =
                "UPDATE Category "
                        + "SET cate_name = ?, icons = ? "
                        + "WHERE cate_id = ?";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

        String sql =
                "DELETE FROM Category WHERE cate_id = ?";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category get(int id) {

        String sql =
                "SELECT * FROM Category WHERE cate_id = ?";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapCategory(rs);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Category get(String name) {

        String sql =
                "SELECT TOP 1 * FROM Category "
                        + "WHERE cate_name = ?";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapCategory(rs);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Category> getAll() {

        List<Category> categories =
                new ArrayList<>();

        String sql =
                "SELECT * FROM Category "
                        + "ORDER BY cate_id ASC";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                categories.add(
                        mapCategory(rs)
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public List<Category> search(String keyword) {

        List<Category> categories =
                new ArrayList<>();

        String sql =
                "SELECT * FROM Category "
                        + "WHERE cate_name LIKE ? "
                        + "ORDER BY cate_id ASC";

        try (
                Connection conn = super.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + keyword + "%"
            );

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    categories.add(
                            mapCategory(rs)
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    private Category mapCategory(ResultSet rs)
            throws SQLException {

        Category category = new Category();

        category.setId(
                rs.getInt("cate_id")
        );

        category.setName(
                rs.getString("cate_name")
        );

        category.setIcon(
                rs.getString("icons")
        );

        return category;
    }
}