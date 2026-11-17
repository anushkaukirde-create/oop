import java.sql.*;
import java.util.Scanner;

/**
 * Experiment 10: JDBC CRUD on a student table (MySQL).
 * Uses PreparedStatement (prevents SQL injection) and manual
 * transaction management: commit() on success, rollback() on error.
 *
 * Requires: MySQL server running, database "college" created
 * (see schema.sql), and mysql-connector-j.jar inside the lib folder.
 */
public class Main {

    static final String URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASSWORD = "root";   // change to your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            con.setAutoCommit(false);              // manual transaction control
            System.out.println("Connected to MySQL database 'college'.");
            createTableIfMissing(con);

            boolean exit = false;
            while (!exit) {
                System.out.println("\n===== STUDENT MANAGEMENT (JDBC) =====");
                System.out.println("1. Create (insert student)");
                System.out.println("2. Read (view all students)");
                System.out.println("3. Update student");
                System.out.println("4. Delete student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                if (!sc.hasNextLine()) break;
                String choice = sc.nextLine().trim();

                try {
                    switch (choice) {
                        case "1": insertStudent(con, sc); break;
                        case "2": viewStudents(con);      break;
                        case "3": updateStudent(con, sc); break;
                        case "4": deleteStudent(con, sc); break;
                        case "5": exit = true;            break;
                        default:  System.out.println("Invalid choice.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number entered.");
                } catch (SQLException e) {
                    rollbackQuietly(con);
                    System.out.println("Database error: " + e.getMessage() + "  (transaction rolled back)");
                }
            }
            System.out.println("Closing connection. Goodbye!");

        } catch (SQLException e) {
            System.out.println("Could not connect to the database: " + e.getMessage());
            System.out.println("Checklist:");
            System.out.println("  1. Is MySQL server running on localhost:3306?");
            System.out.println("  2. Did you create the 'college' database? (run schema.sql)");
            System.out.println("  3. Is the USER/PASSWORD in Main.java correct?");
            System.out.println("  4. Is mysql-connector-j.jar inside the 'lib' folder?");
        }
        sc.close();
    }

    static void createTableIfMissing(Connection con) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS student ("
                + "id INT PRIMARY KEY, name VARCHAR(50), course VARCHAR(50), marks DOUBLE)";
        try (Statement st = con.createStatement()) {
            st.execute(sql);
            con.commit();
        }
    }

    // CREATE
    static void insertStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Course: ");
        String course = sc.nextLine().trim();
        System.out.print("Marks: ");
        double marks = Double.parseDouble(sc.nextLine().trim());

        String sql = "INSERT INTO student (id, name, course, marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setDouble(4, marks);
            int rows = ps.executeUpdate();
            con.commit();
            System.out.println(rows + " record inserted.");
        }
    }

    // READ
    static void viewStudents(Connection con) throws SQLException {
        String sql = "SELECT id, name, course, marks FROM student ORDER BY id";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.printf("%-6s %-20s %-15s %-8s%n", "ID", "NAME", "COURSE", "MARKS");
            System.out.println("-------------------------------------------------------");
            int count = 0;
            while (rs.next()) {
                System.out.printf("%-6d %-20s %-15s %-8.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("course"), rs.getDouble("marks"));
                count++;
            }
            if (count == 0) System.out.println("No records found.");
        }
    }

    // UPDATE
    static void updateStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("ID of student to update: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("New name: ");
        String name = sc.nextLine().trim();
        System.out.print("New course: ");
        String course = sc.nextLine().trim();
        System.out.print("New marks: ");
        double marks = Double.parseDouble(sc.nextLine().trim());

        String sql = "UPDATE student SET name = ?, course = ?, marks = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, marks);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            con.commit();
            System.out.println(rows > 0 ? "Record updated." : "No student with ID " + id);
        }
    }

    // DELETE
    static void deleteStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("ID of student to delete: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            con.commit();
            System.out.println(rows > 0 ? "Record deleted." : "No student with ID " + id);
        }
    }

    static void rollbackQuietly(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ignored) {
        }
    }
}
