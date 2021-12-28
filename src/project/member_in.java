package project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class member_in extends user {
	member_in() {
		CardLayout cards = new CardLayout();
		JFrame mi=new JFrame();
		mi.setResizable(false);
		mi.setTitle("회원등록");
		mi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mi.getContentPane().setLayout(cards);
		mi.setResizable(false);
		mi.setBounds(400, 200, 470, 345);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(100, 100, 370, 270);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 5), null));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mi.setContentPane(contentPane);
		contentPane.setLayout(null);
		mi.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uBC88\uD638");
		lblNewLabel.setFont(new Font("HY나무L", Font.PLAIN, 20));
		lblNewLabel.setBounds(37, 64, 84, 32);
		contentPane.add(lblNewLabel);
		
		JTextField m_id = new JTextField();
		m_id.setText("\uD734\uB300\uC804\uD654 \uB4B7\uBC88\uD638 4\uC790\uB9AC");
		m_id.setBounds(150, 64, 175, 32);
		contentPane.add(m_id);
		m_id.setColumns(10);
		m_id.setText("");
		
		JLabel lbl = new JLabel("(휴대전화 뒷번호 4자리)");
		lbl.setFont(new Font("HY나무L", Font.PLAIN, 12));
		lbl.setBounds(150, 100, 175, 32);
		contentPane.add(lbl);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("HY나무L", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(37, 157, 84, 32);
		contentPane.add(lblNewLabel_2);
		
		JTextField p_num = new JTextField();
		p_num.setColumns(10);
		p_num.setBounds(150, 157, 175, 32);
		contentPane.add(p_num);
		p_num.setText("");
		
		JButton member_cancel = new JButton("\uC785\uB825\uCDE8\uC18C");
		member_cancel.setFont(new Font("HY나무L", Font.BOLD, 14));
		member_cancel.setBounds(94, 242, 92, 32);
		contentPane.add(member_cancel);
		member_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(m_id.getText());
				m_id.setText("");
				p_num.setText("");
			}
		});
		
		JButton btnexit = new JButton("\uC885\uB8CC");
		btnexit.setFont(new Font("HY나무L", Font.PLAIN, 12));
		btnexit.setBounds(346, 10, 77, 23);
		contentPane.add(btnexit);
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mi.dispose();
			}
		});
		
		JButton member_finish = new JButton("\uB4F1\uB85D");
		member_finish.setFont(new Font("HY나무L", Font.BOLD, 14));
		member_finish.setBounds(266, 242, 92, 32);
		contentPane.add(member_finish);
		
		member_finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid=m_id.getText();
				String pnum=p_num.getText();
				Connection con=null;
				PreparedStatement ps=null;
				if(mid.equals("")) {
					JOptionPane.showMessageDialog(null, "회원번호를 입력해주세요!");
				}
				else if(pnum.equals("")) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요!");
				}
				else if(mid.equals("")==false&&pnum.equals("")==false) {	
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
						System.out.println("DB 연결 완료");
						Statement stmt=conn.createStatement();
						int n=0;
						String sql="INSERT INTO member (m_id, p_num,stamp) VALUES('"+mid+"','"+pnum+"','"+n+"')";
						stmt.execute(sql);
						System.out.println("등록완료");
						JOptionPane.showMessageDialog(null, "등록완료");
						mi.dispose();
						conn.close();
					}catch(ClassNotFoundException e1) {
						System.out.println("JDBC 드라이버가 존재하지 않습니다"+e1);
						JOptionPane.showMessageDialog(null, "등록실패");
					} catch(java.sql.SQLException e1) {
						System.out.println("DB SQL문 오류:"+e1);
						JOptionPane.showMessageDialog(null, "이미 등록된 회원입니다.");
						mi.dispose();
					}catch(Exception e1) {
						e1.printStackTrace(); 
					}
				}
			}
			
		});	
	}
}
