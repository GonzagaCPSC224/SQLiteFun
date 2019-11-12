public class Main {
    public static void main(String[] args) {
        ContactDatabaseHelper helper = new ContactDatabaseHelper();

        Contact contact = new Contact("Spike the Bulldog", "509-509-5095");
        helper.insertContact(contact);

        helper.closeConnection();
    }
}
