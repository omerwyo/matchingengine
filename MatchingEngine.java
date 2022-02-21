import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MatchingEngine {

    static final OrderBook orderBook = new OrderBook();

    public static void main(String[] args) {
        Order buy2 = new Order(Side.BUY, "a732", 7, 450);
        Order buy1 = new Order(Side.BUY, "ey7r", 7, 450);
        Order sell1 = new Order(Side.SELL, "bG0d", 12, 250);
        Order sell2 = new Order(Side.SELL, "Ljup", 12, 250);

        // orderBook.executeLOBuy(buy2);
        // orderBook.executeLOBuy(buy1);
        // orderBook.executeLOSell(sell1);
        // orderBook.executeLOSell(sell2);

        // System.out.println(orderBook);

        try {
            File file = new File("inputFOK.txt");
            FileReader fr = new FileReader(file); 
            BufferedReader br = new BufferedReader(fr); 
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("END")) break;
                System.out.println(line);

                String[] command = line.split(" ");

                // Handle the command using the first word
                if (command[0].equals("SUB")) orderHandler(command);
                else if (command[0].equals("CXL")) CXLHandler(command);
                else CRPHandler(command);
                
                System.out.println(orderBook);
                System.out.println();
            }
            fr.close(); // closes the stream
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(orderBook);
    }

    // Runs when the first word of the command is SUB
    public static void orderHandler(String[] orderCommand){
        Side side = orderCommand[2].equals("B") ? Side.BUY : Side.SELL;
        String orderId = orderCommand[3];
        int quantity = Integer.parseInt(orderCommand[4]);

        int totalTransacted = 0;

        if (orderCommand[1].equals("MO")){
            if ( side == Side.BUY ){ 
                totalTransacted = orderBook.MOBuy(new Order(side, orderId, quantity)); 
                System.out.println(totalTransacted);
            }
            else { 
                totalTransacted = orderBook.MOSell(new Order(side, orderId, quantity)); 
                System.out.println(totalTransacted);
            }
        }

        else if ( orderCommand[1].equals("LO") ){
            int price = Integer.parseInt(orderCommand[5]);

            if ( side == Side.BUY ){ 
                totalTransacted = orderBook.LOBuy(new Order(side, orderId, price, quantity)); 
                System.out.println(totalTransacted);
            }
            else { 
                totalTransacted = orderBook.LOSell(new Order(side, orderId, price, quantity)); 
                System.out.println(totalTransacted);
            }
        }

        else if ( orderCommand[1].equals("IOC") ){
            int price = Integer.parseInt(orderCommand[5]);

            if ( side == Side.BUY ){ 
                totalTransacted = orderBook.IOCBuy(new Order(side, orderId, price, quantity)); 
                System.out.println(totalTransacted);
            }
            else { 
                totalTransacted = orderBook.IOCSell(new Order(side, orderId, price, quantity)); 
                System.out.println(totalTransacted);
            }
        }

        else if ( orderCommand[1].equals("FOK") ){
            int price = Integer.parseInt(orderCommand[5]);

            if ( side == Side.BUY ){ 
                totalTransacted = orderBook.FOKBuy(new Order(side, orderId, price, quantity)); 
                System.out.println(totalTransacted);
            }
            else { 
                totalTransacted = orderBook.FOKSell(new Order(side, orderId, price, quantity)); 
                System.out.println(totalTransacted);
            }
        }

    }
    
    public static void CXLHandler(String[] cancelCommand){
        String orderId = cancelCommand[1];
        orderBook.CXL(orderId);
    }

    public static void CRPHandler(String[] cancelCommand){
        String orderId = cancelCommand[1];
        int quantity = Integer.parseInt(cancelCommand[2]);
        int price = Integer.parseInt(cancelCommand[3]);

        orderBook.CRP(new Order(orderId, quantity, price));
    }
}
