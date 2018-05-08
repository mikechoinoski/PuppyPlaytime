package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.Role;
import com.choinoski.persistence.DataValidator;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.InputValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *  This servlet creates a new pack from the information provided by a form. If the pack
 *  is successully create, the user is logged into the yourPack page.
 *
 *
 * @author mrchoinoski
 */
@WebServlet(
        name = "insertNewPack",
        urlPatterns = { "/insertNewPack" }
)
public class CreatePackInsertServlet extends HttpServlet {

    /**
     *  Handles HTTP Get requests. Sets data for the HTTP request
     *  data. Redirects data to another servlet to display yourPack page.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("yourPack");

    }

    /**
     *  Handles HTTP Post requests. Sets data for the HTTP request
     *  data. Redirects data to another servlet to display yourPack page.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao dao = new GenericDao(Pack.class);
        DataValidator validator = new DataValidator();
        HttpSession    session        = request.getSession();

        String packNameText     = request.getParameter("packName");
        String firstNameText    = request.getParameter("firstName");
        String lastNameText     = request.getParameter("lastName");
        String addressText      = request.getParameter("address");
        String phoneText        = request.getParameter("phoneNumber");
        String emailText        = request.getParameter("emailAddress");
        String packPasswordText = request.getParameter("password");

        ArrayList errors = null;

        session.removeAttribute("errorList");
        session.removeAttribute("packName");
        session.removeAttribute("firstName");
        session.removeAttribute("lastName");
        session.removeAttribute("address");
        session.removeAttribute("phoneNumber");
        session.removeAttribute("emailAddress");
        session.removeAttribute("password");

        errors = validator.validateFormPackData(packNameText, firstNameText, lastNameText,
                addressText, phoneText, emailText, packPasswordText);

        if (errors.size() == 0) {
            Pack   newPack  = new Pack(packNameText,
                    firstNameText,
                    lastNameText,
                    addressText,
                    emailText,
                    phoneText,
                    packPasswordText);

            int id = dao.insert(newPack);

            if (id > 0) {
                Pack insertedPack = (Pack) dao.getById(id);
                insertedPack.addRole("user");
                session.setAttribute("packId", id);
                request.login(packNameText, packPasswordText);
            }

            response.sendRedirect("yourPack");

        } else {

            session.setAttribute("errorList", errors);

            session.setAttribute("packName",request.getParameter("packName"));
            session.setAttribute("firstName",request.getParameter("firstName"));
            session.setAttribute("lastName" ,request.getParameter("lastName"));
            session.setAttribute("address" ,request.getParameter("address"));
            session.setAttribute("phoneNumber" ,request.getParameter("phoneNumber"));
            session.setAttribute("emailAddress",request.getParameter("emailAddress"));
            session.setAttribute("password",request.getParameter("password"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/createNewPack.jsp");
            dispatcher.forward(request, response);
        }



    }



}
