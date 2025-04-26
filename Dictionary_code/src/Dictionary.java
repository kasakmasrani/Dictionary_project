
//Dictionary.java

// import necessary package
import java.io.*;
import java.sql.*;
import java.util.*;

// Class representing a word and its meaning
class WordsListClass {
    // Instance variables to store the word and its meaning
    public String word;
    public String meaning;

    // Constructor to initialize the word and meaning
    public WordsListClass(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    // Getter method to retrieve the word
    public String getWord() {
        return word;
    }

    // Setter method to update the word
    public void setWord(String word) {
        this.word = word;
    }
    // Getter method to retrieve the meaning
    public String getMeaning() {
        return meaning;
    }

    // Setter method to update the meaning
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}

public class Dictionary {
    
    // Static variables to store database connection and user choice
    private static final Scanner scanner = new Scanner(System.in);
    public static Connection connection;
    //public int choice;

    // Data structures to store word revisions and list
    public DoublyLinkedList revision;
    public ArrayList<WordsListClass> wordList;

    // Variables to store word, query, and meaning
    private String word, query, meaning, pronounciation;

    // Constructor to initialize database connection and data structures
    public Dictionary() throws Exception{
        try {
            // Initialize database connection and data structures
            // String dbUrl = "jdbc:mysql://localhost:3306/dictionary_db";
            String dbUrl = "jdbc:mysql://localhost:3306/dictionary_db";
            String dbUser = "your_user";
            String dbPass = "you_passowrd";
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            // Initialize data structures
            revision = new DoublyLinkedList();
            wordList = new ArrayList<>();
            System.out.println((connection!=null) ? "Connection Done" : "Failed");

            // Sort word list and create tables
            sortWordList();
            createTables();
        }
        catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }


    }

    // Method to create tables in the database
    private void createTables() throws Exception{

        try {
        // Create statement object to execute SQL queries
        Statement statement = connection.createStatement();

        query = "CREATE TABLE IF NOT EXISTS Register (User_ID INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), E_mail VARCHAR(100) UNIQUE, Created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Login (Login_ID INT PRIMARY KEY AUTO_INCREMENT, User_ID INT, Login_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (User_ID) REFERENCES Register(User_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS User_AUTH (USER_AUTH_ID INT PRIMARY KEY AUTO_INCREMENT, Username VARCHAR(100), Password VARCHAR(255), User_ID INT, FOREIGN KEY (User_ID) REFERENCES Register(User_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Search_History (Search_ID INT PRIMARY KEY AUTO_INCREMENT, User_ID INT, Search_Query VARCHAR(255), Search_operation VARCHAR(100), Search_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (User_ID) REFERENCES Register(User_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Wordsdata (Word_ID INT PRIMARY KEY AUTO_INCREMENT, Word VARCHAR(100) UNIQUE, Pronounciation VARCHAR(100), Meaning TEXT, Part_Of_Speech VARCHAR(50))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Synonyms (Synonym_ID INT PRIMARY KEY AUTO_INCREMENT, Word_ID INT, Synonym VARCHAR(100), FOREIGN KEY (Word_ID) REFERENCES Wordsdata(Word_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Antonyms (Antonym_ID INT PRIMARY KEY AUTO_INCREMENT, Word_ID INT, Antonym VARCHAR(100), FOREIGN KEY (Word_ID) REFERENCES Wordsdata(Word_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Hypernyms (Hypernym_ID INT PRIMARY KEY AUTO_INCREMENT, Word_ID INT, Hypernym VARCHAR(100), FOREIGN KEY (Word_ID) REFERENCES Wordsdata(Word_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Hyponyms (Hyponym_ID INT PRIMARY KEY AUTO_INCREMENT, Word_ID INT, Hyponym VARCHAR(100), FOREIGN KEY (Word_ID) REFERENCES Wordsdata(Word_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Sentence (Sentence_ID INT PRIMARY KEY AUTO_INCREMENT, Word_ID INT, Sentence TEXT, FOREIGN KEY (Word_ID) REFERENCES Wordsdata(Word_ID))";
        statement.execute(query);

        query = "CREATE TABLE IF NOT EXISTS Suggested_Words (ID INT PRIMARY KEY AUTO_INCREMENT, User_ID INT, Word TEXT, Meaning TEXT, Pronounciation VARCHAR(200), Status VARCHAR(50), FOREIGN KEY (User_ID) REFERENCES Register(User_ID))";
        statement.execute(query);

    } catch (SQLException e) {
        System.out.println("Error creating tables: " + e.getMessage());
    }


    }

    // Method to perform dictionary operations based on user choice

