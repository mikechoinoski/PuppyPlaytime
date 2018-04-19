<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>
<center>

    <form method="post" action="UploadServlet" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Weight</td>
            <td><input type="text" name="weight"></td>
        </tr>
        <tr>
            <td>Breed</td>
            <td><input type="text" name="breed"></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><input type="text" name="gender"></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="text" name="dateOfBirth"></td>
        </tr>
        <tr>
            <td>Intact</td>
            <td><input type="text" name="intact"></td>
        </tr>
    </table>
    <br><br>
        <u>Add a Picture</u><br>
        <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
        <input type="submit" value="Upload" />
    </form>

</center>
<c:import url="/jsp/page_bottom.jsp" />