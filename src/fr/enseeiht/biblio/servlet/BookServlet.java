package fr.enseeiht.biblio.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enseeiht.biblio.entity.Book;
import fr.enseeiht.biblio.entity.Author;
import fr.enseeiht.biblio.entity.Reservation;
import fr.enseeiht.biblio.facade.BookFacade;
import fr.enseeiht.biblio.facade.AuthorFacade;
import fr.enseeiht.biblio.facade.ReservationFacade;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    @EJB
    private BookFacade bookFacade;
    
    @EJB
    private AuthorFacade authorFacade;
    
    @EJB
    private ReservationFacade reservationFacade;

    private static final long serialVersionUID = 1L;

    public BookServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if(op != null) {
            if(op.equals("list")) {
                List<Book> books = bookFacade.findAll();
                List<Reservation> reservations = reservationFacade.findAll();
                request.setAttribute("books", books);
                request.setAttribute("reservations", reservations);
                request.getRequestDispatcher("./list_books_admin.jsp").forward(request, response);
            } else if(op.equals("add")) {
                List<Author> authors = authorFacade.findAll();
                request.setAttribute("authors", authors);
                request.getRequestDispatcher("./add_book.jsp").forward(request, response);
            } else if(op.equals("delete")) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                bookFacade.deleteBook(bookId);
                response.sendRedirect("./BookServlet?op=list");
            } else if(op.equals("return")) {
                int reservationId = Integer.parseInt(request.getParameter("reservationId"));
                reservationFacade.returnBook(reservationId);
                response.sendRedirect("./BookServlet?op=list");
            }
        } else {
            throw new IllegalStateException("This error should never be triggered");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if(op != null && op.equals("add")) {
            String title = request.getParameter("title");
            int publicationYear = Integer.parseInt(request.getParameter("publication_year"));
            int authorId = Integer.parseInt(request.getParameter("author_id"));
            Author author = authorFacade.find(authorId);

            Book newBook = new Book(title, publicationYear, author);
            bookFacade.create(newBook);

            response.sendRedirect("./BookServlet?op=list");
        } else {
            doGet(request, response);
        }
    }
}
