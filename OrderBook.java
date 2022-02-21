import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

class OrderBook {
    // We store our buy and sell sides as TreeSets
    TreeSet<Order> buySide;
    TreeSet<Order> sellSide;
    HashMap<String, Order> orderDictionary;

    public OrderBook(){
        this.buySide = new TreeSet<Order>();
        this.sellSide = new TreeSet<Order>();
        this.orderDictionary = new HashMap<String, Order>();
    }

    public int LOBuy(Order order){
        int totalTransacted = 0;

        // check if this order can be fulfilled, even partially
        if (this.sellSide.size() != 0 && sellSide.first().getPrice() <= order.getPrice()){
            while( !sellSide.isEmpty() ){
                Order sellOrder = this.sellSide.first();

                // can no longer fill order, break out of loop and add into the OB
                if (sellOrder.getPrice() > order.getPrice()){
                    break;
                }

                if (sellOrder.getQuantity() >= order.getQuantity()){
                    totalTransacted += order.getQuantity() * sellOrder.getPrice();

                    // In the case that the order cancels out the one in sellSide cleanly
                    if ( sellOrder.getQuantity() - order.getQuantity() == 0 ){
                        this.sellSide.remove(sellOrder);
                        orderDictionary.remove(sellOrder.getOrderId());
                    }
                    else{
                        sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                    }
                    return totalTransacted;
                }
                
                if (sellOrder.getQuantity() < order.getQuantity()){
                    totalTransacted += sellOrder.getQuantity() * sellOrder.getPrice();
                    order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
                    this.sellSide.remove(sellOrder);
                }
            }
        }
        
        this.buySide.add(order);
        orderDictionary.put(order.getOrderId(), order);
        return totalTransacted;
    }

    public int LOSell(Order order){
        int totalTransacted = 0;
        // check if this order can be fulfilled, even partially
        if (this.buySide.size() != 0 && buySide.first().getPrice() >= order.getPrice()){
            while( !sellSide.isEmpty() ){
                Order buyOrder = this.buySide.first();

                // can no longer fill order, break out of loop and add into the OB
                if (buyOrder.getPrice() < order.getPrice()){
                    break;
                }

                if (buyOrder.getQuantity() >= order.getQuantity()){
                    totalTransacted += order.getQuantity() * buyOrder.getPrice();

                    // In the case that the order cancels out the one in buySide cleanly
                    if ( buyOrder.getQuantity() - order.getQuantity() == 0 ){
                        this.buySide.remove(buyOrder);
                        orderDictionary.remove(buyOrder.getOrderId());
                    }
                    else {
                        buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
                    }
                    return totalTransacted;
                }
                
                if (buyOrder.getQuantity() < order.getQuantity()){
                    totalTransacted += buyOrder.getQuantity() * buyOrder.getPrice();
                    order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
                    this.buySide.remove(buyOrder);
                }
            }
        }

        this.sellSide.add(order);
        orderDictionary.put(order.getOrderId(), order);
        return totalTransacted;
    }

    public int MOBuy(Order order){
        int totalTransacted = 0;

        while( !sellSide.isEmpty() ){
            Order sellOrder = this.sellSide.first();

            if (sellOrder.getQuantity() >= order.getQuantity()){
                totalTransacted += order.getQuantity() * sellOrder.getPrice();
                
                // In the case that the order cancels out the one in sellSide cleanly
                if ( sellOrder.getQuantity() - order.getQuantity() == 0 ){
                    this.sellSide.remove(sellOrder);
                    orderDictionary.remove(sellOrder.getOrderId());
                }
                else {
                    sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                }
                return totalTransacted;
            }

            if (sellOrder.getQuantity() < order.getQuantity()){
                totalTransacted += sellOrder.getQuantity() * sellOrder.getPrice();
                order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
                this.sellSide.remove(sellOrder);
                continue;
            }
        }   

        return totalTransacted;
    }

