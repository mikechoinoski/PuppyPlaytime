<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>
    <h2 class="pageHeader">The ${userPack.packName} Pack</h2>

    <table cellspacing="10">
        <tr>
            <th align="left">Pack Leader</th>
            <td>${userPack.firstName} ${userPack.lastName}</td>
            <td width="5"></td>
            <th align="left">Phone Number</th>
            <td>${userPack.phoneNumber}</td>
        </tr>
        <tr>
            <th align="left">Address</th>
            <td>${userPack.address}</td>
            <td width="5"></td>
            <th align="left">Email Address</th>
            <td>${userPack.emailAddress}</td>
        </tr>
    </table>

    <br><br>
    <h3><font color="#8b4513">Pack Members</font></h3>
    <br>

    <c:if test="${userPack.packSize > 0}">
        <form form method="post" action="YourPackUpdate">
            <table cellspacing="3">
                <tr>
                    <th width="80"></th>
                    <th width="80">Name</th>
                    <th width="120">Birth Date</th>
                    <th width="50">Weight</th>
                    <th width="90">Breed</th>
                    <th width="90">Gender</th>
                    <th width="60">Intact</th>
                    <th width="50"><font color="red">Remove</font></th>
                </tr>
                <c:forEach var="userMembers" items="${sessionScope.userPack.members}">
                    <tr>
                        <td width="80" align="center">
                            <c:choose>
                                <c:when test="${empty userMembers.pictureFilename}">
                                    <img src="./uploadedPhotos/default_dog.png" height="80" width="80">
                                </c:when>
                                <c:otherwise>
                                    <img src="./uploadedPhotos/${userMembers.pictureFilename}" height="80" width="80">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td width="80" align="center">
                            <input type="text" name="memberName${userMembers.packMemberNumber}" value="${userMembers.name}" style="width:100px">
                        </td>
                        <td width="120" align="center">
                            <input type="date" name="memberBirthday${userMembers.packMemberNumber}"
                                   style="width:120px" value="${userMembers.dateOfBirth}">
                        </td>
                        <td width="40" align="center">
                            <input type="number" name="memberWeight${userMembers.packMemberNumber}" min="0" max="300"
                                   value="${userMembers.weight}" style="width:40px">
                        </td>
                        <td width="90" align="center">
                            <input type="text" name="memberBreed${userMembers.packMemberNumber}"
                                   value="${userMembers.breed}" style="width:100px">
                        </td>
                        <td width="90" align="center">
                            <select name="memberGender${userMembers.packMemberNumber}" style="width:90px">
                                <option value="Male"
                                        <c:if test="${userMembers.sex.toString() == 'M'}">
                                            selected="selected"
                                        </c:if>
                                >Male</option>
                                <option value="Female"
                                        <c:if test="${userMembers.sex.toString() == 'F'}">
                                            selected="selected"
                                        </c:if>
                                >Female</option>
                            </select>
                        </td>
                        <td width="60" align="center">
                            <select name="memberIntact${userMembers.packMemberNumber}" style="width:60px">
                                <option value="Yes"
                                        <c:if test="${userMembers.intact}">
                                            selected="selected"
                                        </c:if>
                                >Yes</option>
                                <option value="No"
                                        <c:if test="${!userMembers.intact}">
                                            selected="selected"
                                        </c:if>
                                >No</option>
                            </select>
                        </td>
                        <td width="50" align="center">
                            <input type="checkbox" name="memberToRemove${userMembers.packMemberNumber}">
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <input type="submit" value="Make My Changes" />
        </form>
        <br><br>
    </c:if>

    <br>
    <h3><a href="createNewMember">Add a New Pack Member</a></h3>
    <br>
    <br>
    <h2><a href="logOut"><font color="darkred">Log Out</font></a></h2>

<c:import url="/jsp/page_bottom.jsp" />