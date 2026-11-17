import java.util.Scanner;

/**
 * Experiment 4: Inheritance - Product base class with
 * Electronics and Clothing subclasses.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Enter Electronics product ---");
        System.out.print("Product ID: ");
        int id1 = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Product Name: ");
        String name1 = sc.nextLine();
        System.out.print("Brand: ");
        String brand = sc.nextLine();
        System.out.print("Warranty (years): ");
        int warranty = Integer.parseInt(sc.nextLine().trim());
        Electronics e = new Electronics(id1, name1, brand, warranty);

        System.out.println("\n--- Enter Clothing product ---");
        System.out.print("Product ID: ");
        int id2 = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Product Name: ");
        String name2 = sc.nextLine();
        System.out.print("Size: ");
        String size = sc.nextLine();
        System.out.print("Material: ");
        String material = sc.nextLine();
        Clothing c = new Clothing(id2, name2, size, material);

        System.out.println("\n===== PRODUCT DETAILS =====");
        e.displayElectronics();
        System.out.println();
        c.displayClothing();
        sc.close();
    }
}

class Product {
    protected int productId;
    protected String productName;

    Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    void displayProduct() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Product Name : " + productName);
    }
}

class Electronics extends Product {
    private String brand;
    private int warranty;

    Electronics(int id, String name, String brand, int warranty) {
        super(id, name);
        this.brand = brand;
        this.warranty = warranty;
    }

    void displayElectronics() {
        System.out.println("[Electronics]");
        displayProduct();
        System.out.println("Brand        : " + brand);
        System.out.println("Warranty     : " + warranty + " year(s)");
    }
}

class Clothing extends Product {
    private String size;
    private String material;

    Clothing(int id, String name, String size, String material) {
        super(id, name);
        this.size = size;
        this.material = material;
    }

    void displayClothing() {
        System.out.println("[Clothing]");
        displayProduct();
        System.out.println("Size         : " + size);
        System.out.println("Material     : " + material);
    }
}
