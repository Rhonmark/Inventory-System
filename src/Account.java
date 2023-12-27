import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Account implements Serializable {
    private static final long serialVersionUID = 7950368284888976160L;
    private String username;
    private String businessname;
    private String password;
    private ArrayList<Item> items;
    private ArrayList<String> recordhistory;

    Account(String username, String businessname, String password) {
        this.username = username;
        this.businessname = businessname;
        this.password = password;
        this.items = new ArrayList<>();
        this.recordhistory = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public String getbusinessname() {
        return this.businessname;
    }

    public String getpassword() {
        return this.password;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(String itemName) {
        Iterator<Item> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getproductName().equals(itemName)) {
                iterator.remove();
                break;
            }
        }
    }

    public Item getItem(String name) {
        for (Item i : items) {
            if (i.getproductName().equals(name)) {
                return i;
            }
        }

        return null;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public String[][] getInventory() {
        String[][] array = { null, null, null, null, null, null };
        if (!this.items.isEmpty()) {
            array = new String[this.items.size()][6];
            for (int i = 0; i < this.items.size(); i++) {
                array[i][0] = items.get(i).getproductName();
                array[i][1] = Integer.toString(items.get(i).getonStock());
                array[i][2] = Integer.toString(items.get(i).getminValue());
                array[i][3] = Integer.toString(items.get(i).getrsAmount());
                array[i][4] = Double.toString(items.get(i).getunitPrice());
                array[i][5] = Double.toString(items.get(i).getunitPrice() * items.get(i).getonStock());
            }
        }

        return array;
    }

    public void changeItems(ArrayList<Item> newitems) {
        this.items = newitems;
    }

    public ArrayList<String> getRecords() {
        return this.recordhistory;
    }

    public void addRecord(String record) {
        recordhistory.add(record);
    }

    public boolean isItemPresent(Item item) {
        for (Item i : items) {
            if (i.getproductName().equals(item.getproductName())) {
                new Error("Item is already in inventory.");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Account> db = Database.loadDB();

        Account acc = db.get(0);
        ArrayList<Item> items = acc.getItems();
        System.out.println(items.isEmpty());
    }
}