package com.nyu.pqs.probSet1;
/**
 * Description:
 * A Phonebook class that allows users to create a new Phonebook in memory, add an entry, delete an
 * entry, search for entry by any field, write the Phonebook to a file and read the Phonebook from 
 * a file. 
 * @author Prakhar Verma
 * @version 1.1 
 * 
 *
 * 2. addEntry(String name,String address,String phoneNumber,String email,String note):
 * 
 * 
 */
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Phonebook implements java.io.Serializable {
    /**
     *
     */
  private static final long serialVersionUID = 1L;
  private List<PhonebookEntry> phonebook;

  /**  
   * Description of createNewPhonebook():
   * Creates a new Phonebook in Memory 
   */
  public void createNewPhonebook() {
    phonebook = new ArrayList<PhonebookEntry>();
  }

  /**
   * void addEntry(String name, String address, String phoneNumber,String email, String note):
   * Add a new entry into the Phonebook. Arguments accepted in the form of Strings. Entry with a 
   * duplicate 'name' field is not allowed. Other fields may or may not have duplicates.
   * 
   * @param name : type String - Holds the name of the contact, no limit on length
   * @param address : type String - Holds the address of the contact, no limit on length 
   * @param phoneNumber : type String - Holds the Phone Number of the contact, no limit on length
   * @param email : type String - Holds the email of the contact, no limit on length
   * @param note : type String - Holds a note, no limit on length
   * @throws IllegalArgumentException when Phonebook already contains entry with name given as
   *         argument
   */
  public void addEntry(String name, String address, String phoneNumber,
      String email, String note) {
    int searchIndex = returnSearchIndex("name",name);
    //if searchIndex == -1, the contact was not found in the Phonebook, so add entry to Phonebook
    if(searchIndex ==-1){
      PhonebookEntry entry = new PhonebookEntry();
      entry.setEntry(name, address, phoneNumber, email, note);
      phonebook.add(entry);
    }
    else{
      throw new IllegalArgumentException("Entry with name already exists in Phone Book");
    }
  }
  
  /**
   * void deleteEntry(String name):
   * Delete an entry from the Phonebook
   * 
   * @param name - type String, name of the contact that has to be deleted
   * @throws IllegalArgumentException when the entry being deleted is not present in the Phonebook
   */
  public void deleteEntry(String name) {
    int searchIndex = returnSearchIndex("name",name);
    if(searchIndex !=-1){
      phonebook.remove(searchIndex);
    }
    else{
      throw new IllegalArgumentException("Entry with name not found");
    }
  }
  
  /**
   * List searchEntry(String field,String value):
   * Searches for an entry in the Phonebook with the value of the respective field. Returns a list
   * of Phonebook objects if one or more contacts found that match the search criteria. Else an 
   * empty list is returned.
   * 
   * @param field - type String, name of the field to be looked into (example :- name, email etc.)
   * @param value - type String, value of the search string
   * @return List of type PhonebookEntry
   * @throws IllegalArgumentException when argument is invalid
   */
  public List<PhonebookEntry> searchEntry(String field, String value)
      throws IllegalArgumentException {
    List<PhonebookEntry> searchResults = new ArrayList<PhonebookEntry>();
    for (int i = 0; i < phonebook.size(); i++) {
      if (phonebook.get(i).isEqualField(field, value)) {
        searchResults.add(phonebook.get(i));
      }
    }
    return searchResults;
  }
  /**
   * void writeToFile(String filepath):
   * Serializes the current object with its data to a file specified by the user.
   * 
   * @param filepath, type String - path where the file is to be saved
   * @throws IOException when I/O operation fails
   */
  public void writeToFile(String filepath) throws IOException {
    FileOutputStream outputfile = new FileOutputStream(filepath);
    ObjectOutputStream out = new ObjectOutputStream(outputfile);
    out.writeObject(this);
    out.close();
    outputfile.close();
  }

  /**
   * Phonebook readFromFile(String filepath):
   * Deserializes object of type Phonebook from a file specified by the user. Has to be a file 
   * created by the writeToFile function containing serialized Phonebook object data.
   * 
   * @param filePath - type String - path where the file is read from
   * @return Phonebook Object 
   * @throws IOException when argument is invalid
   * @throws ClassNotFoundException when class being loaded is not found
   */
  public static Phonebook readFromFile(String filePath) throws IOException, ClassNotFoundException{
    FileInputStream inputfile = new FileInputStream(filePath);
    ObjectInputStream in = new ObjectInputStream(inputfile);
    Phonebook phonebook = (Phonebook) in.readObject();
    in.close();
    inputfile.close();
    return phonebook;
  }
 
  /*
  * Private helper function that helps in returning the search index of the search item. Used while
  * searching for Contact by name which does not allow duplicates.
  */
  private int returnSearchIndex(String field, String value) {
    int searchIndex = -1;
    for (int i = 0; i < phonebook.size(); i++) {
      if (phonebook.get(i).isEqualField(field, value)) {
        searchIndex = i;
      }
    }
    return searchIndex;
  }
  
} 
