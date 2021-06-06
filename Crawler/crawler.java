<<<<<<< HEAD

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
=======
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
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
<<<<<<< HEAD
import java.io.FileWriter;   
=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
class crawler implements Runnable
{
	//Data Members
	//this Set to handle duplicates
<<<<<<< HEAD
   FileWriter myWriter=null;
=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
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
<<<<<<< HEAD
    	System.out.printf("i am thread %d from first line of run\n",Thread.currentThread().getId());
    	while(Current_size<=5000)
    	{
    	if(URLS_Queue.isEmpty())
    	break;
    	String url = URLS_Queue.poll();
    	crawl(url);
    	}
    	//for(int i =0; i < n;i++)
			/*
=======
    	crawl();
    	//for(int i =0; i < n;i++)
			
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
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
<<<<<<< HEAD
        */
=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
        
        
        
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
<<<<<<< HEAD
        URL Link_Url=null;
         try 
          {
            //create the URL from the string to get the host ;
  		    Link_Url = new URL(Url);              
  		  } 
          catch (MalformedURLException e) 
          {
  			System.out.println("There is an error happen while creating the URL \n" );
  			e.getStackTrace();
  			System.out.println(Url);
  			return null;
  	      } 		                                           
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
 			try 
 			  {
 				doc =conn.get();
 			  } 
 			catch (IOException e) 
 			  {
 				return null;
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
=======
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
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
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
<<<<<<< HEAD
            }
 	        
 
            return null;
           }
        
         
        return null;
     }
