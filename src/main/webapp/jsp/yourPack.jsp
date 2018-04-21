<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>
<center>

    <h2>Your Pack</h2>
    <br><br>

    <table>
        <tr>
            <td>Pack Leader</td>
            <td>${userPack.packName}</td>
        </tr>
        <tr>
            <td>First Name</td>
            <td>${userPack.firstName}</td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td>${userPack.lastName}</td>
        </tr>
        <tr>
            <td>Address</td>
            <td>${userPack.address}</td>
        </tr>
        <tr>
            <td>Phone Number</td>
            <td>${userPack.phoneNumber}</td>
        </tr>
        <tr>
            <td>Email Address</td>
            <td>${userPack.emailAddress}</td>
        </tr>
    </table>

    <br>
    <h3>Pack Members</h3>
    <br>

    <table>
        <tr>
            <th width="100">Name</th>
            <th width="50">Size</th>
            <th width="100">Breed</th>
            <th width="50">Gender</th>
            <th width="50">Intact</th>
        </tr>
        <c:forEach var="userPack" items="${members}">
            <tr>
                <td width="100" align="center">${currentUser.name}</td>
                <td width="50" align="center">${currentUser.age}</td>
                <td width="50" align="center">${currentUser.size}</td>
                <td width="100" align="center">${currentUser.breed}</td>
                <td width="50" align="center">${currentUser.sex}</td>
                <td width="50" align="center">${currentUser.intact}</td>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    <h3><a href="createNewMember">Add a New Pack Member</a></h3>


</center>

<c:import url="/jsp/page_bottom.jsp" />