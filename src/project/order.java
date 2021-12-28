package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class order extends user {
	order() { //주문내역확인 
		JFrame or_f=new JFrame();
		or_f.setTitle("이전주문내역");
		or_f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		or_f.setBounds(150, 150, 584, 453);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(200, 200, 350, 250);
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 5), null));
		or_f.setContentPane(contentPane);
		contentPane.setLayout(null);
		or_f.setVisible(true);
		
		JButton btnexit = new JButton("종 료");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				or_f.dispose();
			}
		});
		btnexit.setBounds(459, 10, 91, 23);
		contentPane.add(btnexit);
		
		ordertable = new JTable(modell);
		ordertable.setFillsViewportHeight(true);
		ordertable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane=new JScrollPane(ordertable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(ordertable);
		scrollPane.setBounds(20, 43, 530, 251);
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc=ordertable.getColumnModel();
		for(int i=0;i<tc.getColumnCount();i++) {
			tc.getColumn(i).setCellRenderer(dtc);
		}
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("* 회원번호 : ");
		lblNewLabel.setFont(new Font("HY나무L", Font.BOLD, 12));
		lblNewLabel.setBounds(22, 333, 77, 30);
		contentPane.add(lblNewLabel);
		
		JButton memo=new JButton("[ 상세내역 ]");
		memo.setFont(new Font("HY나무L", Font.BOLD, 12));
		memo.setBounds(32, 304, 100, 30);
		contentPane.add(memo);
		
		JLabel lblmnum = new JLabel();
		lblmnum.setFont(new Font("HY나무L", Font.PLAIN, 12));
		lblmnum.setBounds(101, 333, 70, 30);
		contentPane.add(lblmnum);
		
		JLabel lblNewLabel_2 = new JLabel("* 날짜 : ");
		lblNewLabel_2.setFont(new Font("HY나무L", Font.BOLD, 12));
		lblNewLabel_2.setBounds(180, 333, 49, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lbldate = new JLabel();
		lbldate.setFont(new Font("HY나무L", Font.PLAIN, 12));
		lbldate.setBounds(230, 333, 140, 30);
		contentPane.add(lbldate);
		
		JLabel lblNewLabel_2_1 = new JLabel("* 메뉴 : ");
		lblNewLabel_2_1.setBounds(22, 373, 70, 30);
		lblNewLabel_2_1.setFont(new Font("HY나무L", Font.BOLD, 12));
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblmenu = new JLabel();
		lblmenu.setBounds(80, 373, 303, 34);
		lblmenu.setFont(new Font("HY나무L", Font.PLAIN, 12));
		contentPane.add(lblmenu);
		
		JLabel lblmoney = new JLabel("* 금액 : ");
		lblmoney.setBounds(420, 333, 50, 30);
		lblmoney.setFont(new Font("HY나무L", Font.BOLD, 12));
		contentPane.add(lblmoney);
		
		JLabel lblmnum_1_1_1 = new JLabel();
		lblmnum_1_1_1.setFont(new Font("HY나무L", Font.PLAIN, 12));
		lblmnum_1_1_1.setBounds(477, 333, 70, 30);
		contentPane.add(lblmnum_1_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("* 결제방법 : ");
		lblNewLabel_2_1_2.setFont(new Font("HY나무L", Font.BOLD, 12));
		lblNewLabel_2_1_2.setBounds(404, 373, 77, 30);
		contentPane.add(lblNewLabel_2_1_2);
		
		JLabel lblway = new JLabel();
		lblway.setFont(new Font("HY나무L", Font.PLAIN, 12));
		lblway.setBounds(483, 373, 77, 30);
		contentPane.add(lblway);
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","0000");
			System.out.println("sales 테이블 연결 완료");
			Statement stmt=conn.createStatement();
			String sql="SELECT * FROM sales";
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String m_id=rs.getString(1);
				String date=rs.getString(2);
				String menu=rs.getString(3);
				int s=rs.getInt(4);
				String money=Integer.toString(s);
				String way=rs.getString(5);
				
				Object data[]= {m_id,date,menu,money,way};
				modell.addRow(data);
			}
			conn.close();
		}catch(ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버가 존재하지 않습니다"+e1);
			JOptionPane.showMessageDialog(null, "업로드실패");
		} catch(java.sql.SQLException e1) {
			System.out.println("DB SQL문 오류:"+e1);
			JOptionPane.showMessageDialog(null, "업로드실패");
		}catch(Exception e1) {
			e1.printStackTrace(); 
		}
		memo.addActionListener(new ActionListener() { //상세내역
			public void actionPerformed(ActionEvent e) {
				if(ordertable.getSelectedRow()!=-1) {
					int nRow=ordertable.getSelectedRow();
					int nColumn=ordertable.getSelectedColumn();
					Object mnum=(Object)ordertable.getValueAt(nRow, nColumn);
					lblmnum.setText((String) mnum);
					Object date=(Object)ordertable.getValueAt(nRow, nColumn+1);
					lbldate.setText((String) date);
					Object menu=(Object)ordertable.getValueAt(nRow, nColumn+2);
					lblmenu.setText((String) menu);
					Object price=(Object)ordertable.getValueAt(nRow, nColumn+3);
					lblmnum_1_1_1.setText((String) price);
					Object way=(Object)ordertable.getValueAt(nRow, nColumn+4);
					lblway.setText((String) way);
				}
			}
		});
	}
}
