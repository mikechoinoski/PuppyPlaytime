<%@include file="head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.choinoski.entity.PackMember" %>

<html><body>

<div class="container-fluid">
    <h2>Search Results: </h2>

    <form action="searchMembers" class="form-inline"/>
    <tr>
        <td colspan=2><u>Results to Return:</u></td>
    </tr>
    <tr>
        <td colspan=2 align="center">All
            <input type="radio" name="searchType"
                   value="Return All"/>
            Size
            <input type="radio" name="searchType"
                   value="Search by Size"/>
            Gender <input type="radio" name="searchType"
                             value="Search by Gender" /><br><br>
        </td>
    </tr>
    <tr>
        <td>Search Term: </td>
        <td><input type="text" name="searchTerm"
                   placeholder="Enter Text" /></td>
    </tr>
    <tr>
        <td colspan=2 align="center">
            <input type="submit" value="Submit Search" />
        </td>
    </tr>
    </table>
    <br><br>
    <table>
        <tr>
            <th width="100">Name</th>
            <th width="200">Size</th>
            <th width="200">Breed</th>
            <th width="100">Gender</th>
            <th width="100">Intact</th>
        </tr>
        <c:forEach var="currentUser" items="${members}">
            <tr>
                <td width="100">${currentUser.name}</td>
                <td width="200">${currentUser.size}</td>
                <td width="200">${currentUser.breed}</td>
                <td width="100">${currentUser.sex}</td>
                <td width="100">${currentUser.intact}</td>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    Hi There!! ${testPack.packName}.

</div>

</body>
</html>
