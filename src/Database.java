import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {

    public static void saveData(Account acc) throws IOException, ClassNotFoundException {
        ArrayList<Account> db = loadDB();
        db.add(acc);

        saveDB(db);
    }

    public static void saveDB(ArrayList<Account> db) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("database.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(db);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static ArrayList<Account> loadDB() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("database.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @SuppressWarnings("unchecked")
        ArrayList<Account> db = (ArrayList<Account>) objectInputStream.readObject();
        objectInputStream.close();
        return db;
    }

    public static void updateAccount(Account account) throws ClassNotFoundException, IOException {
        ArrayList<Account> accs = loadDB();

        for (int i = 0; i < accs.size(); i++) {
            if (accs.get(i).getUsername().equals(account.getUsername())
                    && accs.get(i).getpassword().equals(account.getpassword())) {
                accs.remove(i);
                accs.add(account);
                break;
            }
        }
        saveDB(accs);
    }

    public void main() throws IOException, ClassNotFoundException {

    }
}
