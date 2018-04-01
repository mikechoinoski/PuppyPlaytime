<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<center>

    <form method="post" action="UploadServlet" enctype="multipart/form-data">
        <tr>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
        Name:<br>
        <input type="text" name="name"><br>
        Weight:<br>
        <input type="text" name="weight">
        Breed:<br>
        <input type="text" name="breed">
        Gender:<br>
        <input type="text" name="gender">
        Date of Birth:<br>
        <input type="text" name="dateOfBirth">
        Intact:<br>
        <input type="text" name="intact">
        Select file to upload:
        <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
        <input type="submit" value="Upload" />
    </form>

</center>
<c:import url="/jsp/page_bottom.jsp" />