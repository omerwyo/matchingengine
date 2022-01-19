// public class BuyOrder extends Order implements Comparable<BuyOrder>{
//     // We specify the comparator as required by the matching engine. 

//     public BuyOrder(char side, String orderId, int price, int quantity){
//         super(side, orderId, price, quantity);
//     }
//     @Override
//     public int compareTo(BuyOrder otherBuyOrder){
//         return Integer.compare(otherBuyOrder.getPrice(), this.getPrice());
//     }
// }