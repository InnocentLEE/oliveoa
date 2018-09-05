<html>
<body>
<h2>Hello World!</h2>
itolive <h1>Hail Hydra</h1>
<br/>
<h1>File Upload</h1>
<form action="documentflow/upload.do" method="post" enctype="multipart/form-data">
    file:<input type="file" name="file" width="120px">
    <input type="submit" value="Upload">
</form>
<h1>File Download</h1>
<form action="documentflow/download.do" method="get">
    <input type="hidden" value="">
    <input type="submit" value="Download">
</form>
</body>
</html>
