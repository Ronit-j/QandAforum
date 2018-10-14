/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdl4;
//select a.question_,b.answer , c.username from questions a ,answers b, user c  where a.question_id=b.question_id &b.id=c.id;
//remember this query for life 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ronit
 */
class Conn
{
    // initialize socket and input output streams
    Socket socket            = null;
    BufferedReader br=null;
    PrintWriter out=null;
    BufferedReader in=null;
    OutputStreamWriter os =null;
    //ObjectInputStream obj_in = null;
    // constructor to put ip address and port
    Conn(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
           
            
                this.os= new OutputStreamWriter(socket.getOutputStream());
                this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.in = new BufferedReader(new InputStreamReader(System.in));
    		 out= new PrintWriter(os);         
                 System.out.println("Connected2");
           
           
         System.out.println("hello world bro");
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }	      	        
    }
    
    void close(){
    	try
        {
            br.close();
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }    
}


class LoginUi{
	
	static Conn client=new Conn("localhost",9001);
    final JFrame f=new JFrame("Login Window");   
    JTextField u,r;
    JPasswordField p;
    JLabel l1,l2; 
	JLabel l3; 
	
	public LoginUi(){
		  
	 
	}	
	void init()
        {
            l1=new JLabel("Username ");
	l1.setBounds(48, 87, 100, 20);
    l2=new JLabel("Password");
    l2.setBounds(48, 137, 100, 20);
    l3=new JLabel("");
    l3.setBounds(100, 237, 250, 20);
    u=new JTextField();
    u.setBounds(148, 87, 150, 20);
    p = new JPasswordField(); 
    p.setBounds(148, 137, 150, 20);;
    JLabel Result=new JLabel("Result");
    Result.setBounds(48,337, 150, 20);
    
    
    r=new JTextField("");
    r.setBounds(148,337,150,20);
    f.getContentPane().setLayout(null);
    System.out.println("waduhek");
    
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(0, 0, 5, 7);
    f.getContentPane().add(tabbedPane);
    f.getContentPane().add(l1);
    f.getContentPane().add(u);
    f.getContentPane().add(l2);
    f.getContentPane().add(p);
    f.getContentPane().add(r);
    f.getContentPane().add(Result);
    JButton b=new JButton("Login");
    JButton signup=new JButton("signup");
    f.getContentPane().add(signup);
    signup.setBounds(173, 220, 95, 30);
    signup.addActionListener(new ActionListener()
    {
           
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        
                        signup();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginUi.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
    });
    
    b.setBounds(173, 187, 95, 30);
    b.addActionListener(new ActionListener(){  
            
    	public void actionPerformed(ActionEvent e){  
                try {
                    login();
                    b.setEnabled(true);
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                }  
    	    });  
    f.getContentPane().add(b);
    f.getContentPane().add(l3);  
    f.setSize(400,400);
    f.setVisible(true); 
        }
        void signup() throws IOException
        {
            client.out.println(4);
            client.out.flush();
            client.out.println(u.getText());
            client.out.flush();
            client.out.println(p.getText());
            client.out.flush();
            r.setText("Sign up Successful");
            
            
            
        }
	void login() throws IOException{
            client.out.println(3);
            client.out.flush();
            client.out.println(u.getText());
            client.out.flush();
            client.out.println(p.getText());
            client.out.flush();
            String kaku=null;
            kaku=client.br.readLine();
            if(kaku.contentEquals("1"))
            {
                r.setText("login Success");
              
                f.setVisible(false);
                f.dispose();
                replace();
            }
            else
            {
                r.setText("Login failed sign up first");
            }
            
        }

    private void replace() throws IOException {
     
    final JFrame f=new JFrame("Main Frame");
    
    f.setLayout(null);   
    f.setSize(1500, 1500);
    JButton ask=new JButton("ask");
    ask.setBounds(48,10,100,50);
    JButton answer=new JButton("answer");
    answer.setBounds(148,10,100,50);
    JLabel Question=new JLabel("Question");
    JTextField qq=new JTextField("");
    qq.setBounds(100, 65,395,35);
    Question.setBounds(10,60,90,50);
    JLabel Answer=new JLabel("Answer");
    Answer.setBounds(10,110,90,30);
    JTextField aa=new JTextField("write answer here");
    aa.setBounds(100, 110, 395, 30);
    JLabel Type=new JLabel("Type");
    Type.setBounds(10,160,90,30);
    JTextField tag=new JTextField("write type here");
    tag.setBounds(100,160,100,30);
    JTextField questionid =new JTextField("write question id here");
    questionid.setBounds(100,210,100,50);
    JLabel QuestionId=new JLabel("QuestionID");
    QuestionId.setBounds(10,210,90,50);
    f.getContentPane().add(ask);
    f.getContentPane().add(answer);
    f.getContentPane().add(Answer);
    f.getContentPane().add(Question);
    f.getContentPane().add(Type);
    f.getContentPane().add(QuestionId);
    f.getContentPane().add(qq);
    f.getContentPane().add(aa);
    f.getContentPane().add(questionid);
    f.getContentPane().add(tag);
    
    answer.addActionListener(new ActionListener(){  
            
    	public void actionPerformed(ActionEvent e){  
                try {
                    f.dispose();
                    answerquestion(aa.getText(),Integer.parseInt(questionid.getText()));
                    replace();
                    //does something
                    answer.setEnabled(true);
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                }  
    	    });  
    
    ask.addActionListener(new ActionListener(){  
            
    	public void actionPerformed(ActionEvent e){  
            
                try {
                    f.dispose();
                    askquestion(qq.getText(),tag.getText());
                    replace();
                    
                    //does some function
                    ask.setEnabled(true);
                } catch (IOException ex) {
                    Logger.getLogger(LoginUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                }  
    	    });  
    
  JPanel panel = new JPanel();
   client.out.println(5);
   client.out.flush();
   int size=Integer.parseInt(client.br.readLine());
   System.out.println(size);
   String data[][] = new String[100][100];
   String s=null;
   for(int i=0;i<size;i++)
   {
       s=client.br.readLine();
       System.out.println(s);
       String wadu[]=s.split(",");
       data[i]=wadu;
       
       System.out.println(data[i][0]);
       System.out.println(data[i][1]);
       System.out.println(data[i][2]);
       
   }
   
  String col[] = {"QuestionID","Question","Type","answer"};
  
  JTable table = new JTable(data,col);
  JTableHeader header = table.getTableHeader();
  header.setBackground(Color.blue);
  JScrollPane pane = new JScrollPane(table);
  pane.setBounds(0, 0, 1000, 1000);
  table.setBounds(0,0, 1000, 1000);
  table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
  panel.add(pane);
  panel.setLayout(null);
  panel.setBounds(500, 50, 1000, 1000);
  f.add(panel);
   
 
    f.setVisible(true);
    
    
    
    
    
    }
    void askquestion(String question,String type)
    {
        client.out.println(2);
        client.out.flush();
        client.out.println(question);
        client.out.flush();
        client.out.println(type);
        client.out.flush();
        
        
        
    }
    void answerquestion(String Answer,int questionid)
    {
        
        client.out.println(1);
        client.out.flush();
        client.out.println(Answer);
        client.out.flush();
        client.out.println(questionid);
        client.out.flush();
        
    }

}

public class Sdl4 {

    public static void main(String[] args) {
        
        
        LoginUi ui =new LoginUi();
	ui.init();
		   
        
    }
    
}
