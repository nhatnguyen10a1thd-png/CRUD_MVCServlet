package nguyen.vn.bt1_2.controller.admin;

import jakarta.servlet.RequestDispatcher;
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
        urlPatterns = "/admin/category/add"
)
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class CategoryAddController
        extends HttpServlet {

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        RequestDispatcher dispatcher =
                req.getRequestDispatcher(
                        "/views/admin/add-category.jsp"
                );

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String name =
                req.getParameter("name");

        if (name == null ||
                name.isBlank()) {

            req.setAttribute(
                    "error",
                    "Tên danh mục không được để trống."
            );

            req.getRequestDispatcher(
                    "/views/admin/add-category.jsp"
            ).forward(req, resp);

            return;
        }

        String icon = null;

        Part iconPart =
                req.getPart("icon");

        if (iconPart != null &&
                iconPart.getSize() > 0) {

            icon = saveImage(iconPart);
        }

        Category category =
                new Category();

        category.setName(name.trim());
        category.setIcon(icon);

        cateService.insert(category);

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
                    "Chỉ cho phép JPG, JPEG, PNG hoặc WEBP."
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