import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CustomerLogin extends JFrame{
	JLabel userLabel=new JLabel("Account No.:");
	JLabel pass =new JLabel("Password:");
	JTextField userText =new JTextField(20);
	JTextField passText=new JTextField(20);
	JButton sub=new JButton("Submit");

	CustomerLogin(){
		setTitle("Customer Login");
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

		passText.setBounds(120,70,200,25);
		passText.setFont(pl);
		add(passText);

		sub.setBounds(120,110,90,30);
		sub.setFont(bl);
		add(sub);

	}
}
