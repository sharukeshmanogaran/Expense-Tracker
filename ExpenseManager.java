import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private int nextId = 1;
    private static final String FILE_NAME = "expenses.csv";

    // Constructor automatically loads saved expenses when the app starts
    public ExpenseManager() {
        loadFromFile();
    }

    public void addExpense(double amount, String category, String description) {
        Expense newExpense = new Expense(nextId++, amount, category, description, LocalDate.now());
        expenses.add(newExpense);
        saveToFile();
        System.out.println("Expense added & saved!");
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("\n--- All Expenses ---");
        double total = 0;
        for (Expense e : expenses) {
            System.out.println(e);
            total += e.getAmount();
        }
        System.out.printf("Total Spending: Rs. %.2f%n", total);
    }

    public void showCategorySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to summarize.");
            return;
        }
        Map<String, Double> summary = new HashMap<>();
        for (Expense e : expenses) {
            summary.put(e.getCategory(), summary.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        System.out.println("\n--- Spending by Category ---");
        for (Map.Entry<String, Double> entry : summary.entrySet()) {
            System.out.printf("%-12s : Rs. %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public void deleteExpense(int id) {
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        if (removed) {
            saveToFile();
            System.out.println("Expense ID " + id + " deleted.");
        } else {
            System.out.println("Expense ID " + id + " not found.");
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.write(e.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Expense e = Expense.fromCsv(line);
                    expenses.add(e);
                    if (e.getId() >= nextId) {
                        nextId = e.getId() + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}