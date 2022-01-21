import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatchingEngine {
    public static void main(String[] args) {
        Order buy2 = new Order(Side.BUY, "a732", 7, 450);
        Order buy1 = new Order(Side.BUY, "iv15", 7, 450);
        Order buy3 = new Order(Side.BUY, "ey7r", 9, 300);
        Order sell1 = new Order(Side.SELL, "bG0d", 12, 250);
        Order sell2 = new Order(Side.SELL, "Ljup", 12, 250);

        OrderBook orderBook = new OrderBook();

        orderBook.executeLOBuy(buy2);
        orderBook.executeLOBuy(buy1);
        orderBook.executeLOBuy(buy3);
        orderBook.executeLOSell(sell1);
        orderBook.executeLOSell(sell2);

        orderBook.executeCXL("bG0d");

        System.out.println(orderBook);

        // try {
        //     File file = new File("input.txt");
        //     FileReader fr = new FileReader(file); 
        //     BufferedReader br = new BufferedReader(fr); 
        //     String line;
        //     while ((line = br.readLine()) != null) {
        //         if (line.equals("END")) break;

        //         // Handle the command using the first word
        //         if (line.split(" ")[0].equals("SUB")) orderHandler(line);
        //         else if (line.split(" ")[0].equals("CXL")) CXLHandler(line);
        //         else CRPHandler(line);
        //     }
        //     fr.close(); // closes the stream
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    // Runs when the first word of the command is SUB
    public static void orderHandler(String orderCommand){

    }
    
    public static void CXLHandler(String cancelCommand){

    }

    public static void CRPHandler(String cancelCommand){

    }
}
