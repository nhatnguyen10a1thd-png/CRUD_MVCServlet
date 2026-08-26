package nguyen.vn.bt1_2.service.impl;

import nguyen.vn.bt1_2.dao.CategoryDao;
import nguyen.vn.bt1_2.dao.impl.CategoryDaoImpl;
import nguyen.vn.bt1_2.model.Category;
import nguyen.vn.bt1_2.service.CategoryService;
import nguyen.vn.bt1_2.util.Constant;

import java.io.File;
import java.util.List;

public class CategoryServiceImpl
        implements CategoryService {

    private final CategoryDao categoryDao =
            new CategoryDaoImpl();

    @Override
    public void insert(Category category) {

        categoryDao.insert(category);
    }

    @Override
    public void edit(Category newCategory) {

        Category oldCategory =
                categoryDao.get(
                        newCategory.getId()
                );

        if (oldCategory == null) {
            return;
        }

        oldCategory.setName(
                newCategory.getName()
        );

        /*
         * Nếu người dùng chọn ảnh mới
         * thì xóa ảnh cũ.
         */
        if (newCategory.getIcon() != null) {

            deleteImage(
                    oldCategory.getIcon()
            );

            oldCategory.setIcon(
                    newCategory.getIcon()
            );
        }

        categoryDao.edit(oldCategory);
    }

    @Override
    public void delete(int id) {

        Category oldCategory =
                categoryDao.get(id);

        categoryDao.delete(id);

        if (oldCategory != null) {
            deleteImage(
                    oldCategory.getIcon()
            );
        }
    }

    @Override
    public Category get(int id) {

        return categoryDao.get(id);
    }

    @Override
    public Category get(String name) {

        return categoryDao.get(name);
    }

    @Override
    public List<Category> getAll() {

        return categoryDao.getAll();
    }

    @Override
    public List<Category> search(
            String keyword
    ) {

        return categoryDao.search(keyword);
    }

    private void deleteImage(String imagePath) {

        if (imagePath == null ||
                imagePath.isBlank()) {
            return;
        }

        File file =
                new File(
                        Constant.DIR,
                        imagePath
                );

        if (file.exists()) {
            file.delete();
        }
    }
}