     public static void performDictionaryOperations(User user, Dictionary dictionary) throws Exception {

        int choice = 0;
        // Loop until the user chooses to exit (choice 6)
        while(choice!=6){

            // Display menu options for dictionary operations
            System.out.println("\nSelect choice");

            System.out.println("1. Search Word");
            System.out.println("2. To Suggest Word to Dictionary");
            System.out.println("3. Read Dictionary");
            System.out.println("4. Short Word Note");
            System.out.println("5. Print Word Note");
            System.out.println("6. Exit\n");

            // Get the user's choice
            choice = dictionary.getValidChoice();
            
            // Handle each menu option using a switch statement
            switch (choice ) {

                case 1:
                    // Search for a word
                    Queue queue = new Queue();
                    queue.suggest(dictionary);
                    dictionary.searchWord(user,queue);
                    break;
            
                case 2:
                    // Add a word to the dictionary
                    dictionary.suggestWord(user);
                    break;

                case 3:
                    // Read the dictionary
                    dictionary.readDictionary(user);
                    break;
                
                case 4:
                    // Create a short word note
                    dictionary.createShortNote(user);
                    break;
                
                case 5:
                    // Print the short word note
                    dictionary.printShortNote(user);
                    break;

                case 6:
                    // Exit the loop and display a thank you message
                    System.out.println("Thank You....");
                    choice = 6;
                    break;

                default:
                    // Display an error message for invalid input
                    System.out.println("Invalid Input");
                    break;
            }

        }
    }

    // Method to suggest a word to the dictionary
    private void suggestWord(User user) throws Exception {

        try {

        // Prompt the user to enter the word, meaning, and pronunciation
        System.out.print("Enter word to suggest: ");
        word = scanner.nextLine();

        System.out.print("Enter meaning: ");
        meaning = scanner.nextLine();
        
        System.out.print("Enter pronunciation: ");
        pronounciation = scanner.nextLine();

        // Check if the word already exists in the suggested words table
        String checkWordQuery = "SELECT * FROM Suggested_Words WHERE Word = ?";
        PreparedStatement pst = connection.prepareStatement(checkWordQuery);
        pst.setString(1, word);
        ResultSet rs = pst.executeQuery();

        // Check if the word already exists in the dictionary
        if(isWordExists(word)){
            System.out.println("Word exists in Dictionary");
            recordHistory(user.getUsername(), "Word Suggesstion failed", word);
            return;
        }

        // If the word already exists, display a message and record the history
        if (rs.next()) {
            System.out.println("Word already suggested");
            recordHistory(user.getUsername(), "Word Suggesstion failed", word);
            return;
        }

        // Insert the suggested word into the Suggested_Words table
        String insertSuggestedWordQuery = "INSERT INTO Suggested_Words (user_id,Word, Meaning, Pronounciation) VALUES (?, ?, ?, ?)";
        pst = connection.prepareStatement(insertSuggestedWordQuery);
        pst.setInt(1, user.getId());
        pst.setString(2, word);
        pst.setString(3, meaning);
        pst.setString(4, pronounciation);
        pst.executeUpdate();

        // Display a success message and record the history
        System.out.println("Word suggested successfully");
        recordHistory(user.getUsername(), "Word Suggested", word);
    }
    catch (SQLException e) {
        System.out.println("Error suggesting word: " + e.getMessage());
    }

    }

   
    // Method to search for a word in the dictionary
    private void searchWord(User user,Queue queue) throws Exception{

        try{
        // Display Frequently Searched Words
        System.out.println("Frequently Searched Words : ");
        
        queue.print();

        // Prompt the user to enter the word
        System.out.println("Enter Word");
        word = scanner.nextLine();

        // Prepare the SQL query to retrieve word information
        query = "Select pronounciation,meaning,part_of_speech from wordsdata where word = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, word);

        // Execute the query and retrieve the results
        ResultSet resultSet = statement.executeQuery();

        // If the word is found, display its information
        if(resultSet.next()){
            System.out.println("    -   " + resultSet.getString("pronounciation"));
            System.out.println("    -   " + resultSet.getString("meaning"));
            System.out.println("    -   " + resultSet.getString("part_of_speech"));

            // Record the search history
            recordHistory(user.getUsername(),"Search",word);
            
            // Display additional word data
            displayWordData(user);
        }
        else{
            // If the word is not found, display a message
            System.out.println("Word not in the dictionary");
        }
    }
    catch (SQLException e) {
        System.out.println("Error searching word: " + e.getMessage());
    }


    }

