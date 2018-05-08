<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>
        <h2 class="pageHeader">Administrative Functions</h2>
        <br><br>
        <h3>Packs</h3>
        <br><br>
        <form method="post" action="AdministrativeFunctions">
            <table>
                <tr>
                    <th>Pack Number</th>
                    <th>Pack Name</th>
                    <th>Remove</th>
                </tr>
                <c:forEach var="packToRemove" items="${allPacks}">
                    <c:if test="${packToRemove.packNumber != userPack.packNumber}">
                        <tr>
                            <td width="80" align="center">
                                    ${packToRemove.packNumber}
                            </td>
                            <td width="80" align="center">
                                    ${packToRemove.packName}
                            </td>
                            <td width="50" align="center">
                                <input type="checkbox" name="packToRemove${packToRemove.packNumber}">
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                <tr>
                    <td colspan="3" align="center"><input type="submit" value="Remove Users" /></td>
                </tr>
            </table>
        </form>

        <br>
        <br>
        <h2><a href="logOut"><font color="darkred">Log Out</font></a></h2>

    </center>
<c:import url="/jsp/page_bottom.jsp" />