import java.time.LocalDate;

public class Expense {
    private int id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Expense(int id, double amount, String category, String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }

    // Converts this Expense into a single line for expenses.csv
    public String toCsv() {
        return id + "," + amount + "," + category.replace(",", " ") + "," 
               + description.replace(",", " ") + "," + date;
    }

    // Rebuilds an Expense object from a saved line in expenses.csv
    public static Expense fromCsv(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        double amount = Double.parseDouble(parts[1]);
        String category = parts[2];
        String description = parts[3];
        LocalDate date = LocalDate.parse(parts[4]);
        return new Expense(id, amount, category, description, date);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | %-10s | Rs. %.2f | %s",
                id, date, category, amount, description);
    }
}