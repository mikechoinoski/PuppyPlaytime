package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Playdate;
import com.choinoski.entity.PlaydateMember;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.LoggedInPack;
import com.choinoski.persistence.RequestParameters;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class CreatePlaydateInsertServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. Forwards data to a JSP to display.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session         = request.getSession();

        String    locationText = request.getParameter("playdateLocation");
        LocalDate dateText     = LocalDate.parse(request.getParameter("playdateDate"),
                ofPattern("yyyy-MM-dd"));
        LocalTime timeText    = LocalTime.parse(request.getParameter("playdateTime"));
        String    privateText = request.getParameter("playdatePrivate");
        Boolean privatePlaydate = true;

        if (privateText.equals("No")) {
            privatePlaydate = false;
        }

        Pack     currentPack = (Pack) session.getAttribute("userPack");
        Playdate newPlaydate = new Playdate(currentPack.getPackNumber(), locationText, dateText, timeText, "pending", privatePlaydate);

        GenericDao dao       = new GenericDao(Playdate.class);

        int newPlaydateNumber = dao.insert(newPlaydate);

        LoggedInPack      retrievePack = new LoggedInPack();
        RequestParameters myRequest    = new RequestParameters();

        Map<String, String[]> checkBoxMappedValues = request.getParameterMap();
        List<PackMember>      allSearchedMembers   = (List<PackMember>) session.getAttribute("searchMembers");

        List<PackMember> playdateMembers = myRequest.getMembersFromCheckbox(allSearchedMembers,checkBoxMappedValues));

        PlaydateMember newPlaydateMember = null;


        for (PackMember currentMember : playdateMembers) {
            newPlaydateMember = new PlaydateMember(newPlaydateNumber, currentMember.getPackMemberNumber(),"Pending");
            newPlaydate.addMember(newPlaydateMember);
        }

        String url = "/jsp/Playdate.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
