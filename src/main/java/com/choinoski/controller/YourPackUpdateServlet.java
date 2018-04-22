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

                memberName     = request.getParameter("memberName" + memberNumberText);
                memberBirthday = request.getParameter("memberBirthday" + memberNumberText);
                memberWeight   = request.getParameter("memberWeight" + memberNumberText);
                memberBreed    = request.getParameter("memberBreed" + memberNumberText);
                memberGender   = request.getParameter("memberGender" + memberNumberText);
                memberIntact   = request.getParameter("memberIntact" + memberNumberText);

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

        String    memberNumberText  = Integer.toString(member.getPackMemberNumber());
        int       convertedWeight   = Integer.parseInt(weight);
        LocalDate convertedBirthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        char   convertedGender      = ' ';

        if (intact.equals("Yes")) {
            convertedGender  = 'M';
        } else if (intact.equals("No")) {
            convertedGender  = 'F';
        }

        Boolean   convertedIntact   = null;

        if (intact.equals("Yes")) {
            convertedIntact = true;
        } else if (intact.equals("No")) {
            convertedIntact = false;
        }


        if (!name.equals(member.getName())) {
            member.setName(name);
            updatesMade = true;
        }
        if (!convertedBirthday.equals(member.getDateOfBirth())) {
            member.setDateOfBirth(convertedBirthday);
            updatesMade = true;
        }
        if (!weight.equals(member.getWeight())) {
            member.setWeight(convertedWeight);
            updatesMade = true;
        }
        if (!breed.equals(member.getBreed())) {
            member.setBreed(breed);
            updatesMade = true;
        }
        if (!(convertedGender == member.getSex())) {
            member.setBreed(breed);
            updatesMade = true;
        }
        if (!convertedIntact.equals(member.isIntact())) {
            member.setBreed(breed);
            updatesMade = true;
        }
        //BooleanUtils.toStringYesNo(myBoolean)



    }






}
