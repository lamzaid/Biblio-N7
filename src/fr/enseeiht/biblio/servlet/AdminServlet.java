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
import fr.enseeiht.biblio.facade.ReservationFacade;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    @EJB
    private ReservationFacade reservationFacade;

    private static final long serialVersionUID = 1L;

    public AdminServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op != null && op.equals("list_reservations")) {
            List<Reservation> reservations = reservationFacade.findAll();
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("./admin_reservations.jsp").forward(request, response);
        } else {
            throw new IllegalStateException("This error should never be triggered");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op != null && op.equals("validate")) {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            Reservation reservation = reservationFacade.find(reservationId);
            if (reservation != null) {
                reservation.setValidated(true);
                reservationFacade.update(reservation);
            }

            response.sendRedirect("./AdminServlet?op=list_reservations");
        } else {
            doGet(request, response);
        }
    }
}
