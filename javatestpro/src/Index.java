import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Index {

    static class Help_data {
        int TF;
        int Doc_num;
        // int total_score;

        public Help_data(int a, int b) {
            TF = a;
            Doc_num = b;
            // total_score=c;
        }

        public int gettf() {
            return TF;
        }

        /*
         * public int gettotal_Score() { return total_score; }
         */

        public void settf(int new_tf) {
            TF = new_tf;
        }

        public int getDoc_num() {
            return Doc_num;
        }

        public void print() {
            System.out.print(gettf() + " " + getDoc_num());
        }

    }

    static class Indexer_test {
        HashMap<String, Vector<Help_data>> Detials = new HashMap<>();

        public void print() {
            for (String name : Detials.keySet()) {
                String key = name.toString();
                Vector<Help_data> value = Detials.get(name);
                System.out.print(key + " ");
                for (int i = 0; i < value.size(); i++) {
                    value.get(i).print();
                    System.out.print("    ");
                }
                System.out.println();
            }
            // System.out.println();

        }

        public void insert(String data, int Current_doc) throws IOException {
           
           // String html = data;

           // Document document = Jsoup.parse(html);
           // Document doc = Jsoup.connect("https://www.wikipedia.org/").get();
           Document document = Jsoup.connect("https://stackoverflow.com/questions/12526979/jsoup-get-all-links-from-a-page#").get();

            String title = document.title();

            for (String Html_tags : Init_Score.keySet()) {
                int tf = 1;

                Elements Current_words = document.getElementsByTag(Html_tags);
                Integer Current_score = Init_Score.get(Html_tags);
                for (Element i : Current_words) {
                    String help = i.text();
                
                    help = help.replaceAll("[^0-9a-zA-Z]", " ");
                    help = help.toLowerCase();
                    String[] Sep_words = help.split(" ");
                    for (String word : Sep_words) {
                        tf = 1;
                        // System.out.println(word);
                        if (!word.equals("")) {
                            Vector<Help_data> vec = new Vector<Help_data>();

                            if (Detials.containsKey(word)) {
                                boolean check = false;
                                int place = 0;
                                vec = Detials.get(word);
                                
                                for (int k = 0; k < vec.size(); k++) {
                                    check = false;
                                    if (Current_doc + 1 == vec.get(k).getDoc_num()) {
                                        tf+=vec.get(k).gettf();
                                        check = true;
                                        place = k;
                                        break;
                                    }
                                }
                                if (check) {
                                    vec.get(place).settf(tf);

                                } else {
                                    Help_data hh = new Help_data(tf, Current_doc + 1);
                                    vec.add(hh);
                                    
                                    Detials.replace(word, vec);
                                }
                            }

                            else {
                                Help_data hh = new Help_data(tf, Current_doc + 1);
                                vec.add(hh);
                                Detials.put(word, vec);

                            }

                        }

                    }

                }
            }

        }

    }

    /*
     * int begin, end;
     * 
     * public Index(int b, int e) { begin = b; end = e; }
     * 
     * public void run() { for(int i=begin;i<end;i++) {
     * handling(Indexer.Crawler_output.get(i),i); } }
     */

    public static List<String> Crawler_output = new ArrayList<String>() {
        {
            add("<html><head><title>im im yousef</title></head>" + "<body><p>elmahdy ahmed ahmed</p></body></html>");
            add("<html><head><title>fun</title></head></html>");
            add("<html><head><title>hello ahmed ahmed ahmed world</title></head>"
                    + "<body><p>Sample content yousef Content?</p></body></html>");
            add("<html><head><title>Ahmed ahmed</title></head></html>");
        }
    };

    public static final HashMap<String, Integer> Init_Score = new HashMap<String, Integer>() {
        {

            put("title", 100);
            put("h1", 50);
            put("h2", 40);
            put("h3", 30);
            put("h4", 20);
            put("h5", 10);
            put("h6", 5);
            put("em", 3);
			put("b", 3);
			put("i", 3);
			put("u", 3);
			put("a", 3);
            put("p", 2);
        }
    };

    // public static final HashMap<String, Vector<Help_data>> last = new
    // HashMap<String, Vector<Help_data>>() { };

    /*
     * public static void handling(String data, int Current_doc) {
     * 
     * String html = data; Document document = Jsoup.parse(html); String title =
     * document.title(); HashMap<String, Integer> Word_score = new HashMap<>();
     * HashMap<String, Vector<Help_data>> Detials = new HashMap<>(); // Elements
     * paragraphs = document.getElementsByTag("p");
     * 
     * for (String Html_tags : Init_Score.keySet()) { int tf = 1;
     * 
     * Elements Current_words = document.getElementsByTag(Html_tags); Integer
     * Current_score = Init_Score.get(Html_tags); for (Element i : Current_words) {
     * String help = i.text(); help = help.replaceAll("[^0-9a-zA-Z]", " "); help =
     * help.toLowerCase(); String[] Sep_words = help.split(" "); for (String word :
     * Sep_words) { tf = 1; // System.out.println(word); if (!word.equals("")) { if
     * (Detials.containsKey(word)) { tf++; }
     * 
     * Integer pr_score = Word_score.getOrDefault(word, 0); Word_score.put(word,
     * pr_score + Current_score); Vector<Help_data> vec = new Vector<Help_data>();
     * Help_data hh = new Help_data(tf, Current_doc + 1); vec.add(hh);
     * Detials.put(word, vec); }
     * 
     * }
     * 
     * } } // System.out.println(Word_score); // System.out.println(Detials);
     * 
     * for (String name: Detials.keySet()) { String key = name.toString();
     * Vector<Help_data> value = Detials.get(name); --> value.add()
     * System.out.println(key + " " + value); }
     * 
     * 
     * /* Vector<Help_data> v = new Vector<Help_data>(); Vector<Help_data> v2 = new
     * Vector<Help_data>(); for (String H : Detials.keySet()) {
     * 
     * 
     * for (String H2 : last.keySet()) { if(H==H2) { v = last.get(H2); v2 =
     * Detials.get(H);
     * 
     * } else{
     * 
     * } }
     * 
     * }
     */

    // return Detials;

    // }

    public static void main(String[] args) throws IOException {
        // System.out.println("wodeion");
        /*
         * HashMap<String, Vector<Help_data>> collect = new HashMap<>(); HashMap<String,
         * Vector<Help_data>> last = new HashMap<>(); for(int
         * i=0;i<Crawler_output.size();i++) { String data =Crawler_output.get(i);
         * collect = handling(data,i); for(int j=0;j<collect.size();j++) { String a =
         * collect. if(last.containsKey(a)) {
         * 
         * }
         * 
         * } }
         */

        Indexer_test ob = new Indexer_test();

       /* for (int i = 0; i < Crawler_output.size(); i++) {
            String data = Crawler_output.get(i);
            Path fileName = Path.of("demo1.txt");
            //String content = "hello world !!";
            //Files.writeString(fileName, content);

            String actual = Files.readString(fileName);
            //System.out.println(actual);
            ob.insert(data, i);
            // handling(data, i);
        }*/


        ob.insert("kk", 0);
        ob.print();

    }
}