    public int MOSell(Order order){
        int totalTransacted = 0;

        while( !buySide.isEmpty() ){
            Order buyOrder = this.buySide.first();

            if (buyOrder.getQuantity() >= order.getQuantity()){
                totalTransacted += order.getQuantity() * buyOrder.getPrice();

                // In the case that the order cancels out the one in buySide cleanly
                if ( buyOrder.getQuantity() - order.getQuantity() == 0 ){
                    this.buySide.remove(buyOrder);
                    orderDictionary.remove(buyOrder.getOrderId());
                }
                else {
                    buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
                }
                return totalTransacted;
            }
            
            if (buyOrder.getQuantity() < order.getQuantity()){
                totalTransacted += buyOrder.getQuantity() * buyOrder.getPrice();
                this.buySide.remove(buyOrder);
                continue;
            }
        } 
    
        return totalTransacted;
    }

    public int IOCBuy(Order order){
        int totalTransacted = 0;

        // check if this order can be fulfilled, even partially
        if (this.sellSide.size() != 0 && sellSide.first().getPrice() <= order.getPrice()){
            Order sellOrder = this.sellSide.first();

            if (sellOrder.getQuantity() >= order.getQuantity()){
                totalTransacted += order.getQuantity() * sellOrder.getPrice();

                // In the case that the order cancels out the one in sellSide cleanly
                if ( sellOrder.getQuantity() - order.getQuantity() == 0 ){
                    this.sellSide.remove(sellOrder);
                    orderDictionary.remove(sellOrder.getOrderId());
                    sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                }
                else{
                    sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                }
                return totalTransacted;
            }
            
            // Partially fulfill but don't continue
            if (sellOrder.getQuantity() < order.getQuantity()){
                totalTransacted += order.getPrice() * sellOrder.getQuantity();
                order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
                this.sellSide.remove(sellOrder);
                return totalTransacted;
            }
        }
        return totalTransacted;
    }
    

    public int IOCSell(Order order){
        int totalTransacted = 0;
        // check if this order can be fulfilled, even partially
        if (this.buySide.size() != 0 && buySide.first().getPrice() >= order.getPrice()){
            Order buyOrder = this.buySide.first();
            
            // Fully able to sell
            if (buyOrder.getQuantity() >= order.getQuantity()){
                totalTransacted += buyOrder.getPrice() * order.getQuantity();

                // In the case that the order cancels out the one in buySide cleanly
                if ( buyOrder.getQuantity() - order.getQuantity() == 0 ){
                    this.buySide.remove(buyOrder);
                    orderDictionary.remove(buyOrder.getOrderId());
                }
                else {
                    buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
                }
                return totalTransacted;
            }
            
            if (buyOrder.getQuantity() < order.getQuantity()){
                totalTransacted += buyOrder.getPrice() * order.getQuantity();
                order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
                this.buySide.remove(buyOrder);
                return totalTransacted;
            }
        }
        
        return totalTransacted;
    }

    public int FOKBuy(Order order){
        int totalTransacted = 0;
        int sum = 0;
        boolean flag = false;

        ArrayList<Order> storage = new ArrayList<Order>();

        if (sellSide.first().getPrice() > order.getPrice()) return totalTransacted;

        for (Order sellOrder : sellSide){
            if(order.getPrice() < sellOrder.getPrice()){
                break;
            }
            sum += sellOrder.getQuantity();
            storage.add(sellOrder);
            if ( sum >= order.getQuantity() ){
                flag = true;
                break;
            }
        }

        if(flag){
            for (int i = 0 ; i < storage.size() ; i++){
                if (i == storage.size() - 1){
                    int lowerQty = order.getQuantity() <= storage.get(i).getQuantity() ? order.getQuantity() : storage.get(i).getQuantity();
                    totalTransacted += lowerQty * storage.get(i).getPrice();
                    sum -= lowerQty * storage.get(i).getPrice();
                    if (sum == 0){
                        this.sellSide.remove(storage.get(i));
                        orderDictionary.remove(storage.get(i).getOrderId());
                        break;
                    }
                    else{
                        storage.get(i).setQuantity(storage.get(i).getQuantity() - order.getQuantity());
                        break;
                    }
                }
                else{
                    totalTransacted += storage.get(i).getQuantity() * storage.get(i).getPrice();
                    sum -= storage.get(i).getQuantity() * storage.get(i).getPrice();
                    order.setQuantity(order.getQuantity() - storage.get(i).getQuantity());
                    this.sellSide.remove(storage.get(i));
                    orderDictionary.remove(storage.get(i).getOrderId());
                }
            }
        }
    
        return totalTransacted;
    }

