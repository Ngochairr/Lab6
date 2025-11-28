package filter;

import com.student.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/student"})
public class AdminFilter implements Filter {
    
    // Admin-only actions
    private static final String[] ADMIN_ACTIONS = {
        "new",
        "insert",
        "edit",
        "update",
        "delete"
    };
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO: Log initialization
        System.out.println("AdminFilter initialized");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // TODO: Cast to HTTP types
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // TODO: Get action parameter
        String action = httpRequest.getParameter("action");
        // TODO: Check if action requires admin role
        if (isAdminAction(action)) {
            
            // TODO: Get session and user
            HttpSession session = httpRequest.getSession();
            User user = (User) session.getAttribute("user");
            // TODO: Check if user is admin
            if (user != null && user.isAdmin()) {
                // Allow access
                chain.doFilter(request, response);
            } else {
                // Deny access
                // Redirect with error message
                String contextPath = httpRequest.getContextPath();
                String loginURL = contextPath + "/login";
                httpResponse.sendRedirect(loginURL);
                httpRequest.setAttribute("errorMessage", "You do not have permission to access this page.");
            }
        } else {
            // Not an admin action, allow
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
        // TODO: Log destruction
        System.out.println("AdminFilter destroyed");
    }
    
    private boolean isAdminAction(String action) {
        // TODO: Check if action is in ADMIN_ACTIONS array
        for (String AdmAct : ADMIN_ACTIONS) {
            if (AdmAct.equals(action)) {
                return true;
            }
        }
        return false;
    }
}