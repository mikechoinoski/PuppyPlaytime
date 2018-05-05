package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Playdate;
import com.choinoski.entity.PlaydateMember;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.LoggedInPack;
import com.choinoski.persistence.RequestParameters;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.Properties;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 *  This servlet is used to create a new playdate. A playdate and the members associated with it are
 *  inserted into the database.
 *
 * @author mrchoinoski
 */
public class CreatePlaydateInsertServlet extends HttpServlet {

    private Properties properties;

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. A new playdate is created inserting the playdate and the members associated wtih the
     *  playdate. A redirect is done to the playdates page.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao        dao          = new GenericDao(Playdate.class);
        LoggedInPack      retrievePack = new LoggedInPack();
        RequestParameters myRequest    = new RequestParameters();

        LocalDate dateText    = null;
        Playdate newPlaydate  = null;
        int newPlaydateNumber = 0;

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        Pack     currentPack = (Pack) session.getAttribute("userPack");

        LocalTime timeText        = LocalTime.parse(request.getParameter("playdateTime"));
        String    privateText     = request.getParameter("playdatePrivate");
        Boolean   privatePlaydate = true;

        String    locationText = request.getParameter("playdateLocation");

        properties = (Properties) servletContext.getAttribute("puppyPlaytimeProperties");

        dateText   = LocalDate.parse(request.getParameter("playdateDate"),
                ofPattern(properties.getProperty("form.date.format")));

        if (privateText.equals("No")) {
            privatePlaydate = false;
        }

        newPlaydate = new Playdate(currentPack.getPackNumber(), locationText, dateText, timeText, "pending",
                privatePlaydate);

        newPlaydateNumber = dao.insert(newPlaydate);

        if (newPlaydateNumber > 0) {
            Map<String, String[]> checkBoxMappedValues = request.getParameterMap();
            List<PackMember>      allSearchedMembers   = (List<PackMember>) session.getAttribute("searchMembers");

            List<PackMember> playdateMembers = myRequest.getMembersFromCheckbox(allSearchedMembers,checkBoxMappedValues,
                    "memberCheckBox");

            PlaydateMember newPlaydateMember = null;

            for (PackMember currentMember : playdateMembers) {
                newPlaydateMember = new PlaydateMember(properties.getProperty("playdate.status.pending"),
                        currentMember);
                newPlaydate.addMember(newPlaydateMember);
            }
        }

        response.sendRedirect("Playdates");

    }

}