    // Method to read the dictionary
    private void readDictionary(User user) throws Exception{
 
        try {
        System.out.println("\nPress 'O' to add word to quick revision");
        System.out.println("Press 'E' to stop reading");
        System.out.println("Press Enter for next word\n");

        // Display the word list
        System.out.println("      Word      |         Meaning       ");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------");

        // Iterate through the word list
        for(WordsListClass words : wordList){

            // Display the word and meaning
            System.out.printf("%-14s = %-50s\n",words.getWord(),words.getMeaning());

            // Prompt the user for input
            String input = scanner.nextLine();

            // Record the reading history
            recordHistory(user.getUsername(),"Reading Word",words.getWord());

            // Check if the user wants to add the word to the quick revision
            if(input.equalsIgnoreCase("O")){
                recordHistory(user.getUsername(),"Added to short note",words.getWord());
                revision.insertEnd(words);
            }

            // Check if the user wants to stop reading
            else if(input.equalsIgnoreCase("E")){
                recordHistory(user.getUsername(),"Reading ended",words.getWord());
                break;
            }

            System.out.println();
            }
        
        // Display a separator
        System.out.println("-----------------------------------------------------------------------");
        }
        catch (SQLException e) {
            System.out.println("Error reading dictionary: " + e.getMessage());
        }

    }

    // Method to create a short note
    private void createShortNote(User user) throws Exception{

        try {
        // Display the words in the note
        System.out.println("\nWords in the note are :- \n");
        revision.printAll();

        // Loop until the user exits
        while (true) {

            // Display options
            System.out.println("\n1. Add word to note");
            System.out.println("2. Remove word from Note");
            System.out.println("3. Change word ");
            System.out.println("4. Display Note");
            System.out.println("5. to exit\n");

            // Get the user's choice
            int choice = getValidChoice();

            // Add a word to the note
            if(choice == 1){

                System.out.print("\nEnter word to add :- ");
                word = scanner.nextLine();

                // Check if the word is already in the note
                if(revision.contains(word)){
                    System.out.println("word already in note");
                }
                else{
                    System.out.print("Enter corresponding meaning :- ");
                    String mean = scanner.nextLine();
                    WordsListClass wordObject = new WordsListClass(word, mean);
                    revision.insertEnd(wordObject);
                    recordHistory(user.getUsername(),"insertion in short note",word);
                }
                
            }

            // Remove a word from the note
            else if(choice == 2){

                System.out.print("Enter word to remove:- ");
                word = scanner.nextLine();
                revision.deleteNode(word);
                recordHistory(user.getUsername(),"deletion in short note",word);
            }

            // Change a word in the note
            else if(choice == 3){
                
                String OldWord = revision.change();
                recordHistory(user.getUsername(),"modification in short note",OldWord);
            }

            // Display the note
            else if(choice == 4){

                recordHistory(user.getUsername(),"reading short note","---");
                System.out.println("\n-----------------------------------------------------");
                revision.printAll();
                System.out.println("-----------------------------------------------------\n");
            }

            // Exit the loop
            else if(choice == 5){
                break;
            }

            // Invalid choice
            else{
                System.out.println("Invalid choice");
            }
            
        }
    } catch (SQLException e) {
        System.out.println("Error creating short note: " + e.getMessage());
    }
    }

