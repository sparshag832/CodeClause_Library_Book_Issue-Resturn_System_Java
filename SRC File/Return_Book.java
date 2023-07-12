import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.conf.LongPropertyDefinition;
import com.mysql.cj.exceptions.RSAException;

public class Return_Book extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtISsID;
	private JTextField txtDate;
	Date retond;
	java.sql.Date retd;
	int f;

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
String query="select Issue_ID,Issue_Date,Return_Date,Return_On,Fine from Issue_Return";
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

	public Return_Book() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 685, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Return Book");
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
		
		JLabel lblIssID = new JLabel("Enter Issue ID");
		lblIssID.setBounds(142, 41, 85, 14);
		contentPane.add(lblIssID);
		
		txtISsID = new JTextField();
		txtISsID.setBounds(143, 66, 126, 20);
		contentPane.add(txtISsID);
		txtISsID.setColumns(10);
		String id=txtISsID.getText();
		
		JLabel lblDate = new JLabel("Enter Present Date");
		lblDate.setBounds(142, 104, 136, 14);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(142, 129, 126, 20);
		contentPane.add(txtDate);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
					String query="select Return_Date from Issue_Return where Issue_Id = ? ";
					PreparedStatement statement=connection.prepareStatement(query);
					statement.setString(1,txtISsID.getText());
					 ResultSet rs=statement.executeQuery();
					while(rs.next())
					{
						 retd=rs.getDate(1);
					}
					
					String current_Date=txtDate.getText();
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				      java.util. Date retond = format.parse(current_Date);
					java.sql.Date sqlDate1=new java.sql.Date(retond.getTime());
					
					long diff = sqlDate1.getTime() - retd.getTime();
                   
                    int days=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                     
					f=days*10;
					    String queryString="update Issue_Return set Return_On=? , Fine=? where Issue_ID=? ";
						PreparedStatement statement1=connection.prepareStatement(queryString);
						statement1.setDate(1, sqlDate1);
						statement1.setInt(2,f);
						statement1.setString(3, txtISsID.getText());
	
						int r=statement1.executeUpdate();
						connection.close();
						if(r>0)
						{
						JOptionPane.showMessageDialog(null, "Book Returned");
						txtISsID.setText(null);
						txtDate.setText(null);
				disp();
						}
					}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
	
				
			}
		});
		btnReturnBook.setBackground(new Color(0, 255, 0));
		btnReturnBook.setForeground(new Color(255, 255, 224));
		btnReturnBook.setBounds(386, 100, 108, 23);
		contentPane.add(btnReturnBook);
		disp();
	}

}	