import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class UpdateUserData extends JFrame{
	JLabel userLabel=new JLabel("Account No:");
	JLabel pass =new JLabel("Choice:");
	JTextField userText =new JTextField(20);
	JComboBox b;
	JButton sub=new JButton("Submit");
	Connection cnn;
	Statement sm;

	UpdateUserData(){
		String []s={"Name","Branch"};
		b=new JComboBox(s);

		setTitle("Update Data");
		setSize(400,200);
		setLayout(null);

		Font ul=new Font("Arial",Font.BOLD,14);
		Font pl=new Font("Arial",Font.PLAIN,14);
		Font bl=new Font("Arial",Font.BOLD,14);

		userLabel.setBounds(20,30,100,25);
		userLabel.setFont(ul);
		add(userLabel);

		pass.setBounds(20,70,100,25);
		pass.setFont(ul);
		add(pass);

		userText.setBounds(120,30,200,25);
		userText.setFont(pl);
		add(userText);

		b.setBounds(120,70,200,25);
		b.setFont(pl);
		add(b);

		sub.setBounds(120,110,90,30);
		sub.setFont(bl);
		add(sub);

		try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Driver register");
					cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system","root","");
					System.out.println("Connected");
				}catch(Exception e){
					System.out.println(e);
		}

		sub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int ac=Integer.parseInt(userText.getText());
				String c=(String)b.getSelectedItem();
				String data=JOptionPane.showInputDialog(UpdateUserData.this,"Enter "+c,"Upadate Data");
				String q="Update customer_details set "+c+"='"+data+"' where Account_NO="+ac;
				System.out.println(q);
				try{
						sm=cnn.createStatement();
						int af=sm.executeUpdate(q);

						JOptionPane.showMessageDialog(UpdateUserData.this,"Data Updated");
										}catch(Exception e1){
											System.out.println(e1);
											JOptionPane.showMessageDialog(UpdateUserData.this,"Something Wrong");
						}

			}
		});

	}
}
