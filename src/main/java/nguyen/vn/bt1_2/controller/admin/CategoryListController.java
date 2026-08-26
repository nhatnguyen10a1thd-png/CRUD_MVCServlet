package nguyen.vn.bt1_2.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nguyen.vn.bt1_2.model.Category;
import nguyen.vn.bt1_2.service.CategoryService;
import nguyen.vn.bt1_2.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = "/admin/category/list"
)
public class CategoryListController
        extends HttpServlet {

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        List<Category> cateList =
                cateService.getAll();

        req.setAttribute(
                "cateList",
                cateList
        );

        RequestDispatcher dispatcher =
                req.getRequestDispatcher(
                        "/views/admin/list-category.jsp"
                );

        dispatcher.forward(req, resp);
    }
}