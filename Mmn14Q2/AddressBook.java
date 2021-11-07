import java.io.Serializable;
import java.util.TreeMap;
import Exceptions.ContactNotFoundException;
import Exceptions.DuplicateContactException;
import Exceptions.InvalidPhoneNumException;

public class AddressBook implements Serializable {

    private TreeMap<String, String> addressBook; // <fullname, phonenumber>

    public AddressBook() {
        addressBook = new TreeMap<String,String>();
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook.getAddressBook();
    }

    // adds a contact to the address book
    public void add(String name, String phoneNumber) throws DuplicateContactException, InvalidPhoneNumException {
        if (addressBook.containsKey(name)) {
            throw new DuplicateContactException("A contact with the name " + name + " already exists");
        } else if (!validatePhoneNum(phoneNumber)) {
            throw new InvalidPhoneNumException(phoneNumber + " is not a valid phone number");
        }
        addressBook.put(name, phoneNumber);
    }

    // updates a contact's phone number
    public void update(String name, String phoneNumber) throws ContactNotFoundException, InvalidPhoneNumException {
        if (!addressBook.containsKey(name)) {
            throw new ContactNotFoundException("A contact with the name " + name + " does not exist");
        } else if (!validatePhoneNum(phoneNumber)) {
            throw new InvalidPhoneNumException(phoneNumber + " is not a valid phone number");
        }
        addressBook.put(name, phoneNumber);
    }

    // deletes a contact from the address book
    public void delete(String name) throws ContactNotFoundException {
        if (!addressBook.containsKey(name)) {
            throw new ContactNotFoundException("A contact with the name " + name + " does not exist");
        }
        addressBook.remove(name);
    }

    public TreeMap<String, String> search(String name) {
        return new TreeMap<String, String>() {{put(name, addressBook.get(name));}};
    }

    // returns the addressbook
    public TreeMap<String, String> getAddressBook() {
        return addressBook;
    }

    // validates that the phone number has exactly 10 digits which are all numbers
    private boolean validatePhoneNum(String phoneNumber) {
        if (phoneNumber.length() == 10 && phoneNumber.matches("^[0-9]*$")) {
            return true;
        }
        return false;
    }

}
