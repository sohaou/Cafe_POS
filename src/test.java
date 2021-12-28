import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		getContentPane().setLayout(null);
		CardLayout cards = new CardLayout();
		JFrame mi=new JFrame();
		mi.setResizable(false);
		mi.setTitle("ȸ�����");
		mi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mi.getContentPane().setLayout(cards);
		mi.setResizable(false);
		mi.setBounds(100, 100, 483, 357);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(396, 139, 419, 242);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 4), null));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mi.setContentPane(contentPane);
		contentPane.setLayout(null);
		mi.setVisible(true);
	}
}
