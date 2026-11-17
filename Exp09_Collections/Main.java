import java.util.*;

/**
 * Experiment 9: Employee Directory using the Collections Framework.
 * ArrayList -> ordered employee records (allows duplicates)
 * HashSet   -> unique department names
 * HashMap   -> employee ID -> employee (unique keys)
 * Iterator  -> traverse the ArrayList
 */
public class Main {

    static ArrayList<Employee> employees = new ArrayList<>();
    static HashSet<String> departments = new HashSet<>();
    static HashMap<Integer, Employee> employeeMap = new HashMap<>();

    static void addEmployee(Employee e) {
        if (employeeMap.containsKey(e.id)) {
            System.out.println("Employee ID " + e.id + " already exists. Not added.");
            return;
        }
        employees.add(e);
        departments.add(e.department);
        employeeMap.put(e.id, e);
        System.out.println("Added: " + e);
    }

    static void displayAll() {
        System.out.println("\n--- All Employees (via Iterator) ---");
        if (employees.isEmpty()) {
            System.out.println("No records.");
            return;
        }
        Iterator<Employee> itr = employees.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    static void showHierarchy() {
        System.out.println("\n--- Basic collection hierarchy ---");
        Collection<Employee> c = employees;
        System.out.println("ArrayList is a List?        " + (c instanceof List));
        System.out.println("ArrayList is a Collection?  " + (c instanceof Collection));
        System.out.println("HashSet is a Set?           " + ((Object) departments instanceof Set));
        System.out.println("HashSet is a Collection?    " + ((Object) departments instanceof Collection));
        System.out.println("HashMap is a Collection?    " + ((Object) employeeMap instanceof Collection)
                + "  (Map does not extend Collection)");
        System.out.println("\nCollection");
        System.out.println(" |-- List  -> ArrayList");
        System.out.println(" |-- Set   -> HashSet");
        System.out.println(" '-- Queue");
        System.out.println("Map (separate) -> HashMap");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        addEmployee(new Employee(101, "Amit Sharma", "IT"));
        addEmployee(new Employee(102, "Neha Joshi", "HR"));
        addEmployee(new Employee(103, "Rahul Patil", "IT"));
        addEmployee(new Employee(104, "Sneha Kulkarni", "Finance"));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== EMPLOYEE DIRECTORY =====");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees (Iterator)");
            System.out.println("3. Show unique departments (HashSet)");
            System.out.println("4. Search employee by ID (HashMap)");
            System.out.println("5. Show collection hierarchy");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextLine()) break;
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Department: ");
                        String dept = sc.nextLine().trim();
                        addEmployee(new Employee(id, name, dept));
                        break;
                    case "2":
                        displayAll();
                        break;
                    case "3":
                        System.out.println("Departments (" + departments.size() + " unique): " + departments);
                        break;
                    case "4":
                        System.out.print("Enter ID: ");
                        int sid = Integer.parseInt(sc.nextLine().trim());
                        Employee found = employeeMap.get(sid);
                        System.out.println(found != null ? found : "No employee with ID " + sid);
                        break;
                    case "5":
                        showHierarchy();
                        break;
                    case "6":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered.");
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }
}

class Employee {
    final int id;
    final String name;
    final String department;

    Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Dept: " + department;
    }
}
