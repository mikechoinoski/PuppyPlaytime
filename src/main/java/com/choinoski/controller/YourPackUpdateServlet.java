package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.DataConverter;
import com.choinoski.persistence.GenericDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 *  This servlet shows the user their pack information and pack members. Administrators are redirected to another
 *  page that allows admin functions.
 *
 * @author mrchoinoski
 */
public class YourPackUpdateServlet extends HttpServlet {

    private GenericDao dao;

    private Properties properties;

    /**
     * Handles HTTP GET requests. Sets data for the HTTP request data. Processes member updates.
     * Forwards data to a JSP to display.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session     = request.getSession();
        Pack        userPack    = (Pack) session.getAttribute("userPack");

        Set<PackMember> memberSet     = new HashSet<>(userPack.getMembers());
        ServletContext servletContext = getServletContext();

        dao = new GenericDao(PackMember.class);
        properties = (Properties) servletContext.getAttribute("puppyPlaytimeProperties");

        processUpdates(memberSet, request);
        processDeletes(userPack, memberSet, request);

        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Processes deleting Pack Members.
     *
     * @param theUserPack the logged in user's pack
     * @param packMembers all of the user's pack members
     * @param request the Http Servlet Request
     *
     */
    public void processDeletes(Pack theUserPack, Set<PackMember> packMembers, HttpServletRequest request) {

        String memberNumberText = null;
        String checkBoxValue    = null;

        for (PackMember currentMember: packMembers) {

            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());
            checkBoxValue    = request.getParameter("memberToRemove" + memberNumberText);

            if (!(checkBoxValue == null)) {
                dao.delete(currentMember);
                boolean removed = theUserPack.removeMember(currentMember);
            }
        }

    }

    /**
     * Processes updating Pack Members.
     *
     * @param packMembers all of the user's pack members
     * @param request the Http Servlet Request
     *
     */
    public void processUpdates(Set<PackMember> packMembers, HttpServletRequest request) {

        String memberNumberText = null;

        String memberName       = null;
        String memberBirthday   = null;
        String memberWeight     = null;
        String memberBreed      = null;
        String memberGender     = null;
        String memberIntact     = null;

        for (PackMember currentMember: packMembers) {

            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());

            memberName     = request.getParameter("memberName" + memberNumberText);
            memberBirthday = request.getParameter("memberBirthday" + memberNumberText);
            memberWeight   = request.getParameter("memberWeight" + memberNumberText);
            memberBreed    = request.getParameter("memberBreed" + memberNumberText);
            memberGender   = request.getParameter("memberGender" + memberNumberText);
            memberIntact   = request.getParameter("memberIntact" + memberNumberText);

            boolean thereAreUpdates = currentMember.updatePackMember(memberName, memberBirthday, memberWeight,
                    memberBreed, memberGender, memberIntact, properties.getProperty("form.date.format"));

            if (thereAreUpdates) {
                dao.saveOrUpdate(currentMember);
            }

        }
    }

}