    public int FOKSell(Order order){
        int totalTransacted = 0;
        int sum = 0;
        boolean flag = false;

        ArrayList<Order> storage = new ArrayList<Order>();

        if (buySide.first().getPrice() < order.getPrice()) return totalTransacted;

        for (Order buyOrder : buySide){
            if(order.getPrice() > buyOrder.getPrice()){
                break;
            }
            sum += buyOrder.getQuantity();
            storage.add(buyOrder);
            if ( sum >= order.getQuantity() ){
                flag = true;
                break;
            }
        }

        if(flag){
            for (int i = 0 ; i < storage.size() ; i++){
                if (i == storage.size() - 1){
                    int lowerQty = order.getQuantity() <= storage.get(i).getQuantity() ? order.getQuantity() : storage.get(i).getQuantity();
                    totalTransacted += lowerQty * storage.get(i).getPrice();
                    sum -= lowerQty * storage.get(i).getPrice();
                    if (sum == 0){
                        this.buySide.remove(storage.get(i));
                        orderDictionary.remove(storage.get(i).getOrderId());
                        break;
                    }
                    else{
                        storage.get(i).setQuantity(storage.get(i).getQuantity() - order.getQuantity());
                        break;
                    }
                }
                else{
                    totalTransacted += storage.get(i).getQuantity() * storage.get(i).getPrice();
                    sum -= storage.get(i).getQuantity() * storage.get(i).getPrice();
                    order.setQuantity(order.getQuantity() - storage.get(i).getQuantity());
                    this.buySide.remove(storage.get(i));
                    orderDictionary.remove(storage.get(i).getOrderId());
                }
            }
        }
    
        return totalTransacted;
    }

    // O(logn)
    public void CXL(String orderId){
        // Check if we actually have this order in our OB
        // We trade memory for a faster cancel by hashing the values of the orderId to the order objects
        // Hashmap lookups are O(1)
        if (!orderDictionary.containsKey(orderId)){
            // do nothing if not found
            return;
        }
        Order toCancel = orderDictionary.get(orderId);

        // Check if its in the buySide or the sellSide, and remove accordingly
        if ( buySide.contains(toCancel) ) { buySide.remove(toCancel); }
        else { sellSide.remove(toCancel); }

        return;
    }

    // O(logn) at worst, if its quantity doesn't increase and price doesn't change, O(1)
    public void CRP(Order order){
        // hashmap lookups are O(1)
        if (!orderDictionary.containsKey(order.getOrderId())){
            // do nothing if not found
            return;
        }
        Order toCancel = orderDictionary.get(order.getOrderId());

        if ( order.getQuantity() <= toCancel.getQuantity() && toCancel.getPrice() == order.getPrice() ){
            toCancel.setQuantity(order.getQuantity());
            return;
        }
        else {
            this.CXL(toCancel.getOrderId());
            // add a new order with new attributes
            Side side = toCancel.getSide();
            String orderId = order.getOrderId();
            int price = order.getPrice(); 
            int quantity = order.getQuantity();
            Order newOrder = new Order(side, orderId, price, quantity);
            int temp = toCancel.getSide() == Side.BUY ? this.LOBuy(newOrder) : this.LOSell(newOrder);
            return;
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
        return toRet.toString().replace(",", "").replace("[", "").replace("]", "");
    }
}