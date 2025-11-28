<%-- 
    Document   : change-password
    Created on : Nov 27, 2025, 6:26:36 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <form action="change-password" method="post">
            <input type="password" name="currentPassword" placeholder="Current Password">
            <input type="password" name="newPassword" placeholder="New Password">
            <input type="password" name="confirmPassword" placeholder="Confirm Password">
            <button type="submit">Change Password</button>
        </form>
    </body>
</html>
