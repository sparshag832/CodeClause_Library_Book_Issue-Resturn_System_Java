import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class HomePage extends JFrame {

	private JPanel contentPane;
	static String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		 JDesktopPane desktopPane = new JDesktopPane();
		 desktopPane.setBackground(new Color(255, 192, 203));
			contentPane.add(desktopPane, BorderLayout.CENTER);
			
			JButton btnViewBooks = new JButton("View Books");
			btnViewBooks.setForeground(new Color(255, 255, 224));
			btnViewBooks.setBackground(new Color(220, 20, 60));
			btnViewBooks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewBook frame = new ViewBook();
					desktopPane.add(frame);
					frame.setVisible(true);
				}
			});
			btnViewBooks.setBounds(469, 188, 116, 23);
			desktopPane.add(btnViewBooks);
			
			JButton btnIssueBook = new JButton("Issue Book");
			btnIssueBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Issue_Book frame = new Issue_Book();
					desktopPane.add(frame);
					frame.setVisible(true);
				}
			});
			btnIssueBook.setForeground(new Color(255, 255, 240));
			btnIssueBook.setBackground(new Color(220, 20, 60));
			btnIssueBook.setBounds(134, 352, 129, 23);
			desktopPane.add(btnIssueBook);
			
			JButton btnReturnBook = new JButton("Return Book");
			btnReturnBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Return_Book frame = new Return_Book();
					desktopPane.add(frame);
					frame.setVisible(true);
				}
			});
			btnReturnBook.setForeground(new Color(255, 255, 224));
			btnReturnBook.setBackground(new Color(220, 20, 60));
			btnReturnBook.setBounds(469, 352, 116, 23);
			desktopPane.add(btnReturnBook);
			
			JLabel lblAvailableServices = new JLabel("Available Services");
			lblAvailableServices.setBackground(new Color(255, 0, 0));
			lblAvailableServices.setHorizontalAlignment(SwingConstants.CENTER);
			lblAvailableServices.setForeground(new Color(255, 222, 173));
			lblAvailableServices.setFont(new Font("Rockwell", Font.PLAIN, 20));
			lblAvailableServices.setBounds(206, 49, 312, 25);
			desktopPane.add(lblAvailableServices);
			
			JButton btnNewButton = new JButton("Add Book");
			btnNewButton.setForeground(new Color(255, 255, 224));
			btnNewButton.setBackground(new Color(220, 20, 60));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					AddBook ab=new AddBook();
					desktopPane.add(ab);
					ab.setVisible(true);
				}
			});
			btnNewButton.setBounds(134, 188, 129, 23);
			desktopPane.add(btnNewButton);
			
			JButton btnUpdateBook = new JButton("Update Book");
			btnUpdateBook.setForeground(new Color(255, 255, 224));
			btnUpdateBook.setBackground(new Color(220, 20, 60));
			btnUpdateBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					id=	JOptionPane.showInputDialog(null, "Enter Criminal ID");
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=root");
						String queString="select *  from books where ID=?";
						PreparedStatement statement=cn.prepareStatement(queString);
						statement.setString(1, id);
						ResultSet resultset=statement.executeQuery();
						if(resultset.next())
						{
							Update_Book obj=new Update_Book();
							desktopPane.add(obj);
							obj.setVisible(true);
							
						}
						else 
							JOptionPane.showMessageDialog(null, "Invalid ID Entered");
						}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
				}
			});
			btnUpdateBook.setBounds(301, 266, 116, 23);
			desktopPane.add(btnUpdateBook);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(220, 20, 60));
			panel.setBounds(10, 40, 704, 34);
			desktopPane.add(panel);
			panel.setLayout(null);


			
			
	}

}