=======
      }
             return null;
  }
             return null;
 }
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
    
    
    
    
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
<<<<<<< HEAD
  	/*
=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
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
<<<<<<< HEAD
    	BufferedWriter writer ;
=======
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
    	 for (String url : URL_Set)
    	 {
    		 if(all == true || domains.contains(GetDomain(url)) )
    		 { 
    			try {
<<<<<<< HEAD
    				Connection conn = Jsoup.connect(url);
    				Document doc = conn.get();
    				FileWriter FW = new FileWriter(counter+".txt");
					writer=new BufferedWriter(FW);
					writer.write(doc.toString());
=======
				org.jsoup.nodes.Document doc =Jsoup.connect(url).get();
				myfile = new FileWriter(counter +".txt");
				myfile.write(doc.body().toString());
				myfile.close();
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
			    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
    		 }
            counter++; 
         }
    }
    
<<<<<<< HEAD
   */

    
    public void crawl(String fetched_URL)
    {   
    	//String fetched_URL;
    	BufferedWriter writer=null;
    	Document doc1 = null;
    	
    	/*
    	 try {
			 myWriter = new FileWriter("URLs.txt");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		*/
        while(Current_size < 5000) //Should be 5000 Later 
        { 
        	if(URL_Set.contains(fetched_URL) == false)
        	{
        		System.out.printf("i am thread %d from first line crawl\n",Thread.currentThread().getId());
        		try {
					URL myU = new URL(fetched_URL);
				} catch (MalformedURLException e2) {
					// TODO Auto-generated catch block
					return;
				}
        		Connection conn = Jsoup.connect(fetched_URL);
        		System.out.println(conn.response().statusCode());
        	if(conn.response().statusCode()==0)
    		{
        		
        		//System.out.println("connection done sucssefully \n" );
        		URL Link_Url=null;
				try 
		          {
		            //create the URL from the string to get the host ;
				    Link_Url = new URL(fetched_URL);
    		      }
	          catch (MalformedURLException e) 
	          {
	  			//System.out.println("There is an error happen while creating the URL i am in the while loop \n" );
	  			e.getStackTrace();
	  			System.out.println(fetched_URL);
	  	      } 	
        		 String type;
					 try {
						//check the type
						   URLConnection u = null;
						  
						   try {
							u = Link_Url.openConnection();
						} catch (IOException e) {
							//System.out.println("openConnection() error");
							e.printStackTrace();
						}
	     			       type = u.getHeaderField("Content-Type"); // Get the type of the URL (we save URLs of type HTML only).
	     				   System.out.println(type + "\tI am thread "+Thread.currentThread().getId()+Thread.currentThread().getName());
						 if(type==null||(!type.equalsIgnoreCase("text/html; charset=utf-8")&&!type.equalsIgnoreCase("text/html")))
								 return;
						doc1 =conn.get();
					 }catch (IOException e1) {
						// System.out.println("There is an error connection.get \n" );
							e1.printStackTrace();
				    }	
					
					 if(doc1!=null)
					 {
						 String result = doc1.body().text();
							result = Compact(result);
							boolean duplic ;
							synchronized(this) 
							 {	
								duplic = Compacted.contains(result);
							 }
							
							   // System.out.println("URL created successfuly \n" );
							    
							    if(!duplic &&!result.isEmpty()&&(Link_Url.getProtocol().equalsIgnoreCase("http")||Link_Url.getProtocol().equalsIgnoreCase("https"))) //To avoid duplicate contents or empty content.
								{
							    	//System.out.printf("\nThis link has NOT the same content of another one i am %d \n",Thread.currentThread().getId() );
									  String txtName ;
									  synchronized(this) 
									   {	
									     Compacted.add(result);
									     txtName= Integer.toString(Current_size)+".txt";
									   }
									  //convert the document to .TXT file
									  
									  FileWriter FW = null;
									try {
										FW = new FileWriter(txtName);
									} catch (IOException e) {
										//System.out.println("File writer error");
										e.printStackTrace();
									} //textfile's name is the index of this URL in the set of URLs.
									  writer=new BufferedWriter(FW);
									  synchronized(this) 
									   {								
									     Current_size++;
									     System.out.println("i am thread "+Thread.currentThread().getId() + " just incremented to "+Current_size);
									   }
									   System.out.println(Current_size);
									   try {
										writer.write(doc1.toString());
									} catch (IOException e) {
										//System.out.println("Fwrite.tostring error");
										e.printStackTrace();
									}
									   
				     				   //check the Robot
				     				   boolean Exist=false;
				     				  Vector<String>Robot_txt1=Robot_Handling(fetched_URL); //returns list of disallowed links in robot.txt
				                      if(Robot_txt1!=null)
				                        Exist=Search(Robot_txt1,fetched_URL); //this URL is disallowed -> Exist = 1;
				                    //check for the following :
				           			//1-URL is not in the robots.txt and that it is a HTML file (!Exist).
				           			//2-URL has type not null (type!=null).
				           			//3-URL type is HTML.
				                      if(!Exist)
				                      {
				                    	  synchronized(this) 
										   {
				                    	  URL_Set.add(fetched_URL);
				                    	  File f = new File("URLs.txt");
				  				        try {
				  				            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
				  				            bw.append(fetched_URL+"\n");
				  				            bw.close();
				  				        } catch (IOException e) {
				  				           // System.out.println(e.getMessage());
				  				        }
				                    	  //myWriter.write(fetched_URL);
										   }
				                    	  
				                    	//<a is the tag and hre_f the attribute_ that have the link 
				                          org.jsoup.select.Elements links = doc1.select("a[href]"); 
				                          for(Element Link :links) //Loop for all links included in the content of this URL to fetch them later.
				                          {
				                              String NewLink = Link.attr("abs:href");
				                              
				                              crawl(NewLink);
				                              
				                          }
				                    	  
				                    	  
				                      
				                      }
				                      else
				                      {
				                    	 // System.out.println("the type is not html or the url is forbbiden \n" );
				                    	  return;
				                      }  
								}
							else 
								{
								  

								//System.out.printf("\nThis link has the same content of another one i am or error in protocol %d \n",Thread.currentThread().getId() );
								  return;   // do not add it
							    
					  		  }
					         	 
        		
    		}
    		else
    		{
    			return ;
    		}
        	
        	
        }
        	else
            	return;
        }
        else
         return;
    }
        System.out.println("loooooooooooooooooooooop");
        
}
}
        
    
    
   
=======
   

    
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

>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c

