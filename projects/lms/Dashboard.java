import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {

    // Main frame and card layouts
    private static JFrame frame;
    private static CardLayout mainCardLayout;
    private static JPanel mainPanel;
    private static CardLayout centerCardLayout;
    private static JPanel centerPanel;

    // Login components
    private static JTextField loginUsernameField;
    private static JPasswordField loginPasswordField;

    // Navigation buttons
    private static JButton dashboardNavBtn;
    private static JButton addBookNavBtn;
    private static JButton deleteBookNavBtn;
    private static JButton viewBooksNavBtn;
    private static JButton searchBookNavBtn;
    private static JButton issueBookNavBtn;
    private static JButton returnBookNavBtn;
    private static JButton viewIssuedBooksNavBtn;
    private static JButton logoutNavBtn;

    // Add Book form fields
    private static JTextField addBookIdField;
    private static JTextField addBookNameField;
    private static JTextField addAuthorField;

    // Delete Book form fields
    private static JTextField deleteBookIdField;

    // Search Book form fields
    private static JTextField searchBookIdField;
    private static JLabel searchResultNameLabel;
    private static JLabel searchResultAuthorLabel;
    private static JLabel searchResultStatusLabel;

    // Issue Book form fields
    private static JTextField issueStudentField;
    private static JTextField issueBookIdField;

    // Return Book form fields
    private static JTextField returnBookIdField;

    // Register form fields
    private static JTextField registerUsernameField;
    private static JPasswordField registerPasswordField;
    private static JPasswordField registerConfirmPasswordField;

    // JTables
    private static JTable booksTable;
    private static DefaultTableModel booksTableModel;
    private static JTable issuedBooksTable;
    private static DefaultTableModel issuedBooksTableModel;

    // ============ CUSTOM TABLE HEADER RENDERER ============
    
    private static class CustomTableHeaderRenderer extends javax.swing.table.DefaultTableCellRenderer {
        public CustomTableHeaderRenderer() {
            setOpaque(true);
            setBackground(new Color(25, 35, 75));
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setHorizontalAlignment(SwingConstants.CENTER);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 50, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));
        }
    }
    
    // ============ DATABASE CONNECTION ============

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:library.db?busy_timeout=5000";
        return DriverManager.getConnection(url);
    }

    // ============ MAIN METHOD ============

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "SQLite JDBC Driver not found!");
            return;
        }

        // Set Nimbus LookAndFeel for consistent colors on macOS
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus not available, use default
        }

        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);

        mainCardLayout = new CardLayout();
        mainPanel = new JPanel(mainCardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel registerPanel = createRegisterPanel();
        JPanel dashboardPanel = createDashboardPanel();

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(dashboardPanel, "dashboard");

        frame.add(mainPanel);
        mainCardLayout.show(mainPanel, "login");
        frame.setVisible(true);
    }

    // ============ LOGIN PANEL ============

    private static JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // Title label
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(30, 60, 114));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Login form panel
        JPanel loginForm = new JPanel(new GridBagLayout());
        loginForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(30, 60, 114), 2),
            "Login",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(30, 60, 114)
        ));
        loginForm.setBackground(Color.WHITE);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(6, 6, 6, 6);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        loginForm.add(new JLabel("Username:"), gbc2);
        gbc2.gridx = 1;
        loginUsernameField = new JTextField(15);
        loginForm.add(loginUsernameField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        loginForm.add(new JLabel("Password:"), gbc2);
        gbc2.gridx = 1;
        loginPasswordField = new JPasswordField(15);
        loginForm.add(loginPasswordField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnPanel.setBackground(Color.WHITE);

        JButton loginBtn = createStyledButton("Login");
        JButton registerBtn = createStyledButton("Register");
        JButton exitBtn = createStyledButton("Exit");

        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);
        btnPanel.add(exitBtn);
        loginForm.add(btnPanel, gbc2);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUsernameField.setText("");
                registerPasswordField.setText("");
                registerConfirmPasswordField.setText("");
                mainCardLayout.show(mainPanel, "register");
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(loginForm, gbc);

        return panel;
    }

    // ============ REGISTER PANEL ============

    private static JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // Title label
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(30, 60, 114));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Register form panel
        JPanel registerForm = new JPanel(new GridBagLayout());
        registerForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(30, 60, 114), 2),
            "Create Account",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(30, 60, 114)
        ));
        registerForm.setBackground(Color.WHITE);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(6, 6, 6, 6);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        registerForm.add(new JLabel("Username:"), gbc2);
        gbc2.gridx = 1;
        registerUsernameField = new JTextField(15);
        registerForm.add(registerUsernameField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        registerForm.add(new JLabel("Password:"), gbc2);
        gbc2.gridx = 1;
        registerPasswordField = new JPasswordField(15);
        registerForm.add(registerPasswordField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        registerForm.add(new JLabel("Confirm Password:"), gbc2);
        gbc2.gridx = 1;
        registerConfirmPasswordField = new JPasswordField(15);
        registerForm.add(registerConfirmPasswordField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnPanel.setBackground(Color.WHITE);

        JButton registerBtn = createStyledButton("Register");
        JButton backToLoginBtn = createStyledButton("Back to Login");

        btnPanel.add(registerBtn);
        btnPanel.add(backToLoginBtn);
        registerForm.add(btnPanel, gbc2);

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        backToLoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(mainPanel, "login");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(registerForm, gbc);

        return panel;
    }

    // ============ DASHBOARD PANEL ============

    private static JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // ===== TOP HEADER BAR =====
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(25, 35, 75));
        headerPanel.setPreferredSize(new Dimension(0, 50));

        JLabel headerTitle = new JLabel("  📚 Library Management System");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 18));
        headerTitle.setForeground(Color.WHITE);
        headerPanel.add(headerTitle, BorderLayout.WEST);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        headerRight.setOpaque(false);
        JLabel userLabel = new JLabel("👤 Librarian");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        userLabel.setForeground(new Color(200, 210, 255));
        headerRight.add(userLabel);

        JButton headerLogoutBtn = new JButton("🚪 Logout");
        headerLogoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        headerLogoutBtn.setBackground(new Color(211, 47, 47));
        headerLogoutBtn.setForeground(Color.WHITE);
        headerLogoutBtn.setFocusPainted(false);
        headerLogoutBtn.setBorderPainted(false);
        headerLogoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        headerLogoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        headerRight.add(headerLogoutBtn);
        headerPanel.add(headerRight, BorderLayout.EAST);

        // ===== LEFT NAVIGATION PANEL =====
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(25, 35, 75));
        navPanel.setPreferredSize(new Dimension(200, 0));

        // Navigation header with icon
        JLabel navHeader = new JLabel("  📋 MENU");
        navHeader.setFont(new Font("Arial", Font.BOLD, 15));
        navHeader.setForeground(new Color(180, 190, 255));
        navHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        navHeader.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        navPanel.add(navHeader);

        // Separator
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(60, 70, 120));
        sep.setMaximumSize(new Dimension(170, 1));
        sep.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(sep);
        navPanel.add(Box.createVerticalStrut(8));

        // Create navigation buttons with icons
        dashboardNavBtn = createNavButton("🏠 Dashboard");
        addBookNavBtn = createNavButton("➕ Add Book");
        deleteBookNavBtn = createNavButton("🗑️ Delete Book");
        viewBooksNavBtn = createNavButton("📖 View Books");
        searchBookNavBtn = createNavButton("🔍 Search Book");
        issueBookNavBtn = createNavButton("📤 Issue Book");
        returnBookNavBtn = createNavButton("📥 Return Book");
        viewIssuedBooksNavBtn = createNavButton("📋 Issued Books");
        logoutNavBtn = createNavButton("🚪 Logout");

        navPanel.add(dashboardNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(addBookNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(deleteBookNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(viewBooksNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(searchBookNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(issueBookNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(returnBookNavBtn);
        navPanel.add(Box.createVerticalStrut(4));
        navPanel.add(viewIssuedBooksNavBtn);
        navPanel.add(Box.createVerticalStrut(8));

        // Separator before logout
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(60, 70, 120));
        sep2.setMaximumSize(new Dimension(170, 1));
        sep2.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(sep2);
        navPanel.add(Box.createVerticalStrut(8));

        navPanel.add(logoutNavBtn);
        navPanel.add(Box.createVerticalGlue());

        // ===== CENTER CONTENT AREA =====
        centerCardLayout = new CardLayout();
        centerPanel = new JPanel(centerCardLayout);
        centerPanel.setBackground(new Color(245, 245, 250));

        centerPanel.add(createHomePanel(), "home");
        centerPanel.add(createAddBookPanel(), "addBook");
        centerPanel.add(createDeleteBookPanel(), "deleteBook");
        centerPanel.add(createViewBooksPanel(), "viewBooks");
        centerPanel.add(createSearchBookPanel(), "searchBook");
        centerPanel.add(createIssueBookPanel(), "issueBook");
        centerPanel.add(createReturnBookPanel(), "returnBook");
        centerPanel.add(createViewIssuedBooksPanel(), "viewIssuedBooks");

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(navPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    // Helper method to create navigation buttons
    private static JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(190, 42));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(new Color(35, 45, 85));
        btn.setForeground(new Color(200, 210, 255));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);

        // Mouse hover effects
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(45, 60, 120));
                btn.setForeground(Color.WHITE);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 40, 90));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(45, 60, 120));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(35, 45, 85));
                btn.setForeground(new Color(200, 210, 255));
            }
        });

        // Strip emoji and space prefix for action commands
        String actionCmd = text.replaceAll("[^a-zA-Z ]", "").trim();
        if (actionCmd.equals("Dashboard")) {
            // Set active state for Dashboard by default
            btn.setBackground(new Color(55, 75, 150));
            btn.setForeground(Color.WHITE);
        }

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset all nav buttons
                resetNavButtons();
                btn.setBackground(new Color(55, 75, 150));
                btn.setForeground(Color.WHITE);

                switch (actionCmd) {
                    case "Dashboard":
                        centerCardLayout.show(centerPanel, "home");
                        break;
                    case "Add Book":
                        centerCardLayout.show(centerPanel, "addBook");
                        break;
                    case "Delete Book":
                        centerCardLayout.show(centerPanel, "deleteBook");
                        break;
                    case "View Books":
                        loadBooksTable();
                        centerCardLayout.show(centerPanel, "viewBooks");
                        break;
                    case "Search Book":
                        centerCardLayout.show(centerPanel, "searchBook");
                        break;
                    case "Issue Book":
                        centerCardLayout.show(centerPanel, "issueBook");
                        break;
                    case "Return Book":
                        centerCardLayout.show(centerPanel, "returnBook");
                        break;
                    case "Issued Books":
                        loadIssuedBooksTable();
                        centerCardLayout.show(centerPanel, "viewIssuedBooks");
                        break;
                    case "Logout":
                        logout();
                        break;
                }
            }
        });

        return btn;
    }

    // Reset all navigation buttons to default state
    private static void resetNavButtons() {
        JButton[] navBtns = {
            dashboardNavBtn, addBookNavBtn, deleteBookNavBtn, viewBooksNavBtn,
            searchBookNavBtn, issueBookNavBtn, returnBookNavBtn, viewIssuedBooksNavBtn, logoutNavBtn
        };
        for (JButton navBtn : navBtns) {
            navBtn.setBackground(new Color(35, 45, 85));
            navBtn.setForeground(new Color(200, 210, 255));
        }
    }

    // ============ REUSABLE STYLED BUTTON HELPER ============

    private static JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(41, 98, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(130, 38));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);

        // Mouse hover effects
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(66, 133, 244));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(25, 75, 200));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(66, 133, 244));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(41, 98, 255));
            }
        });

        return btn;
    }

    // ============ CENTER PANEL BUILDERS ============

    private static JPanel createHomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Welcome section
        JLabel welcomeLabel = new JLabel("📚 Welcome to Library Management System");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        welcomeLabel.setForeground(new Color(25, 35, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(welcomeLabel, gbc);

        JLabel subLabel = new JLabel("Use the navigation menu to manage books and users");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subLabel.setForeground(Color.GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subLabel, gbc);

        // Stats Cards
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Card 1: Total Books
        JPanel totalBooksCard = createStatCard("📖", "Total Books", getBookCount(), new Color(41, 98, 255));
        gbc.gridx = 0;
        panel.add(totalBooksCard, gbc);

        // Card 2: Issued Books
        JPanel issuedBooksCard = createStatCard("📤", "Issued Books", getIssuedBookCount(), new Color(255, 152, 0));
        gbc.gridx = 1;
        panel.add(issuedBooksCard, gbc);

        // Card 3: Available Books
        JPanel availableBooksCard = createStatCard("✅", "Available Books", getAvailableBookCount(), new Color(76, 175, 80));
        gbc.gridx = 2;
        panel.add(availableBooksCard, gbc);

        // Quick actions section
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(25, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel quickActionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        quickActionsPanel.setBackground(new Color(245, 245, 250));

        JLabel quickLabel = new JLabel("Quick Actions:");
        quickLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        quickLabel.setForeground(new Color(25, 35, 75));
        quickActionsPanel.add(quickLabel);

        JButton quickAddBook = createStyledButton("➕ Add Book");
        quickAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerCardLayout.show(centerPanel, "addBook");
                resetNavButtons();
                addBookNavBtn.setBackground(new Color(55, 75, 150));
                addBookNavBtn.setForeground(Color.WHITE);
            }
        });
        quickActionsPanel.add(quickAddBook);

        JButton quickIssueBook = createStyledButton("📤 Issue Book");
        quickIssueBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerCardLayout.show(centerPanel, "issueBook");
                resetNavButtons();
                issueBookNavBtn.setBackground(new Color(55, 75, 150));
                issueBookNavBtn.setForeground(Color.WHITE);
            }
        });
        quickActionsPanel.add(quickIssueBook);

        JButton quickViewBooks = createStyledButton("📖 View Books");
        quickViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBooksTable();
                centerCardLayout.show(centerPanel, "viewBooks");
                resetNavButtons();
                viewBooksNavBtn.setBackground(new Color(55, 75, 150));
                viewBooksNavBtn.setForeground(Color.WHITE);
            }
        });
        quickActionsPanel.add(quickViewBooks);

        panel.add(quickActionsPanel, gbc);

        return panel;
    }

    // Helper to create a stat card panel
    private static JPanel createStatCard(String emoji, String title, int count, Color accentColor) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        card.setPreferredSize(new Dimension(200, 130));
        card.setMinimumSize(new Dimension(150, 120));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Emoji icon
        JLabel iconLabel = new JLabel(emoji);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        card.add(iconLabel, gbc);

        // Count
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 2, 0);
        JLabel countLabel = new JLabel(String.valueOf(count));
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        countLabel.setForeground(accentColor);
        card.add(countLabel, gbc);

        // Title
        gbc.gridy = 2;
        gbc.insets = new Insets(2, 0, 0, 0);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        card.add(titleLabel, gbc);

        // Add a vertical glue to push content to center vertically
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        card.add(Box.createVerticalGlue(), gbc);

        return card;
    }

    // Get total book count from database
    private static int getBookCount() {
        String query = "SELECT COUNT(*) FROM books";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            // ignore
        }
        return 0;
    }

    // Get issued book count from database
    private static int getIssuedBookCount() {
        String query = "SELECT COUNT(*) FROM issued_books WHERE return_date IS NULL";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            // ignore
        }
        return 0;
    }

    // Get available book count from database
    private static int getAvailableBookCount() {
        int total = getBookCount();
        int issued = getIssuedBookCount();
        return total - issued;
    }

    private static JPanel createAddBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // Title
        JLabel title = new JLabel("➕ Add New Book");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Card panel with shadow effect
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 8, 8, 8);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookIdLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(bookIdLabel, gbc2);
        gbc2.gridx = 1;
        addBookIdField = new JTextField(20);
        addBookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(addBookIdField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookNameLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(bookNameLabel, gbc2);
        gbc2.gridx = 1;
        addBookNameField = new JTextField(20);
        addBookNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(addBookNameField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        authorLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(authorLabel, gbc2);
        gbc2.gridx = 1;
        addAuthorField = new JTextField(20);
        addAuthorField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(addAuthorField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        JButton addBtn = createStyledButton("📥 Add Book");
        addBtn.setPreferredSize(new Dimension(160, 42));
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        formPanel.add(addBtn, gbc2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(formPanel, gbc);

        return panel;
    }

    private static JPanel createDeleteBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("🗑️ Delete Book");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 8, 8, 8);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookIdLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(bookIdLabel, gbc2);
        gbc2.gridx = 1;
        deleteBookIdField = new JTextField(20);
        deleteBookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(deleteBookIdField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        JButton deleteBtn = createStyledButton("🗑️ Delete Book");
        deleteBtn.setPreferredSize(new Dimension(160, 42));
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        formPanel.add(deleteBtn, gbc2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(formPanel, gbc);

        return panel;
    }

    private static JPanel createViewBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 250));

        JLabel title = new JLabel("📖 Books List", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);

        booksTableModel = new DefaultTableModel(new String[]{"Book ID", "Book Name", "Author", "Status"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        booksTable = new JTable(booksTableModel);
        booksTable.setRowHeight(30);
        booksTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        booksTable.setSelectionBackground(new Color(225, 235, 255));
        booksTable.setShowGrid(false);
        booksTable.setIntercellSpacing(new Dimension(0, 0));
        
        // Custom header renderer to ensure proper background and text colors
        booksTable.getTableHeader().setDefaultRenderer(new CustomTableHeaderRenderer());
        booksTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollPane = new JScrollPane(booksTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 15, 5, 15),
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1)
        ));
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(245, 245, 250));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        JButton refreshBtn = createStyledButton("🔄 Refresh");
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBooksTable();
            }
        });
        btnPanel.add(refreshBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel createSearchBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("🔍 Search Book");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        searchPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 8, 8, 8);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookIdLabel.setForeground(new Color(60, 60, 70));
        searchPanel.add(bookIdLabel, gbc2);
        gbc2.gridx = 1;
        searchBookIdField = new JTextField(20);
        searchBookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(searchBookIdField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        JButton searchBtn = createStyledButton("🔍 Search");
        searchBtn.setPreferredSize(new Dimension(160, 42));
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        searchPanel.add(searchBtn, gbc2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(searchPanel, gbc);

        // Result display panel
        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        resultPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(8, 8, 8, 8);

        gbc3.gridx = 0;
        gbc3.gridy = 0;
        JLabel nameTitle = new JLabel("Book Name:");
        nameTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameTitle.setForeground(new Color(60, 60, 70));
        resultPanel.add(nameTitle, gbc3);
        gbc3.gridx = 1;
        searchResultNameLabel = new JLabel("");
        searchResultNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchResultNameLabel.setForeground(new Color(25, 35, 75));
        resultPanel.add(searchResultNameLabel, gbc3);

        gbc3.gridx = 0;
        gbc3.gridy = 1;
        JLabel authorTitle = new JLabel("Author:");
        authorTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        authorTitle.setForeground(new Color(60, 60, 70));
        resultPanel.add(authorTitle, gbc3);
        gbc3.gridx = 1;
        searchResultAuthorLabel = new JLabel("");
        searchResultAuthorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchResultAuthorLabel.setForeground(new Color(25, 35, 75));
        resultPanel.add(searchResultAuthorLabel, gbc3);

        gbc3.gridx = 0;
        gbc3.gridy = 2;
        JLabel statusTitle = new JLabel("Status:");
        statusTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusTitle.setForeground(new Color(60, 60, 70));
        resultPanel.add(statusTitle, gbc3);
        gbc3.gridx = 1;
        searchResultStatusLabel = new JLabel("");
        searchResultStatusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchResultStatusLabel.setForeground(new Color(25, 35, 75));
        resultPanel.add(searchResultStatusLabel, gbc3);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(resultPanel, gbc);

        return panel;
    }

    private static JPanel createIssueBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("📤 Issue Book");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 8, 8, 8);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JLabel studentLabel = new JLabel("Student Name:");
        studentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        studentLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(studentLabel, gbc2);
        gbc2.gridx = 1;
        issueStudentField = new JTextField(20);
        issueStudentField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(issueStudentField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookIdLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(bookIdLabel, gbc2);
        gbc2.gridx = 1;
        issueBookIdField = new JTextField(20);
        issueBookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(issueBookIdField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        JButton issueBtn = createStyledButton("📤 Issue Book");
        issueBtn.setPreferredSize(new Dimension(160, 42));
        issueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                issueBook();
            }
        });
        formPanel.add(issueBtn, gbc2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(formPanel, gbc);

        return panel;
    }

    private static JPanel createReturnBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("📥 Return Book");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 8, 8, 8);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookIdLabel.setForeground(new Color(60, 60, 70));
        formPanel.add(bookIdLabel, gbc2);
        gbc2.gridx = 1;
        returnBookIdField = new JTextField(20);
        returnBookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(returnBookIdField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        JButton returnBtn = createStyledButton("📥 Return Book");
        returnBtn.setPreferredSize(new Dimension(160, 42));
        returnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        formPanel.add(returnBtn, gbc2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(formPanel, gbc);

        return panel;
    }

    private static JPanel createViewIssuedBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 250));

        JLabel title = new JLabel("📋 Issued Books List", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25, 35, 75));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);

        issuedBooksTableModel = new DefaultTableModel(
            new String[]{"Issue ID", "Student Name", "Book ID", "Issue Date", "Return Date"}, 0
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        issuedBooksTable = new JTable(issuedBooksTableModel);
        issuedBooksTable.setRowHeight(30);
        issuedBooksTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        issuedBooksTable.setSelectionBackground(new Color(225, 235, 255));
        issuedBooksTable.setShowGrid(false);
        issuedBooksTable.setIntercellSpacing(new Dimension(0, 0));
        
        // Custom header renderer to ensure proper background and text colors
        issuedBooksTable.getTableHeader().setDefaultRenderer(new CustomTableHeaderRenderer());
        issuedBooksTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollPane = new JScrollPane(issuedBooksTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 15, 5, 15),
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1)
        ));
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(245, 245, 250));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        JButton refreshBtn = createStyledButton("🔄 Refresh");
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadIssuedBooksTable();
            }
        });
        btnPanel.add(refreshBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ============ LOGOUT ============

    private static void logout() {
        mainCardLayout.show(mainPanel, "login");
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }

    // ============ DATABASE OPERATIONS ============

    private static void registerUser() {
        String username = registerUsernameField.getText().trim();
        String password = new String(registerPasswordField.getPassword()).trim();
        String confirmPassword = new String(registerConfirmPasswordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, "Passwords do not match!");
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(frame, "Password must be at least 4 characters long!");
            return;
        }

        // Check if username already exists
        String checkQuery = "SELECT * FROM users WHERE username = ?";
        boolean userExists = false;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(checkQuery)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                userExists = rs.next();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        if (userExists) {
            JOptionPane.showMessageDialog(frame, "Username already exists! Please try with a different username.");
            return;
        }

        // Insert new user
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame,
                "Registration Successful! You can now login.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            registerUsernameField.setText("");
            registerPasswordField.setText("");
            registerConfirmPasswordField.setText("");
            mainCardLayout.show(mainPanel, "login");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }

    private static void loginUser() {
        String username = loginUsernameField.getText().trim();
        String password = new String(loginPasswordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter username and password!");
            return;
        }

        String query = "SELECT * FROM users WHERE username=? AND password=?";
        boolean loginSuccess = false;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                loginSuccess = rs.next();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        if (loginSuccess) {
            JOptionPane.showMessageDialog(frame, "Login Successful! Welcome " + username + "!");
            mainCardLayout.show(mainPanel, "dashboard");
            centerCardLayout.show(centerPanel, "home");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Username or Password!");
        }
    }

    private static void addBook() {
        String idStr = addBookIdField.getText().trim();
        String bookName = addBookNameField.getText().trim();
        String author = addAuthorField.getText().trim();

        if (idStr.isEmpty() || bookName.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Book ID must be a number!");
            return;
        }

        String query = "INSERT INTO books (book_id, book_name, author, status) VALUES (?, ?, ?, 'Available')";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, bookId);
            pstmt.setString(2, bookName);
            pstmt.setString(3, author);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book Added Successfully!");
            addBookIdField.setText("");
            addBookNameField.setText("");
            addAuthorField.setText("");
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE") || e.getMessage().contains("PRIMARY KEY")) {
                JOptionPane.showMessageDialog(frame, "Duplicate Book ID! Please use a different Book ID.");
            } else {
                JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            }
        }
    }

    private static void deleteBook() {
        String idStr = deleteBookIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Book ID!");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Book ID must be a number!");
            return;
        }

        // Check if book exists
        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        boolean bookExists = false;
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(checkQuery)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                bookExists = rs.next();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        if (!bookExists) {
            JOptionPane.showMessageDialog(frame, "Book Not Found!");
            return;
        }

        // Delete the book
        String deleteQuery = "DELETE FROM books WHERE book_id = ?";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book Deleted Successfully!");
            deleteBookIdField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }

    private static void loadBooksTable() {
        booksTableModel.setRowCount(0);
        String query = "SELECT * FROM books ORDER BY book_id";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("book_name");
                String author = rs.getString("author");
                String status = rs.getString("status");
                booksTableModel.addRow(new Object[]{id, name, author, status});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }

    private static void searchBook() {
        String idStr = searchBookIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Book ID!");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Book ID must be a number!");
            return;
        }

        String query = "SELECT * FROM books WHERE book_id = ?";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    searchResultNameLabel.setText(rs.getString("book_name"));
                    searchResultAuthorLabel.setText(rs.getString("author"));
                    searchResultStatusLabel.setText(rs.getString("status"));
                } else {
                    searchResultNameLabel.setText("");
                    searchResultAuthorLabel.setText("");
                    searchResultStatusLabel.setText("");
                    JOptionPane.showMessageDialog(frame, "Book Not Found!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }

    private static void issueBook() {
        String studentName = issueStudentField.getText().trim();
        String idStr = issueBookIdField.getText().trim();

        if (studentName.isEmpty() || idStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Book ID must be a number!");
            return;
        }

        // Check if book exists and is available
        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        boolean bookFound = false;
        String bookStatus = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(checkQuery)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bookFound = true;
                    bookStatus = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        if (!bookFound) {
            JOptionPane.showMessageDialog(frame, "Book Not Found!");
            return;
        }

        if (!"Available".equals(bookStatus)) {
            JOptionPane.showMessageDialog(frame, "Book is not available! Current status: " + bookStatus);
            return;
        }

        // Update book status to Issued
        String updateQuery = "UPDATE books SET status = 'Issued' WHERE book_id = ?";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        // Insert into issued_books
        String issueDate = LocalDate.now().toString();
        String insertQuery = "INSERT INTO issued_books (student_name, book_id, issue_date) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
            pstmt.setString(1, studentName);
            pstmt.setInt(2, bookId);
            pstmt.setString(3, issueDate);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book Issued Successfully to " + studentName + "!");
            issueStudentField.setText("");
            issueBookIdField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        String idStr = returnBookIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Book ID!");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Book ID must be a number!");
            return;
        }

        // Check if book exists
        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        boolean bookExists = false;
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(checkQuery)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                bookExists = rs.next();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        if (!bookExists) {
            JOptionPane.showMessageDialog(frame, "Book Not Found!");
            return;
        }

        // Update book status to Available
        String updateBookQuery = "UPDATE books SET status = 'Available' WHERE book_id = ?";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateBookQuery)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
            return;
        }

        // Update return date in issued_books
        String returnDate = LocalDate.now().toString();
        String updateIssueQuery = "UPDATE issued_books SET return_date = ? WHERE book_id = ? AND return_date IS NULL";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateIssueQuery)) {
            pstmt.setString(1, returnDate);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book Returned Successfully!");
            returnBookIdField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }

    private static void loadIssuedBooksTable() {
        issuedBooksTableModel.setRowCount(0);
        String query = "SELECT * FROM issued_books ORDER BY issue_id";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int issueId = rs.getInt("issue_id");
                String student = rs.getString("student_name");
                int bookId = rs.getInt("book_id");
                String issueDate = rs.getString("issue_date");
                String returnDate = rs.getString("return_date");
                if (returnDate == null) {
                    returnDate = "Not Returned";
                }
                issuedBooksTableModel.addRow(new Object[]{issueId, student, bookId, issueDate, returnDate});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
        }
    }
}

