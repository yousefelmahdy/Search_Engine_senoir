import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.sql.Date;
class crawler implements Runnable
{
	//Data Members
	//this Set to handle duplicates
   public Set<String> URL_Set = new HashSet<String>();
    //this Queue to Get the URLs in it and visit them by logic FIFO
   public Queue<String>URLS_Queue=new  LinkedList<String>(); 
    //Compact String represents content of pages to avoid duplication content
    Set<String>Compacted = new HashSet<String>(); 
    int NumberOfThreads ;
    int MaxPages=5000;
    int Current_size;
    
    //Data Members for re-crawling
    long LastCrawlingTime;
    long LastCrawlingHour;
    long LastCrawlingDay ; 
    

  
    //Constructor
    public crawler(Queue<String>URL, Set<String> uRL_Set, int current_size2 , int NOT) {
	this.URLS_Queue=URL;
	this.URL_Set=uRL_Set;
	this.Current_size=current_size2;
	this.NumberOfThreads = NOT ;
	}
    

	@Override
    public void run() {

    	int d=0;
    	crawl();
    	//for(int i =0; i < n;i++)
			
        Date today = new Date(d); 
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(today); 
        LastCrawlingDay = cal.get(Calendar.DAY_OF_WEEK); 
        LastCrawlingTime = System.currentTimeMillis();
        LastCrawlingHour = cal.get(Calendar.HOUR);
     // to handle case of (CurrentHour = 2 & LastCrawlingHour = 12) --> CurrentHour-LastCrawlingHour will never be > 6 
        synchronized(this) 
        {
        if( LastCrawlingHour == 12 )
        	 LastCrawlingHour = 0;
     // to handle case of (CurrentDay = 2 & LastCrawlingDay = 7) --> CurrentDay-LastCrawling will never be > 1 
        if( LastCrawlingDay == 7 ) 
        	LastCrawlingDay = 0; 
        }
        long CurrentTime,CurrentDay,CurrentHour ;
        while(true)
        {
        	CurrentTime =  System.currentTimeMillis();
        	CurrentDay = cal.get(Calendar.DAY_OF_WEEK); 
        	CurrentHour = cal.get(Calendar.HOUR);  
        	if(Math.subtractExact(CurrentTime,LastCrawlingTime) >= 600000) //after 10 minutes of last Re-Crawling --> Do Re-Crawling
        		{
        		   ReCrawl(0); // zero indicates that this re-crawling is for only URLs with extension .COM 
        		   LastCrawlingTime = System.currentTimeMillis();
        		}
        	else
        	{
        		if(Math.subtractExact(CurrentHour,LastCrawlingHour) >= 6)
        		{
         		   ReCrawl(1); // zero indicates that this re-crawling is for only URLs with extension .COM / .NET or .ORG
         		    today = new Date(d); 
         	        cal.setTime(today); 
         	       LastCrawlingHour = cal.get(Calendar.HOUR); 
         	      if( LastCrawlingHour == 12 )// to handle case of (CurrentHour = 2 & LastCrawlingHour = 12) --> CurrentHour-LastCrawlingHour will never be > 6 
                 	 LastCrawlingHour = 0;
         		}
        		else if(Math.subtractExact(CurrentDay,LastCrawlingDay) >= 1)
        		{
        			ReCrawl(1); // zero indicates that this re-crawling is for only URLs with extension .COM / .NET or .ORG
         		    today = new Date(d); 
         	        cal.setTime(today); 
         	       LastCrawlingDay = cal.get(Calendar.DAY_OF_WEEK);  
        	        if( LastCrawlingDay == 7 ) // to handle case of (CurrentDay = 2 & LastCrawlingDay = 7) --> CurrentDay-LastCrawling will never be > 1 
        	        	LastCrawlingDay = 0; 
        		}
        	}
        	
        }
        
        
        
    }

    
    
