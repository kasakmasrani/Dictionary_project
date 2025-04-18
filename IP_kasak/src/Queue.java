
//Queue.java
// Data Structure
import java.sql.*;

//A Queue class to store the top 3 most frequently searched words.
public class Queue {
    
    static int front, rear;
    int size = 3;
    String[] frequentwords;

    // Constructor to initialize the queue
    Queue() {
        frequentwords = new String[size];
        front = rear = -1;
    }

    /**
     * Suggests the most frequently searched words by the user.
     * @param dictionary The dictionary object containing the database connection.
     * @throws SQLException If a database access error occurs.
     */

    void suggest(Dictionary dictionary) throws SQLException {

        // SQL query to retrieve the top 3 most frequently searched words
        String query = "SELECT search_query FROM search_history WHERE search_operation = 'search' GROUP BY search_query ORDER BY count(search_query) DESC LIMIT 3";
        Statement statement = Dictionary.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try {
            // Enqueue the top 3 most frequently searched words
            while (resultSet.next()) {
                enqueue(resultSet.getString(1));
            }
        } finally {
            // Ensure resources are closed
            resultSet.close();
            statement.close();
        }
    }

    /**
     * Adds a word to the queue.
     * @param word The word to be added to the queue.
     */
    void enqueue(String word) {

        // Check if the queue is full
        if (rear == size - 1) {
            System.out.println("Queue is full. Cannot enqueue " + word);
        } 
        else {
            if (front == -1) {
                front = 0;
            }
            rear++;

            frequentwords[rear] = word;
        }
    }

    //Prints all the words in the queue.
    
    void print() {
        
        // Check if the queue is empty
        if (front == -1) {
            System.out.println("Queue is empty.");
        } else {
            for (int i = front; i <= rear; i++) {
                System.out.println("    -   " + frequentwords[i]);
            }
            System.out.println();
        }
    }
}
