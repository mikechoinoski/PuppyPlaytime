<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <center>

        <h2>Current Playdates</h2>
        <br><br>

            <c:forEach var="singlePlaydate" items="${currentPlaydates}">
                <div class="memberPlain">
                    <table border="1">
                        <tr align="center">
                            <td><b>Location:</b> ${fn:trim(singlePlaydate.playdateLocation)}</td>
                            <td><b>Date: </b>${fn:trim(singlePlaydate.date)}</td>
                            <td><b>Time: </b>${fn:trim(singlePlaydate.time)}</td>
                            <td><b>Private: </b>${fn:trim(singlePlaydate.privatePlaydate)}</td>
                        </tr>
                        <tr>
                            <td colspan="4" width="600" align="center">
                                <c:forEach var="singlePlaymate" items="${singlePlaydate.playdateMembers}">
                                    <c:choose>
                                        <c:when test="${empty singlePlaymate.packMember.pictureFilename}">
                                            <img src="./uploadedPhotos/default_dog.png" class="playmateImage">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="./uploadedPhotos/${singlePlaymate.packMember.pictureFilename}"
                                                 class="playmateImage">
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </div>
                <br><br>
            </c:forEach>

    </center>
<c:import url="/jsp/page_bottom.jsp" />