package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class start extends JFrame implements ActionListener{
	@SuppressWarnings("unused")
	private JFrame mainFrame;
	private JPanel contentPane;
	private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
    JFrame lf=new JFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start frame = new start();
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
	start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("카페 pos기 프로그램");
		
		JButton use = new JButton("\uC0AC\uC6A9\uC790");
		use.setBounds(63, 58, 99, 69);
		contentPane.add(use);
		
		JButton ma = new JButton("\uAD00\uB9AC\uC790");
		ma.setBounds(225, 58, 99, 69);
		contentPane.add(ma);
		
		use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user u2=new user();
			}
		});
		ma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}
	public void login() {
		// TODO Auto-generated method stub
		lf.setTitle("관리자 로그인");
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lf.setBounds(280, 150, 397, 217);
		lf.setVisible(false);
		
		JPanel lp=new JPanel();
		lf.add(lp);
		lf.setVisible(true);
		
		lp.setLayout(null);
		
		JLabel userLabel = new JLabel("User");
	    userLabel.setBounds(10, 10, 80, 25);
	    lp.add(userLabel);
	    
	    JLabel passLabel = new JLabel("Pass");
        passLabel.setBounds(10, 40, 80, 25);
        lp.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        lp.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        lp.add(passText);
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
        btnInit = new JButton("Reset");
        btnInit.setBounds(10, 80, 100, 25);
        lp.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new JButton("Login");
        btnLogin.setBounds(160, 80, 100, 25);
        lp.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
	}
        public void isLoginCheck(){
            if(userText.getText().equals("admin") && new String(passText.getPassword()).equals("1234")){
                JOptionPane.showMessageDialog(null, "Success");
                bLoginCheck = true;
               
                // 로그인 성공이라면 매니져창 뛰우기
                if(isLogin()){
                	lf.dispose();
                	manager m2=new manager();
                }                  
            }else{
                JOptionPane.showMessageDialog(null, "Faild");
            }
        }
       
        public boolean isLogin() {     
            return bLoginCheck;
        }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

