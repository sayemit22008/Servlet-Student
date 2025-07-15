<!DOCTYPE html>
<html>
<head><title>Student Form</title></head>
<body>
<h2>Student Form</h2>
<form action="StudentServlet" method="post">
    Name:<input type="text" name="name" required><br><br>
    Email:<input type="email" name="email" required><br><br>

    <input type="submit" name="action" value="Insert">
    <input type="submit" name="action" value="View">
    <input type="submit" name="action" value="Update">
    <input type="submit" name="action" value="Delete">
</form>
</body>
</html>
