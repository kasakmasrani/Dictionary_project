//DoublyLinkedList.java
//  Data Structure
import java.util.Scanner;

// Class representing a doubly linked list

public class DoublyLinkedList {

    // Inner class representing a node in the list
    class Node {
        WordsListClass wordNode; // Data stored in the node
        Node next; // Pointer to the next node
        Node prev; // Pointer to the previous node
        
        // Constructor to initialize a node with data
        Node(WordsListClass data) {
            this.wordNode = data;
            this.next = null;
            this.prev = null;
        }
    }
    Node head = null;   // Head of the list

    // Method to check if the list is empty
    boolean isEmpty(){
        if( head == null){
            return true;
        }
        return false;
    }

    // Method to insert a new node at the end of the list
    void insertEnd(WordsListClass value) {

        // Create a new node
        Node newNode = new Node(value);

        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, make the new node the head
            head = newNode;

        } else {
            // Start from the head of the list
            Node current = head;

            // Traverse to the end of the list
            while (current.next != null) {
                current = current.next;
            }

            // Adjust pointers to insert the new node at the end
            // Set the next pointer of the last node to point to the new node
            current.next = newNode;

            // Set the previous pointer of the new node to point to the last node
            newNode.prev = current;
        }

        System.out.println("Word Added");
    }

    // Method to delete a node with the given data
    void deleteNode(String delWord){
        
        // Check if the list is empty
        if(isEmpty()){
            // If List is Empty, print message
            System.out.println("Note is Empty");

        }

        // Check word present in the list or not
        else if(contains(delWord)){

            Node temp = head; // Variable pointing head
            boolean isDeleted = false; 
            
            // Traverse the list to find the node to delete
            while(temp!=null && !temp.wordNode.word.equalsIgnoreCase(delWord)){
                
                temp = temp.next;
            }

            // Check if the node to delete is found
            if(temp == null){
                return;
            }

            // Adjust pointers to delete the node
            if(head == temp){
                isDeleted = true;
                head = temp.next;
            }

            if(temp.next!=null){
                temp.next.prev = temp.prev;
            }

            if(temp.prev != null){
                temp.prev.next = temp.next;
            }

            // Print message if the word is deleted
            if(isDeleted){
                System.out.println("Word removed Successfully");
            }
        }
        else{
            System.out.println("Word not in the Note");
        }
    }

    // Method to change a word in the list
    String change(){
        if(isEmpty()){
            System.out.println("Note is Empty");
            return "empty note";
        }
        else {
            Scanner sc =new Scanner(System.in);
            System.out.print("Enter old word :- ");
            String oldWord = sc.nextLine();

            // Check if the old word exists in the list
            if(!contains(oldWord)){
                System.out.println("Word not in the note");
                return oldWord + " - not avilable";
            }
            System.out.print("Enter new word:- ");
            String newWord = sc.nextLine();

            // Check if the old and new words are the same
            if(oldWord.equalsIgnoreCase(newWord)){
                System.out.println("old and new word must be different");
                return "old and new words were same";
            }

            else{
                Node temp = head;
                
                boolean isChanged = false;

                // Traverse the list to find the node to change
                while(temp!=null){

                    if(temp.wordNode.word.equalsIgnoreCase(oldWord)){
                        // Replace the old word with the new word
                        String updatedData = temp.wordNode.word = newWord;
                        System.out.println("New Word in place of old is: \n" + updatedData);
                        isChanged = true;
                        break;
                    }
                    temp = temp.next;
                }

                // Print message if the word is changed
                if(isChanged){
                    System.out.println("Word changed successfully");
                }
                return oldWord;
           }   
        }
        
    }

    // Method to search for a word in the list
    boolean contains(String word){
        boolean isFound = false;

        if(!isEmpty()){
            
            Node currentNode = head;
            while(currentNode!=null){

                String word1 = currentNode.wordNode.word;
                if(word1.equalsIgnoreCase(word)){
                    isFound =  true;
                    break;
                }
                currentNode = currentNode.next;
            }
        }
        return isFound;
    }

    // Method to print all elements in the list
    void printAll(){
        if(head==null){
            System.out.println("Empty note");
        }
        else{
            int index=1;
            Node currentNode = head;
            while(currentNode!=null){
                System.out.println(index++ + ". " + currentNode.wordNode.getWord() + " := \n" + currentNode.wordNode.getMeaning() + "\n");
                currentNode = currentNode.next;
            }
        }
    }
}


