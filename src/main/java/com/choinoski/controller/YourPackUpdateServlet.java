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

    private GenericDao dao;

    private Boolean    updatesMade;
    private Boolean    removeMembers;

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

        HttpSession session     = request.getSession();

        Pack   userPack         = (Pack) session.getAttribute("userPack");

        String memberNumberText = null;

        String memberName       = null;
        String memberBirthday   = null;
        String memberWeight     = null;
        String memberBreed      = null;
        String memberGender     = null;
        String memberIntact     = null;

        dao = new GenericDao(PackMember.class);



        //List membersToRemove = new ArrayList();

        Set<PackMember> memberSet = new HashSet<>(userPack.getMembers());

        //for (PackMember currentMember: userPack.getMembers()) {
         //   memberSet.add(currentMember);
        //}

        for (PackMember currentMember: memberSet) {

            updatesMade    = false;
            removeMembers  = false;
            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());

            String checkBoxValue = request.getParameter("memberToRemove" + memberNumberText);

            if (!(checkBoxValue == null)) {
                //membersToRemove.add(currentMember);
                userPack.removeMember(currentMember);
                //removeMembers = true;
            } else {

                memberName     = request.getParameter("memberName");
                memberBirthday = request.getParameter("memberBirthday");
                memberWeight   = request.getParameter("memberWeight");
                memberBreed    = request.getParameter("memberBreed");
                memberGender   = request.getParameter("memberGender");
                memberIntact   = request.getParameter("memberIntact");

                updatePackMember(currentMember, memberName, memberBirthday, memberWeight, memberBreed, memberGender,
                        memberIntact);

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

    public void updatePackMember(PackMember member, String name, String birthday, String weight, String breed,
                                 String gender, String intact) {

        String memberNumberText = Integer.toString(member.getPackMemberNumber());

        Integer.parseInt(weight);

        LocalDate convertedBirthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        if (!(name + memberNumberText).equals(member.getName())) {
            member.setName(name);
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



    }






}
