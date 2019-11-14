import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContactDatabaseHelper helper = new ContactDatabaseHelper();
        Controller controller = new Controller(helper);

        Contact contact = new Contact("Spike the Bulldog", "509-509-5095");
        helper.insertContact(contact);

        List<Contact> contactList = helper.getAllContactsList();
        System.out.println(contactList);

        Contact newContact = new Contact("SPIKE", "208-208-2082");
        helper.updateContact(1, newContact);

        //helper.deleteAllContacts();

        for (int i = 0; i < 25; i++) { // fill up our table a little more so we can see the scroll bar
            helper.insertContact(contact);
        }
        controller.updateJList();

        helper.closeConnection();
    }
}
