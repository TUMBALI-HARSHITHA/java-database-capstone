✅ User Story 1: Admin Login
Title: Admin Login to the Portal
User Story:
As an admin, I want to log into the portal using my username and password so that I can securely manage the platform.

Acceptance Criteria:

Admin can access a login page.

Admin is authenticated using valid credentials.

On success, admin is redirected to the dashboard.

On failure, an error message is shown.

✅ User Story 2: Admin Logout
Title: Admin Logout from the Portal
User Story:
As an admin, I want to log out of the portal so that I can ensure the security of the system when I leave.

Acceptance Criteria:

A logout option is available in the dashboard.

On clicking logout, the session is terminated.

The user is redirected to the login screen.

✅ User Story 3: Add Doctor Profile
Title: Add New Doctor
User Story:
As an admin, I want to add new doctors to the platform so that they can begin managing their schedules and patients.

Acceptance Criteria:

Admin can access an "Add Doctor" form.

Required fields include name, specialization, email, and contact info.

Upon submission, the doctor’s data is saved to the MySQL database.

A success confirmation is shown.

✅ User Story 4: Delete Doctor Profile
Title: Delete Existing Doctor
User Story:
As an admin, I want to delete a doctor’s profile from the system so that outdated or inactive records are removed.

Acceptance Criteria:

Admin can see a list of registered doctors.

Each doctor has a “Delete” button.

On confirmation, the selected doctor is deleted from the database.

Deleted records do not appear in the doctor list.

✅ User Story 5: View Appointment Statistics
Title: Run Monthly Appointment Statistics Report
User Story:
As an admin, I want to run a stored procedure in MySQL CLI to see the number of appointments per month so that I can monitor clinic usage trends.

Acceptance Criteria:

A stored procedure (e.g., CALL GetMonthlyAppointments()) is available in MySQL.

When executed, it returns month-wise appointment counts.

Data helps identify peak usage and underused periods.

