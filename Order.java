import java.util.Date;

/*
 Abstract Order class, for which we only specify the implementation of toString, getters and setters
 This allows us to abstract away the Buy and Sell orders that extend this class.
*/
public class Order implements Comparable<Order> {
    private String orderId;
    private Side side;
    private int price;
    private int quantity;
    private long timeOrderPlaced;

    // Constructor for Limit Order
    public Order(Side side, String orderId, int price, int quantity) {
        this.side = side;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;

        // For this implementation of the matching engine we use the time the Order is received to know its ordering 
        this.timeOrderPlaced = System.nanoTime();
    }

    // Constructor for Market Order
    public Order(Side side, String orderId, int quantity) {
        this.side = side;
        this.orderId = orderId;
        this.quantity = quantity;

        // For this implementation of the matching engine we use the time the Order is received to know its ordering 
        this.timeOrderPlaced = System.nanoTime();
    }

    @Override
    public int compareTo(Order otherOrder) {
        int toRet;
        if (this.getSide() == Side.BUY) {
            toRet = Integer.compare(otherOrder.getPrice(), this.getPrice());
            // if price is the same, compare them by time of entry
            if (toRet == 0) {
                // ordered earlier if this order was placed before the one we are comparing against
                // we saved the order entered as a long, nanotime
                return Long.compare(this.getTimeOrderPlaced(), otherOrder.getTimeOrderPlaced());
            }
            return toRet;
        } else {
            // Similar logic if the side is sell just with the opposite way of comparing price
            toRet = Integer.compare(this.getPrice(), otherOrder.getPrice());
            // if price is the same, compare them by time of entry
            if (toRet == 0) {
                // ordered earlier if this order was placed before the one we are comparing against
                // we saved the order entered as a long, nanotime
                return Long.compare(this.getTimeOrderPlaced(), otherOrder.getTimeOrderPlaced());
            }
            return toRet;
        }
    }

    @Override
    public String toString() {
        return "'" + Integer.toString(quantity) + "@" + Integer.toString(price) + "#" + orderId + "'";
    }

    // @Override
    // public boolean equals(Object otherOrder){
    //     return (otherOrder instanceof Order) && (this.compareTo((Order) otherOrder) == 0) 
    //     && this.getSide() == ((Order) otherOrder).getSide() && this.getOrderId().compareTo(((Order) otherOrder).getOrderId()) == 0;
    // }

    // Getters and Setters for the fields
    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Side getSide() {
        return this.side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTimeOrderPlaced() {
        return this.timeOrderPlaced;
    }
}