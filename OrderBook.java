import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;
class OrderBook{
    // We store our buy and sell sides as TreeSets
    Set<Order> buySide;
    Set<Order> sellSide;
    Map<String, Order> orderDictionary;

    public OrderBook(){
        // this.buySide = new TreeSet<Order>();
        this.buySide = new TreeSet<Order>();
        this.sellSide = new TreeSet<Order>();

        // this.sellSide = new TreeSet<Order>();
        this.orderDictionary = new HashMap<String, Order>();
    }

    public int executeLOBuy(Order newBuyOrder){
        this.buySide.add(newBuyOrder);
        orderDictionary.put(newBuyOrder.getOrderId(), newBuyOrder);
        return 0;
    }

    public int executeLOSell(Order newSellOrder){
        int totalTransacted;
        this.sellSide.add(newSellOrder);
        orderDictionary.put(newSellOrder.getOrderId(), newSellOrder);
        return 0;
    }

    public void executeCXL(String orderId){
        // Check if we actually have this order in our OB
        // We trade memory for a faster cancel by hashing the values of the orderId to the order objects
        if (!orderDictionary.containsKey(orderId)){
            return;
        }
        Order toCancel = orderDictionary.get(orderId);

        // Check if its in the buySide or the sellSide, and remove accordingly
        if ( buySide.contains(toCancel) ) { buySide.remove(toCancel); }
        else { sellSide.remove(toCancel); }

        return;
    }

    public void executeCRP(Order order){
        if (!orderDictionary.containsKey(order.getOrderId())){
            return;
        }
        Order toCancel = orderDictionary.get(order.getOrderId());

        if ( toCancel.getQuantity() <= order.getQuantity() && toCancel.getPrice() == order.getPrice() ){

        }
    }

    // toString method to print the visual representation of an OrderBook
    @Override
    public String toString(){
        // Use a StringBuilder to create our return String
        StringBuilder toRet = new StringBuilder();
        
        // Formatting
        toRet.append("B: ");
        toRet.append(buySide.toString());
        toRet.append("\nS: ");
        toRet.append(sellSide.toString());
        return toRet.toString();
    }

    // toString for ArrayList
    // @Override
    // public String toString(){
    //     StringBuilder toRet = new StringBuilder();
    //     // Append to the return StringBuilder
    //     toRet.append("B: ");
    //     for(int i = 0 ; i < buySide.size() ; i++){
    //         toRet.append(buySide.get(i) + " ");
    //     }
    //     toRet.append("\nS: ");
    //     for(int i = 0 ; i < sellSide.size() ; i++){
    //         toRet.append(sellSide.get(i) + " ");
    //     }
    //     return toRet.append("\n").toString();
    // }
}