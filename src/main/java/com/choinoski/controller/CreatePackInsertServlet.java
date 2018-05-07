package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.Role;
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

    private ArrayList errors;

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

        HttpSession    session        = request.getSession();

        String packNameText     = request.getParameter("packName");
        String firstNameText    = request.getParameter("firstName");
        String lastNameText     = request.getParameter("lastName");
        String addressText      = request.getParameter("address");
        String phoneText        = request.getParameter("phoneNumber");
        String emailText        = request.getParameter("emailAddress");
        String packPasswordText = request.getParameter("password");

        Boolean validationSuccessful = false;
        session.removeAttribute("errorList");
        session.removeAttribute("packName");
        session.removeAttribute("firstName");
        session.removeAttribute("lastName");
        session.removeAttribute("address");
        session.removeAttribute("phoneNumber");
        session.removeAttribute("emailAddress");
        session.removeAttribute("password");

        errors = new ArrayList();

        validationSuccessful = validateFormData(packNameText, firstNameText, lastNameText,
                addressText, phoneText, emailText, packPasswordText);

        if (validationSuccessful) {
            Pack   newPack  = new Pack(packNameText,
                    firstNameText,
                    lastNameText,
                    addressText,
                    phoneText,
                    emailText,
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

    private boolean validateFormData(String packName, String firstName, String lastName,
            String address, String phoneNumber, String emailAddress, String password) {

        boolean validationSuccess = true;

        if (!InputValidator.nameValidation(packName)) {
            errors.add("Invalid PackName");
            validationSuccess = false;
        }
        if (!InputValidator.nameValidation(firstName)) {
            errors.add("Invalid First Name");
            validationSuccess = false;
        }
        if (!InputValidator.nameValidation(lastName)) {
            errors.add("Invalid Last Name");
            validationSuccess = false;
        }
        if (!InputValidator.addressValidation(address)) {
            errors.add("Invalid Address");
            validationSuccess = false;
        }
        if (!InputValidator.phoneValidation(phoneNumber)) {
            errors.add("Invalid Phone Number");
            validationSuccess = false;
        }
        if (!InputValidator.emailValidation( emailAddress)) {
            errors.add("Invalid Email Address");
            validationSuccess = false;
        }
        if (!InputValidator.passwordValidation(password)) {
            errors.add("Invalid Password");
            validationSuccess = false;
        }

        return validationSuccess;

    }

}
