<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>
        <h2 class="pageHeader">Create Playdate</h2>
        <br><br>

        <form method="post" action="InsertPlaydate">
            <table>
                <tr>
                    <td align="center">
                        <table>
                            <tr>
                                <th align="left">Location</th>
                                <td colspan="2"><input type="text" name="playdateLocation" required
                                                       value="${playdateLocation}"></td>
                            </tr>
                            <tr>
                                <th align="left">Date</th>
                                <td colspan="2">
                                    <input type="date" name="playdateDate" required value="${playdateDate}">
                                </td>
                            </tr>
                            <tr>
                                <th align="left">Time</th>
                                <td colspan="2">
                                    <input type="time" name="playdateTime" required value="${playdateTime}">
                                </td>
                            </tr>
                            <tr>
                                <th align="left">Private</th>
                                <td align="center">
                                    <label for="playdatePrivateYes">Yes</label>
                                    <input type="radio" id="playdatePrivateYes" name="playdatePrivate" value="yes"
                                            <c:if test="${playdatePrivate == 'yes'}">
                                                checked="checked"
                                            </c:if>
                                    required/>
                                </td>
                                <td align="center">
                                    <label for="playdatePrivateNo">No</label>
                                    <input type="radio" id="playdatePrivateNo" name="playdatePrivate" value="no"
                                            <c:if test="${playdatePrivate == 'no'}">
                                                checked="checked"
                                            </c:if>
                                    required/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2"><br><br></td>
                </tr>
                <tr>
                    <td align="center" colspan="2"><h3>Who wants to Play?</h3><br></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <c:forEach var="singleMember" items="${sessionScope.userPack.members}">
                            <div class="memberPlain">
                                <table>
                                    <tr>
                                        <td align="center"><b>${singleMember.name}</b></td>
                                    </tr>
                                    <tr>
                                        <td width="150" align="center">
                                            <c:choose>
                                                <c:when test="${empty singleMember.pictureFilename}">
                                                    <input type="checkbox"
                                                           name="memberCheckBox${singleMember.packMemberNumber}"
                                                           id="member${singleMember.packMemberNumber}" checked
                                                           value="memberCheckBoxValue${singleMember.packMemberNumber}"
                                                           style="display:none">
                                                    <label for="member${singleMember.packMemberNumber}" style="background:url(./uploadedPhotos/default_dog.png) no-repeat; background-size: cover; background-position: center;">
                                                    </label>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox"
                                                           name="memberCheckBox${singleMember.packMemberNumber}"
                                                           id="member${singleMember.packMemberNumber}" checked
                                                           value="memberCheckBoxValue${singleMember.packMemberNumber}"
                                                           style="display:none">
                                                    <label for="member${singleMember.packMemberNumber}" style="background:url('https://s3.us-east-2.amazonaws.com/puppyplaytimebucket/${singleMember.pictureFilename}') no-repeat; background-size: cover; background-position: center;">
                                                    </label>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:forEach>
                        <c:forEach var="singleMember" items="${sessionScope.membersForPlaydate}">
                            <div class="memberPlain">
                                <table>
                                    <tr>
                                        <td align="center"><b>${singleMember.name}</b></td>
                                    </tr>
                                    <tr>
                                        <td width="150" align="center">
                                            <c:choose>
                                                <c:when test="${empty singleMember.pictureFilename}">
                                                    <input type="checkbox"
                                                           name="memberCheckBox${singleMember.packMemberNumber}"
                                                           id="member${singleMember.packMemberNumber}" checked
                                                           value="memberCheckBoxValue${singleMember.packMemberNumber}"
                                                           style="display:none">
                                                    <label for="member${singleMember.packMemberNumber}" style="background:url(./uploadedPhotos/default_dog.png) no-repeat; background-size: cover; background-position: center;">
                                                    </label>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox"
                                                           name="memberCheckBox${singleMember.packMemberNumber}"
                                                           id="member${singleMember.packMemberNumber}" checked
                                                           value="memberCheckBoxValue${singleMember.packMemberNumber}"
                                                           style="display:none">
                                                    <label for="member${singleMember.packMemberNumber}" style="background:url('https://s3.us-east-2.amazonaws.com/puppyplaytimebucket/${singleMember.pictureFilename}') no-repeat; background-size: cover; background-position: center;">
                                                    </label>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Create New Playdate" /></td>
                </tr>
            </table>
        </form>
<br><br>
    <c:forEach var="oneError" items="${errorListPlaydate}">
        <font color="red">${oneError}</font><br>
    </c:forEach>

<c:import url="/jsp/page_bottom.jsp" />