import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Item implements Serializable {
    private String productID;
    private String productName;
    private int onStock;
    private String measurement;
    private String category;
    private int minValue;
    private int rsAmount; 
    private double unitPrice;
    private double totalValue; 
    private LocalDateTime date = LocalDateTime.now(); // Get current date & time
    private transient DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm"); // Format pattern to
                                                                                                 // show only dd-MM-yyy
                                                                                                 // HH:mm example:
                                                                                                 // 21-10-2023 16:36
    private String formattedDate = date.format(format); 

    Item(String productName, int onStock, int minValue, int rsAmount,
            double unitPrice) {
        this.productID = productName + unitPrice;
        this.productName = productName;
        this.onStock = onStock;
        this.minValue = minValue;
        this.rsAmount = rsAmount;
        this.unitPrice = unitPrice;
        this.totalValue = onStock * unitPrice;
        this.category = "None";
    }

    // Setter for formattedDate
    public void setDate(LocalDateTime date) {
        this.formattedDate = date.format(format);
    }

    public String getDate() {
        return this.formattedDate;
    }

    // Getter for productID
    public String getproductID() {
        return productID;
    }

    // Setter for productID
    public void setproductID(String productID) {
        this.productID = productID;
    }

    // Getter for productName
    public String getproductName() {
        return productName;
    }

    // Setter for productName
    public void setproductName(String productName) {
        this.productName = productName;
    }

    // Getter for onStock
    public int getonStock() {
        return onStock;
    }

    // Setter for onStock same langggggg
    public void setQuantity(int quantity) {
        this.onStock = quantity;
        this.totalValue = quantity * unitPrice;
    }

    // Getter for minValue
    public int getminValue() {
        return minValue;
    }

    // Setter for minValue
    public void setminValue(int minValue) {
        this.minValue = minValue;
    }

    // Getter for rsAmount (restock amount)
    public int getrsAmount() {
        return rsAmount;
    }

    // Setter for rsAmount
    public void setrsAmount(int rsAmount) {
        this.rsAmount = rsAmount;
    }

    // Getter for unitPrice
    public double getunitPrice() {
        return unitPrice;
    }

    // Setter for unitPrice
    public void setunitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.totalValue = onStock * unitPrice;
    }

    // Getter for totalValue
    public double gettotalValue() {
        return totalValue;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public String getMeasurement() {
        return this.measurement;
    }
}
