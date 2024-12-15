import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingApp {

    // Static lists to store users, admins, and product catalog
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static HashMap<String, Product> productCatalog = new HashMap<>();
    private static User loggedInUser = null;
    private static ArrayList<Product> cart = new ArrayList<>();  // User's cart

    // Main method to run the application
    public static void main(String[] args) {
        // Initialize sample data
        admins.add(new Admin("admin", "admin123"));
        productCatalog.put("Laptop", new Product("Laptop", 90000.00));
        productCatalog.put("Smartphone", new Product("Smartphone", 6000.00));
        productCatalog.put("Headphones", new Product("Headphones", 250.00));

        SwingUtilities.invokeLater(() -> new MainMenu());
    }

    // User Class
    static class User {
        String username;
        String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    // Admin Class
    static class Admin {
        String username;
        String password;

        public Admin(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    // Product Class
    static class Product {
        String productName;
        double price;

        public Product(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }
    }

    // MainMenu Frame (Starting point)
    static class MainMenu extends JFrame {
        public MainMenu() {
            setTitle("Shopping App");
            setSize(300, 200);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1));

            JButton userLoginButton = new JButton("User Login");
            JButton userRegisterButton = new JButton("User Registration");
            JButton adminLoginButton = new JButton("Admin Login");
            JButton exitButton = new JButton("Exit");

            userLoginButton.addActionListener(e -> {
                dispose();
                new UserLogin();
            });

            userRegisterButton.addActionListener(e -> {
                dispose();
                new UserRegistration();
            });

            adminLoginButton.addActionListener(e -> {
                dispose();
                new AdminLogin();
            });

            exitButton.addActionListener(e -> System.exit(0));

            panel.add(userLoginButton);
            panel.add(userRegisterButton);
            panel.add(adminLoginButton);
            panel.add(exitButton);

            add(panel);
            setVisible(true);
        }
    }

    // User Login Frame
    static class UserLogin extends JFrame {
        public UserLogin() {
            setTitle("User Login");
            setSize(300, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel usernameLabel = new JLabel("Username:");
            JTextField usernameField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");
            JButton backButton = new JButton("Back");

            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                for (User user : users) {
                    if (user.username.equals(username) && user.password.equals(password)) {
                        loggedInUser = user;
                        JOptionPane.showMessageDialog(this, "Login successful. Welcome " + username + "!");
                        dispose();
                        new UserMenu();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            });

            backButton.addActionListener(e -> {
                dispose();
                new MainMenu();
            });

            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(loginButton);
            panel.add(backButton);

            add(panel);
            setVisible(true);
        }
    }

    // User Registration Frame
    static class UserRegistration extends JFrame {
        public UserRegistration() {
            setTitle("User Registration");
            setSize(300, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel usernameLabel = new JLabel("Username:");
            JTextField usernameField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField();
            JButton registerButton = new JButton("Register");
            JButton backButton = new JButton("Back");

            registerButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                users.add(new User(username, password));
                JOptionPane.showMessageDialog(this, "Registration successful.");
                dispose();
                new MainMenu();
            });

            backButton.addActionListener(e -> {
                dispose();
                new MainMenu();
            });

            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(registerButton);
            panel.add(backButton);

            add(panel);
            setVisible(true);
        }
    }

    // Admin Login Frame
    static class AdminLogin extends JFrame {
        public AdminLogin() {
            setTitle("Admin Login");
            setSize(300, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel usernameLabel = new JLabel("Username:");
            JTextField usernameField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");
            JButton backButton = new JButton("Back");

            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                for (Admin admin : admins) {
                    if (admin.username.equals(username) && admin.password.equals(password)) {
                        JOptionPane.showMessageDialog(this, "Admin login successful.");
                        dispose();
                        new AdminMenu();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Invalid admin username or password.");
            });

            backButton.addActionListener(e -> {
                dispose();
                new MainMenu();
            });

            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(loginButton);
            panel.add(backButton);

            add(panel);
            setVisible(true);
        }
    }

    // Admin Menu Frame
    static class AdminMenu extends JFrame {
        public AdminMenu() {
            setTitle("Admin Menu");
            setSize(300, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 1));
            JButton addProductButton = new JButton("Add Product");
            JButton viewProductsButton = new JButton("View Products");
            JButton logoutButton = new JButton("Logout");

            addProductButton.addActionListener(e -> {
                dispose();
                new AddProduct();
            });

            viewProductsButton.addActionListener(e -> {
                StringBuilder products = new StringBuilder("Available Products:\n");
                for (String productName : productCatalog.keySet()) {
                    Product product = productCatalog.get(productName);
                    products.append("- ").append(product.getProductName()).append(": ₹").append(product.getPrice()).append("\n");
                }
                JOptionPane.showMessageDialog(this, products.toString());
            });

            logoutButton.addActionListener(e -> {
                dispose();
                new MainMenu();
            });

            panel.add(addProductButton);
            panel.add(viewProductsButton);
            panel.add(logoutButton);

            add(panel);
            setVisible(true);
        }
    }

    // Add Product Frame (Admin can add products)
    static class AddProduct extends JFrame {
        public AddProduct() {
            setTitle("Add Product");
            setSize(300, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel productNameLabel = new JLabel("Product Name:");
            JTextField productNameField = new JTextField();
            JLabel priceLabel = new JLabel("Price:");
            JTextField priceField = new JTextField();
            JButton addButton = new JButton("Add");
            JButton backButton = new JButton("Back");

            addButton.addActionListener(e -> {
                String productName = productNameField.getText();
                try {
                    double price = Double.parseDouble(priceField.getText());
                    productCatalog.put(productName, new Product(productName, price));
                    JOptionPane.showMessageDialog(this, "Product added successfully.");
                    dispose();
                    new AdminMenu();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price.");
                }
            });

            backButton.addActionListener(e -> {
                dispose();
                new AdminMenu();
            });

            panel.add(productNameLabel);
            panel.add(productNameField);
            panel.add(priceLabel);
            panel.add(priceField);
            panel.add(addButton);
            panel.add(backButton);

            add(panel);
            setVisible(true);
        }
    }

    // User Menu Frame (Logged-in User)
    static class UserMenu extends JFrame {
        public UserMenu() {
            setTitle("User Menu");
            setSize(300, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(5, 1));
            JButton searchProductButton = new JButton("Search Product");
            JButton viewCartButton = new JButton("View Cart");
            JButton checkoutButton = new JButton("Proceed to Checkout");
            JButton logoutButton = new JButton("Logout");

            searchProductButton.addActionListener(e -> {
                String productName = JOptionPane.showInputDialog(this, "Enter product name:");
                Product product = productCatalog.get(productName);
                if (product != null) {
                    JOptionPane.showMessageDialog(this, "Product found: " + product.getProductName() + " - ₹" + product.getPrice());
                    int option = JOptionPane.showConfirmDialog(this, "Do you want to add it to your cart?");
                    if (option == JOptionPane.YES_OPTION) {
                        cart.add(product);
                        JOptionPane.showMessageDialog(this, product.getProductName() + " added to your cart.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found.");
                }
            });

            viewCartButton.addActionListener(e -> {
                if (cart.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Your cart is empty.");
                } else {
                    StringBuilder cartDetails = new StringBuilder("Your Cart:\n");
                    double total = 0;
                    for (Product product : cart) {
                        cartDetails.append(product.getProductName()).append(": ₹").append(product.getPrice()).append("\n");
                        total += product.getPrice();
                    }
                    cartDetails.append("Total: ₹").append(total);
                    JOptionPane.showMessageDialog(this, cartDetails.toString());
                }
            });

            checkoutButton.addActionListener(e -> {
                if (cart.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Your cart is empty.");
                } else {
                    double total = 0;
                    for (Product product : cart) {
                        total += product.getPrice();
                    }

                    // Payment Method Selection
                    String paymentOption = JOptionPane.showInputDialog(this, "Choose payment method: Online/Offline");
                    if (paymentOption != null && paymentOption.equalsIgnoreCase("Online")) {
                        JOptionPane.showMessageDialog(this, "Processing online payment...");
                        JOptionPane.showMessageDialog(this, "Payment successful! Total: ₹" + total);
                    } else if (paymentOption != null && paymentOption.equalsIgnoreCase("Offline")) {
                        JOptionPane.showMessageDialog(this, "Offline payment selected. Please visit the store.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid payment method selected.");
                        return;
                    }

                    cart.clear();  // Clear cart after payment
                }
            });

            logoutButton.addActionListener(e -> {
                dispose();
                new MainMenu();
            });

            panel.add(searchProductButton);
            panel.add(viewCartButton);
            panel.add(checkoutButton);
            panel.add(logoutButton);

            add(panel);
            setVisible(true);
        }
    }
}
