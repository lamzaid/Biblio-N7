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
import fr.enseeiht.biblio.facade.BookFacade;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	
	@EJB
    private BookFacade bookFacade;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if(op != null) {
			if(op.equals("list")) {
				List<Book> books = bookFacade.findAll();
		        request.setAttribute("books", books);
		        request.getRequestDispatcher("./list_books.jsp").forward(request, response);
			}else if(op.equals("delete")) {
				int bookId = Integer.parseInt(request.getParameter("bookId"));
				bookFacade.deleteBook(bookId);
	            response.sendRedirect("./BookServlet?op=list");
	        }
		}else {
		    // A revoir
			throw new IllegalStateException("This error should never be triggered");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated constructor stub
		doGet(request, response);
	}

}
