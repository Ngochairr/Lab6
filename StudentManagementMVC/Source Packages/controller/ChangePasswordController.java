/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.controller;

/**
 *
 * @author admin
 */
import com.student.dao.UserDAO;
import com.student.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // User MUST be logged in to change password
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login");
            return;
        }

        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
            return;
        }

        User currentUser = (User) session.getAttribute("currentUser");

        // Read form values
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate fields
        if (currentPassword == null || newPassword == null || confirmPassword == null ||
            currentPassword.isBlank() || newPassword.isBlank() || confirmPassword.isBlank()) {

            request.setAttribute("error", "Please fill in all fields.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        // Validate current password
        if (!currentPassword.equals(currentUser.getPassword())) {
            request.setAttribute("error", "Incorrect current password.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        // Validate new password length
        if (newPassword.length() < 8) {
            request.setAttribute("error", "New password must be at least 8 characters long.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        // Validate confirm password
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirm password do not match.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        
        // Hash new password
        String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        // Update password in database 
        userDAO.updatePassword(currentUser.getId(), hashed);
        // Refresh session user
        currentUser.setPassword(newPassword);
        session.setAttribute("currentUser", currentUser);

        request.setAttribute("success", "Password changed successfully.");
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }
}