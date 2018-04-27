<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>

<center>

    <h2>Search Results: </h2>

    <form action="searchMembers" class="form-inline"/>
    <table>
    <tr>
        <td colspan=2><u>Results to Return:</u></td>
    </tr>
    <tr>
        <td>

        </td>
    </tr>
    <tr>
        <td>
            <select name="memberGender${userMembers.packMemberNumber}" style="width:90px">
                <option value="Yes"
                        <c:if test="${searchCriteria.sex.toString() == 'M'}">
                            selected="selected"
                        </c:if>
                >Male</option>
                <option value="No"
                        <c:if test="${searchCriteria.sex.toString() == 'F'}">
                            selected="selected"
                        </c:if>
                >Female</option>
            </select>
        </td>
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
            <th width="50">Size</th>
            <th width="100">Breed</th>
            <th width="50">Gender</th>
            <th width="50">Intact</th>
        </tr>
        <c:forEach var="currentUser" items="${members}">
            <tr>
                <td width="100" align="center">${currentUser.name}</td>
                <td width="50" align="center">${currentUser.size}</td>
                <td width="100" align="center">${currentUser.breed}</td>
                <td width="50" align="center">${currentUser.sex}</td>
                <td width="50" align="center">${currentUser.intact}</td>
            </tr>
        </c:forEach>
    </table>

</center>

<c:import url="/jsp/page_bottom.jsp" />