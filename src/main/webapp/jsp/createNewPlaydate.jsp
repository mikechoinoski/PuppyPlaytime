<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <center>

        <h2>Create Playdate</h2>
        <br><br>

        <form method="post" action="InsertPlaydate">
            <table>
                <tr>
                    <td>Location:</td>
                    <td><input type="text" name="playdateLocation"></td>
                </tr>
                <tr>
                    <td>Date:</td>
                    <td><input type="date" name="playdateDate"></td>
                </tr>
                <tr>
                    <td>Time:</td>
                    <td><input type="time" name="playdateTime"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="playdatePublic"></td>
                </tr>
                <tr>
                    <td colspan="2"><br></td>
                </tr>

               <tr>
                   <td align="center">
                       <c:forEach var="singleMember" items="${sessionScope.userPack.members}">
                           <div class="memberContainer">
                               <table>
                                   <tr>
                                       <td align="center"><b>${singleMember.name}</b></td>
                                   </tr>
                                   <tr>
                                       <td width="150" align="center">
                                           <c:choose>
                                               <c:when test="${empty singleMember.pictureFilename}">
                                                   <input type="checkbox" name="memberCheckBox${singleMember.packMemberNumber}" id="member${singleMember.packMemberNumber}" value="memberCheckBoxValue${singleMember.packMemberNumber}" style="display:none">
                                                   <label for="member${singleMember.packMemberNumber}" style="background:url(./uploadedPhotos/default_dog.png) no-repeat; background-size: cover; background-position: center;"></label>
                                               </c:when>
                                               <c:otherwise>
                                                   <input type="checkbox" name="memberCheckBox${singleMember.packMemberNumber}" id="member${singleMember.packMemberNumber}" value="memberCheckBoxValue${singleMember.packMemberNumber}" style="display:none">
                                                   <label for="member${singleMember.packMemberNumber}" style="background:url(./uploadedPhotos/${singleMember.pictureFilename}) no-repeat; background-size: cover; background-position: center;"></label>
                                               </c:otherwise>
                                           </c:choose>
                                           </a>
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

    </center>
<c:import url="/jsp/page_bottom.jsp" />