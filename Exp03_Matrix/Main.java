import java.util.Scanner;

/**
 * Experiment 3: Matrix operations using 2D arrays.
 * Addition, multiplication, transpose, row-wise and column-wise sums,
 * with dimension validation.
 */
public class Main {

    static int[][] readMatrix(Scanner sc, String name) {
        int r, c;
        do {
            System.out.print("Enter rows and columns of Matrix " + name + ": ");
            r = sc.nextInt();
            c = sc.nextInt();
            if (r <= 0 || c <= 0) System.out.println("Dimensions must be positive.");
        } while (r <= 0 || c <= 0);

        int[][] m = new int[r][c];
        System.out.println("Enter " + (r * c) + " elements of Matrix " + name + ":");
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                m[i][j] = sc.nextInt();
        return m;
    }

    static void print(String title, int[][] m) {
        System.out.println(title);
        for (int[] row : m) {
            for (int v : row) System.out.printf("%6d", v);
            System.out.println();
        }
    }

    static int[][] add(int[][] a, int[][] b) {
        int[][] c = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < b.length; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    static int[][] transpose(int[][] a) {
        int[][] t = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                t[j][i] = a[i][j];
        return t;
    }

    static void rowSums(String name, int[][] a) {
        System.out.println("Row-wise sums of " + name + ":");
        for (int i = 0; i < a.length; i++) {
            int s = 0;
            for (int j = 0; j < a[0].length; j++) s += a[i][j];
            System.out.println("  Row " + (i + 1) + " = " + s);
        }
    }

    static void colSums(String name, int[][] a) {
        System.out.println("Column-wise sums of " + name + ":");
        for (int j = 0; j < a[0].length; j++) {
            int s = 0;
            for (int i = 0; i < a.length; i++) s += a[i][j];
            System.out.println("  Column " + (j + 1) + " = " + s);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] a = readMatrix(sc, "A");
        int[][] b = readMatrix(sc, "B");

        System.out.println("\n===== RESULTS =====");
        print("Matrix A:", a);
        print("Matrix B:", b);

        System.out.println("\n-- Addition --");
        if (a.length == b.length && a[0].length == b[0].length)
            print("A + B:", add(a, b));
        else
            System.out.println("Matrix addition is not possible.");

        System.out.println("\n-- Multiplication --");
        if (a[0].length == b.length)
            print("A x B:", multiply(a, b));
        else
            System.out.println("Matrix multiplication is not possible.");

        System.out.println("\n-- Transpose --");
        print("Transpose of A:", transpose(a));
        print("Transpose of B:", transpose(b));

        System.out.println("\n-- Sums --");
        rowSums("A", a);
        colSums("A", a);
        rowSums("B", b);
        colSums("B", b);

        sc.close();
    }
}
