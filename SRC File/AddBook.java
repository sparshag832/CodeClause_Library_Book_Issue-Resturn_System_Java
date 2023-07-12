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

public class AddBook extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtBN;
	private JTextField txtPN;
	private JTextField txtBG;
	private JTextField txtBP;
	private JTextField txtBID;

	
	
	
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 700, 449);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle("Add Books");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(10, 26, 709, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Book Details");
		lblNewLabel_1.setForeground(new Color(255, 255, 240));
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(274, 11, 143, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblBN = new JLabel("Enter Book Name :");
		lblBN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBN.setHorizontalAlignment(SwingConstants.CENTER);
		lblBN.setBounds(64, 204, 121, 14);
		contentPane.add(lblBN);
		
		txtBN = new JTextField();
		txtBN.setBounds(75, 237, 222, 20);
		contentPane.add(txtBN);
		txtBN.setColumns(10);
		
		JLabel lblPN = new JLabel("Enter Publisher Name :");
		lblPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPN.setHorizontalAlignment(SwingConstants.CENTER);
		lblPN.setBounds(78, 287, 132, 14);
		contentPane.add(lblPN);
		
		txtPN = new JTextField();
		txtPN.setColumns(10);
		txtPN.setBounds(78, 312, 222, 20);
		contentPane.add(txtPN);
		
		JLabel lblBG = new JLabel("Enter Book Genre :");
		lblBG.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBG.setHorizontalAlignment(SwingConstants.CENTER);
		lblBG.setBounds(415, 204, 121, 14);
		contentPane.add(lblBG);
		
		txtBG = new JTextField();
		txtBG.setColumns(10);
		txtBG.setBounds(415, 237, 222, 20);
		contentPane.add(txtBG);
		
		JLabel lblBP = new JLabel("Enter Book Price :");
		lblBP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBP.setHorizontalAlignment(SwingConstants.CENTER);
		lblBP.setBounds(402, 287, 121, 14);
		contentPane.add(lblBP);
		
		txtBP = new JTextField();
		txtBP.setColumns(10);
		txtBP.setBounds(415, 320, 222, 20);
		contentPane.add(txtBP);
		
		JButton btnSV = new JButton("SAVE");
		btnSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
					    String queryString="insert into books values(?,?,?,?,?)";
						PreparedStatement statement1=connection.prepareStatement(queryString);
						statement1.setString(1, txtBID.getText());
						statement1.setString(2, txtBN.getText());
						statement1.setString(3,txtBG.getText());
						statement1.setString(4,txtPN.getText());
						statement1.setInt(5,(Integer.parseInt(txtBP.getText())));
						int r=statement1.executeUpdate();
						connection.close();
						if(r>0)
						{
						JOptionPane.showMessageDialog(null, "Data Saved");
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
				}
			}
		});
		btnSV.setForeground(new Color(255, 255, 224));
		btnSV.setBackground(new Color(0, 255, 0));
		btnSV.setBounds(312, 372, 89, 23);
		contentPane.add(btnSV);
		
		JLabel lblBID = new JLabel("Enter Book ID :");
		lblBID.setHorizontalAlignment(SwingConstants.CENTER);
		lblBID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBID.setBounds(279, 109, 105, 14);
		contentPane.add(lblBID);
		
		txtBID = new JTextField();
		txtBID.setColumns(10);
		txtBID.setBounds(233, 142, 222, 20);
		contentPane.add(txtBID);
	}
}
