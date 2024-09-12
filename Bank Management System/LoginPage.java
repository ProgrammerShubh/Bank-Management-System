import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class LoginPage extends JFrame{
	JLabel l=new JLabel("Login Window",JLabel.CENTER);
	JPanel c=new JPanel();
	JPanel n=new JPanel();
	JButton customer=new JButton(new ImageIcon("img/customer.png"));
	JButton manager=new JButton(new ImageIcon("img/manager.jpg"));
	Connection cnn;
	Statement sm;
	LoginPage(){
		setSize(500,600);
		setTitle("Login Page");
		l.setFont(new Font("Times New Roman",Font.BOLD,25));
		customer.setFont(new Font("Times New Roman",Font.BOLD,20));
		manager.setFont(new Font("Times New Roman",Font.BOLD,20));
		n.add(l);
		add(n,"North");
		c.setLayout(new GridLayout(2,0));
		c.add(customer);
		c.add(manager);
		add(c);
		n.setBackground(Color.BLUE);

		manager.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ManagerLogin ml=new ManagerLogin();
				LoginPage.this.setVisible(false);
				ml.setVisible(true);
			}
		});

		try{
				Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Driver register");
					cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system","root","");
					System.out.println("Connected");
				}catch(Exception e){
					System.out.println(e);
		}

		customer.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						/*CustomerLogin c=new CustomerLogin();
						LoginPage.this.setVisible(false);
						c.setVisible(true);*/
						int ac=Integer.parseInt(JOptionPane.showInputDialog(LoginPage.this,"Enter Account Number","Account No"));
						try{
							sm=cnn.createStatement();
							String q="select name from customer_details where Account_NO="+ac;
							ResultSet b=sm.executeQuery(q);
							if(b.next()){
								CustomerWindow c=new CustomerWindow(ac);
								c.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(LoginPage.this,"Login Failed");
							}
						}catch(Exception e5){
							System.out.println(e5);
						}

					}
		});

	}
	public static void main(String[] args){
		LoginPage f=new LoginPage();
		f.setVisible(true);
	}
}