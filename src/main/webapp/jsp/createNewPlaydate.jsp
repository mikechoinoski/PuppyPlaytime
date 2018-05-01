<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <center>

        <h2>Create Your Pack</h2>
        <br><br>

        <form method="post" action="insertNewPack">
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
                   <td>
                       <c:forEach var="singleMember" items="${yourPackMembers}">
                           <c:choose>
                               <c:when test="${empty singleMember.pictureFilename}">
                                   <div class="memberContainer">
                               </c:when>
                               <c:otherwise>
                                   <div class="memberSelected">
                               </c:otherwise>
                           </c:choose>

                               <table>
                                   <tr>
                                       <td colspan="3" align="center"><b>${singleMember.name}</b></td>
                                   </tr>
                                   <tr>
                                       <td width="35" align="center">
                                           <c:if test="${singleMember.size == 'XS'}">
                                               <font color="#b8860b" size="2"><b>X</b><br></font>
                                           </c:if>
                                           <c:if test="${singleMember.size == 'XS'}">
                                               <font color="#b8860b" size="2"><b>S</b></font>
                                           </c:if>
                                           <c:if test="${singleMember.size == 'S'}">
                                               <font color="#b8860b" size="3"><b>S</b></font>
                                           </c:if>
                                           <c:if test="${singleMember.size == 'M'}">
                                               <font color="#b8860b" size="4"><b>M</b></font>
                                           </c:if>
                                           <c:if test="${singleMember.size == 'L'}">
                                               <font color="#b8860b" size="5"><b>L</b></font>
                                           </c:if>
                                           <c:if test="${singleMember.size == 'XL'}">
                                               <font color="#b8860b" size="6"><b>X</b><br></font>
                                           </c:if>
                                           <c:if test="${singleMember.size == 'XL'}">
                                               <font color="#b8860b" size="6"><b>L</b></font>
                                           </c:if>
                                           <br><br>
                                           <font color="darkred" size="5"><b>${singleMember.age}</b></font>
                                       </td>
                                       <td width="130" align="center">
                                           <c:choose>
                                               <c:when test="${empty singleMember.pictureFilename}">
                                                   <img src="./uploadedPhotos/default_dog.png" height="130" width="130">
                                               </c:when>
                                               <c:otherwise>
                                                   <img src="./uploadedPhotos/${singleMember.pictureFilename}"
                                                        height="130" width="130">
                                               </c:otherwise>
                                           </c:choose>
                                           </a>
                                           <input type="checkbox" name="selectedPackMember=${singleMember.packMemberNumber}" style="background-image: url(../uploadedPhotos/default_dog.png)">

                                       </td>
                                       <td width="35" align="center">
                                           <c:if test="${singleMember.sex.toString() == 'M'}">
                                               <font color="blue" size="5"><b>M</b></font>
                                           </c:if>
                                           <c:if test="${singleMember.sex.toString() == 'F'}">
                                               <font color="#ff1493" size="5"><b>F</b></font>
                                           </c:if>
                                           <c:if test="${!singleMember.intact}"><br><br><font color="green" size="5"><b>+</b></font></c:if>
                                       </td>
                                   </tr>
                                   <tr>
                                       <td colspan="3" align="center"><b>${singleMember.breed}</b></td>
                                   </tr>
                               </table>
                           </div>
                       </c:forEach>
                   </td>
               </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Create New Pack" /></td>
                </tr>
            </table>
        </form>

    </center>
<c:import url="/jsp/page_bottom.jsp" />