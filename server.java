/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdl4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronit
 */
class clientHandler implements Runnable
{
        int clientid;
        private Socket myClientSocket;
        boolean m_bRunThread = true; 
        public clientHandler() { 
           super(); 
        } 
  		
        clientHandler(Socket s) { 
           myClientSocket = s; 
           clientid=0;
        }  

    @Override
    public void run() {
           PrintStream out=null;
            BufferedReader  br = null;
            try {
                br = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                 BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
            try {
                out= new PrintStream(myClientSocket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                String s1 = "";
		String s2 = "";
		String b = "";
                String tag="";
		int choice = 0,i,c;
		double d;
                Database db=new Database();
                Integer qid = null;
                String password = null;
               String username = null;
                   
          
                do
                {    
              try {
                  System.out.println("Please enter the choice:");
                    String waku=null;
                    waku=br.readLine();
                   
                    System.out.println(waku);
                   choice = Integer.parseInt(waku);
                   System.out.println("hwlllaldslasldlasdaldlasdaldallads");
               } catch (IOException ex) {
                   Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
               }
                 String u = null;
            String p = null;
              
                switch(choice)
                {
                    case 1:
               {
                   try {
                       s2=br.readLine();
                   } catch (IOException ex) {
                       Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               {
                   try {
                       qid=Integer.parseInt(br.readLine());
                   } catch (IOException ex) {
                       Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               {
                   try {
                       db.answerq(s2,qid);
                   } catch (SQLException ex) {
                       Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
                        break;
                    case 2:
               {
                   try {
                       s1=br.readLine();
                   } catch (IOException ex) {
                       Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               {
                   try {
                       b=br.readLine();
                   } catch (IOException ex) {
                       Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               {
                   try {
                       db.askq(s1, b);
                   } catch (SQLException ex) {
                       Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
                        break;
                    case 3:
                           
     
                  try {
                      u=br.readLine();
                  } catch (IOException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
              
                  try {
                      p=br.readLine();
                  } catch (IOException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
                            int k = 0;
              
                  try {
                      k=db.login(u,p);
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
                            out.println(k);
                            out.flush();
                            if(k==1)
                            {
                  try {
                      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/trial","root","root");
                      PreparedStatement st;
                      st = conn.prepareStatement("select id from user where username=?");
                      st.setString(1, u);
                      ResultSet rs=st.executeQuery();
                      rs.next();
                      clientid=rs.getInt(1);
                      System.out.println("successful login");
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
                            }else
                                System.out.println("failed");
                          break;
                    case 4:
                        
              try {
                  u = br.readLine();
              } catch (IOException ex) {
                  Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
              
                  try {
                      p=br.readLine();
                  } catch (IOException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
            Connection conn = null;
              try {
                  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trial","root","root");
              } catch (SQLException ex) {
                  Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
          
            Statement st;
              {
                  try {
                      st = conn.createStatement();
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
            PreparedStatement ps = null;
              {
                  try {
                      ps = conn.prepareStatement("insert into user values(?,?)");
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              {
                  try {
                      ps.setString(1,u);
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              {
                  try {
                      ps.setString(2,p);
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              {
                  try {
                      ps.executeUpdate();
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              {
                  try {
                      conn.close();
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
                
                    case 5:
              {
                  try {
                      Vector<String> Data=new Vector<String>();
                      Data=db.all();
                      System.out.println(Data.size());
                      out.println(Data.size());
                      out.flush();
                      for (String Data1 : Data) {
                          out.println(Data1);
                          out.flush();
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(clientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
                        
                        
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("waduuuuuuuuuu");
                        
                }
                }while(choice!=6);
        
    }
}
class Database
{
    Database()
    {
          try {
            // TODO code application logic here
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
           System.out.println("hella you failed");
        }
    }
    int login(String username,String password) throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/trial","root","root");
            PreparedStatement st;
            st = conn.prepareStatement("select username , password from user where username=?");
            st.setString(1, username);
            ResultSet rs=st.executeQuery();
            rs.next();
            if(rs.getString(2).equalsIgnoreCase(password))
            {
                System.out.print("login successful");
                return 1;
            }
            else
                    System.out.print("login failed");
            
            
            conn.close();
    
        
      return 0;
        
            
    }
    void askq(String questions,String tag) throws SQLException
    {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/trial","root","root");
          
            Statement st;
            st = conn.createStatement();
            ResultSet rs2=st.executeQuery("select count(*) from questions ");
            rs2.next();
            int kk=rs2.getInt(1);
            PreparedStatement ps;
            ps = conn.prepareStatement("insert into questions values(?,?,?)");
            ps.setString(1,questions);
            ps.setInt(2,kk+1);
            ps.setString(3,tag);
            ps.executeUpdate();
            conn.close();

    }
    void answerq(String answer,Integer qid ) throws SQLException
    {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/trial","root","root");
            PreparedStatement ps;
            ps = conn.prepareStatement("select question_,question_id from questions where type=? or type='general'");
            System.out.println("Enter the answer to the question :");
            
               
              
            ps = conn.prepareStatement("insert into answers(answer,question_id,id) values(?,?,?)");
            ps.setString(1, answer);
            ps.setInt(2, qid);
            ps.setInt(3,3);
            ps.execute();
            conn.close();
        
        
    }

   Vector<String> all() throws SQLException
   {
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/trial","root","root");
          
            Statement st;
            st = conn.createStatement();
            ResultSet rs2=st.executeQuery("select count(*) from questions b,answers a where a.question_id=b.question_id ");
            rs2.next();
            int kk=rs2.getInt(1);
            Vector<String >v=new Vector<String>();
            rs2=st.executeQuery("select questions.question_id,question_,type ,answer from questions  left join answers   on questions.question_id=answers.question_id ;");
            int i=0;
            
            while(rs2.next())
            {
                
                String r1=rs2.getString(1);
                String r2=rs2.getString(2);
                String r3=rs2.getString(3);
                String r4=rs2.getString(4);
                if(r1.length()==0)
                {
                    r1="null";
                }
                if(r2.length()==0)
                r2="null";
                if(r3.length()==0)
                    r3="null";
                   if(r4==null||r4.length()==0)
                       r4="null";
                String s="";
                
                
               
                
    
              s+=r1;
              s+=",";
              s+=r2;
              s+=",";
              s+=r3;
              s+=",";
              s+=r4;
              
             System.out.println(s);
              v.add(s);
             
             
                i++;
            }
           
        
       return v;
   }
}
public class server
{
    //initialize socket and input stream
    ServerSocket    server   = null;
    //DataInputStream str_in       =  null;
    //DataOutputStream str_out       =  null;
    //ObjectOutputStream obj_out = null;
    //ObjectInputStream obj_in = null;
    boolean ServerOn = true;
   BufferedReader br=null;
    //private ExecutorService executorService = Executors.newFixedThreadPool(10);        
    BufferedReader in=null;
    PrintStream out=null;
    // constructor 
    server() throws IOException
    {    
    	server = new ServerSocket(9001); 
                    
    	System.out.println("Server started");
    	 while(true) 
    	 {
    		 Socket socket = null;
        // starts server and waits for a connection
    		 try{
    			 System.out.println("Waiting for a client ...");
 
    			 socket = server.accept();
          	  
                         System.out.println("Client accepted");
            
                 //executorService.submit(new clientHandler(socket));
                 
    			 clientHandler cliThread = new clientHandler(socket);
    			 Thread t = new Thread(cliThread);
    			 t.start(); 
    		 }catch(IOException i){
    	    		server.close();
    	    		System.out.println(i);
    		 }
    	}
    }
    void close()
    {
    	try{
    		server.close();
    	}
    	catch(IOException i)
        {
            System.out.println(i);
        }
    	
    }
 
    public static void main(String args[]) throws IOException
    {        
		new server();
    }
}
