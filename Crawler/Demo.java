<<<<<<< HEAD

=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import org.jsoup.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Demo
{
	
	public static void main(String [] args )
    {
		Queue<String>URLS_Queue=new  LinkedList<String>();
		Set<String> URL_Set = new HashSet<String>();
		 int Current_size=0;
<<<<<<< HEAD
		 
		 System.out.print("hello");
		URLS_Queue.add("https://www.w3schools.com/jsref/event_onkeypress.asp");
		URLS_Queue.add("https://www.youtube.com/watch?v=TCd8QIS-2KI");
		URLS_Queue.add("https://codinginflow.com/");
		URLS_Queue.add("https://stackoverflow.com/questions/12526979/jsoup-get-all-links-from-a-page#");
		URLS_Queue.add("https://www.w3schools.com/xml/ajax_xmlhttprequest_response.asp");
		URLS_Queue.add("https://en.wikipedia.org/?fbclid=IwAR1WEcp_LYFywH7-4DVnGBr1iDw3jv6JnddSrs_HLt_lrOcNaxJvSS0gN8I");
	    URLS_Queue.add("https://en.wikipedia.org/wiki/Main_Page");
		URLS_Queue.add("https://www.youtube.com/watch?v=hxFuaZL9pRY");
		URLS_Queue.add("https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/onreadystatechange");
	    URLS_Queue.add("https://developer.mozilla.org/en-US/docs/Web/API/DOMString");
	    URLS_Queue.add("https://developer.mozilla.org/en-US/docs/Web/API/Document/readystatechange_event");
		URLS_Queue.add("https://www.w3schools.com/xml/ajax_xmlhttprequest_response.asp");
		URLS_Queue.add("https://stackoverflow.com/questions/15145929/");
		URLS_Queue.add("https://brantu.com/eg-en/");
		URLS_Queue.add("https://www.facebook.com/mawaqifghariba0/");
		URLS_Queue.add("https://www.google.com/search?");
	    URLS_Queue.add("https://stackoverflow.com/questions/16758346/how-pause-and-then-resume-a-thread");
		URLS_Queue.add("https://www.youm7.com/");
		URLS_Queue.add("https://en.wikipedia.org/wiki/COVID-19");
	    URLS_Queue.add("https://www.amnesty.org/en/");
	    URLS_Queue.add("https://twitter.com/youm7");
		URLS_Queue.add("https://www.semrush.com/website/amazon.com/?");
		URLS_Queue.add("https://www.instagram.com/");
		URLS_Queue.add("https://www.linkedin.com/feed/");
		URLS_Queue.add("https://visualstudio.microsoft.com/thank-you-downloading-visual-studio");
		URLS_Queue.add("https://eg.indeed.com/?r=us");
	    URLS_Queue.add("https://www.pinterest.com/");
		URLS_Queue.add("https://www.quora.com/");
		URLS_Queue.add("https://www.chase.com/");
	    URLS_Queue.add("https://www.themuse.com/");
	    ///////////////////////////////////////////
	    URLS_Queue.add("https://www.bbc.com/news");
	    URLS_Queue.add("https://time.com/");
	    URLS_Queue.add("https://codeforces.com/");
	    URLS_Queue.add("https://www.dailymotion.com/");
	    URLS_Queue.add("https://www.sciencemag.org/");
	    URLS_Queue.add("https://www.britannica.com/");
	    URLS_Queue.add("https://www.httpwatch.com/");
	    URLS_Queue.add("https://www.restapitutorial.com/");
	    URLS_Queue.add("https://www.javatpoint.com/");
	    URLS_Queue.add("https://www.khanacademy.org/");
	    URLS_Queue.add("https://www.futurelearn.com/info/courses/programming-101/0/steps/43783");
	    URLS_Queue.add("https://www.usnews.com/education/best-global-universities/rankings");
	    URLS_Queue.add("https://www.computerhope.com/jargon/o/os.htm");
	  
	    
	   
		//URL_Set.add("https://codinginflow.com/");
=======
		//URLS_Queue.add("https://stackoverflow.com/questions/12526979/jsoup-get-all-links-from-a-page#");
		 URLS_Queue.add("https://www.youtube.com/watch?v=TCd8QIS-2KI");
		//URLS_Queue.add("https://codinginflow.com/");
		//URL_Set.add("https://stackoverflow.com/questions/12526979/jsoup-get-all-links-from-a-page#");
		 //URL_Set.add("https://www.youtube.com/watch?v=TCd8QIS-2KI");
		//URL_Set.add("https://codinginflow.com/");


		/* SEEDS */ 
		/* 
		https://www.bbc.com/news
		https://time.com/
		https://www.geeksforgeeks.org/
		https://stackoverflow.com/
		https://codeforces.com/
		https://www.w3schools.com/
		https://www.dailymotion.com/ 
		*/
		
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
		
		Scanner myObj = new Scanner(System.in);
		int n =myObj.nextInt();
		crawler c = new crawler(URLS_Queue,URL_Set,Current_size,n);
<<<<<<< HEAD
		//(new Thread(c)).start();
		
=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
		Thread ths[] = new Thread[n];
		for(int i =0; i < n;i++)
			ths[i]=new Thread(c);
		
		for(int i =0; i < n;i++)
			ths[i].start();
<<<<<<< HEAD
			
        
		
    }
}
=======
        
		
    }
}

>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
