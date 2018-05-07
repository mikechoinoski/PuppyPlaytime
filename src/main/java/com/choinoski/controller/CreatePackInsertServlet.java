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

    private ArrayList errors = new ArrayList();

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

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        String packNameText     = request.getParameter("packName");
        String firstNameText    = request.getParameter("firstName");
        String lastNameText     = request.getParameter("lastName");
        String addressText      = request.getParameter("address");
        String phoneText        = request.getParameter("phoneNumber");
        String emailText        = request.getParameter("emailAddress");
        String packPasswordText = request.getParameter("password");

        Boolean validationSuccessful = validateFormData(packNameText, firstNameText, lastNameText,
                addressText, phoneText, emailText, packPasswordText);


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
            request.login(packName, packPassword);
        }

        response.sendRedirect("yourPack");

    }

    private boolean validateFormData(String packName, String firstName, String lastName,
    String address, String phoneNumber, String emailAddress, String password) {

        boolean validationSuccess = true;

        if (!InputValidator.nameValidation(packName)) {
            errors.add("Incorrect PackName");
        }
        if (!InputValidator.nameValidation(firstName)) {
            errors.add("Incorrect First Name");
        }
        if (!InputValidator.nameValidation(lastName)) {
            errors.add("Incorrect Last Name");
        }
        if (!InputValidator.addressValidation(address)) {
            errors.add("Incorrect Address");
        }
        if (!InputValidator.phoneValidation(phoneNumber)) {
            errors.add("Incorrect Phone Number");
        }
        if (!InputValidator.emailValidation( emailAddress)) {
            errors.add("Incorrect Phone Number");
        }
        if (!InputValidator.passwordValidation(password)) {
            errors.add("Incorrect Phone Number");
        }

        return validationSuccess;

    }

}
