<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<center>

<form method="post" action="UploadServlet" enctype="multipart/form-data">
    Select file to upload:
    <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
    <input type="submit" value="Upload" />
</form>

</center>
<c:import url="/jsp/page_bottom.jsp" />