package fr.enseeiht.biblio.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.enseeiht.biblio.entity.Reservation;
import fr.enseeiht.biblio.entity.Book;
import fr.enseeiht.biblio.facade.BookFacade;
import fr.enseeiht.biblio.facade.ReservationFacade;
import fr.enseeiht.biblio.facade.StudentFacade;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    @EJB
    private BookFacade bookFacade;

    @EJB
    private ReservationFacade reservationFacade;

    @EJB
    private StudentFacade studentFacade;

    private static final long serialVersionUID = 1L;

    public StudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        Integer studentId = (Integer) request.getSession().getAttribute("studentId");

        System.out.println("Retrieved studentId from session: " + studentId);

        if (studentId == null) {
            System.out.println("studentId is null, redirecting to login.jsp");
            response.sendRedirect("login.jsp");
            return;
        }

        if (op != null && op.equals("list_books")) {
            List<Book> books = bookFacade.findAllAvailable();
            request.setAttribute("books", books);
            request.getRequestDispatcher("./list_books.jsp").forward(request, response);
        } else if (op != null && op.equals("reservations")) {
            List<Reservation> reservations = reservationFacade.findByStudent(studentId);
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("./student_reservations.jsp").forward(request, response);
        } else {
            throw new IllegalStateException("This error should never be triggered");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        Integer studentId = (Integer) request.getSession().getAttribute("studentId");

        System.out.println("Retrieved studentId from session in doPost: " + studentId);

        if (studentId == null) {
            System.out.println("studentId is null in doPost, redirecting to login.jsp");
            response.sendRedirect("login.jsp");
            return;
        }

        if (op != null && op.equals("reserve")) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book book = bookFacade.find(bookId); // Fetch book entity by bookId

            Reservation reservation = new Reservation(studentFacade.find(studentId), book);
            reservationFacade.create(reservation);

            response.sendRedirect("./StudentServlet?op=reservations");
        } else if (op != null && op.equals("cancel")) {
            try {
                String reservationIdStr = request.getParameter("reservationId");
                System.out.println("reservationIdStr: " + reservationIdStr);
                int reservationId = Integer.parseInt(reservationIdStr);
                reservationFacade.cancel(reservationId);

                response.sendRedirect("./StudentServlet?op=reservations");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid reservation ID");
            }
        } else {
            doGet(request, response);
        }
    }
}
