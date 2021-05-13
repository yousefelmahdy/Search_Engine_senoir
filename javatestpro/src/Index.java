import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;

public class Index implements Runnable  {

    int begin, end;

    public Index(int b, int e) {
        begin = b;
        end = e;
    }

    public void run() {
        for(int i=begin;i<end;i++)
        {
            handling(Indexer.Crawler_output.get(i));
        }
    }


    public static final HashMap<String, Integer> Init_Score = new HashMap<String, Integer>() {
        {

            put("title", 100);
            put("h1", 50);
            put("h2", 40);
            put("h3", 30);
            put("h4", 20);
            put("h5", 10);
            put("h6", 5);
            put("p", 2);
        }
    };

   

    public static void handling(String link) {

        String html = "<html><head><title>Sample &Title</title></head>" + "<body><p>Sample Content?</p></body></html>";
        Document document = Jsoup.parse(html);
        String title = document.title();
        HashMap<String, Integer> Word_score = new HashMap<>();
        // Elements paragraphs = document.getElementsByTag("p");
        
        for (String Html_tags : Init_Score.keySet()) {
            Elements Current_words = document.getElementsByTag(Html_tags);
            Integer Current_score = Init_Score.get(Html_tags);
            for (Element i : Current_words) {
                String help = i.text();
                help=help.replaceAll("[^0-9a-zA-Z]", " ");
                help = help.toLowerCase();
                String[] Sep_words = help.split(" ");
                for (String word : Sep_words) {

                   // System.out.println(word);
                    if (!word.equals("")) {
                        Integer pr_score = Word_score.getOrDefault(word, 0);
                        Word_score.put(word, pr_score + Current_score);

                    }
                }

            }
        }

        System.out.println(Word_score);

    }

    public static void main(String[] args) {

        // System.out.println("wodeion");
        handling("KDK");
    }
}
