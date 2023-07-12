import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.JavaBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;


public class Issue_Book extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtBId;
	private JTextField txtISsueD;
	private JTextField txtRetD;
	private JTextField txtSID;
	private JLabel lblRETID;
	  Date Issue_Date;
	  Date Return_Date;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	void disp()
	{
		try
		{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
String query="select Issue_ID,Book_Id,Student_Id,Issue_Date,Return_Date from Issue_Return";
PreparedStatement statement=connection.prepareStatement(query);
ResultSet result=statement.executeQuery();

java.sql.ResultSetMetaData rsmd=result.getMetaData();
DefaultTableModel model=new DefaultTableModel();
table.setModel(model);
for(int i=1;i<=rsmd.getColumnCount();i++)
{
	model.addColumn(rsmd.getColumnName(i));
}

while(result.next())
{
	String row[]=new String[rsmd.getColumnCount()];
	for(int i=1;i<=rsmd.getColumnCount();i++)
	{
		row[i-1]=result.getString(i);
	}
 	model.addRow(row);
}
connection.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public Issue_Book() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 685, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Issue Book");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 171, 572, 258);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		panel.add(table, BorderLayout.CENTER);
		
		JScrollPane jScrollPane=new JScrollPane(table);
		panel.add(jScrollPane);
		
		JLabel lblBId = new JLabel("Enter Book ID");
		lblBId.setBounds(95, 45, 85, 14);
		contentPane.add(lblBId);
		
		txtBId = new JTextField();
		txtBId.setBounds(96, 70, 126, 20);
		contentPane.add(txtBId);
		txtBId.setColumns(10);
		
		txtISsueD = new JTextField();
		txtISsueD.setBounds(96, 125, 126, 20);
		contentPane.add(txtISsueD);
		txtISsueD.setColumns(10);
		
		
		
		
		JLabel lblIssueD = new JLabel("Enter Issue Date");
		lblIssueD.setBounds(95, 100, 127, 14);
		contentPane.add(lblIssueD);
		
		JLabel lblSID = new JLabel("Enter Student ID");
		lblSID.setBounds(290, 45, 136, 14);
		contentPane.add(lblSID);
		
		txtRetD = new JTextField();
		txtRetD.setBounds(290, 125, 126, 20);
		contentPane.add(txtRetD);
		txtRetD.setColumns(10);
		
		txtSID = new JTextField();
		txtSID.setColumns(10);
		txtSID.setBounds(290, 70, 126, 20);
		contentPane.add(txtSID);
		
		lblRETID = new JLabel("Enter Return Date");
		lblRETID.setBounds(289, 100, 116, 14);
		contentPane.add(lblRETID);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String issueDate=txtISsueD.getText();
				String returnDate=txtRetD.getText();
				try {
				     	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			      java.util. Date    Issue_Date = format.parse(issueDate);
			      java.util. Date	  Return_Date= format.parse(returnDate);
				java.sql.Date sqlDate1=new java.sql.Date(Issue_Date.getTime());
				java.sql.Date sqlDate2=new java.sql.Date(Return_Date.getTime());
				
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
					    String queryString="insert into Issue_Return(Book_Id,Student_Id,Issue_Date,Return_Date) values(?,?,?,?)";
						PreparedStatement statement1=connection.prepareStatement(queryString);
						statement1.setString(1, txtBId.getText());
						statement1.setString(2, txtSID.getText());
						statement1.setDate(3, sqlDate1);
						statement1.setDate(4,sqlDate2);
						
						int r=statement1.executeUpdate();
						connection.close();
						if(r>0)
						{
						JOptionPane.showMessageDialog(null, "Data Saved");
						txtBId.setText(null);
						txtSID.setText(null);
						txtISsueD.setText(null);
						txtRetD.setText(null);
				disp();
						}
					}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		btnIssueBook.setBackground(new Color(0, 255, 0));
		btnIssueBook.setForeground(new Color(255, 255, 224));
		btnIssueBook.setBounds(454, 96, 108, 23);
		contentPane.add(btnIssueBook);
		
		disp();
	}
}
