# 🔐 PWD Manager

##  Overview
PWD Manager is a Java-based desktop password manager application with a GUI built using Swing. It allows users to create accounts, log in with authentication, and store website credentials locally using JSON files.

---

##  Features
- User registration with phone number validation
- Login system with password verification
- OTP-style authentication (secure code verification)
- Add, view, and manage saved website passwords
- Local JSON-based storage per user
- Multi-panel Swing GUI navigation

---

## How It Works
- User data is stored in `users.json`
- Each user gets a separate file: `username.json`
- Authentication uses a generated 6-digit secure code
- After login, users can add and view saved credentials

---

## Technologies Used
- Java
- Swing (GUI)
- JSON (org.json)
- Google libphonenumber
- UUID (for secure code generation)

---

## Notes
- SMS functionality is currently simulated (Twilio code is commented out)
- Data is stored locally (not encrypted)
- Built for educational purposes

---

## Future Improvements
- Encrypt stored passwords (AES / BCrypt)
- Move from JSON files to a database (MySQL / Firebase)
- Improve UI using JavaFX or a web frontend
- Implement real SMS OTP verification
- Add master password hashing

---

## Author
Kalp Trivedi