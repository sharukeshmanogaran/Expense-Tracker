import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Personal Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Category Summary");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount (Rs.): ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        if (amount <= 0) {
                            System.out.println("Amount must be greater than zero.");
                            break;
                        }

                        System.out.print("Enter category (Food/Travel/Bills/Other): ");
                        String category = scanner.nextLine();

                        System.out.print("Enter description: ");
                        String desc = scanner.nextLine();

                        manager.addExpense(amount, category, desc);
                        break;
                    case 2:
                        manager.viewAllExpenses();
                        break;
                    case 3:
                        manager.showCategorySummary();
                        break;
                    case 4:
                        System.out.print("Enter Expense ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        manager.deleteExpense(id);
                        break;
                    case 5:
                        System.out.println("Exiting. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine(); // Clear bad input so it doesn't loop infinitely
            }
        }
    }
}