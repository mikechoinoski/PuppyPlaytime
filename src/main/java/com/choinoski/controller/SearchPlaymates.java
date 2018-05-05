package com.choinoski.controller;

import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.MemberSearchCriteria;
import com.choinoski.util.SearchDataConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

/**
 *  This servlet allows a user to search for playmate and also select playmates to create a playdate.
 *
 * @author mrchoinoski
 */
@WebServlet(
        urlPatterns = {"/SearchPlaymates"}
)

public class SearchPlaymates extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Properties properties;

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request data. A search is performed
     *  based on default or entered data. A forward is done back to the search page.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String minimumAgeText  = request.getParameter("minimumAge");
        String maximumAgeText  = request.getParameter("maximumAge");
        String minimumSizeText = request.getParameter("minimumSize");
        String maximumSizeText = request.getParameter("maximumSize");
        String genderText      = request.getParameter("gender");
        String fixedText       = request.getParameter("fixed");

        GenericDao dao         = new GenericDao(PackMember.class);

        List<PackMember> searchMembers = null;

        MemberSearchCriteria searchParameters = null;

        LocalDate minimumDate = null;
        LocalDate maximumDate = null;

        int minimumAge     = 0;
        int maximumAge     = 0;
        int minimumWeight  = 0;
        int maximumWeight  = 0;

        char    gender = ' ';
        Boolean intact = null;

        HttpSession          session          = request.getSession();

        ServletContext servletContext = getServletContext();

        properties = (Properties) servletContext.getAttribute("puppyPlaytimeProperties");

        minimumAge = Integer.parseInt(properties.getProperty("member.age.min"));
        maximumAge = Integer.parseInt(properties.getProperty("member.age.max"));

        if (session.getAttribute("currentCriteria") == null) {

            searchParameters = new MemberSearchCriteria(minimumAge, maximumAge,
                    properties.getProperty("member.size.min"),
                    properties.getProperty("member.size.max"),
                    properties.getProperty("default.search.gender"),
                    properties.getProperty("default.search.fixed"));
            searchMembers = dao.getAll();
            session.setAttribute("currentCriteria", searchParameters);
            session.setAttribute("searchMembers", searchMembers);

        } else if (!((minimumAgeText == null) && (maximumAgeText == null) && (minimumSizeText == null) &&
                    (maximumSizeText == null) && (genderText == null) && (fixedText == null))) {

            if(minimumAgeText != null) {
                minimumAge = Integer.parseInt(minimumAgeText);
            }
            if(maximumAgeText != null) {
                maximumAge = Integer.parseInt(maximumAgeText);
            }
            searchParameters = new MemberSearchCriteria(minimumAge, maximumAge, minimumSizeText,
                    maximumSizeText, genderText, fixedText);

            minimumDate      = LocalDate.now().minusYears(searchParameters.getMinimumAge());
            maximumDate      = LocalDate.now().minusYears(searchParameters.getMaximumAge());
            minimumWeight    = searchParameters.getMinimumWeightForSize(searchParameters.getMinimumSize());
            maximumWeight    = searchParameters.getMaximumWeightForSize(searchParameters.getMaximumSize());
            gender           = searchParameters.getCharGender(searchParameters.getGender());
            intact           = searchParameters.getIntact(searchParameters.getFixed());
            searchMembers = dao.getByMultipleProperty(
                    "weight", minimumWeight, maximumWeight,
                    "dateOfBirth", maximumDate, minimumDate,
                    "sex", gender,
                    "intact",intact);
            session.setAttribute("currentCriteria", searchParameters);
            session.setAttribute("searchMembers", searchMembers);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchPlaymates.jsp");
        dispatcher.forward(request, response);
    }
}
