# 🤖 AI-Powered Resume Screener

A Java application that uses AI to automatically screen resumes 
against job descriptions and generate match scores.

## 🛠️ Tech Stack
- Java 21
- MySQL (JDBC)
- Groq AI API (Llama 3.3)
- VS Code

## ✨ Features
- Stores jobs and candidates in MySQL database
- Sends resume + job description to AI
- AI gives match score out of 100
- Shows matched and missing skills
- Saves screening result to database

## 📁 Project Structure
- App.java - Main controller
- DatabaseConnection.java - MySQL connection
- TableSetup.java - Creates database tables
- DataInserter.java - Inserts sample data
- AIScreener.java - Calls Groq AI API
- ResultExtractor.java - Parses AI response
- ResultSaver.java - Saves result to MySQL

## 📊 Sample Output
=== Final Screening Report ===
Candidate ID : 1
Job ID       : 1
Score        : 60/100
Matched      : Java, MySQL
Missing      : REST APIs, Git, Spring Boot
Verdict      : Partial match, needs Spring Boot experience
==============================

## 🚀 How to Run
1. Clone the repository
2. Set up MySQL database
3. Add your Groq API key in AIScreener.java
4. Run App.java

## 👨‍💻 Author
Divesh - Java Developer (Fresher)