    public String GetDomain(String url)
    {
    	URL link;
    	String domain ="";
    	try {
			link = new URL(url);
			 url=link.getHost();
			 
			int start = url.lastIndexOf('.') ;//to get the index of first . after WWW. --> to get domain
	    	domain = url.substring(start);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return domain;
    }
    
    
    
     //Handle the robot.txt part ......
  	Vector<String> Robot_Handling(String Url)
  	{
		int Last_Value;
  		//System.out.println(Url);
          URL Link_Url=null;
             try {
          //create the URL from the string to get the host ;
  		 Link_Url = new URL(Url);              
  		         } catch (MalformedURLException e) {
  			 System.out.println("There is an error happen while creating the URL \n" );}
  		                                           
          //get the host as the robot.txt is in the root..
             if(Link_Url!=null)
             {
              String Link_Host= Link_Url.getHost();
              String Link_Protocol=Link_Url.getProtocol();
              String robot_URL= Link_Protocol+"://"+ Link_Host +"/robots.txt";
              
              //Connecting to the web page
              Connection conn = Jsoup.connect(robot_URL);
              //executing the get request
              Document doc = null;
  			try {
  				doc =conn.get();
  			    } catch (IOException e) {
  				//e.printStackTrace();
  			    }
  			
  	 if(doc!=null)   
  	  {
              //Retrieving the contents (body) of the web page
              String result = doc.body().text();
              //System.out.println(result);
              //this Vector will have the URLs in robots.txt
              Vector<String> V = new Vector<String>();
              int index=0;
              //get the index of the first char in the User-agent
          	int User_Ag=result.indexOf("user-agent",0);
          	if(User_Ag==-1)
      		{
      			User_Ag=result.indexOf("User-agent",0);
      		}
          	if(User_Ag==-1)
          	{
          	  User_Ag=result.indexOf("User-Agent",0);
          	}
              //check if something have returned  if not User_Ag will be -1
          	while(User_Ag>=0)
          	{
          	  //make sure that the web site prevent all the crawlers if there is User-agent: *
              //note that * is far from U by 12 indexes
          	if(result.charAt(User_Ag+12)=='*')
          	{
          	    Last_Value=User_Ag;
          		index=User_Ag;
          	   //get the  next User agent
          		User_Ag=result.indexOf("user-agent",User_Ag+10);	
          		if(User_Ag==-1)
          		{
          			User_Ag=result.indexOf("User-agent",Last_Value+10);
          		}
          		if(User_Ag==-1)
              	{
              	  User_Ag=result.indexOf("User-Agent",Last_Value+10);
              	}
          		int D_search=result.indexOf("disallow",index);
          		if(D_search==-1)
          		{
          			D_search=result.indexOf("Disallow",index);
          		}
          		//check that all the Disallows which are found is before the next user agent or the Last User agent was the Last one 
          		while(D_search<User_Ag||User_Ag==-1)
              	{   //break if didn't find Disallow statement and lock for the next User_Agent have *
              		if(D_search<0)     
              			break;
              		if(result.indexOf(" ",D_search+10)!=-1)
              		{
              			if(result.charAt(D_search+10)!=' ') //if there is Disallow: in robot.txt
              			{ 	
              		   // System.out.println(result.subSequence(D_search+10,result.indexOf(" ",D_search+10)));
              		    V.add((result.subSequence(D_search+10,result.indexOf(" ",D_search+10)).toString()));
              			}
              		}
              		index=D_search+10;
              		//search for the next disallow from index D_search+10
              		D_search=result.indexOf("disallow",index);
              		if(D_search==-1)
              		{
              			D_search=result.indexOf("Disallow",index);
              		}
              	}
          	}else
          	{
             Last_Value=User_Ag;
          	 User_Ag=result.indexOf("user-agent",User_Ag+10);	//the  next User agent
          	if(User_Ag==-1)
              User_Ag=result.indexOf("User-agent",Last_Value+10);
      		
          	if(User_Ag==-1)
          	  User_Ag=result.indexOf("User-Agent",Last_Value+10);
          	}	
  		 }
          	return V;
      }
             return null;
  }
             return null;
 }
    
    
    
    
    //to create a compact string of page content.
    private String Compact(String content)
    {
    	String c = "";
    	int i = 0;
    	while(i < content.length())
    	{
    		c+= content.charAt(i);
    		i+=10;
    	}
    	return c;
    }
    
    
    //function for searching (Will be used to search for the URL in Robots.txt)
  	public boolean Search(Vector<String>V,String S)
  	{
  		for(String element:V)
  		if(S.contains(element))
  			return true;
  		return false;
  	}
  	
    

    //To Calculate how often each page's content changes .(for Re-crawling)
    public void ReCrawl(int idx)
    {
    	int counter = 0;
    	Set<String>domains = new HashSet<String>() ;
    	boolean all = false ;
    	if(idx >= 0)
    	{
    		domains.add(".com");
    	}
    	if(idx >= 1)
    		{
    		  domains.add(".net");
    		  domains.add(".org");
    		}
    	if(idx ==2)
    	{
    	 all =true;
    	}
    		
    	FileWriter myfile;
    	 for (String url : URL_Set)
    	 {
    		 if(all == true || domains.contains(GetDomain(url)) )
    		 { 
    			try {
				org.jsoup.nodes.Document doc =Jsoup.connect(url).get();
				myfile = new FileWriter(counter +".txt");
				myfile.write(doc.body().toString());
				myfile.close();
			    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
    		 }
            counter++; 
         }
    }
    
   

    
    public void crawl()
    {   
    	String fetched_URL;
    	BufferedWriter writer=null;
    	Document doc1 = null;
        while(Current_size < 100) //Should be 5000 Later 
        {
        	if(!URLS_Queue.isEmpty())
        	{
        		//to prevent more than one thread from adding in the queue
        		synchronized(this.URLS_Queue) {	
        		fetched_URL=URLS_Queue.poll();
        		Connection conn = Jsoup.connect(fetched_URL);
        		if(conn!=null)
        		{
            	try {
					 doc1 =  conn.get();
					String result = doc1.body().text();
					result = Compact(result);
					if(Compacted.contains(result))
						{
						  System.out.println("\nThis link has the same content of another one \n" );
						  continue;   // do not add it
						}
					else 
						{
						Compacted.add(result);
						File f = new File("URLs.txt");
				        try {
				            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
				            bw.append(fetched_URL+"\n");
				            bw.close();
				        } catch (IOException e) {
				           // System.out.println(e.getMessage());
				        }
						//convert the document to .TXT file
						FileWriter FW = new FileWriter(Current_size+".txt");
						writer=new BufferedWriter(FW);
						Current_size++;
						System.out.println(Current_size);
						writer.write(doc1.toString());
						}
				    }
				 catch (IOException e) {
					 System.out.println("\nCan't fetch this URL\n" );
					//e.printStackTrace();
				                       }
        		    }
        		}
        
        if (doc1!=null)
        {
        //<a is the tag and hre_f the attribute_ that have the link 
        org.jsoup.select.Elements links = doc1.select("a[href]"); 
        for(Element Link :links )
        {   boolean Exist = false;
            String NewLink = Link.attr("abs:href");
            Vector<String>Robot_txt1=Robot_Handling(Link.attr("abs:href"));
            if(Robot_txt1!=null)
             Exist=Search(Robot_txt1,Link.attr("abs:href"));
             //Check that the file is a HTML file
             URL url;
             String type=null;
			try {
				 url = new URL(NewLink);
				 URLConnection u;
				try {
					 u = url.openConnection();
					  type = u.getHeaderField("Content-Type");
					 System.out.println(type);
				    } catch (IOException e) {
					
					//e.printStackTrace();
				                            }
			     } catch (MalformedURLException e) {
				
				//e.printStackTrace();
			                                           }
			
			//check that the URL is not in the robots.txt and that it is a HTML file
            if(!Exist&&type!=null&&(type.contentEquals("text/html; charset=utf-8")||type.contentEquals("text/html")))
            {
            	synchronized(this.URLS_Queue) {	
            if(URL_Set.contains(NewLink) == false )
            {
            	URL_Set.add(NewLink); //add the set
            	URLS_Queue.add(NewLink); //add to the queue
            	System.out.println("\n New link is "+NewLink+", Current size is "+ Current_size +'\n' );	
            }
            else
            	System.out.println("\n New link is "+NewLink+"thus link is already taken , Current size is still the same \n " );
            		                            }	
            }
            	
         
            	
          }
        
        }
       }
       else
       break;
        }
        return ;
    }
}


