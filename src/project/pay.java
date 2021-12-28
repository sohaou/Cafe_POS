package project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class pay extends user {
	public void pay(){ //결제함수
		CardLayout cards = new CardLayout();
		JFrame p=new JFrame(); //새로운 프레임 생성
		p.setResizable(false);
		p.setTitle("결제창");
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.getContentPane().setLayout(cards);
		p.setBounds(400, 170, 500, 395);
		JPanel contentPane = new JPanel(); //패널생성
		contentPane.setBackground(Color.WHITE); //패널의 배경색을 white로 설정
		contentPane.setBounds(110, 110, 350, 250); //패널의 위치 및 크기 설정
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 5), null));
		p.setContentPane(contentPane);//프레임에 패널 추가
		contentPane.setLayout(null);
		p.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("\uACB0\uC81C\uBC29\uC2DD");//결제방식 라벨
		lblNewLabel.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblNewLabel.setBounds(41, 45, 75, 27);
		contentPane.add(lblNewLabel);
		
		JRadioButton card_radio = new JRadioButton("\uCE74\uB4DC");//카드결제 라디오 버튼
		card_radio.setFont(new Font("HY나무L", Font.PLAIN, 13));
		card_radio.setBounds(164, 46, 65, 23);
		card_radio.setBackground(Color.WHITE);
		contentPane.add(card_radio);
		
		JRadioButton cash_radio = new JRadioButton("\uD604\uAE08"); //현금결제 라디오 버튼
		cash_radio.setFont(new Font("HY나무L", Font.PLAIN, 13));
		cash_radio.setBounds(346, 46, 65, 23);
		cash_radio.setBackground(Color.WHITE);
		contentPane.add(cash_radio);
		
		ButtonGroup way=new ButtonGroup();//현금,카드 라디오 버튼 그룹화
		ButtonGroup take=new ButtonGroup();//매장,포장 라디오 버튼 그룹화
		
		way.add(card_radio); 
		way.add(cash_radio);
		
		JLabel lblTakeout = new JLabel("TakeOut"); //포장유무 라벨
		lblTakeout.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblTakeout.setBounds(41, 103, 75, 27);
		contentPane.add(lblTakeout);
		
		JRadioButton btnin = new JRadioButton("\uB9E4\uC7A5"); //매장 라디오 버튼
		btnin.setFont(new Font("HY나무L", Font.PLAIN, 13));
		btnin.setBounds(164, 106, 65, 23);
		btnin.setBackground(Color.WHITE);
		contentPane.add(btnin);
		
		JRadioButton btntake = new JRadioButton("\uD3EC\uC7A5"); //포장 라디오 버튼
		btntake.setFont(new Font("HY나무L", Font.PLAIN, 13));
		btntake.setBounds(346, 106, 65, 23);
		btntake.setBackground(Color.WHITE);
		contentPane.add(btntake);
		
		take.add(btnin);
		take.add(btntake);
		
		JLabel lblm_1 = new JLabel("\uD68C\uC6D0\uBC88\uD638"); //회원번호  라벨
		lblm_1.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblm_1.setBounds(41, 167, 75, 27);
		contentPane.add(lblm_1);
		
		JLabel lblmoney = new JLabel();//결제금액 표시 라벨
		lblmoney.setFont(new Font("HY나무L", Font.BOLD, 20));
		lblmoney.setBounds(167, 235, 223, 27);
		String y=Integer.toString(total_m); //총 금액을 String형으로 변환
		lblmoney.setText(y); //총 금액을 라벨에 표시
		contentPane.add(lblmoney);
		
		JTextField member_num = new JTextField(); //회원번호를 입력하는 텍스트필드
		member_num.setBounds(164, 163, 172, 35);
		contentPane.add(member_num);
		member_num.setColumns(10);
		member_num.setText("");//텍스트필드 값을 ""로 설정해놓음
		
		JButton btnsearch = new JButton("\uC870\uD68C"); //회원번호 조회버튼
		btnsearch.setFont(new Font("HY나무L", Font.PLAIN, 14));
		btnsearch.setBounds(350, 163, 75, 35);
		contentPane.add(btnsearch);
		btnsearch.addActionListener(new ActionListener() { //회원조회 버튼이 클릭되면
			public void actionPerformed(ActionEvent e) {
				Connection con=null; //db연결
				PreparedStatement ps=null;
				String m_num=member_num.getText(); //텍스트필드에 입력된 값을 변수에 저장
				String n = null;
				String y = null;
				if(m_num=="") { //회원번호가 입력되어 있지 않으면
					JOptionPane.showMessageDialog(null, "회원번호를 입력해주세요.");//해당 메세지 출력
				}
				else {//회원번호가 입력되어 있으면
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
					System.out.println("DB 연결 완료");
					Statement stmt=conn.createStatement();
					
					String sql="SELECT * FROM member WHERE m_id='"+m_num+"'"; //member테이블에서 입력한 회원번호의 모든 정보를 불러옴
					ResultSet rs=stmt.executeQuery(sql);

					while(rs.next()) {
						n=rs.getString(1); //회원번호 저장
						y=rs.getString(2); //전화번호 저장
						int s=rs.getInt(3); //stamp개수 저장
					}
					if(m_num.equals(n)) { //텍스트필드에 입력한 회원번호와 member 테이블에서 가져온 회원번호가 일치하면
						JOptionPane.showMessageDialog(null, "가입된 회원입니다."); //해당 메시지를 출력하고
						m=m_num;//해당 회원번호를 m에 저장
					}
					else { //만약 회원번호가 존재하지 않으면
						JFrame pm=new JFrame();//새로운 프레임 생성
						pm.setTitle("회원등록_");
						pm.setResizable(false);
						pm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						pm.getContentPane().setLayout(cards);
						pm.setBounds(100, 100, 390, 225);
						JPanel contentPane = new JPanel();
						contentPane.setBounds(100, 100, 370, 200);
						contentPane.setBackground(Color.WHITE);
						contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 5), null));
						pm.setContentPane(contentPane);
						contentPane.setLayout(null);
						pm.setVisible(true);
						
						JLabel lblNewLabel = new JLabel("\uB4F1\uB85D\uB418\uC9C0 \uC54A\uC740 \uD68C\uC6D0\uC785\uB2C8\uB2E4.");//회원등록이 되어있지 않음을 안내
						lblNewLabel.setFont(new Font("HY나무L", Font.BOLD, 14));
						lblNewLabel.setBounds(105, 48, 200, 35);
						contentPane.add(lblNewLabel);
						
						JButton btnin = new JButton("회원등록"); //회원등록 버튼
						btnin.setFont(new Font("HY나무L", Font.PLAIN, 12));
						btnin.setBounds(42, 135, 91, 30);
						contentPane.add(btnin);
						btnin.addActionListener(new ActionListener() { //회원등록이 눌리면
							public void actionPerformed(ActionEvent e) {
								member_in m3=new member_in();//회원가입하는 함수를 불러오고
								pm.dispose();//해당 프레임은 닫는다.
							}
						});
						JButton btnc = new JButton("닫기");//회원가입을 하지 않고 프레임을 닫는 버튼
						btnc.setFont(new Font("HY나무L", Font.PLAIN, 12));
						btnc.setBounds(218, 135, 91, 30);
						contentPane.add(btnc);
						btnc.addActionListener(new ActionListener() { //등록창닫기
							public void actionPerformed(ActionEvent e) {
								pm.dispose();
							}
						});
					}
					conn.close();
				}catch(ClassNotFoundException e1) {
					System.out.println("JDBC 드라이버가 존재하지 않습니다"+e1);
					JOptionPane.showMessageDialog(null, "조회실패");
				} catch(java.sql.SQLException e1) {
					System.out.println("DB SQL문 오류:"+e1);
					JOptionPane.showMessageDialog(null, "조회실패");
				}catch(Exception e1) {
					e1.printStackTrace(); 
				}
			}
			}
		});
		
		JLabel lblmoney_1 = new JLabel("\uACB0\uC81C\uAE08\uC561"); //결제금액라벨
		lblmoney_1.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblmoney_1.setBounds(41, 235, 75, 27);
		contentPane.add(lblmoney_1);
		
		JButton btncancel = new JButton("\uCDE8\uC18C");//결제취소버튼
		btncancel.setFont(new Font("HY나무L", Font.PLAIN, 14));
		btncancel.setBounds(110, 294, 90, 35);
		contentPane.add(btncancel);
		btncancel.addActionListener(new ActionListener() { //결제취소이 클릭되면
			public void actionPerformed(ActionEvent e) {
				p.dispose();//해당 프레임을 닫는다.
			}
		});
		
		JButton btnpay = new JButton("\uACB0\uC81C");//결제버튼
		btnpay.setFont(new Font("HY나무L", Font.PLAIN, 14));
		btnpay.setBounds(290, 294, 90, 35);
		contentPane.add(btnpay);
		btnpay.addActionListener(new ActionListener() { //결제버튼이 클린되면
			public void actionPerformed(ActionEvent e) {
				int yy=0;
				if(cash_radio.isSelected()==false&&card_radio.isSelected()==false) { //결제방식이 선택되지 않으면
					JOptionPane.showMessageDialog(null, "결제방식을 선택해주세요.");//해당 메시지창 표시
				}
				else if(btnin.isSelected()==false&&btntake.isSelected()==false) { //포장유무가 선택되지 않으면
					JOptionPane.showMessageDialog(null, "TakeOut 여부를 선택해주세요.");//해당 메시지창 표시
				}
				else {//결제방식과 포장유무가 모두 선택되어있으면
				String []da=new String[100];//테이블에 저장된 메뉴명을 배열에 저장
				for(int a=0;a<n;a++) { //선택한 총 음료의 개수 만큼의 반복을 통해
						Object select=menutable.getModel().getValueAt(a,0); //테이블에 저장된 메뉴명을 select에 저장하여
						menu_s=menu_s+select+",";	//menu_s에 select를 추가하여 총 메뉴를 한 배열에 저장한다.
						da[a]=(String) select;//배열에 결제한 메뉴명을 저장
				}
				Connection con=null;
				PreparedStatement ps=null;
				String ways=null;
				if(card_radio.isSelected()) { //카드 라디오 버튼이 클릭되어 있으면
					ways="카드"; //결제방식을 카드로 저장
				}
				else if(cash_radio.isSelected()) {//현금 라디오 버튼이 클릭되어 있으면
					ways="현금"; //결제방식을 현금으로 저장
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
					System.out.println("판매DB 연결 완료");
					Statement stmt=conn.createStatement();
					if(member_num.getText()!=null) {//회원번호가 입력되어 있으면(회원번호가 존재하면)
						String sql="INSERT INTO sales (m_id, date, menu_s, price_s, way) VALUES('"+m+"','"+format_t+"','"+menu_s+"',"+total_m+",'"+ways+"')";
						//판매내역을 저장하는 테이블에 회원번호,날짜,메뉴내역,금액,결제방법을 저장한다.
						stmt.execute(sql);
						String stam="SELECT stamp FROM member WHERE m_id='"+member_num.getText()+"'";//입력한 회원번호의 스탬프 수를 불러온다.
						ResultSet x=stmt.executeQuery(stam);
						while(x.next()) {
							String xx=x.getString(1);
							yy=Integer.parseInt(xx);
						}
						yy=yy+n;//기존 스탬프 수에 음료의 수만큼의 스탬프를 추가한다.
						String s_num="UPDATE member SET stamp="+yy+" where m_id='"+member_num.getText()+"'";
						//member테이블에 입력한 회원번호의 stamp개수를 결제한 총 음료 개수인 n만큼 추가한다.
						stmt.execute(s_num);
					}
					else if(member_num.getText()=="") {//회원번호를 입력하지 않으면
						String sql="INSERT INTO sales (id, date,menu_s,price_s,way) VALUES('x','"+format_t+"','"+menu_s+"',"+total_m+",'"+ways+"')";
						//회원번호는 null로 저장되고 날짜, 메뉴, 금액, 결제방식이 판매내역 테이블에 저장된다.
						stmt.execute(sql);
					}
					model.setNumRows(0);//결제가 완료되면 테이블 내역을 비우고
					total_m=0;//총 금액은 0이된다.
					String y=Integer.toString(total_m);
					totalbl.setText(y);//금액 표시 라벨의 텍스트를 업데이트한 총 금액으로 바꿔준다.
					for(int i=0;i<n;i++) { //결제를 진행한 메뉴의 개수만큼 반복하여
						for(int j=0;j<13;j++) {
							if(da[i].equals(hc_menu[j])) {//결제된 메뉴 중 hotcoffee 메뉴가 있으면
								int x=Integer.parseInt(hc_num[j]); //해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(hc_sales[j]); //해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
							else if(da[i].equals(ic_menu[j])) {//결제된 메뉴 중 icecoffee 메뉴가 있으면
								int x=Integer.parseInt(ic_num[j]);//해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(ic_sales[j]);//해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
							else if(da[i].equals(hb_menu[j])) {//결제된 메뉴 중 hotbeverage 메뉴가 있으면
								int x=Integer.parseInt(hb_num[j]);//해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(hb_sales[j]);//해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
							else if(da[i].equals(ib_menu[j])) {//결제된 메뉴 중 icebeverage 메뉴가 있으면
								int x=Integer.parseInt(ib_num[j]);//해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(ib_sales[j]);//해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
							else if(da[i].equals(tea_menu[j])) {//결제된 메뉴 중 tea 메뉴가 있으면
								int x=Integer.parseInt(tea_num[j]);//해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(tea_sales[j]);//해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
							else if(da[i].equals(sh_menu[j])) {//결제된 메뉴 중 shake 메뉴가 있으면
								int x=Integer.parseInt(sh_num[j]);//해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(sh_sales[j]);//해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
							else if(da[i].equals(add_menu[j])) {//결제된 메뉴 중 add 메뉴가 있으면
								int x=Integer.parseInt(add_num[j]);//해당 메뉴의 재고를 불러오고
								int b=x-1;//재고에서 -1을한다.
								int z=Integer.parseInt(add_sales[j]);//해당 메뉴의 판매량을 불러오고
								int c=z+1;//판매량을 +1을 한다.
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";//해당 메뉴의 재고와 판매량을 업데이트한다.
								stmt.execute(ss);
							}
						}
					
					}
					JOptionPane.showMessageDialog(null, "결제완료"); //결제가 완료되면 해당 메시지를 출력한다.
					p.dispose(); //결제완료 후 프레임을 닫는다.
					conn.close();
				}catch(ClassNotFoundException e1) {
					System.out.println("JDBC 드라이버가 존재하지 않습니다"+e1);
					JOptionPane.showMessageDialog(null, "결제실패");
				} catch(java.sql.SQLException e1) {
					System.out.println("DB SQL문 오류:"+e1);
					JOptionPane.showMessageDialog(null, "결제실패");
				}catch(Exception e1) {
					e1.printStackTrace(); 
				}
			}
			}
		});
	}
}
