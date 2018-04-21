<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>
<center>

    <h2>Your Pack</h2>
    <br>

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

    <br><br>
    <h3>Pack Members</h3>
    <br>

    <form form method="post" action="DeleteMember">
    <table>
        <tr>
            <th width="100">Name</th>
            <th width="50">Age</th>
            <th width="50">Size</th>
            <th width="100">Breed</th>
            <th width="50">Gender</th>
            <th width="50">Intact</th>
            <th width="50"><font color="red">Delete</font></th>
        </tr>
        <c:forEach var="userMembers" items="${userPack.members}">
            <tr>
                <td width="100" align="center">
                    <input type="text" name="fname" placeholder=${userMembers.name} size="10">
                </td>
                <td width="50" align="center">
                    <input type="text" name="fname" placeholder=${userMembers.age} size="5">
                </td>
                <td width="50" align="center">${userMembers.size}</td>
                <td width="100" align="center">${userMembers.breed}</td>
                <td width="50" align="center">${userMembers.sex}</td>
                <td width="100" align="center">${userMembers.intact}</td>
                <td width="100" align="center">
                    <input type="checkbox" name="MemberToDelete" value=${userMembers.packMemberNumber}>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>

    <br><br>
    <h3><a href="createNewMember">Add a New Pack Member</a></h3>


</center>

<c:import url="/jsp/page_bottom.jsp" />