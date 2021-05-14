import java.util.List;
import java.util.ArrayList;

public class Indexer {
    
    // is that possible!!
  /*  public static  List<String> Crawler_output = new ArrayList<String>()
    {
        {
            add("<html><head><title>im im yousef</title></head>" + "<body><p>elmahdy</p></body></html>");
            add("<html><head><title>fun</title></head></html>");
            add("<html><head><title>hello world</title></head>" + "<body><p>Sample content yousef Content?</p></body></html>");
            add("<html><head><title>Ahmed</title></head></html>");
        }
    };
    public static  List<Thread> Threads = new ArrayList<Thread>();

    public static void runIndexer(int Num_Of_Threads) {
        

        // helping in distrubte threads
        int helper = Crawler_output.size() / Num_Of_Threads;

        for (int i = 0; i < Num_Of_Threads; i++) {
            if (i != Num_Of_Threads - 1) {
               
                Thread T = new Thread(new Index(i * helper, (i * helper) + helper));
                T.start();
                Threads.add(T);
            } else {
               
                Thread T = new Thread(new Index(i * helper, Crawler_output.size()));
                T.start();
                Threads.add(T);
            }

        }

        for (Thread T: Threads){
            try {
                T.join();
            } catch (InterruptedException e) {

            }
        }*/

    }

    public static void main(String[] args) {

        //runIndexer(2);
    }

}
