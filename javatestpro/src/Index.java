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
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;
import opennlp.tools.stemmer.PorterStemmer;

public class Index {


    /* used to be like vector of pair , setters & getters */ 
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

        // public int gettotal_Score() { return total_score; }
         

        public void settf(int new_tf) {
            TF = new_tf;
        }

        public int getDoc_num() {
            return Doc_num;
        }

        public void print(Database db,String name) {
            System.out.print(gettf() + " " + getDoc_num());
            db.insertFreqs(name, getDoc_num() ,gettf());
        }

    }

    static class Indexer_test {
        HashMap<String, Vector<Help_data>> Detials = new HashMap<>();
        public PorterStemmer stemmer = new PorterStemmer();
    

        public void print(Database db) {
            for (String name : Detials.keySet()) {
                String key = name.toString();
                Vector<Help_data> value = Detials.get(name);
                System.out.print(key + " ");
                for (int i = 0; i < value.size(); i++) {
                    value.get(i).print(db,key);
                    System.out.print("    ");
                }
                System.out.println();
            }
            // System.out.println();

        }

        public void insertIntoDatabase() {
            Database db = new Database();
            for (String name : Detials.keySet()) {
                String key = name.toString();
                Vector<Help_data> value = Detials.get(name);
                int TF, noOfDocument;
                for (int i = 0; i < value.size(); i++) {
                    TF = value.get(i).gettf();
                    noOfDocument = value.get(i).getDoc_num();
                    db.insertFreqs(key, noOfDocument, TF);
                }

            }

        }
        /*  insert used to check stop words , steeming , Tf , etc............  */
        public void insert(Document d, int Current_doc) throws IOException {
            
            Document document = d;
            String title = document.title();
            
          //  String description = document.select("meta[name=description]").get(0).attr("content");
            String description ="jhgfd";
            for (String Html_tags : Init_Score.keySet()) {
                int tf = 1;

                Elements Current_words = document.getElementsByTag(Html_tags);
                Integer Current_score = Init_Score.get(Html_tags);
                for (Element i : Current_words) {
                    String help = i.text();

                    help = help.replaceAll("[^a-zA-Z]", " ");
                    help = help.toLowerCase();
                    String[] Sep_words = help.split(" ");
                    for (String word : Sep_words) {
                        tf = 1;
                        word = stemmer.stem(word);
                       
                        if (!word.equals("") && !Stop_Words.containsKey(word) ) {
                            if(word.length()!=1){
                                Vector<Help_data> vec = new Vector<Help_data>();

                                if (Detials.containsKey(word)) {
                                    boolean check = false;
                                    int place = 0;
                                    vec = Detials.get(word);

                                    for (int k = 0; k < vec.size(); k++) {
                                        check = false;
                                        if (Current_doc + 1 == vec.get(k).getDoc_num()) {
                                            tf += vec.get(k).gettf();
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
            Database db = new Database();
            db.insertURLs(Current_doc ,title, description);

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
        /* list used only in checks */
    public static List<String> Crawler_output = new ArrayList<String>() {
        {
            add("<html><head><title>im im yousef</title></head>" + "<body><p>elmahdy ahmed ahmed</p></body></html>");
            add("<html><head><title>fun</title></head></html>");
            add("<html><head><title>hello ahmed ahmed ahmed world</title></head>"
                    + "<body><p>Sample content yousef Content?</p></body></html>");
            add("<html><head><title>Ahmed ahmed</title></head></html>");
        }
    };

    /* tags to extract words from the document and their score */
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

    /* stop words */
    public static final HashMap<String, Integer> Stop_Words = new HashMap<String, Integer>() {
        {

            put("i", 1);
            put("me", 1);
            put("my", 1);
            put("myself", 1);
            put("we", 1);
            put("our", 1);
            put("ours", 1);
            put("ourselves", 1);
            put("you", 1);
            put("your", 1);
            put("yours", 1);
            put("yourself", 1);
            put("yourselves", 1);
            put("he", 1);
            put("him", 1);
            put("his", 1);
            put("himself", 1);
            put("she", 1);
            put("her", 1);
            put("herself", 1);
            put("hers", 1);
            put("it", 1);
            put("its", 1);
            put("itself", 1);
            put("they", 1);
            put("them", 1);
            put("their", 1);
            put("theirs", 1);
            put("themselves", 1);
            put("what", 1);
            put("which", 1);
            put("who", 1);
            put("whom", 1);
            put("this", 1);
            put("that", 1);
            put("these", 1);
            put("those", 1);
            put("am", 1);
            put("is", 1);
            put("are", 1);
            put("was", 1);
            put("were", 1);
            put("be", 1);
            put("been", 1);
            put("being", 1);
            put("have", 1);
            put("has", 1);
            put("had", 1);
            put("having", 1);
            put("do", 1);
            put("does", 1);
            put("did", 1);
            put("doing", 1);
            put("a", 1);
            put("an", 1);
            put("the", 1);
            put("and", 1);
            put("but", 1);
            put("if", 1);
            put("or", 1);
            put("because", 1);
            put("as", 1);
            put("until", 1);
            put("while", 1);
            put("of", 1);
            put("at", 1);
            put("by", 1);
            put("for", 1);
            put("with", 1);
            put("about", 1);
            put("against", 1);
            put("between", 1);
            put("through", 1);
            put("into", 1);
            put("after", 1);
            put("before", 1);
            put("during", 1);
            put("above", 1);
            put("below", 1);
            put("up", 1);
            put("to", 1);
            put("from", 1);
            put("down", 1);
            put("in", 1);
            put("out", 1);
            put("on", 1);
            put("off", 1);
            put("over", 1);
            put("under", 1);
            put("again", 1);
            put("further", 1);
            put("then", 1);
            put("once", 1);
            put("here", 1);
            put("there", 1);
            put("when", 1);
            put("where", 1);
            put("why", 1);
            put("how", 1);
            put("all", 1);
            put("any", 1);
            put("both", 1);
            put("each", 1);
            put("few", 1);
            put("more", 1);
            put("most", 1);
            put("other", 1);
            put("some", 1);
            put("such", 1);
            put("no", 1);
            put("nor", 1);
            put("not", 1);
            put("only", 1);
            put("own", 1);
            put("same", 1);
            put("so", 1);
            put("than", 1);
            put("too", 1);
            put("very", 1);
            put("can", 1);
            put("will", 1);
            put("just", 1);
            put("don", 1);
            put("should", 1);
            put("also", 1);
            put("now", 1);
            put("s", 1);
            put("t", 1);  
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

    static class Database {
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "1234";
        Connection connection;

        public Database() {
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected to Database");
            } catch (SQLException e) {
                System.out.println("Oops, Error!");
            }
        }

        public void insertURLs(int i, String title, String description) {

            // FileReader fr = new FileReader("URLs.txt");
            // BufferedReader br = new BufferedReader(fr); // creates a buffering character
            // input stream
            // StringBuffer sb = new StringBuffer(); // constructs a string buffer with no
            // characters
            // String line;
            String line;
            try (Stream<String> lines = Files.lines(Paths.get("URLs.txt"))) {
                line = lines.skip(i).findFirst().get();
                try {
                    i++;
                    PreparedStatement stat = connection.prepareStatement("insert into URLs values (" + i + ",'" + line
                            + "','" + title + "','" + description + "');");
                    stat.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println(e);
            }

        }

        public void insertFreqs(String word, int noOfDocument, int TF) {

            try {

            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                PreparedStatement stat = connection.prepareStatement(
                        "insert into Frequencies values ('" + word + "'," + noOfDocument + "," + TF + ");");
                stat.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

        }

        public void deleteURLs() {
            try {

                PreparedStatement stat = connection.prepareStatement("delete from URLs ;");
                stat.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void deleteFrequencies() {
            try {

                PreparedStatement stat = connection.prepareStatement("delete from Frequencies ;");
                stat.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void getSpecificURL(String word) {
            try {

                PreparedStatement stat = connection.prepareStatement(
                        "select URL from URLs inner join frequencies ON frequencies.noOfDocument = URLs.noOfDocument  and word = '"
                                + word + "';");
                ResultSet rs = stat.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("URL"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args) throws IOException {
       

        Indexer_test ob = new Indexer_test();
        Database db = new Database();

        

        for (int i = 0; i < 4; i++) {

            File input = new File(i + ".txt");
            Document doc = Jsoup.parse(input, "UTF-8");
            ob.insert(doc, i);

        }

        ob.print(db);
        //ob.insertIntoDatabase();      // Inserts each word with its noOfDocument and TF
        /*
        Every URL is inserted in the Databse in ob.insert() by using db.insertURLs(noOfDocument, title, description);
        */


        
        // db.deleteURLs();
        // db.getSpecificURL("youtube");

    }
}
