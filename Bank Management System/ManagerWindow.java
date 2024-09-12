import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class ManagerWindow extends JFrame{
	JTable t=new JTable();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JLabel l=new JLabel("Welcome Shubham",JLabel.CENTER);
	JButton c=new JButton("Create Account");
	JButton d=new JButton("Delete Account");
	JButton u=new JButton("Update Account");
	JButton s=new JButton("Show All Details");

	Connection cnn;
	Statement sm;
	PreparedStatement ps;


	ManagerWindow(){
		setSize(500,600);
		setTitle("Manager Account");
		p1.setLayout(new GridLayout(4,0));
		p1.add(c);
		p1.add(u);
		p1.add(d);
		p1.add(s);

		add(p1);

		p2.add(l);

		add(p2,"North");
		p2.setBackground(Color.BLUE);

		l.setFont(new Font("Times New Roman",Font.BOLD,25));

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver register");
			cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system","root","");
			System.out.println("Connected");
		}catch(Exception e){
			System.out.println(e);
		}

		c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Random r=new Random();
				//int acc=r.nextDouble()*10000;
				//int accc=Math.random()*10000;
				int acc=Integer.parseInt(JOptionPane.showInputDialog(ManagerWindow.this,"Enter Account No","Create Account"));
				String name=JOptionPane.showInputDialog(ManagerWindow.this,"Enter Name","Create Account");
				double balance=Double.parseDouble(JOptionPane.showInputDialog(ManagerWindow.this,"Enter Balance","Create Account"));
				String branch=JOptionPane.showInputDialog(ManagerWindow.this,"Enter Brach","Create Account");
				try{
				ps=cnn.prepareStatement("insert into customer_details values(?,?,?,?)");
				ps.setInt(1,acc);
				ps.setString(2,name);
				ps.setDouble(3,balance);
				ps.setString(4,branch);

				int data=ps.executeUpdate();
				System.out.println(data);
				JOptionPane.showMessageDialog(ManagerWindow.this,"Data Inserted");

			}catch(Exception e1){
				System.out.println(e1);
				JOptionPane.showMessageDialog(ManagerWindow.this,"Something Wrong");
			}



				//String q="insert into customer_details values("+acc+","+name+","+balance+","+branch+";";

			}
		});

		d.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						int acc=Integer.parseInt(JOptionPane.showInputDialog(ManagerWindow.this,"Enter Account No","Delete Account"));
						String q="delete from customer_details where "+"Account_NO="+acc;
						try{
							sm=cnn.createStatement();
							int data=sm.executeUpdate(q);
							JOptionPane.showMessageDialog(ManagerWindow.this,"Account Deleted");
						}catch(Exception e1){
							System.out.println(e1);
							JOptionPane.showMessageDialog(ManagerWindow.this,"Something Wrong");
						}
					}
		});

		u.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						UpdateUserData u=new UpdateUserData();
						ManagerWindow.this.setVisible(false);
						u.setVisible(true);
					}
		});

		s.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JFrame showData=new JFrame();
						showData.setTitle("All Data");
						showData.setSize(600,600);
						JScrollPane sp=new JScrollPane(t);
						showData.add(sp);
						try{
							String q="select * from customer_details";
							sm=cnn.createStatement();
							ResultSet st=sm.executeQuery(q);
							t.setModel(DbUtils.resultSetToTableModel(st));
					    }catch(Exception e3){
							System.out.println(e3);
						}
						showData.setVisible(true);
					}
		});

	}
}
