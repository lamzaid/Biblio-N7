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
import fr.enseeiht.biblio.entity.Exemplaire;
import fr.enseeiht.biblio.entity.Reservation;
import fr.enseeiht.biblio.facade.BookFacade;
import fr.enseeiht.biblio.facade.ExemplaireFacade;
import fr.enseeiht.biblio.facade.ReservationFacade;
import fr.enseeiht.biblio.facade.StudentFacade;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    @EJB
    private BookFacade bookFacade;

    @EJB
    private ExemplaireFacade exemplaireFacade;

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

        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (op != null) {
            switch (op) {
                case "list_books":
                    List<Book> books = bookFacade.findAll();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("./list_books.jsp").forward(request, response);
                    break;
                case "reservations":
                    List<Reservation> reservations = reservationFacade.findByStudent(studentId);
                    request.setAttribute("reservations", reservations);
                    request.getRequestDispatcher("./student_reservations.jsp").forward(request, response);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        } else {
            throw new IllegalStateException("Operation parameter 'op' is missing");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        Integer studentId = (Integer) request.getSession().getAttribute("studentId");

        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (op != null) {
            switch (op) {
                case "reserve":
                    try {
                        int bookId = Integer.parseInt(request.getParameter("bookId"));
                        List<Exemplaire> exemplaires = exemplaireFacade.findAvailableByBook(bookId);
                        if (!exemplaires.isEmpty()) {
                            Exemplaire exemplaire = exemplaires.get(0);
                            exemplaire.setDisponible(false);
                            exemplaireFacade.update(exemplaire);

                            Reservation reservation = new Reservation(studentFacade.find(studentId), exemplaire);
                            reservationFacade.create(reservation);

                            response.sendRedirect("./StudentServlet?op=reservations");
                        } else {
                            response.sendRedirect("./StudentServlet?op=list_books&error=no_copies");
                        }
                    } catch (NumberFormatException | NullPointerException e) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid bookId parameter");
                    }
                    break;
                case "cancel":
                    try {
                        String reservationIdStr = request.getParameter("reservationId");
                        if (reservationIdStr != null && !reservationIdStr.isEmpty()) {
                            int reservationId = Integer.parseInt(reservationIdStr);
                            Reservation reservation = reservationFacade.find(reservationId);
                            Exemplaire exemplaire = reservation.getExemplaire();
                            exemplaire.setDisponible(true);
                            exemplaireFacade.update(exemplaire);
                            reservationFacade.cancel(reservationId);

                            response.sendRedirect("./StudentServlet?op=reservations");
                        } else {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing reservationId parameter");
                        }
                    } catch (NumberFormatException e) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid reservationId parameter");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown operation in doPost: " + op);
            }
        } else {
            throw new IllegalStateException("Operation parameter 'op' is missing in doPost");
        }
    }
}