    // Method to print the short note
    void printShortNote(User user) throws Exception{

        try {
        
        if(revision.head==null){
            System.out.println("Note is empty");
        }
        else{
            // Create a file writer
            File file = new File(user.getUsername() + "_Note.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            int i=1;
            DoublyLinkedList.Node temp = revision.head;
            while(temp!=null){  // Traverse the list
                bw.write(i++ + ". " + temp.wordNode.word + "    =   " + temp.wordNode.meaning);
                bw.newLine();
                bw.flush();
                temp = temp.next;
            }
            bw.close();
            System.out.println("File created successfully:- " + file.getName() );
            System.out.println("Path to File is :-  " + file.getAbsolutePath());
            recordHistory(user.getUsername(),"Short note printted", "----");
        }
    }
    catch (IOException e) {
        System.out.println("Error printing short note: " + e.getMessage());
    }

    }

    /**
    * Method to validate user input and ensure it's an integer
    * 
    * @return The validated integer choice
    * @throws Exception If an error occurs during validation
    */
    int getValidChoice() throws Exception{
        
        // Initialize the attempt counter
        int attempt = 0;
        int choice =0;
        // Loop until the user enters a valid integer or reaches the maximum attempts
        while(attempt < 3){
            try{

                // Increment the attempt counter
                attempt += 1;

                // Try to parse the user's input as an integer
                choice = Integer.parseInt(scanner.nextLine());

                // If successful, break out of the loop
                break;
                
            }
            catch(NumberFormatException e){

                // If the input is not a valid integer, print an error message
                if(attempt<3){
                    System.out.println("Kindly Enter Proper input in form of integer from given choices");
                    System.out.println("Attempts more than 3 will lead System exit");
                }
                
            }
        }

        // If the user has exceeded the maximum attempts, print an error message and exit the system
        if(attempt >= 3){
            System.out.println("Limit exceeded \nTry again later");
            System.exit(0);
            return 0;
        }
        else{
            // Return the validated integer choice
            return choice;
        }
    }

    /**
    * Method to check if a word exists in the word list
    * 
    * @param wordList The list of words to search
    * @param word1    The word to search for
    * @return True if the word exists, false otherwise
    */

    boolean isWordExists(String word1) {

        // Iterate through each word in the word list
        for(WordsListClass words : wordList){

            // Check if the current word starts with the search word
            if(words.getWord().equalsIgnoreCase(word1)){

                // If found, print the word and return
                System.out.println(word1);
                return true;
            }
        }
        
        // If the word is not found after iterating through the entire list,return false
        return false;
    }

    /**
    * Method to sort the word list by retrieving words from the database and sorting them
    * 
    * @param wordList The list of words to sort
    * @throws Exception If an error occurs during database operations
     */
    void sortWordList() throws Exception{

        // Create a statement object to execute SQL queries
        Statement statement = connection.createStatement();

        // Define the SQL query to retrieve words and meanings from the database
        String query = "SELECT word,meaning FROM wordsdata";

        // Execute the query and store the results in a ResultSet object
        ResultSet resultSet = statement.executeQuery(query);

        // Iterate through the ResultSet and add each word and meaning to the word list
        while (resultSet.next()) {
            WordsListClass word = new WordsListClass(resultSet.getString(1), resultSet.getString(2));
            wordList.add(word);
        }
        Collections.sort(wordList, Comparator.comparing(WordsListClass::getWord));
        
    }

    /**
    * Method to display additional word data (synonym, antonym, sentence, type) for a given word
    * 
    * @param connection   The database connection object
    * @param word  The word to retrieve data for
    * @param user  The user requesting the data
    * @throws Exception If an error occurs during database operations
    */
    private void displayWordData(User user) throws Exception{

        // Initialize user choice to 0
        int user_choice = 0;

        // Loop indefinitely until the user chooses to exit
        while(user_choice != 6){

            // Display menu options for word data
            System.out.println("\nMenu option for words data");
            System.out.println("\n1. to know synonym");
            System.out.println("2. to know antonym");
            System.out.println("3. Hypernyms");
            System.out.println("4. Hyponyms");
            System.out.println("5. use of word in sentence");
            System.out.println("6. exit\n");

            // Get the user's choice
            user_choice = getValidChoice();

            // Handle each menu option
            switch (user_choice) {
                case 1 -> // Display synonyms for the word
                    Synonym(user);
                
                case 2 -> // Display antonyms for the word
                    Antonym(user);

                case 3 -> // Display hypernyms for the word
                    Hypernyms(user);

                case 4 -> // Display hyponyms for the word
                    Hyponyms(user);

                case 5 -> // Display sentence examples for the word
                    Sentence(user);

                case 6 -> // Exit the loop if the user chooses to exit
                    user_choice = 6;

                default -> // Display an error message for invalid input
                    System.out.println("Enter valid input");
                    
            }

        }

    }

    /**
    * Displays the synonym for a given word and records the user's action in the history.
    * 
    * @param user the user requesting the synonym
    * @throws Exception if an error occurs while displaying the synonym
    */
    public void Synonym(User user) throws Exception{

        // Call the Synonym stored procedure
        String procedureCall = "{call Synonym(?)}";
        CallableStatement cq = connection.prepareCall(procedureCall);

        cq.setString(1, word);  // Set the word as the input parameter for the stored procedure

        // Execute the stored procedure and retrieve the result set
        ResultSet rs = cq.executeQuery();

        boolean is = false;
        // Display the synonym if available
        System.out.println("Synonyms are \n");
        while(rs.next()){
            is = true;
            System.out.println(rs.getString(1) + "\n");
        }
        
        if(!is){
            System.out.println("Synonym not available in dictionary");
        }

        // Record the user's action in the history
        recordHistory(user.getUsername(),"Synonym",word);

    }

    /**
    * Displays the antonym for a given word and records the user's action in the history.
     * 
     * @param user the user requesting the antonym
     * @throws Exception if an error occurs while displaying the antonym
    */
    public void Antonym(User user) throws Exception{
        // Call the Antonym stored procedure
        String procedureCall = "{call Antonym(?)}";
        CallableStatement cq = connection.prepareCall(procedureCall); 

        cq.setString(1, word); // Set the word as the input parameter for the stored procedure

        // Execute the stored procedure and retrieve the result set
        ResultSet rs = cq.executeQuery();

        boolean is = false;
        // Display the antonym if available
        System.out.println("Antonyms are \n");
        while(rs.next()){
            is =true;
            System.out.println(rs.getString(1) + "\n");
        }
        if(!is){
            System.out.println("Antonym not available in dictionary");
        }

        // Record the user's action in the history
        recordHistory(user.getUsername(),"Antonym",word);
    }

    /**
    * Displays the hypernym for a given word and records the user's action in the history.
    * 
    * @param user the user requesting the hypernym
    * @throws Exception if an error occurs while displaying the hypernym
     */
    public void Hypernyms(User user) throws Exception{
        // Call the Hypernym stored procedure
        String procedureCall = "{call Hypernym(?)}";

        CallableStatement cq = connection.prepareCall(procedureCall);
        cq.setString(1, word); // Set the word as the input parameter for the stored procedure

        // Execute the stored procedure and retrieve the result set
        ResultSet rs = cq.executeQuery();

        boolean is = false;
        // Display the hypernym if available
        System.out.println("Hypernym are \n");
        while(rs.next()){
            is = true;
            System.out.println(rs.getString(1) + "\n");
        }
        if(!is){
            System.out.println("Hypernym not available in dictionary");
        }

        // Record the user's action in the history
        recordHistory(user.getUsername(),"Hypernym",word);
    }

    /**
    * Displays the hyponym for a given word and records the user's action in the history.
    * 
    * @param user the user requesting the hyponym
     * @throws Exception if an error occurs while displaying the hyponym
     */
    public void Hyponyms(User user) throws Exception{
        // Call the Hyponym stored procedure
        String procedureCall = "{call Hyponym(?)}";
        CallableStatement cq = connection.prepareCall(procedureCall);

        cq.setString(1, word);  // Set the word as the input parameter for the stored procedure

        // Execute the stored procedure and retrieve the result set
        ResultSet rs = cq.executeQuery();

        boolean is = false;
        // Display the hyponym if available
        System.out.println("Hyponyms are \n");
        while(rs.next()){
            is = true;
            System.out.println(rs.getString(1) + "\n");
        }
        if(!is){
            System.out.println("Hyponym not available in dictionary");
        }

        // Record the user's action in the history
        recordHistory(user.getUsername(),"Hyponym",word);
    }
    
    /**
    * Displays a sentence using the given word and records the user's action in the history.
    * 
    * @param user the user requesting the sentence
    * @throws Exception if an error occurs while displaying the sentence
    */
    public void Sentence(User user) throws Exception{
        // Call the Sentence stored procedure
        String procedureCall = "{call Sentence(?)}";
        CallableStatement cq = connection.prepareCall(procedureCall);

        cq.setString(1, word);  // Set the word as the input parameter for the stored procedure

        // Execute the stored procedure and retrieve the result set
        ResultSet rs = cq.executeQuery();

        boolean is = false;
        // Display the sentence if available
        System.out.println("Sentence is \n");
        while(rs.next()){
            is = true;
            System.out.println(rs.getString(1) + "\n");
        }
        if(!is){
            System.out.println("Currently not available in dictionary");
        }

        // Record the user's action in the history
        recordHistory(user.getUsername(),"Use in sentence",word);
    }

    /**
    * Static method to record user history in the database
    * 
    * @param username The username of the user
    * @param history  The type of history (e.g. "Synonym", "Antonym", etc.)
    * @param word     The word related to the history
    * @throws Exception If an error occurs during database operations
    */

    public static void recordHistory(String username, String history, String word) throws Exception{
        
        PreparedStatement pst = Dictionary.connection.prepareStatement("Select user_id from user_auth where username = ?");
        pst.setString(1, username);
        ResultSet resultSet = pst.executeQuery();
        int id =0;
        if(resultSet.next()){
            id = resultSet.getInt(1);
        }
        // Define the SQL query to insert user history into the database
        String query = "INSERT INTO search_history(user_id,search_query,search_operation) VALUES (?,?,?)";

        // Prepare the SQL statement with the database connection
        pst = Dictionary.connection.prepareStatement(query);

        // Set the parameters of the SQL statementf

        pst.setInt(1, id); // Set the username parameter\

        pst.setString(2, word);  // Set the history parameter

        pst.setString(3, history); // Set the word parameter
        
        pst.executeUpdate();    // Execute the SQL statement to insert the user history
    }
}
