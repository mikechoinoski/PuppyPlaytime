package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Role;
import com.choinoski.persistence.GenericDao;
import org.apache.commons.lang3.BooleanUtils;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YourPackUpdateServlet extends HttpServlet {

    private GenericDao dao = null;

    /**
     * Handles HTTP GET requests. Sets data for the HTTP request
     * data. Forwards data to a JSP to display.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();

        Pack   userPack         = (Pack) session.getAttribute("userPack");

        String memberNumberText = null;

        String memberName       = null;
        String memberBirthday   = null;
        String memberWeight     = null;
        String memberBreed      = null;
        String memberGender     = null;
        String memberIntact     = null;

        Boolean updatesMade     = false;
        Boolean removeMembers   = false;

        dao = new GenericDao(PackMember.class);

        //List membersToRemove = new ArrayList();

        Set<PackMember> memberSet = new HashSet<>(userPack.getMembers());

        //for (PackMember currentMember: userPack.getMembers()) {
         //   memberSet.add(currentMember);
        //}

        for (PackMember currentMember: memberSet) {

            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());

            String checkBoxValue = request.getParameter("memberToRemove" + memberNumberText);

            if (!(checkBoxValue == null)) {
                //membersToRemove.add(currentMember);
                userPack.removeMember(currentMember);
                //removeMembers = true;
            } else {
                memberName     = request.getParameter("memberName" + memberNumberText);
                memberBirthday = request.getParameter("memberBirthday" + memberNumberText);
                memberBreed    = request.getParameter("memberBreed" + memberNumberText);

                LocalDate convertedBirthday = LocalDate.parse(request.getParameter("memberBirthDate"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (!memberName.equals(currentMember.getName())) {
                    currentMember.setName(request.getParameter("memberName"));
                    updatesMade = true;
                }
                if (!memberBirthday.equals(currentMember.getDateOfBirth())) {
                    currentMember.setDateOfBirth(convertedBirthday);
                    updatesMade = true;
                }
                if (!memberWeight.equals(currentMember.getWeight())) {
                    currentMember.setWeight(request.getParameter("memberWeight"));
                    updatesMade = true;
                }
                if (!memberBreed.equals(currentMember.getBreed())) {
                    currentMember.setBreed(request.getParameter("memberBreed"));
                    updatesMade = true;
                }
                //BooleanUtils.toStringYesNo(myBoolean)
                if (updatesMade) {
                    dao.saveOrUpdate(currentMember);
                }
            }

            //if (removeMembers) {
            //    for (PackMember currentMember: userPack.getMembers())
            //}

            //isSelected()

            //if(MemberToRemove)

            //request.getParameter("memberIntact" + memberNumberText);
            //request.getParameter("memberGender" + memberNumberText);

            //LocalDate memberDob = LocalDate.parse(request.getParameter("memberDateOfBirth"),
             //       DateTimeFormatter.ofPattern("yyyy-MM-dd"));




            //if (!request.getParameter("memberIntact" + memberNumberText).equals(currentMember.isIntact())) {

            //}




        }

        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
