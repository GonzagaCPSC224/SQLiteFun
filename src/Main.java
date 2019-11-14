import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContactDatabaseHelper helper = new ContactDatabaseHelper();

        Contact contact = new Contact("Spike the Bulldog", "509-509-5095");
        helper.insertContact(contact);

        List<Contact> contactList = helper.getAllContactsList();
        System.out.println(contactList);

        Contact newContact = new Contact("SPIKE", "208-208-2082");
        helper.updateContact(1, newContact);

        helper.deleteAllContacts();

        helper.closeConnection();
    }
}
