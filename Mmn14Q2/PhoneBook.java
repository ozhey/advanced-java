import java.io.Serializable;
import java.util.TreeMap;
import Exceptions.ContactNotFoundException;
import Exceptions.DuplicateContactException;
import Exceptions.InvalidPhoneNumException;

// the logic class for the phonebook application. 
// implements Serializable so it can later be saved / loaded from a file
public class PhoneBook implements Serializable {
    
    private TreeMap<String, String> phoneBook = new TreeMap<String, String>(); // <key: fullname, value: phonenumber>
    
    // replaces the current phonebook with a new one. 
    // main use case is loading a phonebook from a file into memory.
    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook.getPhoneBook();
    }
    
    // returns the phonebook
    public TreeMap<String, String> getPhoneBook() {
        return phoneBook;
    }

    // adds a contact to the phone book. I didn't put any constrainsts on the contact's name.
    public void add(String name, String phoneNumber) throws DuplicateContactException, InvalidPhoneNumException {
        if (phoneBook.containsKey(name)) {
            throw new DuplicateContactException("A contact with the name " + name + " already exists");
        } else if (!validatePhoneNum(phoneNumber)) {
            throw new InvalidPhoneNumException("Phone numbers must contain exactly 10 digits.");
        }
        phoneBook.put(name, phoneNumber);
    }

    // updates a contact's phone number
    public void update(String name, String phoneNumber) throws ContactNotFoundException, InvalidPhoneNumException {
        if (!phoneBook.containsKey(name)) {
            throw new ContactNotFoundException("A contact with the name " + name + " does not exist");
        } else if (!validatePhoneNum(phoneNumber)) {
            throw new InvalidPhoneNumException("Phone numbers must contain exactly 10 digits.");
        }
        phoneBook.put(name, phoneNumber);
    }

    // deletes a contact from the phone book
    public void delete(String name) throws ContactNotFoundException {
        if (!phoneBook.containsKey(name)) {
            throw new ContactNotFoundException("A contact with the name " + name + " does not exist");
        }
        phoneBook.remove(name);
    }

    // searches for a contact in the phone book.
    // returns a treemap so it's easy to insert to the table view.
    // doesn't throw ContactNotFoundException because it's ok to search for non-existing contacts.
    public TreeMap<String, String> search(String name) {
        if (!phoneBook.containsKey(name)) {
            return new TreeMap<String,String>();
        }
        return new TreeMap<String, String>() {{put(name, phoneBook.get(name));}};
    }

    // validates that the phone number has exactly 10 digits which are all numbers
    private boolean validatePhoneNum(String phoneNumber) {
        if (phoneNumber.length() == 10 && phoneNumber.matches("^[0-9]*$")) {
            return true;
        }
        return false;
    }

}
