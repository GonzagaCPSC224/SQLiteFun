import java.util.List;

public class Controller {
    View view;
    ContactDatabaseHelper helper;

    public Controller(ContactDatabaseHelper h) {
        helper = h;
        view = new View(this);
    }

    public void updateJList() {
        List<Contact> contactList = helper.getAllContactsList();
        view.jList.setListData(contactList.toArray());
    }
}
