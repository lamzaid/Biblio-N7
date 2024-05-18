package fr.enseeiht.biblio.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enseeiht.biblio.entity.Author;
import fr.enseeiht.biblio.facade.AuthorFacade;

@WebServlet("/AuthorServlet")
public class AuthorServlet extends HttpServlet {
    @EJB
    private AuthorFacade authorFacade;

    private static final long serialVersionUID = 1L;

    public AuthorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if (op != null && op.equals("add")) {
            request.getRequestDispatcher("./add_author.jsp").forward(request, response);
        } else {
            throw new IllegalStateException("This error should never be triggered");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if (op != null && op.equals("add")) {
            try {
                String name = request.getParameter("name");
                Author newAuthor = new Author(name);
                authorFacade.create(newAuthor);
                response.sendRedirect("./admin_dashboard.jsp");
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else {
            doGet(request, response);
        }
    }
}
