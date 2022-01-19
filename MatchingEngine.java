import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatchingEngine {
    public static void main(String[] args) {
        Order buy1 = new Order('B', "iv15", 7, 450);
        Order buy2 = new Order('B', "ey7r", 9, 300);

        OrderBook ob = new OrderBook();

        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file); 
            BufferedReader br = new BufferedReader(fr); 
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("END")) break;

                // Handle the command using the first word
                if (line.split(" ")[0].equals("SUB")) orderHandler(line);
                else if (line.split(" ")[0].equals("CXL")) CXLHandler(line);
                else CRPHandler(line);


                
            }
            fr.close(); // closes the stream
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void orderHandler(String orderCommand){

    }
    
    public static void CXLHandler(String cancelCommand){

    }

    public static void CRPHandler(String cancelCommand){

    }
}