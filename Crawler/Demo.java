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
		
		
		Scanner myObj = new Scanner(System.in);
		int n =myObj.nextInt();
		crawler c = new crawler(URLS_Queue,URL_Set,Current_size,n);
		Thread ths[] = new Thread[n];
		for(int i =0; i < n;i++)
			ths[i]=new Thread(c);
		
		for(int i =0; i < n;i++)
			ths[i].start();
        
		
    }
}

