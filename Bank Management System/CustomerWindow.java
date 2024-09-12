import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class CustomerWindow extends JFrame{
	JTable t=new JTable();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JLabel l=new JLabel("Welcome ",JLabel.CENTER);
	JButton c=new JButton("View Information");
	JButton d=new JButton("Check Balance");
	JButton u=new JButton("Diposite");
	JButton s=new JButton("Withdraw");

	Connection cnn;
	Statement sm;
	PreparedStatement ps;


	CustomerWindow(int ta){
		setSize(500,600);
		setTitle("Cutomer Account");
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
			String qu="select Name from customer_details where Account_NO="+ta;
			sm=cnn.createStatement();
			ResultSet st=sm.executeQuery(qu);
			st.next();
			String name=st.getString("Name");
			l.setText("Welcome "+name);

		}catch(Exception e){
			System.out.println(e);
		}

		c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					JFrame showData=new JFrame();
					showData.setTitle("All Data");
					showData.setSize(600,600);
					JScrollPane sp=new JScrollPane(t);
					showData.add(sp);
					try{
						String q="select * from customer_details where Account_NO="+ta;
						sm=cnn.createStatement();
						ResultSet st=sm.executeQuery(q);
						t.setModel(DbUtils.resultSetToTableModel(st));
						}catch(Exception e3){
							System.out.println(e3);
						}
					showData.setVisible(true);
					}
		});

		d.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							String q="select balance from customer_details where Account_NO="+ta;
							sm=cnn.createStatement();
							ResultSet st=sm.executeQuery(q);
							st.next();
							JOptionPane.showMessageDialog(CustomerWindow.this,st.getString("Balance"));
						}catch(Exception e3){
							System.out.println(e3);
							JOptionPane.showMessageDialog(CustomerWindow.this,"Something wrong");
						}
					}
		});

		u.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						double bal=Double.parseDouble(JOptionPane.showInputDialog(CustomerWindow.this,"Enter Balance","Add Balance"));
						String q1="select balance from customer_details where Account_NO="+ta;
						try{
							sm=cnn.createStatement();
							ResultSet st=sm.executeQuery(q1);
							st.next();
							double td=st.getDouble("Balance");
							bal=td+bal;
							String q2="Update customer_details set Balance"+"="+bal+" where Account_NO="+ta;
							int ch=sm.executeUpdate(q2);
							if(ch>0){
								JOptionPane.showMessageDialog(CustomerWindow.this,"Dipostite");
							}else{
								JOptionPane.showMessageDialog(CustomerWindow.this,"Something wrong");
							}
						}catch(Exception e6){
							System.out.println(e6);
							JOptionPane.showMessageDialog(CustomerWindow.this,"Something wrong");
						}

					}
		});

		s.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							double bal=Double.parseDouble(JOptionPane.showInputDialog(CustomerWindow.this,"Enter Balance","Add Balance"));
							String q1="select balance from customer_details where Account_NO="+ta;
							sm=cnn.createStatement();
							ResultSet st=sm.executeQuery(q1);
							st.next();
							double current_bal=st.getDouble("Balance");
							if(current_bal>=bal){
								bal=current_bal-bal;
								String q2="Update customer_details set Balance"+"="+bal+" where Account_NO="+ta;
								int ch=sm.executeUpdate(q2);
								if(ch>0){
									JOptionPane.showMessageDialog(CustomerWindow.this,"Withdraw !");
								}else{
								JOptionPane.showMessageDialog(CustomerWindow.this,"Something wrong");
							}
							}else{
								JOptionPane.showMessageDialog(CustomerWindow.this,"Low Balance");
							}
						}catch(Exception e7){
							JOptionPane.showMessageDialog(CustomerWindow.this,"Something wrong");
							System.out.println(e7);
						}
					}
		});

	}

}
