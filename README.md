# Personal Expense Tracker (Java CLI)

A console-based Personal Expense Tracker built with Core Java to record, categorize, summarize, and manage daily expenses with persistent CSV storage.

## Features
- **Add & Delete Expenses:** Record expenses with amount, category, description, and automatic date stamping (`LocalDate`).
- **Category Summary:** Groups and calculates total spending per category using `HashMap`.
- **Persistent Storage:** Automatically saves and loads records to `expenses.csv` using `BufferedWriter` and `BufferedReader`.
- **Input Validation:** Handles invalid non-numeric user inputs gracefully using `try-catch`.

## Concepts Used
- Object-Oriented Programming (Encapsulation, Constructors, `toString()`)
- Java Collections Framework (`ArrayList`, `HashMap`)
- File I/O (`java.io.BufferedReader`, `BufferedWriter`)
- Exception Handling (`InputMismatchException`, `IOException`)

## How to Run
1. Compile the files: `javac *.java`
2. Run the application: `java App`
3