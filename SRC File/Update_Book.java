import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Update_Book extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtBN;
	private JTextField txtPN;
	private JTextField txtBG;
	private JTextField txtBP;
	private JTextField txtBID;

	public  Update_Book() 
	 {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 700, 449);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle("Update Books");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(10, 26, 664, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Update Book Details");
		lblNewLabel_1.setForeground(new Color(255, 255, 240));
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(274, 11, 143, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lbBN = new JLabel("Enter Book Name :");
		lbBN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbBN.setHorizontalAlignment(SwingConstants.CENTER);
		lbBN.setBounds(64, 188, 121, 14);
		contentPane.add(lbBN);
		
		txtBN = new JTextField();
		txtBN.setBounds(64, 213, 222, 20);
		contentPane.add(txtBN);
		txtBN.setColumns(10);
		
		JLabel lblPN = new JLabel("Enter Publisher Name :");
		lblPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPN.setHorizontalAlignment(SwingConstants.CENTER);
		lblPN.setBounds(53, 283, 158, 14);
		contentPane.add(lblPN);
		
		txtPN = new JTextField();
		txtPN.setColumns(10);
		txtPN.setBounds(67, 308, 222, 20);
		contentPane.add(txtPN);
		
		JLabel lblBG = new JLabel("Enter Book Genre :");
		lblBG.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBG.setHorizontalAlignment(SwingConstants.CENTER);
		lblBG.setBounds(404, 180, 121, 14);
		contentPane.add(lblBG);
		
		txtBG = new JTextField();
		txtBG.setColumns(10);
		txtBG.setBounds(404, 213, 222, 20);
		contentPane.add(txtBG);
		
		JLabel lblBP = new JLabel("Enter Book Price :");
		lblBP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBP.setHorizontalAlignment(SwingConstants.CENTER);
		lblBP.setBounds(391, 283, 121, 14);
		contentPane.add(lblBP);
		
		txtBP = new JTextField();
		txtBP.setColumns(10);
		txtBP.setBounds(404, 308, 222, 20);
		contentPane.add(txtBP);
		
		JButton btnUPDT = new JButton("UPDATE");
		btnUPDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=	HomePage.id;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
					    String queryString="update books set Name=?,Genre=?,Publisher=?,Price=? where ID = ?";
						PreparedStatement statement1=connection.prepareStatement(queryString);
						statement1.setString(1, txtBN.getText());
						statement1.setString(2,txtBG.getText());
						statement1.setString(3,txtPN.getText());
						statement1.setInt(4,(Integer.parseInt(txtBP.getText())));
						statement1.setString(5,id);
						int r=statement1.executeUpdate();
						connection.close();
						if(r>0)
						{
						JOptionPane.showMessageDialog(null, "Data Updated");
						txtBN.setText(null);
						txtBG.setText(null);
						txtPN.setText(null);
						txtBP.setText(null);
						txtBID.setText(null);
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}//Inner Catch Closed
				}
			});
		
		btnUPDT.setForeground(new Color(255, 255, 224));
		btnUPDT.setBackground(new Color(0, 255, 0));
		btnUPDT.setBounds(311, 371, 89, 23);
		contentPane.add(btnUPDT);
		
		
		
		JLabel lblBookId = new JLabel("Book ID :"+HomePage.id);
		lblBookId.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookId.setBounds(271, 110, 105, 14);
		contentPane.add(lblBookId);
	}
}
