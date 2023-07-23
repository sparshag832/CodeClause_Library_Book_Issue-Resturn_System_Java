import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class ViewBook extends JInternalFrame {

	private JPanel contentPane;
	JTable table;



	/**
	 * Create the frame.
	 */
	
	void disp(String n)
	{
		
		try
		{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
	String query="select * from books where Name like '"+n+"%'";
	PreparedStatement statement=connection.prepareStatement(query);
	ResultSet result=statement.executeQuery();
	java.sql.ResultSetMetaData rsmd=   result.getMetaData();
	DefaultTableModel model=new DefaultTableModel();
	table.setModel(model);
	table.setRowHeight(80);
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

		void disp()
		{
			try
			{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
	String query="select * from books";
	PreparedStatement statement=connection.prepareStatement(query);
	ResultSet result=statement.executeQuery();

	java.sql.ResultSetMetaData rsmd=result.getMetaData();
	DefaultTableModel model=new DefaultTableModel();
	table.setModel(model);
	// table.setRowHeight(10);
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

	
	public ViewBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 683, 468);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("View Books");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(37, 110, 600, 300);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		panel.add(table, BorderLayout.CENTER);
		
		JScrollPane jScrollPane=new JScrollPane(table);
		panel.add(jScrollPane);
		
		JLabel lblNewLabel = new JLabel("Search By Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(31, 81, 103, 14);
		contentPane.add(lblNewLabel);
		
		JTextField textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				disp(textField.getText());
			}
		});
		textField.setBounds(146, 79, 226, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("DELETE BOOK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRowCount()==1)
				{
					
					int row=table.getSelectedRow();
					String taskString=(table.getValueAt(row, 0).toString());
					// System.out.println(taskString);
					 try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
							String queryString="delete from books where ID = ?";
							PreparedStatement statement=connection.prepareStatement(queryString);
							statement.setString(1,taskString);
							int i=statement.executeUpdate();
							//System.out.println(i);
							connection.close();
							if(i>0)
							{
							JOptionPane.showMessageDialog(null, "Book Deleted");
						    textField.setText(null);
							}
							disp();
						}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select Any Book");
				}
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 224));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(444, 76, 135, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setBounds(10, 11, 647, 34);
		contentPane.add(panel_1);
		
		JLabel lblSearchDelete = new JLabel("Search & Delete Book");
		lblSearchDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchDelete.setForeground(new Color(255, 228, 225));
		lblSearchDelete.setFont(new Font("Calisto MT", Font.BOLD, 14));
		lblSearchDelete.setBounds(110, 11, 400, 14);
		panel_1.add(lblSearchDelete);
		
		disp();

	}

}
