// public class SellOrder extends Order implements Comparable<SellOrder>{
//     // We specify the comparator as required by the matching engine. 

//     public SellOrder(char side, String orderId, int price, int quantity){
//         super(side, orderId, price, quantity);
//     }

//     @Override
//     public int compareTo(SellOrder otherSellOrder){
//         return Integer.compare(this.getPrice(), otherSellOrder.getPrice());
//     }
// }
