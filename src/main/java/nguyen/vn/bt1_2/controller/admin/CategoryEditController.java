package nguyen.vn.bt1_2.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import nguyen.vn.bt1_2.model.Category;
import nguyen.vn.bt1_2.service.CategoryService;
import nguyen.vn.bt1_2.service.impl.CategoryServiceImpl;
import nguyen.vn.bt1_2.util.Constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(
        urlPatterns = "/admin/category/edit"
)
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class CategoryEditController
        extends HttpServlet {

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        String id =
                req.getParameter("id");

        Category category =
                cateService.get(
                        Integer.parseInt(id)
                );

        if (category == null) {

            resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );

            return;
        }

        req.setAttribute(
                "category",
                category
        );

        req.getRequestDispatcher(
                "/views/admin/edit-category.jsp"
        ).forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        int id =
                Integer.parseInt(
                        req.getParameter("id")
                );

        String name =
                req.getParameter("name");

        Category category =
                new Category();

        category.setId(id);
        category.setName(name);

        Part iconPart =
                req.getPart("icon");

        /*
         * Nếu không chọn ảnh mới:
         * icon = null.
         *
         * Service sẽ giữ lại ảnh cũ.
         */
        if (iconPart != null &&
                iconPart.getSize() > 0) {

            category.setIcon(
                    saveImage(iconPart)
            );
        }

        cateService.edit(category);

        resp.sendRedirect(
                req.getContextPath()
                        + "/admin/category/list"
        );
    }

    private String saveImage(Part part)
            throws IOException,
            ServletException {

        String originalName =
                Paths.get(
                        part.getSubmittedFileName()
                ).getFileName().toString();

        int index =
                originalName.lastIndexOf('.');

        String extension =
                index >= 0
                        ? originalName.substring(index)
                        : "";

        extension =
                extension.toLowerCase();

        if (!extension.equals(".jpg")
                && !extension.equals(".jpeg")
                && !extension.equals(".png")
                && !extension.equals(".webp")) {

            throw new ServletException(
                    "File ảnh không hợp lệ."
            );
        }

        String fileName =
                System.currentTimeMillis()
                        + extension;

        Path directory =
                Paths.get(
                        Constant.DIR,
                        "category"
                );

        Files.createDirectories(directory);

        Path destination =
                directory.resolve(fileName);

        Files.copy(
                part.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        return "category/" + fileName;
    }
}