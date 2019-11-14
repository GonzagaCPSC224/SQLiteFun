import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabaseHelper {
    // SQL: structured query language

    // think of a database like an Excel workbook
    // the work book sheets are like database tables
    // the sheet rows are like database "records"

    // databases need a name
    static final String DATABASE_NAME = "databaseContacts.db";
    // need a connection url (like a command to open a file)
    static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    static final String TABLE_CONTACTS = "tableContacts";
    static final String ID = "id";
    static final String NAME = "name";
    static final String PHONE_NUMBER = "phoneNumber";
    static final String IMAGE_PATH = "imagePath";

    Connection connection;

    public ContactDatabaseHelper() {
        getConnection();
        createContactsTable();
    }

    public void createContactsTable() {
        // to interact with a SQLite database
        // we construct SQL statements
        // these are strings that we try to get SQLite to execute
        // CREATE TABLE tableContacts(id INTEGER PRIMARY KEY AUTOINCREMENT,
        // name TEXT,
        // phoneNumber TEXT,
        // imagePath TEXT)

        // to minimize typos... we will define some string constants for our
        // table name and column names
        String sqlCreate = "CREATE TABLE " + TABLE_CONTACTS + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE_NUMBER + " TEXT, " +
                IMAGE_PATH + " TEXT)";
        System.out.println(sqlCreate);
        // primary key uniquely identifies records
        // autoincrement means let sqlite assign unique ids
        // start at 1 and go up by 1
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlCreate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertContact(Contact contact) {
        // INSERT INTO tableContacts VALUES(null, 'Spike the Bulldog',
        // '509-509-5095', '')
        String sqlInsert = "INSERT INTO " + TABLE_CONTACTS + " VALUES(null, '" +
                contact.getName() + "', '" +
                contact.getPhoneNumber() + "', '" +
                contact.getImagePath() + "')";
        System.out.println(sqlInsert);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlInsert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contact> getAllContactsList() {
        List<Contact> contactList = new ArrayList<>();
        // iterate through each record in our table
        // extract the column values
        // create a contact
        // add the contact to the list
        // much like file I/O while(inFile.hasNextLine()) {}
        // while (!infile.eof()) {}
        // SELECT * FROM tableContacts
        String sqlSelect = "SELECT * FROM " + TABLE_CONTACTS;
        System.out.println(sqlSelect);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                // need to advance to the first record (if there is one)
                while (resultSet.next()) { // returns false when there are no more records
                    int id = resultSet.getInt(ID);
                    String name = resultSet.getString(NAME);
                    String phoneNumber = resultSet.getString(PHONE_NUMBER);
                    String imagePath = resultSet.getString(IMAGE_PATH);
                    Contact contact = new Contact(id, name, phoneNumber, imagePath);
                    contactList.add(contact);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contactList;
    }

    public void updateContact(int id, Contact newContact) {
        // update record with id to have new info stored in newContact
        // UPDATE tableContacts SET name='SPIKE', phoneNumber='208-208-2082' WHERE id=1
        String sqlUpdate = "UPDATE " + TABLE_CONTACTS + " SET " +
                NAME + "='" + newContact.getName() + "', " +
                PHONE_NUMBER + "='" + newContact.getPhoneNumber() + "' WHERE " +
                ID + "=" + id;
        System.out.println(sqlUpdate);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAllContacts() {
        // DELETE FROM tableContacts
        String sqlDelete = "DELETE FROM " + TABLE_CONTACTS;
        System.out.println(sqlDelete);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlDelete);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getConnection() {
        // have a field for a Connection reference
        try {
            // creates a database if it doesn't exist
            connection = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("Successfully connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        // close the connection (just like a file we've opened)
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
