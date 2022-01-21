import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
class OrderBook{
    // We store our buy and sell sides as TreeMaps
    Map<String, Order> buySide;
    Map<String, Order> sellSide;

    public OrderBook(){
        this.buySide = new TreeMap<>();
        this.sellSide = new TreeMap<>();
    }

    // check IOC first
    // check FOK
    // if its LO

    // toString for TreeMap
    @Override
    public String toString(){
        StringBuilder toRet = new StringBuilder();
        toRet.append("B: ");


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