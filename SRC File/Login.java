import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private JTextField txtUSRNM;
	private JPasswordField txtPASS;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		getContentPane().setBackground(new Color(244, 164, 96));
		setBackground(new Color(255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 425);
		getContentPane().setLayout(null);
		
		JLabel lblUSRnm = new JLabel("Username:");
		lblUSRnm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUSRnm.setBounds(218, 163, 72, 14);
		getContentPane().add(lblUSRnm);
		
		JLabel lblPASS = new JLabel("Password:");
		lblPASS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPASS.setBounds(386, 163, 72, 14);
		getContentPane().add(lblPASS);
		
		txtUSRNM = new JTextField();
		txtUSRNM.setBounds(200, 199, 129, 20);
		getContentPane().add(txtUSRNM);
		txtUSRNM.setColumns(10);
		
		txtPASS = new JPasswordField();
		txtPASS.setBounds(361, 199, 129, 20);
		getContentPane().add(txtPASS);
		txtPASS.setColumns(10);
		
		JLabel lblRule = new JLabel("Please Enter Login Credentials -");
		lblRule.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRule.setBounds(225, 95, 239, 38);
		getContentPane().add(lblRule);
		
		JButton btnLGN = new JButton("LOGIN");
		btnLGN.setBackground(new Color(0, 255, 0));
		btnLGN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					 String userValue = txtUSRNM.getText();        //get user entered username from the textField1  
				     String passValue =(String) txtPASS.getText();        //get user entered pasword from the textField2  
				      
				        //check whether the credentials are authentic or not  
				        if (userValue.equals("CommanMan") && passValue.equals("cm@12345")) {  //if authentic, navigate user to a new page  
				              
				            //create instance of the NewPage  
				        	HomePage frame = new HomePage();
				        	
							frame.setVisible(true);
				        }
				        else {
				        	JOptionPane.showMessageDialog(null, "Enter Valid Credentials");
				        	txtUSRNM.setText(null);
				        	txtPASS.setText(null);	
						}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				  
			}
		});
		btnLGN.setBounds(307, 302, 89, 23);
		getContentPane().add(btnLGN);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setBackground(new Color(255, 99, 71));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxNewCheckBox.isSelected())
				{
					 txtPASS.setEchoChar((char)0);
				}
				else {
					 txtPASS.setEchoChar('*');
				}
				
			}
		});
		chckbxNewCheckBox.setBounds(361, 247, 129, 23);
		getContentPane().add(chckbxNewCheckBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(10, 26, 682, 38);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblHEAD = new JLabel("Welcome To Apni Library");
		lblHEAD.setBounds(200, 11, 312, 25);
		panel.add(lblHEAD);
		lblHEAD.setForeground(new Color(255, 222, 173));
		lblHEAD.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblHEAD.setHorizontalAlignment(SwingConstants.CENTER);
		

		
	}
}
