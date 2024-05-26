package fr.enseeiht.biblio.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enseeiht.biblio.entity.Book;
import fr.enseeiht.biblio.entity.Author;
import fr.enseeiht.biblio.entity.Exemplaire;
import fr.enseeiht.biblio.facade.BookFacade;
import fr.enseeiht.biblio.facade.AuthorFacade;
import fr.enseeiht.biblio.facade.ExemplaireFacade;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    @EJB
    private BookFacade bookFacade;
    
    @EJB
    private AuthorFacade authorFacade;
    
    @EJB
    private ExemplaireFacade exemplaireFacade;

    private static final long serialVersionUID = 1L;

    public BookServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if (op != null) {
            if (op.equals("list")) {
                List<Book> books = bookFacade.findAll();
                request.setAttribute("books", books);
                request.getRequestDispatcher("./list_books_admin.jsp").forward(request, response);
            } else if (op.equals("add")) {
                List<Author> authors = authorFacade.findAll();
                request.setAttribute("authors", authors);
                request.getRequestDispatcher("./add_book.jsp").forward(request, response);
            } else if (op.equals("delete")) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                bookFacade.deleteBook(bookId);
                response.sendRedirect("./BookServlet?op=list");
            } else if (op.equals("view_exemplaires")) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                Book book = bookFacade.find(bookId);
                List<Exemplaire> exemplaires = exemplaireFacade.findByBook(bookId);
                request.setAttribute("book", book);
                request.setAttribute("exemplaires", exemplaires);
                request.getRequestDispatcher("./view_exemplaires.jsp").forward(request, response);
            }
        } else {
            throw new IllegalStateException("This error should never be triggered");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if (op != null && op.equals("add")) {
            String title = request.getParameter("title");
            int publicationYear = Integer.parseInt(request.getParameter("publication_year"));
            int authorId = Integer.parseInt(request.getParameter("author_id"));
            int copies = Integer.parseInt(request.getParameter("copies"));
            Author author = authorFacade.find(authorId);

            Book newBook = new Book(title, publicationYear, author);
            bookFacade.create(newBook);

            List<Exemplaire> exemplaires = new ArrayList<>();
            for (int i = 0; i < copies; i++) {
                Exemplaire exemplaire = new Exemplaire(newBook);
                exemplaires.add(exemplaire);
                exemplaireFacade.create(exemplaire);
            }

            newBook.setExemplaires(exemplaires);
            bookFacade.update(newBook);

            response.sendRedirect("./BookServlet?op=list");
        } else {
            doGet(request, response);
        }
    }
}
