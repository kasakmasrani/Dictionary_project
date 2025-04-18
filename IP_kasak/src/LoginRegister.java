//LoginRegister.java
//Import required Classes
import java.sql.*;
import java.util.Scanner;

// Class representing a user
class User{

    public String username;
    public String password;
    public int id;

    // Constructor to initialize a user with username and password
    public User(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    // Getter method to retrieve the username 
    public String getUsername() {
        return username;
    }

    // Getter method to retrieve the password
    public String getPassword() {
        return password;
    }

    // Getter method to retrieve the Id
    public int getId() {
        return id;
    }

    
}

// Class handling login and registration
public class LoginRegister extends Dictionary{
    
    public LoginRegister() throws Exception {
        // default constructor to explicitly handle exception of parent constructor
    }

    // Scanner object to read user input
    static Scanner sc = new Scanner(System.in);
    static String query;

    // Main method to start the program
    public static void main(String[] args) throws Exception{
        
        // Create a new Dictionary object
        Dictionary dictionary = new Dictionary();

        // Loop indefinitely to handle user choices
        while(true){
            System.out.println("\n===================Welcome to Dictionary System===================");
            System.out.println("\nSelect 1 To Register");
            System.out.println("Select 2 To Login");
            System.out.println("Select 3 To Exit\n");
                                
            int choice = dictionary.getValidChoice();
    
            if(choice == 1){
                register();
            }
            else if(choice == 2){
                login(dictionary);
                
            }
            else if(choice ==3){
                System.out.println("Exiting.... ");
                System.exit(0);
            }
            else{
                System.out.println("Invalid Choice");
            }
        }
    }

    // Method to handle user registration
    public static void register() throws Exception{
        
        // Prompt user for name
        System.out.print("Enter name:     ");
        String name = sc.nextLine();
        
        // Check if username is available
        if(checkUsername(name)){

            // Generate username
            String username = generateUsername(name);

            System.out.print("Enter E-mail (format: abc@gmail.com)      :   ");
            String email = validateEmail();

            String password = password();
            
            // Add user to database
            addUser(name,username, password, email);

        }
        else{
            System.out.println("User already Exists");
        }

    }

    // Method to generate a username based on first and last names
    public static String generateUsername(String name) {
        try {

            String u_name = String.valueOf(name); 

            // Check if last name is empty
            if (u_name == null || u_name.isEmpty() ) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            String username = u_name;

            // Remove any spaces from the username
            username = username.replaceAll("\\s", ""); // remove spaces

            username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase(); // capitalize first letter
            username += "_" + (int) (Math.random() * 100); // add random suffix
            return username;

        } catch (IllegalArgumentException e) {
            // Handle the case where the first or last name is empty
            System.out.println("Error: " + e.getMessage());
            return null;

        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return null;
    }
}

    // Method to prompt user for password and confirm password
    private static String password(){
        while(true){
            System.out.print("Enter Password    :   ");
            String password = sc.nextLine();
            System.out.print("Confirm Password  :   ");
            String confirmPassword = sc.nextLine();
            if(password.equals(confirmPassword)){
                return password;
            }
            else{
                System.out.println("Password and confirm password must be same");
                System.out.println("Re-Enter Password");
            }
        }
    }

    // Method to validate email format
    static String validateEmail(){
        while(true){
            String email = sc.nextLine();
            if(email.endsWith("@gmail.com")){
                return email;
            }
            else{
                System.out.println("Re - Enter E-mail in specified format");
            }
        }
    }

    // Method to add user to database
    static void addUser(String name, String username, String password, String email) throws Exception{
        int user_id =0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = Dictionary.connection.prepareStatement("INSERT INTO register(name, E_Mail) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setString(2, email);

            if (pst.executeUpdate() > 0) {

                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user_id = generatedKeys.getInt(1);
                }

            System.out.println("User Added SuccessFully");
            } else {
                System.out.println("Unable to add user, \nTry again\n");
            }

        } 
        catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }

        try (PreparedStatement pst = Dictionary.connection.prepareStatement("INSERT INTO user_auth(username,password,user_id) VALUES (?, ?,?)")) {
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, user_id);

            if (pst.executeUpdate() > 0) {
                connection.commit();
                System.out.println("\nYour username is  " + username);
            } else {
                System.out.println("Unable to add user, \nTry again\n");
            }

        } 
        catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
        connection.setAutoCommit(true);
    }
    
    // Method to check if username is available
    static boolean checkUsername(String name) throws Exception{
        try (PreparedStatement pst = Dictionary.connection.prepareStatement("SELECT Name FROM register WHERE Name = ?")) {
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking username: " + e.getMessage());
            return false;
        }

    }

    // Method to login
    private static void login(Dictionary dictionary) throws Exception{

        System.out.print("Enter username    : ");
        String username = sc.nextLine();
        System.out.print("Enter password    : ");
        String password = sc.nextLine();

        User user = loginUser(username, password);
        if(user!=null){
            
            try (PreparedStatement pst = connection.prepareStatement("INSERT INTO login(user_id) VALUES (?)")) {
                
                pst.setInt(1, user.id);
    
                if (pst.executeUpdate() > 0) {
                    System.out.println("Login Successfull!");
                    Dictionary.recordHistory(username, "Login success", "-------");
                    Dictionary.performDictionaryOperations(user,dictionary);
                } else {
                    System.out.println("Unable to add user, \nTry again\n");
                }
    
            } 
            catch (SQLException e) {
                System.out.println("Error adding user: " + e.getMessage());
            }
              
        }
        else{
            System.out.println("Invalid username or password!");
            Dictionary.recordHistory(username, "Login Failed", "-------");
        }
    }
 
    // Method to extract user data from database
    static User loginUser(String username, String password) throws Exception{
        String query = "SELECT * FROM user_auth WHERE username = ? and password = ?";
        try( PreparedStatement pst = Dictionary.connection.prepareStatement(query)){
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                User user = new User(resultSet.getInt(4),username,password);
                return user;
    }
        }
        return null;
    }
   
    
}
