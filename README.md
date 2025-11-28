STUDENT INFORMATION:
Name: Nguyễn Văn Ngọc Hải
Student ID: ITCSIU23007
Class: Web Application Development_S1_2025-26_G02_lab02

COMPLETED EXERCISES:
[x] Exercise 1: Database & User Model
[x] Exercise 2: User Model & DAO
[x] Exercise 3: Login/Logout Controllers
[x] Exercise 4: Views & Dashboard
[x] Exercise 5: Authentication Filter
[x] Exercise 6: Admin Authorization Filter
[x] Exercise 7: Role-Based UI
[X] Exercise 8: Change Password

AUTHENTICATION COMPONENTS:
- Models: User.java
- DAOs: UserDAO.java
- Controllers: LoginController.java, LogoutController.java, DashboardController.java
- Filters: AuthFilter.java, AdminFilter.java
- Views: login.jsp, dashboard.jsp, updated student-list.jsp

TEST CREDENTIALS:
Admin:
- Username: admin
- Password: password123

Regular User:
- Username: john
- Password: password123

FEATURES IMPLEMENTED:
- User authentication with BCrypt
- Session management
- Login/Logout functionality
- Dashboard with statistics
- Authentication filter for protected pages
- Admin authorization filter
- Role-based UI elements
- Password security

SECURITY MEASURES:
- BCrypt password hashing
- Session regeneration after login
- Session timeout (30 minutes)
- SQL injection prevention (PreparedStatement)
- Input validation
- XSS prevention (JSTL escaping)

KNOWN ISSUES: Change Password is implemented but not in the UI
BONUS FEATURES:

TIME SPENT: 5 hours

TESTING NOTES: Typed into the box with intentionally wrong and right info, seeking the desired results to test
[Describe how you tested authentication, filters, and authorization]
