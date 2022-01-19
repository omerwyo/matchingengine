import java.util.Date;

/*
 Abstract Order class, for which we only specify the implementation of toString, getters and setters
 This allows us to abstract away the Buy and Sell orders that extend this class.
*/
public class Order implements Comparable<Order>{
    private String orderId;
    private char side;
    private int price;
    private int quantity;
    private Date timeOrderPlaced;

    public Order(char side, String orderId, int price, int quantity){
        this.side = side;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;

        // new Date() makes a date object with the current date and time with millisecond precision
        // For this implementation of the matching engine we use the time the order is received
        this.timeOrderPlaced = new Date();
    }

    @Override
    public String toString(){
        return Integer.toString(quantity) + "@" + Integer.toString(price) + "#" + orderId;
    }

    // Getters and Setters for the fields
    public String getOrderId(){
        return this.orderId;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public char getSide(){
        return this.side;
    }

    public void setSide(char side){
        this.side = side;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }
    
    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public Date getTimeOrderPlaced(){
        return this.timeOrderPlaced;
    }

    @Override
    public int compareTo(Order otherOrder){
        int toRet = Integer.compare(this.getPrice(), otherOrder.getPrice());
        
        // if price is the same, compare them by time of entry
        if (toRet == 0){
            // ordered earlier if this order was placed before the one we are comparing against
            return this.getTimeOrderPlaced().before(otherOrder.getTimeOrderPlaced()) ? -1 : 1;
        }

        return toRet;
    }
}