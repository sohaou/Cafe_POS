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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class user extends JFrame {
	private CardLayout cards = new CardLayout(); //카드레이아웃 이용 - 한 개의 Frame에 여러 개의 Panel을 만들어 활용
	public JTable menutable; //포스기 메인 화면에서 주문 내역을 출력하는 테이블
	public JTable ordertable; //이전 주문내역을 출력하는 테이블
	private JPanel contentPane; 
	public JLabel infoClock; //현재 시간을 표시하는 라벨
	public JLabel totalbl=new JLabel(); //총 금액을 표시하는 라벨
	public JPanel bell_p=new JPanel(); //진동벨 패널
	public JPanel hotc_p=new JPanel(); //hot커피
	public JPanel icec_p=new JPanel(); //ice커피
	public JPanel tea_p=new JPanel(); //티
	public JPanel hotb_p=new JPanel(); //뜨거운음료
	public JPanel iceb_p=new JPanel(); //차가운음료
	public JPanel shake_p=new JPanel(); //쉐이크
	public JPanel add_p=new JPanel(); //추가
	
	public static JFrame user=new JFrame(); //user모드 메인 프레임
	public String[] menu = new String[100]; //menu_table(db)에서 불러온 메뉴명을 저장하는 배열
	public String[] price =new String[100]; //menu_table(db)에서 불러온 메뉴 가격을 저장하는 배열
	public String[] kinds=new String[100]; //menu_table(db)에서 불러온 메뉴 종류를 저장하는 배열
	public String[] num=new String[100]; //menu_table(db)에서 불러온 메뉴 재고 수를 저장하는 배열
	public String[] sales=new String[100]; //menu_table(db)에서 불러온 메뉴별 판매량을 저장하는 배열
	public String[] hc_menu = new String[100]; //hot coffee 메뉴명
	public String[] hc_price =new String[100]; //hot coffee 메뉴별 가격
	public String[] hc_num=new String[100]; //hot coffee 메뉴별 재고수
	public String[] hc_sales=new String[100]; //hot coffee 메뉴별 판매량
	public String[] ic_menu = new String[100]; //ice coffee 메뉴명
	public String[] ic_price =new String[100]; //ice coffee 메뉴별 가격
	public String[] ic_num=new String[100];//ice coffee 메뉴별 재고수
	public String[] ic_sales=new String[100];//ice coffee 메뉴별 판매량
	public String[] tea_menu = new String[100]; //tea 메뉴명
	public String[] tea_price =new String[100]; //tea 메뉴별 가격
	public String[] tea_num=new String[100];//tea 메뉴별 재고수
	public String[] tea_sales=new String[100];//tea 메뉴별 판매량
	public String[] hb_menu = new String[100];//hot beverage 메뉴명
	public String[] hb_price =new String[100]; //hot beverage 메뉴별 가격
	public String[] hb_num=new String[100];//hot beverage 메뉴별 재고수
	public String[] hb_sales=new String[100];//hot beverage 메뉴별 가격
	public String[] ib_menu = new String[100];//ice beverage 메뉴명
	public String[] ib_price =new String[100]; //ice beverage 메뉴별 가격
	public String[] ib_num=new String[100];//ice beverage 메뉴별 재고수
	public String[] ib_sales=new String[100];//ice beverage 메뉴별 판매량
	public String[] sh_menu = new String[100];//shake 메뉴명
	public String[] sh_price =new String[100]; //shake 메뉴별 가격
	public String[] sh_num=new String[100];//shake 메뉴별 재고수
	public String[] sh_sales=new String[100];//shake 메뉴별 판매량
	public String[] add_menu = new String[100];//추가메뉴명
	public String[] add_price =new String[100]; //추가 메뉴별 가격
	public String[] add_num=new String[100];//추가 메뉴별 재고수
	public String[] add_sales=new String[100];//추가 메뉴별 판매량
	public Object select_menu=""; //결제한 메뉴들을 한 문자열로 저장하기 위해
	public String m=null; //조회한 회원번호를 저장하는 변수
	public String format_t=null; //오늘 날짜 불러오기
	public String menu_s=">";//결제한 메뉴들을 한 문자열로 저장하기 위해
	public int a=0;// hotcoffee 메뉴 개수
	public int im=0;//icecoffee 메뉴 개수
	public int tm=0;//tea 메뉴 개수
	public int hbn=0;//hotbeberage 메뉴 개수
	public int ibn=0;//icebeberage 메뉴 개수
	public int shn=0;//shake메뉴 개수
	public int addn=0;//add메뉴 개수
	public int total_m=0;//선택한 메뉴의 총 금액
	public int n=0;
	public int ns=0; //스탬프 사용 후 남은 개수
	public int ss=0; //회원별 스탬프 저장 변수

	public int[] h_ab=new int[20]; //메뉴별 재고의 유무를 확인하고 그에 맞는 동작을 수행하기 위한 배열
	public int[] i_c=new int[20];
	public int[] h_b=new int[20];
	public int[] i_b=new int[20];
	public int[] t_=new int[20];
	public int[] s_h=new int[20];
	public int[] a_d=new int[20];

	String header[]= {"메뉴","수량","가격"}; //menutable 헤더
	DefaultTableModel model = new DefaultTableModel(header,0);
	String head[]= {" 회원번호 ","날짜 "," 메뉴 "," 금액 "," 결제방법 "};//ordertable헤더
	DefaultTableModel modell = new DefaultTableModel(head,0);
	
	public void db_menu() {
		Connection con=null;
		PreparedStatement ps=null;
		
		int i=0;
		int p=0;
		int hc=0;
		int ic=0;
		int t=0;
		int hb=0;
		int ib=0;
		int sh=0;
		int add=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
			System.out.println("menu DB 연결 완료");
			Statement stmt=conn.createStatement();
			
			String sql="select * from menu"; //menu 테이블의 모든 정보 불러오기
			ResultSet rs=stmt.executeQuery(sql);

			while(rs.next()) {
				menu[i]=rs.getString(1); //메뉴명 저장
				p=rs.getInt(2); //메뉴 가격 
				price[i]=Integer.toString(p); //int형인 메뉴 가격을 String형으로 변환하여 배열에 저장 -> 테이블에 출력하기 위해
				kinds[i]=rs.getString(3); //메뉴 종류 저장
				int x=rs.getInt(4); //재고 
				num[i]=Integer.toString(x); //재고를 String형으로 변환하여 배열에 저장
				int y=rs.getInt(5); //판매량
				sales[i]=Integer.toString(y);//판매량을 String형으로 변환하여 배열에 저장
				i++;
			}
			conn.close(); //DB연결해제
			for(int j=0;j<i;j++) { //db에서 읽어온 메뉴의 갯수만큼 반복하여 각 음료 구분
				if(kinds[j].equals("hotcoffee")) { //메뉴의 종류가 HOT COFFEE이면
					hc_menu[hc]=menu[j]; //해당 메뉴를 hotcoffee 메뉴 배열에 저장
					hc_price[hc]=price[j];//해당 메뉴의 가격을 hotcoffee 가격 배열에 저장
					hc_num[hc]=num[j];//해당 메뉴의 재고를 hotcoffee 재고 배열에 저장
					hc_sales[hc]=sales[j];//해당 메뉴의 판매량을 hotcoffee 판매량 배열에 저장
					hc++;
				}
				else if(kinds[j].equals("icecoffee")) {
					ic_menu[ic]=menu[j];
					ic_price[ic]=price[j];
					ic_num[ic]=num[j];
					ic_sales[ic]=sales[j];
					ic++;
				}
				else if(kinds[j].equals("tea")) {
					tea_menu[t]=menu[j];
					tea_price[t]=price[j];
					tea_num[t]=num[j];
					tea_sales[t]=sales[j];
					t++;
				}
				else if(kinds[j].equals("hotbeverage")) {
					hb_menu[hb]=menu[j];
					hb_price[hb]=price[j];
					hb_num[hb]=num[j];
					hb_sales[hb]=sales[j];
					hb++;
				}
				else if(kinds[j].equals("icebeverage")) {
					ib_menu[ib]=menu[j];
					ib_price[ib]=price[j];
					ib_num[ib]=num[j];
					ib_sales[ib]=sales[j];
					ib++;
				}
				else if(kinds[j].equals("shake")) {
					sh_menu[sh]=menu[j];
					sh_price[sh]=price[j];
					sh_num[sh]=num[j];
					sh_sales[sh]=sales[j];
					sh++;
				}
				else if(kinds[j].equals("add")) {
					add_menu[add]=menu[j];
					add_price[add]=price[j];
					add_num[add]=num[j];
					add_sales[add]=sales[j];
					add++;
				}
			}
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
	public void bell() { //진동벨 출력하는 함수
		bell_p.setBounds(396, 139, 419, 242);
		bell_p.setBackground(Color.WHITE);
		bell_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null)); //진동벨 표시하는 패널 설정
		bell_p.setLayout(null);
		String[] rows=new String[3];//테이블에 값을 넣기위한 배열
		
		JButton btnbell_1 = new JButton("진동벨1");
		btnbell_1.setBackground(new Color(207,231,244));
		//btnbell_1.setForeground(Color.WHITE);
		btnbell_1.setBounds(20, 20, 80, 47);
		bell_p.add(btnbell_1);
		btnbell_1.addActionListener(new ActionListener() {  //해당 버튼이 클릭되었을 때
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨1";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows); //테이블에 해당 내용 추가
			}
		});
			
		JButton btnbell_2 = new JButton("진동벨2");
		btnbell_2.setBounds(120, 20, 80, 47); 
		btnbell_2.setBackground(new Color(207,231,244));
		bell_p.add(btnbell_2);
		btnbell_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨2";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});
		JButton btnbell_3 = new JButton("진동벨3");
		btnbell_3.setBounds(220, 20, 80, 47);
		btnbell_3.setBackground(new Color(207,231,244));
		bell_p.add(btnbell_3);
		btnbell_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨3";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});	
		JButton btnbell_4 = new JButton("진동벨4");
		btnbell_4.setBounds(320, 20, 80, 47);
		btnbell_4.setBackground(new Color(207,231,244));
		bell_p.add(btnbell_4);
		btnbell_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨4";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});	
		JButton btnbell_5 = new JButton("진동벨5");
		btnbell_5.setBounds(20, 88, 80, 47);
		btnbell_5.setBackground(new Color(207,231,244));
		bell_p.add(btnbell_5);
		btnbell_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨5";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});	
		JButton btnbell_6 = new JButton("진동벨6");
		btnbell_6.setBounds(120, 88, 80, 47);
		btnbell_6.setBackground(new Color(207,231,244));
		bell_p.add(btnbell_6);
		btnbell_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨6";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});	
		JButton btnbell_7 = new JButton("진동벨7");
		btnbell_7.setBounds(220, 88, 80, 47);
		btnbell_7.setBackground(new Color(207,231,244));
		bell_p.add(btnbell_7);
		btnbell_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨7";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});	
		JButton btnbell_8 = new JButton("진동벨8");
		btnbell_8.setBackground(new Color(207,231,244));
		btnbell_8.setBounds(320, 88, 80, 47);
		bell_p.add(btnbell_8);
		btnbell_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows[0]="진동벨8";
				rows[1]="1";
				rows[2]="0";
				model.addRow(rows);
			}
		});	
	}
	public void hotcoffee() { //hotcoffee 메뉴별 버튼
		hotc_p.setBounds(396, 139, 419, 242);
		hotc_p.setBackground(Color.WHITE);
		hotc_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null)); //hotcoffee의 메뉴별 버튼을 출력하는 패널의 설정
		hotc_p.setLayout(null);
		String[] rows=new String[3]; //테이블에 버튼 클릭 내용을 출력하기 위한 배열
		
		JButton btnhc_1 = new JButton(hc_menu[a]); //hotcoffee 메뉴 배열의 a번째에 저장된 메뉴명을 버튼에 표시
		btnhc_1.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) { //만약 해당 배열에 메뉴는 존재하지만, 그 메뉴의 재고수가 0이면
			btnhc_1.setBackground(new Color(71,61,245)); //버튼의 색 이미지를 바꾼다
			btnhc_1.setForeground(Color.WHITE);
			h_ab[1]=0; //hotcoffee 재고 비교 배열 값을 0으로 저장
		}
		if(hc_menu[a]==null) { //hotcoffee의 메뉴병을 저장한 배열에서 a번째에 메뉴가 존재하지 않으면
			h_ab[1]=0; //hotcoffee 재고 비교 배열 값을 0으로 저장
		}
		else { //메뉴가 존재하고, 재고 또한 있으면
			h_ab[1]=Integer.parseInt(hc_num[a]); //hotcoffee 재고 비교 배열 값을 1로 저장
		}
		btnhc_1.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_1.setBounds(15, 20, 85, 47);
		hotc_p.add(btnhc_1); //hotcoffee 패널에 해당 버튼 추가
		String x=hc_menu[a]; //현재 메뉴명을 String x에 저장
		btnhc_1.addActionListener(new ActionListener() { //해당 버튼이 클릭되면
			public void actionPerformed(ActionEvent e) {
				if(h_ab[1]==0) {  //만약 재고가 없거나 메뉴명이 표시되지 않은 빈버튼을 클릭하면
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가"); //해당 메세지창이 표시된다.
				}
				else { //재고가 있고, 존재하는 메뉴이면
				for(int i=0;i<a;i++) { //hotcoffee 메뉴 갯수만큼의 반복을 통해서
					if(hc_menu[i]==null) {
					}
					else if(hc_menu[i].equals(x)) { //hc_menu[i]번째 메뉴와 현재 버튼의 메뉴가 일치하면
						rows[0]=hc_menu[i];
						rows[1]="1";
						rows[2]=hc_price[i];
						model.addRow(rows); //테이블에 메뉴명,갯수,금액을 출력한다.
						total_m+=Integer.parseInt(hc_price[i]);//총 금액에 해당 메뉴의 금액을 추가한다.
						String y=Integer.toString(total_m);//int형인 총 금액을 String형으로 변환하여
						totalbl.setText(y);//총 금액을 표시하는 라벨을 업데이트한다
						n++;//선택한 총 음료의 개수를 +한다.
					}
				}
				}
			}
		});	
		a++;
		
		JButton btnhc_2 = new JButton(hc_menu[a]);
		btnhc_2.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_2.setBackground(new Color(71,61,245));
			btnhc_2.setForeground(Color.WHITE);
			h_ab[2]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[2]=0;
		}
		else if(hc_menu[a]!=null){
			h_ab[2]=Integer.parseInt(hc_num[a]);
		}
		btnhc_2.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_2.setBounds(115, 20, 85, 47);
		hotc_p.add(btnhc_2);
		String x2=hc_menu[a];
		btnhc_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x2)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
						}
					}
				}
			}
		});	
		a++;
		
		JButton btnhc_3 = new JButton(hc_menu[a]);
		btnhc_3.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_3.setBackground(new Color(71,61,245));
			btnhc_3.setForeground(Color.WHITE);
			h_ab[3]=0;
		}
		else if(hc_menu[a]==null) {
			h_ab[3]=0;
		}
		else if(hc_menu[a]!=null){
			h_ab[3]=Integer.parseInt(hc_num[a]);
		}
		btnhc_3.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_3.setBounds(215, 20, 85, 47);
		hotc_p.add(btnhc_3);
		String x3=hc_menu[a];
		a++;
		btnhc_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else{
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x3)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		
		JButton btnhc_4 = new JButton(hc_menu[a]);
		btnhc_4.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_4.setBackground(new Color(71,61,245));
			btnhc_4.setForeground(Color.WHITE);
			h_ab[4]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[4]=0;
		}
		else {
			h_ab[4]=Integer.parseInt(hc_num[a]);
		}
		btnhc_4.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_4.setBounds(315, 20, 85, 47);
		hotc_p.add(btnhc_4);
		String x4=hc_menu[a];
		a++;
		btnhc_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x4)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	

		
		JButton btnhc_5 = new JButton(hc_menu[a]);
		btnhc_5.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_5.setBackground(new Color(71,61,245));
			btnhc_5.setForeground(Color.WHITE);
			h_ab[5]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[5]=0;
		}
		else {
			h_ab[5]=Integer.parseInt(hc_num[a]);
		}
		btnhc_5.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_5.setBounds(15, 95, 85, 47);
		hotc_p.add(btnhc_5);
		String x5=hc_menu[a];
		a++;
		btnhc_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x5)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		
		JButton btnhc_6 = new JButton(hc_menu[a]);
		btnhc_6.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_6.setBackground(new Color(71,61,245));
			btnhc_6.setForeground(Color.WHITE);
			h_ab[6]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[6]=0;
		}
		else {
			h_ab[6]=Integer.parseInt(hc_num[a]);
		}
		btnhc_6.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_6.setBounds(115, 95, 85, 47);
		hotc_p.add(btnhc_6);
		String x6=hc_menu[a];
		a++;
		btnhc_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x6)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		
		JButton btnhc_7 = new JButton(hc_menu[a]);
		btnhc_7.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_7.setBackground(new Color(71,61,245));
			btnhc_7.setForeground(Color.WHITE);
			h_ab[7]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[7]=0;
		}
		else {
			h_ab[7]=Integer.parseInt(hc_num[a]);
		}
		btnhc_7.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_7.setBounds(215, 95, 85, 47);
		hotc_p.add(btnhc_7);
		String x7=hc_menu[a];
		a++;
		btnhc_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x7)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		JButton btnhc_8 = new JButton(hc_menu[a]);
		btnhc_8.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_8.setBackground(new Color(71,61,245));
			btnhc_8.setForeground(Color.WHITE);
			h_ab[8]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[8]=0;
		}
		else {
			h_ab[8]=Integer.parseInt(hc_num[a]);
		}
		btnhc_8.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_8.setBounds(315, 95, 85, 47);
		hotc_p.add(btnhc_8);
		String x8=hc_menu[a];
		a++;
		btnhc_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x8)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		JButton btnhc_9 = new JButton(hc_menu[a]);
		btnhc_9.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_9.setBackground(new Color(71,61,245));
			btnhc_9.setForeground(Color.WHITE);
			h_ab[9]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[9]=0;
		}
		else {
			h_ab[9]=Integer.parseInt(hc_num[a]);
		}
		btnhc_9.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_9.setBounds(15, 170, 85, 47);
		hotc_p.add(btnhc_9);
		String x9=hc_menu[a];
		a++;
		btnhc_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x9)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		JButton btnhc_10 = new JButton(hc_menu[a]);
		btnhc_10.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_10.setBackground(new Color(71,61,245));
			btnhc_10.setForeground(Color.WHITE);
			h_ab[10]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[10]=0;
		}
		else {
			h_ab[10]=Integer.parseInt(hc_num[a]);
		}
		btnhc_10.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_10.setBounds(115, 170, 85, 47);
		hotc_p.add(btnhc_10);
		String x10=hc_menu[a];
		a++;
		btnhc_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x10)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		JButton btnhc_11 = new JButton(hc_menu[a]);
		btnhc_11.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_11.setBackground(new Color(71,61,245));
			btnhc_11.setForeground(Color.WHITE);
			h_ab[11]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[11]=0;
		}
		else {
			h_ab[11]=Integer.parseInt(hc_num[a]);
		}
		btnhc_11.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_11.setBounds(215, 170, 85, 47);
		hotc_p.add(btnhc_11);
		String x11=hc_menu[a];
		a++;
		btnhc_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x11)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		JButton btnhc_12 = new JButton(hc_menu[a]);
		btnhc_12.setBackground(new Color(207,231,244));
		if(hc_menu[a]!=null&&Integer.parseInt(hc_num[a])==0) {
			btnhc_12.setBackground(new Color(71,61,245));
			btnhc_12.setForeground(Color.WHITE);
			h_ab[12]=0;
		}
		if(hc_menu[a]==null) {
			h_ab[12]=0;
		}
		else {
			h_ab[12]=Integer.parseInt(hc_num[a]);
		}
		btnhc_12.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnhc_12.setBounds(315, 170, 85, 47);
		hotc_p.add(btnhc_12);
		String x12=hc_menu[a];
		a++;
		btnhc_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_ab[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<a;i++) {
						if(hc_menu[i]==null) {
						}
						else if(hc_menu[i].equals(x12)) {
							rows[0]=hc_menu[i];
							rows[1]="1";
							rows[2]=hc_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hc_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
	}

	
	public void icecoffee() { //icecoffee 메뉴별 버튼
		icec_p.setBounds(396, 139, 419, 242);
		icec_p.setBackground(Color.WHITE);
		icec_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null));//icecoffee의 메뉴별 버튼을 출력하는 패널의 설정
		icec_p.setLayout(null);
		String[] rows=new String[3];//테이블에 버튼 클릭 내용을 출력하기 위한 배열
	
		JButton btnic_1 = new JButton(ic_menu[im]); //icecoffee 메뉴명이 저장된 배열에서 im번째 값을 버튼에 표시
		btnic_1.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {//만약 해당 배열에 메뉴는 존재하지만, 그 메뉴의 재고수가 0이면
			btnic_1.setBackground(new Color(71,61,245)); //버튼의 색을 바꿔 재고가 없음을 알 수 있도록 한다.
			btnic_1.setForeground(Color.WHITE);
			i_c[1]=0;//icecoffee 재고 비교 배열 값을 0으로 저장
		}
		if(ic_menu[im]==null) {//icecoffee의 메뉴병을 저장한 배열에서 im번째에 메뉴가 존재하지 않으면
			i_c[1]=0;//icecoffee 재고 비교 배열 값을 0으로 저장
		}
		else { //메뉴가 존재하고, 재고 또한 있으면
			i_c[1]=Integer.parseInt(ic_num[im]);//icecoffee 재고 비교 배열 값을 해당 메뉴의 재고수로 저장
		}
		btnic_1.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_1.setBounds(15, 20, 85, 47);
		icec_p.add(btnic_1); //icecoffee패널에 해당 버튼 추가
		String x=ic_menu[im]; //현재 버튼의 메뉴명을 String x에 저장
		btnic_1.addActionListener(new ActionListener() {//해당 버튼이 클릭되면
			public void actionPerformed(ActionEvent e) {
				if(i_c[1]==0) { //만약 재고가 없거나 메뉴명이 표시되지 않은 빈버튼을 클릭하면
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가"); //해당 메세지창이 표시된다.
				}
				else {//재고가 있고, 존재하는 메뉴이면
					for(int i=0;i<im;i++) {//icecoffee 메뉴 개수만큼의 반복을 통해서
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x)) {//icecoffee[i]번째 메뉴명과 현재 버튼의 메뉴가 일치하면
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);//테이블에 메뉴명, 개수, 금액을 출력한다.
							total_m+=Integer.parseInt(ic_price[i]);//총합 금액에 해당 메뉴의 금액을 추가한다.
							String y=Integer.toString(total_m);//int형인 총 금액을 String형으로 변환하여
							totalbl.setText(y);//총 금액을 표시하는 라벨을 업데이트한다
							n++;//선택한 총 음료의 개수를 +한다.
						}
					}
				}
			}
		});	
		im++;
		
		
		JButton btnic_2 = new JButton(ic_menu[im]);
		btnic_2.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_2.setBackground(new Color(71,61,245));
			btnic_2.setForeground(Color.WHITE);
			i_c[2]=0;
		}
		if(ic_menu[im]==null) {
			i_c[2]=0;
		}
		else {
			i_c[2]=Integer.parseInt(ic_num[im]);
		}
		btnic_2.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_2.setBounds(115, 20, 85, 47);
		icec_p.add(btnic_2);
		String x2=ic_menu[im];
		btnic_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x2)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
			
		JButton btnic_3 = new JButton(ic_menu[im]);
		btnic_3.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_3.setBackground(new Color(71,61,245));
			btnic_3.setForeground(Color.WHITE);
			i_c[3]=0;
		}
		if(ic_menu[im]==null) {
			i_c[3]=0;
		}
		else {
			i_c[3]=Integer.parseInt(ic_num[im]);
		}
		btnic_3.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_3.setBounds(215, 20, 85, 47);
		icec_p.add(btnic_3);
		String x3=ic_menu[im];
		btnic_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x3)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}	
				}
			}
		});	
		im++;
			
		JButton btnic_4 = new JButton(ic_menu[im]);
		btnic_4.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_4.setBackground(new Color(71,61,245));
			btnic_4.setForeground(Color.WHITE);
			i_c[4]=0;
		}
		if(ic_menu[im]==null) {
			i_c[4]=0;
		}
		else {
			i_c[4]=Integer.parseInt(ic_num[im]);
		}
		btnic_4.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_4.setBounds(315, 20, 85, 47);
		icec_p.add(btnic_4);
		String x4=ic_menu[im];
		btnic_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x4)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_5 = new JButton(ic_menu[im]);
		btnic_5.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_5.setBackground(new Color(71,61,245));
			btnic_5.setForeground(Color.WHITE);
			i_c[5]=0;
		}
		if(ic_menu[im]==null) {
			i_c[5]=0;
		}
		else {
			i_c[5]=Integer.parseInt(ic_num[im]);
		}
		btnic_5.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_5.setBounds(15, 95, 85, 47);
		icec_p.add(btnic_5);
		String x5=ic_menu[im];
		btnic_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x5)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_6 = new JButton(ic_menu[im]);
		btnic_6.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_6.setBackground(new Color(71,61,245));
			btnic_6.setForeground(Color.WHITE);
			i_c[6]=0;
		}
		if(ic_menu[im]==null) {
			i_c[6]=0;
		}
		else {
			i_c[6]=Integer.parseInt(ic_num[im]);
		}
		btnic_6.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_6.setBounds(115, 95, 85, 47);
		icec_p.add(btnic_6);
		String x6=ic_menu[im];
		btnic_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x6)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_7 = new JButton(ic_menu[im]);
		btnic_7.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_7.setBackground(new Color(71,61,245));
			btnic_7.setForeground(Color.WHITE);
			i_c[7]=0;
		}
		if(ic_menu[im]==null) {
			i_c[7]=0;
		}
		else {
			i_c[7]=Integer.parseInt(ic_num[im]);
		}
		btnic_7.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_7.setBounds(215, 95, 85, 47);
		icec_p.add(btnic_7);
		String x7=ic_menu[im];
		btnic_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x7)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_8 = new JButton(ic_menu[im]);
		btnic_8.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_8.setBackground(new Color(71,61,245));
			btnic_8.setForeground(Color.WHITE);
			i_c[8]=0;
		}
		if(ic_menu[im]==null) {
			i_c[8]=0;
		}
		else {
			i_c[8]=Integer.parseInt(ic_num[im]);
		}
		btnic_8.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_8.setBounds(315, 95, 85, 47);
		icec_p.add(btnic_8);
		String x8=ic_menu[im];
		btnic_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x8)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_9 = new JButton(ic_menu[im]);
		btnic_9.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_9.setBackground(new Color(71,61,245));
			btnic_9.setForeground(Color.WHITE);
			i_c[9]=0;
		}
		if(ic_menu[im]==null) {
			i_c[9]=0;
		}
		else {
			i_c[9]=Integer.parseInt(ic_num[im]);
		}
		btnic_9.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_9.setBounds(15, 170, 85, 47);
		icec_p.add(btnic_9);
		String x9=ic_menu[im];
		btnic_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x9)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_10 = new JButton(ic_menu[im]);
		btnic_10.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_10.setBackground(new Color(71,61,245));
			btnic_10.setForeground(Color.WHITE);
			i_c[10]=0;
		}
		if(ic_menu[im]==null) {
			i_c[10]=0;
		}
		else {
			i_c[10]=Integer.parseInt(ic_num[im]);
		}
		btnic_10.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_10.setBounds(115, 170, 85, 47);
		icec_p.add(btnic_10);
		String x10=ic_menu[im];
		btnic_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x10)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_11 = new JButton(ic_menu[im]);
		btnic_11.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_11.setBackground(new Color(71,61,245));
			btnic_11.setForeground(Color.WHITE);
			i_c[11]=0;
		}
		if(ic_menu[im]==null) {
			i_c[11]=0;
		}
		else {
			i_c[11]=Integer.parseInt(ic_num[im]);
		}
		btnic_11.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_11.setBounds(215, 170, 85, 47);
		icec_p.add(btnic_11);
		String x11=ic_menu[im];
		btnic_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x11)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
		
		JButton btnic_12 = new JButton(ic_menu[im]);
		btnic_12.setBackground(new Color(207,231,244));
		if(ic_menu[im]!=null&&Integer.parseInt(ic_num[im])==0) {
			btnic_12.setBackground(new Color(71,61,245));
			btnic_12.setForeground(Color.WHITE);
			i_c[12]=0;
		}
		if(ic_menu[im]==null) {
			i_c[12]=0;
		}
		else {
			i_c[12]=Integer.parseInt(ic_num[im]);
		}
		btnic_12.setFont(new Font("HY나무L",Font.PLAIN,9));
		btnic_12.setBounds(315, 170, 85, 47);
		icec_p.add(btnic_12);
		String x12=ic_menu[im];
		btnic_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_c[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<im;i++) {
						if(ic_menu[i]==null) {
						}
						else if(ic_menu[i].equals(x12)) {
							rows[0]=ic_menu[i];
							rows[1]="1";
							rows[2]=ic_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(ic_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		im++;
	}
	
	public void Tea() {//tea 메뉴별 버튼
		tea_p.setBounds(396, 139, 419, 242);
		tea_p.setBackground(Color.WHITE);
		tea_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null));//tea의 메뉴별 버튼을 출력하는 패널의 설정
		tea_p.setLayout(null);
		String[] rows=new String[3];//테이블에 버튼 클릭 내용을 출력하기 위한 배열
	
		JButton Tea_1 = new JButton(tea_menu[tm]);//tea메뉴명이 저장된 배열에서 tm번째 값을 버튼에 표시
		Tea_1.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {//만약 해당 배열에 메뉴는 존재하지만, 그 메뉴의 재고수가 0이면
			Tea_1.setBackground(new Color(71,61,245)); //버튼의 색을 바꿔 재고가 없음을 알 수 있도록 한다.
			Tea_1.setForeground(Color.WHITE);
			t_[1]=0;//tea재고 비교 배열 값을 0으로 저장
		}
		if(tea_menu[tm]==null) {//tea메뉴병을 저장한 배열에서 tm번째에 메뉴가 존재하지 않으면
			t_[1]=0;//tea재고 비교 배열 값을 0으로 저장
		}
		else { //메뉴가 존재하고, 재고 또한 있으면
			t_[1]=Integer.parseInt(tea_num[tm]);//tea재고 비교 배열 값을 해당 메뉴의 재고수로 저장
		}
		Tea_1.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_1.setBounds(15, 20, 85, 47);
		tea_p.add(Tea_1);//tea패널의 해당 버튼 추가
		String x=tea_menu[tm];//현재 버튼의 메뉴명을 String x에 저장
		Tea_1.addActionListener(new ActionListener() {//해당 버튼이 클릭되면
			public void actionPerformed(ActionEvent e) {
					if(t_[1] ==0) {//만약 재고가 없거나 메뉴명이 표시되지 않은 빈버튼을 클릭하면
						JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");//해당 메세지창이 표시된다.
					}
					else {//재고가 있고, 존재하는 메뉴이면
						for(int i=0;i<tm;i++) {//tea메뉴 개수만큼의 반복을 통해서
							if(tea_menu[i]==null) {
							}
							else if(tea_menu[i].equals(x)) {//tea[i]번째 메뉴명과 현재 버튼의 메뉴가 일치하면
								rows[0]=tea_menu[i];
								rows[1]="1";
								rows[2]=tea_price[i];
								model.addRow(rows);//테이블에 메뉴명, 개수, 금액을 출력한다.
								total_m+=Integer.parseInt(tea_price[i]);//총합 금액에 해당 메뉴의 금액을 추가한다.
								String y=Integer.toString(total_m);//int형인 총 금액을 String형으로 변환하여
								totalbl.setText(y);//총 금액을 표시하는 라벨을 업데이트한다
								n++;//선택한 총 음료의 개수를 +한다.
							}
						}
				}
			}
		});	
		tm++;
		
		JButton Tea_2 = new JButton(tea_menu[tm]);
		Tea_2.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_2.setBackground(new Color(71,61,245));
			Tea_2.setForeground(Color.WHITE);
			t_[2]=0;
		}
		if(tea_menu[tm]==null) {
			t_[2]=0;
		}
		else {
			t_[2]=Integer.parseInt(tea_num[tm]);
		}
		Tea_2.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_2.setBounds(115, 20, 85, 47);
		tea_p.add(Tea_2);
		String x2=tea_menu[tm];
		Tea_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x2)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_3 = new JButton(tea_menu[tm]);
		Tea_3.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_3.setBackground(new Color(71,61,245));
			Tea_3.setForeground(Color.WHITE);
			t_[3]=0;
		}
		if(tea_menu[tm]==null) {
			t_[3]=0;
		}
		else {
			t_[3]=Integer.parseInt(tea_num[tm]);
		}
		Tea_3.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_3.setBounds(215, 20, 85, 47);
		tea_p.add(Tea_3);
		String x3=tea_menu[tm];
		Tea_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x3)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_4 = new JButton(tea_menu[tm]);
		Tea_4.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_4.setBackground(new Color(71,61,245));
			Tea_4.setForeground(Color.WHITE);
			t_[4]=0;
		}
		if(tea_menu[tm]==null) {
			t_[4]=0;
		}
		else {
			t_[4]=Integer.parseInt(tea_num[tm]);
		}
		Tea_4.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_4.setBounds(315, 20, 85, 47);
		tea_p.add(Tea_4);
		String x4=tea_menu[tm];
		Tea_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x4)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_5 = new JButton(tea_menu[tm]);
		Tea_5.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_5.setBackground(new Color(71,61,245));
			Tea_5.setForeground(Color.WHITE);
			t_[5]=0;
		}
		if(tea_menu[tm]==null) {
			t_[5]=0;
		}
		else {
			t_[5]=Integer.parseInt(tea_num[tm]);
		}
		Tea_5.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_5.setBounds(15, 95, 85, 47);
		tea_p.add(Tea_5);
		String x5=tea_menu[tm];
		Tea_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x5)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_6 = new JButton(tea_menu[tm]);
		Tea_6.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_6.setBackground(new Color(71,61,245));
			Tea_6.setForeground(Color.WHITE);
			t_[6]=0;
		}
		if(tea_menu[tm]==null) {
			t_[6]=0;
		}
		else {
			t_[6]=Integer.parseInt(tea_num[tm]);
		}
		Tea_6.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_6.setBounds(115, 95, 85, 47);
		tea_p.add(Tea_6);
		String x6=tea_menu[tm];
		Tea_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x6)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_7 = new JButton(tea_menu[tm]);
		Tea_7.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_7.setBackground(new Color(71,61,245));
			Tea_7.setForeground(Color.WHITE);
			t_[7]=0;
		}
		if(tea_menu[tm]==null) {
			t_[7]=0;
		}
		else {
			t_[7]=Integer.parseInt(tea_num[tm]);
		}
		Tea_7.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_7.setBounds(215, 95, 85, 47);
		tea_p.add(Tea_7);
		String x7=tea_menu[tm];
		Tea_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x7)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_8 = new JButton(tea_menu[tm]);
		Tea_8.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_8.setBackground(new Color(71,61,245));
			Tea_8.setForeground(Color.WHITE);
			t_[8]=0;
		}
		if(tea_menu[tm]==null) {
			t_[8]=0;
		}
		else {
			t_[8]=Integer.parseInt(tea_num[tm]);
		}
		Tea_8.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_8.setBounds(315, 95, 85, 47);
		tea_p.add(Tea_8);
		String x8=tea_menu[tm];
		Tea_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x8)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_9 = new JButton(tea_menu[tm]);
		Tea_9.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_9.setBackground(new Color(71,61,245));
			Tea_9.setForeground(Color.WHITE);
			t_[9]=0;
		}
		if(tea_menu[tm]==null) {
			t_[9]=0;
		}
		else {
			t_[9]=Integer.parseInt(tea_num[tm]);
		}
		Tea_9.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_9.setBounds(15, 170, 85, 47);
		tea_p.add(Tea_9);
		String x9=tea_menu[tm];
		Tea_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x9)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_10 = new JButton(tea_menu[tm]);
		Tea_10.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_10.setBackground(new Color(71,61,245));
			Tea_10.setForeground(Color.WHITE);
			t_[10]=0;
		}
		if(tea_menu[tm]==null) {
			t_[10]=0;
		}
		else {
			t_[10]=Integer.parseInt(tea_num[tm]);
		}
		Tea_10.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_10.setBounds(115, 170, 85, 47);
		tea_p.add(Tea_10);
		String x10=tea_menu[tm];
		Tea_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x10)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_11 = new JButton(tea_menu[tm]);
		Tea_11.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_11.setBackground(new Color(71,61,245));
			Tea_11.setForeground(Color.WHITE);
			t_[11]=0;
		}
		if(tea_menu[tm]==null) {
			t_[11]=0;
		}
		else {
			t_[11]=Integer.parseInt(tea_num[tm]);
		}
		Tea_11.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_11.setBounds(215, 170, 85, 47);
		tea_p.add(Tea_11);
		String x11=tea_menu[tm];
		Tea_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x11)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
		
		JButton Tea_12 = new JButton(tea_menu[tm]);
		Tea_12.setBackground(new Color(207,231,244));
		if(tea_menu[tm]!=null&&Integer.parseInt(tea_num[tm])==0) {
			Tea_12.setBackground(new Color(71,61,245));
			Tea_12.setForeground(Color.WHITE);
			t_[12]=0;
		}
		if(tea_menu[tm]==null) {
			t_[12]=0;
		}
		else {
			t_[12]=Integer.parseInt(tea_num[tm]);
		}
		Tea_12.setFont(new Font("HY나무L",Font.PLAIN,9));
		Tea_12.setBounds(315, 170, 85, 47);
		tea_p.add(Tea_12);
		String x12=tea_menu[tm];
		Tea_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t_[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<tm;i++) {
						if(tea_menu[i]==null) {
						}
						else if(tea_menu[i].equals(x12)) {
							rows[0]=tea_menu[i];
							rows[1]="1";
							rows[2]=tea_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(tea_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		tm++;
	}
	public void hot_be() {
		hotb_p.setBounds(396, 139, 419, 242);
		hotb_p.setBackground(Color.WHITE);
		hotb_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null));
		hotb_p.setLayout(null);
		String[] rows=new String[3];
		
		JButton hotbe_1 = new JButton(hb_menu[hbn]);
		hotbe_1.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_1.setBackground(new Color(71,61,245));
			hotbe_1.setForeground(Color.WHITE);
			h_b[1]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[1]=0;
		}
		else {
			h_b[1]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_1.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_1.setBounds(15, 20, 85, 47);
		hotb_p.add(hotbe_1);
		String x=hb_menu[hbn];
		hotbe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[1]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<hbn;i++) {
						if(hb_menu[i]==null) {
						}
						else if(hb_menu[i].equals(x)) {
							rows[0]=hb_menu[i];
							rows[1]="1";
							rows[2]=hb_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hb_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		hbn++;
		
		JButton hotbe_2 = new JButton(hb_menu[hbn]);
		hotbe_2.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_2.setBackground(new Color(71,61,245));
			hotbe_2.setForeground(Color.WHITE);
			h_b[2]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[2]=0;
		}
		else {
			h_b[2]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_2.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_2.setBounds(115, 20, 85, 47);
		hotb_p.add(hotbe_2);
		String x2=hb_menu[hbn];
		hotbe_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x2)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;
		
		JButton hotbe_3 = new JButton(hb_menu[hbn]);
		hotbe_3.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_3.setBackground(new Color(71,61,245));
			hotbe_3.setForeground(Color.WHITE);
			h_b[3]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[3]=0;
		}
		else {
			h_b[3]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_3.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_3.setBounds(215, 20, 85, 47);
		hotb_p.add(hotbe_3);
		String x3=hb_menu[hbn];
		hotbe_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
					for(int i=0;i<hbn;i++) {
						if(hb_menu[i]==null) {
						}
						else if(hb_menu[i].equals(x3)) {
							rows[0]=hb_menu[i];
							rows[1]="1";
							rows[2]=hb_price[i];
							model.addRow(rows);
							total_m+=Integer.parseInt(hb_price[i]);
							String y=Integer.toString(total_m);
							totalbl.setText(y);
							n++;
						}
					}
				}
			}
		});	
		hbn++;
		
		JButton hotbe_4 = new JButton(hb_menu[hbn]);
		hotbe_4.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_4.setBackground(new Color(71,61,245));
			hotbe_4.setForeground(Color.WHITE);
			h_b[4]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[4]=0;
		}
		else {
			h_b[4]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_4.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_4.setBounds(315, 20, 85, 47);
		hotb_p.add(hotbe_4);
		String x4=hb_menu[hbn];
		hotbe_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x4)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;
		
		JButton hotbe_5 = new JButton(hb_menu[hbn]);
		hotbe_5.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_5.setBackground(new Color(71,61,245));
			hotbe_5.setForeground(Color.WHITE);
			h_b[5]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[5]=0;
		}
		else {
			h_b[5]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_5.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_5.setBounds(15, 95, 85, 47);
		hotb_p.add(hotbe_5);
		String x5=hb_menu[hbn];
		hotbe_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x5)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;
		
		JButton hotbe_6 = new JButton(hb_menu[hbn]);
		hotbe_6.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_6.setBackground(new Color(71,61,245));
			hotbe_6.setForeground(Color.WHITE);
			h_b[6]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[6]=0;
		}
		else {
			h_b[6]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_6.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_6.setBounds(115, 95, 85, 47);
		hotb_p.add(hotbe_6);
		String x6=hb_menu[hbn];
		hotbe_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x6)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;
		
		JButton hotbe_7 = new JButton(hb_menu[hbn]);
		hotbe_7.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_7.setBackground(new Color(71,61,245));
			hotbe_7.setForeground(Color.WHITE);
			h_b[7]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[7]=0;
		}
		else {
			h_b[7]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_7.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_7.setBounds(215, 95, 85, 47);
		hotb_p.add(hotbe_7);
		String x7=hb_menu[hbn];
		hotbe_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x7)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;	
		
		JButton hotbe_8 = new JButton(hb_menu[hbn]);
		hotbe_8.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_8.setBackground(new Color(71,61,245));
			hotbe_8.setForeground(Color.WHITE);
			h_b[8]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[8]=0;
		}
		else {
			h_b[8]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_8.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_8.setBounds(315, 95, 85, 47);
		hotb_p.add(hotbe_8);
		String x8=hb_menu[hbn];
		hotbe_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x8)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;	
		
		JButton hotbe_9 = new JButton(hb_menu[hbn]);
		hotbe_9.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_9.setBackground(new Color(71,61,245));
			hotbe_9.setForeground(Color.WHITE);
			h_b[9]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[9]=0;
		}
		else {
			h_b[9]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_9.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_9.setBounds(15, 170, 85, 47);
		hotb_p.add(hotbe_9);
		String x9=hb_menu[hbn];
		hotbe_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x9)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;	
		
		JButton hotbe_10 = new JButton(hb_menu[hbn]);
		hotbe_10.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_10.setBackground(new Color(71,61,245));
			hotbe_10.setForeground(Color.WHITE);
			h_b[10]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[10]=0;
		}
		else {
			h_b[10]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_10.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_10.setBounds(115, 170, 85, 47);
		hotb_p.add(hotbe_10);
		String x10=hb_menu[hbn];
		hotbe_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x10)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;	
		
		JButton hotbe_11 = new JButton(hb_menu[hbn]);
		hotbe_11.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_11.setBackground(new Color(71,61,245));
			hotbe_11.setForeground(Color.WHITE);
			h_b[11]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[11]=0;
		}
		else {
			h_b[11]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_11.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_11.setBounds(215, 170, 85, 47);
		hotb_p.add(hotbe_11);
		String x11=hb_menu[hbn];
		hotbe_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x11)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;	
		
		JButton hotbe_12 = new JButton(hb_menu[hbn]);
		hotbe_12.setBackground(new Color(207,231,244));
		if(hb_menu[hbn]!=null&&Integer.parseInt(hb_num[hbn])==0) {
			hotbe_12.setBackground(new Color(71,61,245));
			hotbe_12.setForeground(Color.WHITE);
			h_b[12]=0;
		}
		if(hb_menu[hbn]==null) {
			h_b[12]=0;
		}
		else {
			h_b[12]=Integer.parseInt(hb_num[hbn]);
		}
		hotbe_12.setFont(new Font("HY나무L",Font.PLAIN,9));
		hotbe_12.setBounds(315, 170, 85, 47);
		hotb_p.add(hotbe_12);
		String x12=hb_menu[hbn];
		hotbe_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(h_b[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<hbn;i++) {
					if(hb_menu[i]==null) {
					}
					else if(hb_menu[i].equals(x12)) {
						rows[0]=hb_menu[i];
						rows[1]="1";
						rows[2]=hb_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(hb_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		hbn++;	
	}
	public void ice_be() {
		iceb_p.setBounds(396, 139, 419, 242);
		iceb_p.setBackground(Color.WHITE);
		iceb_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null));
		iceb_p.setLayout(null);
		String[] rows=new String[3];
		
		JButton icebe_1 = new JButton(ib_menu[ibn]);
		icebe_1.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_1.setBackground(new Color(71,61,245));
			icebe_1.setForeground(Color.WHITE);
			i_b[1]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[1]=0;
		}
		else {
			i_b[1]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_1.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_1.setBounds(15, 20, 85, 47);
		iceb_p.add(icebe_1);
		String x=ib_menu[ibn];
		icebe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[1]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
			
		JButton icebe_2 = new JButton(ib_menu[ibn]);
		icebe_2.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_2.setBackground(new Color(71,61,245));
			icebe_2.setForeground(Color.WHITE);
			i_b[2]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[2]=0;
		}
		else {
			i_b[2]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_2.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_2.setBounds(115, 20, 85, 47);
		iceb_p.add(icebe_2);
		String x2=ib_menu[ibn];
		icebe_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x2)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_3 = new JButton(ib_menu[ibn]);
		icebe_3.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_3.setBackground(new Color(71,61,245));
			icebe_3.setForeground(Color.WHITE);
			i_b[3]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[3]=0;
		}
		else {
			i_b[3]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_3.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_3.setBounds(215, 20, 85, 47);
		iceb_p.add(icebe_3);
		String x3=ib_menu[ibn];
		icebe_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x3)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_4 = new JButton(ib_menu[ibn]);
		icebe_4.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_4.setBackground(new Color(71,61,245));
			icebe_4.setForeground(Color.WHITE);
			i_b[4]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[4]=0;
		}
		else {
			i_b[4]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_4.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_4.setBounds(315, 20, 85, 47);
		iceb_p.add(icebe_4);
		String x4=ib_menu[ibn];
		icebe_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x4)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_5 = new JButton(ib_menu[ibn]);
		icebe_5.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_5.setBackground(new Color(71,61,245));
			icebe_5.setForeground(Color.WHITE);
			i_b[5]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[5]=0;
		}
		else {
			i_b[5]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_5.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_5.setBounds(15, 95, 85, 47);
		iceb_p.add(icebe_5);
		String x5=ib_menu[ibn];
		icebe_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x5)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_6 = new JButton(ib_menu[ibn]);
		icebe_6.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_6.setBackground(new Color(71,61,245));
			icebe_6.setForeground(Color.WHITE);
			i_b[6]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[6]=0;
		}
		else {
			i_b[6]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_6.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_6.setBounds(115, 95, 85, 47);
		iceb_p.add(icebe_6);
		String x6=ib_menu[ibn];
		icebe_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x6)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_7 = new JButton(ib_menu[ibn]);
		icebe_7.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_7.setBackground(new Color(71,61,245));
			icebe_7.setForeground(Color.WHITE);
			i_b[7]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[7]=0;
		}
		else {
			i_b[7]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_7.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_7.setBounds(215, 95, 85, 47);
		iceb_p.add(icebe_7);
		String x7=ib_menu[ibn];
		icebe_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x7)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_8 = new JButton(ib_menu[ibn]);
		icebe_8.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_8.setBackground(new Color(71,61,245));
			icebe_8.setForeground(Color.WHITE);
			i_b[8]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[8]=0;
		}
		else {
			i_b[8]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_8.setFont(new Font("HY나무L",Font.PLAIN,9));
		icebe_8.setBounds(315, 95, 85, 47);
		iceb_p.add(icebe_8);
		String x8=ib_menu[ibn];
		icebe_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x8)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_9 = new JButton(ib_menu[ibn]);
		icebe_9.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_9.setBackground(new Color(71,61,245));
			icebe_9.setForeground(Color.WHITE);
			i_b[9]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[9]=0;
		}
		else {
			i_b[9]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_9.setFont(new Font("HY나무L",Font.PLAIN,8));
		icebe_9.setBounds(15, 170, 85, 47);
		iceb_p.add(icebe_9);
		String x9=ib_menu[ibn];
		icebe_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x9)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_10 = new JButton(ib_menu[ibn]);
		icebe_10.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_10.setBackground(new Color(71,61,245));
			icebe_10.setForeground(Color.WHITE);
			i_b[10]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[10]=0;
		}
		else {
			i_b[10]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_10.setFont(new Font("HY나무L",Font.PLAIN,8));
		icebe_10.setBounds(115, 170, 85, 47);
		iceb_p.add(icebe_10);
		String x10=ib_menu[ibn];
		icebe_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x10)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_11 = new JButton(ib_menu[ibn]);
		icebe_11.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_11.setBackground(new Color(71,61,245));
			icebe_11.setForeground(Color.WHITE);
			i_b[11]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[11]=0;
		}
		else {
			i_b[11]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_11.setFont(new Font("HY나무L",Font.PLAIN,8));
		icebe_11.setBounds(215, 170, 85, 47);
		iceb_p.add(icebe_11);
		String x11=ib_menu[ibn];
		icebe_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x11)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
		
		JButton icebe_12 = new JButton(ib_menu[ibn]);
		icebe_12.setBackground(new Color(207,231,244));
		if(ib_menu[ibn]!=null&&Integer.parseInt(ib_num[ibn])==0) {
			icebe_12.setBackground(new Color(71,61,245));
			icebe_12.setForeground(Color.WHITE);
			i_b[12]=0;
		}
		if(ib_menu[ibn]==null) {
			i_b[12]=0;
		}
		else {
			i_b[12]=Integer.parseInt(ib_num[ibn]);
		}
		icebe_12.setFont(new Font("HY나무L",Font.PLAIN,8));
		icebe_12.setBounds(315, 170, 85, 47);
		iceb_p.add(icebe_12);
		String x12=ib_menu[ibn];
		icebe_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i_b[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<ibn;i++) {
					if(ib_menu[i]==null) {
					}
					else if(ib_menu[i].equals(x12)) {
						rows[0]=ib_menu[i];
						rows[1]="1";
						rows[2]=ib_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(ib_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		ibn++;
	}

	public void shake() {
		shake_p.setBounds(396, 139, 419, 242);
		shake_p.setBackground(Color.WHITE);
		shake_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null));
		shake_p.setLayout(null);
		String[] rows=new String[3];
		
		JButton shake_1 = new JButton(sh_menu[shn]);
		shake_1.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_1.setBackground(new Color(71,61,245));
			shake_1.setForeground(Color.WHITE);
			s_h[1]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[1]=0;
		}
		else {
			s_h[1]=Integer.parseInt(sh_num[shn]);
		}
		shake_1.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_1.setBounds(15, 20, 85, 47);
		shake_p.add(shake_1);
		String x=sh_menu[shn];
		shake_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[1]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_2 = new JButton(sh_menu[shn]);
		shake_2.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_2.setBackground(new Color(71,61,245));
			shake_2.setForeground(Color.WHITE);
			s_h[2]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[2]=0;
		}
		else {
			s_h[2]=Integer.parseInt(sh_num[shn]);
		}
		shake_2.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_2.setBounds(115, 20, 85, 47);
		shake_p.add(shake_2);
		String x2=sh_menu[shn];
		shake_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x2)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_3 = new JButton(sh_menu[shn]);
		shake_3.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_3.setBackground(new Color(71,61,245));
			shake_3.setForeground(Color.WHITE);
			s_h[3]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[3]=0;
		}
		else {
			s_h[3]=Integer.parseInt(sh_num[shn]);
		}
		shake_3.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_3.setBounds(215, 20, 85, 47);
		shake_p.add(shake_3);
		String x3=sh_menu[shn];
		shake_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x3)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_4 = new JButton(sh_menu[shn]);
		shake_4.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_4.setBackground(new Color(71,61,245));
			shake_4.setForeground(Color.WHITE);
			s_h[4]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[4]=0;
		}
		else {
			s_h[4]=Integer.parseInt(sh_num[shn]);
		}
		shake_4.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_4.setBounds(315, 20, 85, 47);
		shake_p.add(shake_4);
		String x4=sh_menu[shn];
		shake_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x4)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_5 = new JButton(sh_menu[shn]);
		shake_5.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_5.setBackground(new Color(71,61,245));
			shake_5.setForeground(Color.WHITE);
			s_h[5]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[5]=0;
		}
		else {
			s_h[5]=Integer.parseInt(sh_num[shn]);
		}
		shake_5.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_5.setBounds(15, 95, 85, 47);
		shake_p.add(shake_5);
		String x5=sh_menu[shn];
		shake_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x5)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_6 = new JButton(sh_menu[shn]);
		shake_6.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_6.setBackground(new Color(71,61,245));
			shake_6.setForeground(Color.WHITE);
			s_h[6]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[6]=0;
		}
		else {
			s_h[6]=Integer.parseInt(sh_num[shn]);
		}
		shake_6.setFont(new Font("고딕",Font.PLAIN,8));
		shake_6.setBounds(115, 95, 85, 47);
		shake_p.add(shake_6);
		String x6=sh_menu[shn];
		shake_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x6)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_7 = new JButton(sh_menu[shn]);
		shake_7.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_7.setBackground(new Color(71,61,245));
			shake_7.setForeground(Color.WHITE);
			s_h[7]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[7]=0;
		}
		else {
			s_h[7]=Integer.parseInt(sh_num[shn]);
		}
		shake_7.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_7.setBounds(215, 95, 85, 47);
		shake_p.add(shake_7);
		String x7=sh_menu[shn];
		shake_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x7)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_8 = new JButton(sh_menu[shn]);
		shake_8.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_8.setBackground(new Color(71,61,245));
			shake_8.setForeground(Color.WHITE);
			s_h[8]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[8]=0;
		}
		else {
			s_h[8]=Integer.parseInt(sh_num[shn]);
		}
		shake_8.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_8.setBounds(315, 95, 85, 47);
		shake_p.add(shake_8);
		String x8=sh_menu[shn];
		shake_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x8)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_9 = new JButton(sh_menu[shn]);
		shake_9.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_9.setBackground(new Color(71,61,245));
			shake_9.setForeground(Color.WHITE);
			s_h[9]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[9]=0;
		}
		else {
			s_h[9]=Integer.parseInt(sh_num[shn]);
		}
		shake_9.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_9.setBounds(15, 170, 85, 47);
		shake_p.add(shake_9);
		String x9=sh_menu[shn];
		shake_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x9)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_10 = new JButton(sh_menu[shn]);
		shake_10.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_10.setBackground(new Color(71,61,245));
			shake_10.setForeground(Color.WHITE);
			s_h[10]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[10]=0;
		}
		else {
			s_h[10]=Integer.parseInt(sh_num[shn]);
		}
		shake_10.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_10.setBounds(115, 170, 85, 47);
		shake_p.add(shake_10);
		String x10=sh_menu[shn];
		shake_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x10)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_11 = new JButton(sh_menu[shn]);
		shake_11.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_11.setBackground(new Color(71,61,245));
			shake_11.setForeground(Color.WHITE);
			s_h[11]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[11]=0;
		}
		else {
			s_h[11]=Integer.parseInt(sh_num[shn]);
		}
		shake_11.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_11.setBounds(215, 170, 85, 47);
		shake_p.add(shake_11);
		String x11=sh_menu[shn];
		shake_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x11)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
		
		JButton shake_12 = new JButton(sh_menu[shn]);
		shake_12.setBackground(new Color(207,231,244));
		if(sh_menu[shn]!=null&&Integer.parseInt(sh_num[shn])==0) {
			shake_12.setBackground(new Color(71,61,245));
			shake_12.setForeground(Color.WHITE);
			s_h[12]=0;
		}
		if(sh_menu[shn]==null) {
			s_h[12]=0;
		}
		else {
			s_h[12]=Integer.parseInt(sh_num[shn]);
		}
		shake_12.setFont(new Font("HY나무L",Font.PLAIN,8));
		shake_12.setBounds(315, 170, 85, 47);
		shake_p.add(shake_12);
		String x12=sh_menu[shn];
		shake_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s_h[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<shn;i++) {
					if(sh_menu[i]==null) {
					}
					else if(sh_menu[i].equals(x12)) {
						rows[0]=sh_menu[i];
						rows[1]="1";
						rows[2]=sh_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(sh_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n++;
					}
				}
				}
			}
		});	
		shn++;
	}

	public void add() {
		add_p.setBounds(396, 139, 419, 242);
		add_p.setBackground(Color.WHITE);
		add_p.setBorder(new TitledBorder(new LineBorder(new Color(000,000,205), 4), null));
		add_p.setLayout(null);
		String[] rows=new String[3];
		
		JButton add_1 = new JButton(add_menu[addn]);
		add_1.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_1.setBackground(new Color(71,61,245));
			add_1.setForeground(Color.WHITE);
			a_d[1]=0;
		}
		if(add_menu[addn]==null) {
			a_d[1]=0;
		}
		else {
			a_d[1]=Integer.parseInt(add_num[addn]);
		}
		add_1.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_1.setBounds(15, 20, 85, 47);
		add_p.add(add_1);
		String x=add_menu[addn];
		add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[1]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_2 = new JButton(add_menu[addn]);
		add_2.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_2.setBackground(new Color(71,61,245));
			add_2.setForeground(Color.WHITE);
			a_d[2]=0;
		}
		if(add_menu[addn]==null) {
			a_d[2]=0;
		}
		else {
			a_d[2]=Integer.parseInt(add_num[addn]);
		}
		add_2.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_2.setBounds(115, 20, 85, 47);	
		add_p.add(add_2);
		String x2=add_menu[addn];
		add_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[2]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x2)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_3 = new JButton(add_menu[addn]);
		add_3.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_3.setBackground(new Color(71,61,245));
			add_3.setForeground(Color.WHITE);
			a_d[3]=0;
		}
		if(add_menu[addn]==null) {
			a_d[3]=0;
		}
		else {
			a_d[3]=Integer.parseInt(add_num[addn]);
		}
		add_3.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_3.setBounds(215, 20, 85, 47);
		add_p.add(add_3);
		String x3=add_menu[addn];
		add_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[3]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x3)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_4 = new JButton(add_menu[addn]);
		add_4.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_4.setBackground(new Color(71,61,245));
			add_4.setForeground(Color.WHITE);
			a_d[4]=0;
		}
		if(add_menu[addn]==null) {
			a_d[4]=0;
		}
		else {
			a_d[4]=Integer.parseInt(add_num[addn]);
		}
		add_4.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_4.setBounds(315, 20, 85, 47);
		add_p.add(add_4);
		String x4=add_menu[addn];
		add_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[4]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x4)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_5 = new JButton(add_menu[addn]);
		add_5.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_5.setBackground(new Color(71,61,245));
			add_5.setForeground(Color.WHITE);
			a_d[5]=0;
		}
		if(add_menu[addn]==null) {
			a_d[5]=0;
		}
		else {
			a_d[5]=Integer.parseInt(add_num[addn]);
		}
		add_5.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_5.setBounds(15, 95, 85, 47);
		add_p.add(add_5);
		String x5=add_menu[addn];
		add_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[5]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x5)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_6 = new JButton(add_menu[addn]);
		add_6.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_6.setBackground(new Color(71,61,245));
			add_6.setForeground(Color.WHITE);
			a_d[6]=0;
		}
		if(add_menu[addn]==null) {
			a_d[6]=0;
		}
		else {
			a_d[6]=Integer.parseInt(add_num[addn]);
		}
		add_6.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_6.setBounds(115, 95, 85, 47);
		add_p.add(add_6);
		String x6=add_menu[addn];
		add_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[6]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x6)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_7 = new JButton(add_menu[addn]);
		add_7.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_7.setBackground(new Color(71,61,245));
			add_7.setForeground(Color.WHITE);
			a_d[7]=0;
		}
		if(add_menu[addn]==null) {
			a_d[7]=0;
		}
		else {
			a_d[7]=Integer.parseInt(add_num[addn]);
		}
		add_7.setFont(new Font("HY나무L",Font.PLAIN,9));
		add_7.setBounds(215, 95, 85, 47);
		add_p.add(add_7);
		String x7=add_menu[addn];
		add_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[7]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x7)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_8 = new JButton(add_menu[addn]);
		add_8.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_8.setBackground(new Color(71,61,245));
			add_8.setForeground(Color.WHITE);
			a_d[8]=0;
		}
		if(add_menu[addn]==null) {
			a_d[8]=0;
		}
		else {
			a_d[8]=Integer.parseInt(add_num[addn]);
		}
		add_8.setFont(new Font("HY나무L",Font.PLAIN,8));
		add_8.setBounds(315, 95, 85, 47);
		add_p.add(add_8);
		String x8=add_menu[addn];
		add_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[8]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x8)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_9 = new JButton(add_menu[addn]);
		add_9.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_9.setBackground(new Color(71,61,245));
			add_9.setForeground(Color.WHITE);
			a_d[9]=0;
		}
		if(add_menu[addn]==null) {
			a_d[9]=0;
		}
		else {
			a_d[9]=Integer.parseInt(add_num[addn]);
		}
		add_9.setFont(new Font("HY나무L",Font.PLAIN,8));
		add_9.setBounds(15, 170, 85, 47);
		add_p.add(add_9);
		String x9=add_menu[addn];
		add_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[9]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x9)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_10 = new JButton(add_menu[addn]);
		add_10.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_10.setBackground(new Color(71,61,245));
			add_10.setForeground(Color.WHITE);
			a_d[10]=0;
		}
		if(add_menu[addn]==null) {
			a_d[10]=0;
		}
		else {
			a_d[10]=Integer.parseInt(add_num[addn]);
		}
		add_10.setFont(new Font("HY나무L",Font.PLAIN,8));
		add_10.setBounds(115, 170, 85, 47);
		add_p.add(add_10);
		String x10=add_menu[addn];
		add_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[10]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x10)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_11 = new JButton(add_menu[addn]);
		add_11.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_11.setBackground(new Color(71,61,245));
			add_11.setForeground(Color.WHITE);
			a_d[11]=0;
		}
		if(add_menu[addn]==null) {
			a_d[11]=0;
		}
		else {
			a_d[11]=Integer.parseInt(add_num[addn]);
		}
		add_11.setFont(new Font("HY나무L",Font.PLAIN,8));
		add_11.setBounds(215, 170, 85, 47);
		add_p.add(add_11);
		String x11=add_menu[addn];
		add_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[11]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x11)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
		
		JButton add_12 = new JButton(add_menu[addn]);
		add_12.setBackground(new Color(207,231,244));
		if(add_menu[addn]!=null&&Integer.parseInt(add_num[addn])==0) {
			add_12.setBackground(new Color(71,61,245));
			add_12.setForeground(Color.WHITE);
			a_d[12]=0;
		}
		if(add_menu[addn]==null) {
			a_d[12]=0;
		}
		else {
			a_d[12]=Integer.parseInt(add_num[addn]);
		}
		add_12.setFont(new Font("HY나무L",Font.PLAIN,8));
		add_12.setBounds(315, 170, 85, 47);
		add_p.add(add_12);
		String x12=add_menu[addn];
		add_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a_d[12]==0) {
					JOptionPane.showMessageDialog(null, "재고소진으로 주문불가");
				}
				else {
				for(int i=0;i<addn;i++) {
					if(add_menu[i]==null) {
					}
					else if(add_menu[i].equals(x12)) {
						rows[0]=add_menu[i];
						rows[1]="1";
						rows[2]=add_price[i];
						model.addRow(rows);
						total_m+=Integer.parseInt(add_price[i]);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
					}
				}
				}
			}
		});	
		addn++;
	}

	public void member_in() {
		JFrame mi=new JFrame();
		mi.setResizable(false);
		mi.setTitle("회원등록");
		mi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mi.getContentPane().setLayout(cards);
		mi.setResizable(false);
		mi.setBounds(400, 200, 470, 345);
		contentPane = new JPanel();
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
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("HY나무L", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(37, 157, 84, 32);
		contentPane.add(lblNewLabel_2);
		
		JTextField p_num = new JTextField();
		p_num.setColumns(10);
		p_num.setBounds(150, 157, 175, 32);
		contentPane.add(p_num);
		
		JButton member_cancel = new JButton("\uC785\uB825\uCDE8\uC18C");
		member_cancel.setFont(new Font("HY나무L", Font.BOLD, 14));
		member_cancel.setBounds(94, 242, 92, 32);
		contentPane.add(member_cancel);
		member_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					mi.dispose();
					JOptionPane.showMessageDialog(null, "이미 등록된 회원입니다.");
				}catch(Exception e1) {
					e1.printStackTrace(); 
				}
			}
		});	
	}
	
	public void pay() {//결제함수
		JFrame p=new JFrame();//새로운 프레임 생성
		p.setResizable(false);
		p.setTitle("결제창");
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.getContentPane().setLayout(cards);
		p.setResizable(false);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setBounds(400, 170, 500, 395);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(110, 110, 350, 250);
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 5), null));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setContentPane(contentPane);
		contentPane.setLayout(null);
		p.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("\uACB0\uC81C\uBC29\uC2DD");
		lblNewLabel.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblNewLabel.setBounds(41, 45, 75, 27);
		contentPane.add(lblNewLabel);
		
		JRadioButton card_radio = new JRadioButton("\uCE74\uB4DC");
		card_radio.setFont(new Font("HY나무L", Font.PLAIN, 13));
		card_radio.setBounds(164, 46, 65, 23);
		card_radio.setBackground(Color.WHITE);
		contentPane.add(card_radio);
		
		JRadioButton cash_radio = new JRadioButton("\uD604\uAE08");
		cash_radio.setFont(new Font("HY나무L", Font.PLAIN, 13));
		cash_radio.setBounds(346, 46, 65, 23);
		cash_radio.setBackground(Color.WHITE);
		contentPane.add(cash_radio);
		
		ButtonGroup way=new ButtonGroup();//현금,카드 라디오 버튼 그룹화
		ButtonGroup take=new ButtonGroup();//매장,포장 라디오 버튼 그룹화
		
		way.add(card_radio);
		way.add(cash_radio);
		
		JLabel lblTakeout = new JLabel("TakeOut");
		lblTakeout.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblTakeout.setBounds(41, 103, 75, 27);
		contentPane.add(lblTakeout);
		
		JRadioButton btnin = new JRadioButton("\uB9E4\uC7A5"); //매장
		btnin.setFont(new Font("HY나무L", Font.PLAIN, 13));
		btnin.setBounds(164, 106, 65, 23);
		btnin.setBackground(Color.WHITE);
		contentPane.add(btnin);
		
		JRadioButton btntake = new JRadioButton("\uD3EC\uC7A5"); //포장
		btntake.setFont(new Font("HY나무L", Font.PLAIN, 13));
		btntake.setBounds(346, 106, 65, 23);
		btntake.setBackground(Color.WHITE);
		contentPane.add(btntake);
		
		take.add(btnin);
		take.add(btntake);
		
		JLabel lblTakeout_1 = new JLabel("\uD68C\uC6D0\uBC88\uD638");
		lblTakeout_1.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblTakeout_1.setBounds(41, 167, 75, 27);
		contentPane.add(lblTakeout_1);
		
		JTextField member_num = new JTextField();
		member_num.setBounds(164, 163, 172, 35);
		contentPane.add(member_num);
		member_num.setColumns(10);
		
		JButton btnsearch = new JButton("\uC870\uD68C");
		btnsearch.setFont(new Font("HY나무L", Font.PLAIN, 14));
		btnsearch.setBounds(350, 163, 75, 35);
		contentPane.add(btnsearch);
		btnsearch.addActionListener(new ActionListener() { //회원조회
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				PreparedStatement ps=null;
				String m_num=member_num.getText();
				String n = null;
				String y = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
					System.out.println("DB 연결 완료");
					Statement stmt=conn.createStatement();
					
					String sql="SELECT * FROM member WHERE m_id='"+m_num+"'";
					ResultSet rs=stmt.executeQuery(sql);
					//ps=con.prepareStatement(sql);

					while(rs.next()) {
						n=rs.getString(1);
						y=rs.getString(2);
						int s=rs.getInt(3);
					}
					if(m_num.equals(n)) {
						JOptionPane.showMessageDialog(null, "가입된 회원입니다.");
						m=m_num;
					}
					else {
						JFrame pm=new JFrame();
						pm.setTitle("회원등록_");
						pm.setResizable(false);
						pm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						pm.getContentPane().setLayout(cards);
						pm.setResizable(false);
						pm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						pm.setBounds(100, 100, 390, 225);
						contentPane = new JPanel();
						contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						pm.setContentPane(contentPane);
						contentPane.setLayout(null);
						pm.setVisible(true);
						
						JLabel lblNewLabel = new JLabel("\uB4F1\uB85D\uB418\uC9C0 \uC54A\uC740 \uD68C\uC6D0\uC785\uB2C8\uB2E4.");
						lblNewLabel.setFont(new Font("HY나무L", Font.BOLD, 14));
						lblNewLabel.setBounds(110, 48, 169, 35);
						contentPane.add(lblNewLabel);
						
						JButton btnin = new JButton("회원등록");
						btnin.setFont(new Font("HY나무L", Font.PLAIN, 12));
						btnin.setBounds(42, 135, 91, 30);
						contentPane.add(btnin);
						btnin.addActionListener(new ActionListener() { //회원등록
							public void actionPerformed(ActionEvent e) {
								member_in();
								pm.dispose();
							}
						});
						JButton btnc = new JButton("닫기");
						btnc.setFont(new Font("HY나무L", Font.PLAIN, 12));
						btnc.setBounds(218, 135, 91, 30);
						contentPane.add(btnc);
						btnc.addActionListener(new ActionListener() { //결제취소
							public void actionPerformed(ActionEvent e) {
								pm.dispose();
							}
						});
					}
					//JOptionPane.showMessageDialog(null, "등록완료");
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
		});
		
		JLabel lblTakeout_1_1 = new JLabel("\uACB0\uC81C\uAE08\uC561");
		lblTakeout_1_1.setFont(new Font("HY나무L", Font.BOLD, 14));
		lblTakeout_1_1.setBounds(41, 235, 75, 27);
		contentPane.add(lblTakeout_1_1);
		
		JLabel lblmoney = new JLabel();//결제금액
		lblmoney.setFont(new Font("HY나무L", Font.BOLD, 20));
		lblmoney.setBounds(167, 235, 223, 27);
		String y=Integer.toString(total_m);
		lblmoney.setText(y);
		contentPane.add(lblmoney);
		
		JButton btncancel = new JButton("\uCDE8\uC18C");
		btncancel.setFont(new Font("HY나무L", Font.PLAIN, 14));
		btncancel.setBounds(120, 294, 90, 35);
		contentPane.add(btncancel);
		btncancel.addActionListener(new ActionListener() { //결제취소
			public void actionPerformed(ActionEvent e) {
				p.dispose();
			}
		});
		
		JButton btnpay = new JButton("\uACB0\uC81C");
		btnpay.setFont(new Font("HY나무L", Font.PLAIN, 14));
		btnpay.setBounds(310, 294, 90, 35);
		contentPane.add(btnpay);
		btnpay.addActionListener(new ActionListener() { //결제완료
			public void actionPerformed(ActionEvent e) {
				String []da=new String[100];
				for(int a=0;a<n;a++) {
						Object select=menutable.getModel().getValueAt(a,0);
						menu_s=menu_s+select+",";	
						da[a]=(String) select;
				}
				Connection con=null;
				PreparedStatement ps=null;
				String ways=null;
				if(card_radio.isSelected()) {
					ways="카드";
				}
				else if(cash_radio.isSelected()) {
					ways="현금";
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
					System.out.println("판매DB 연결 완료");
					Statement stmt=conn.createStatement();
					String sql="INSERT INTO sales (id, date,menu_s,price_s,way) VALUES('"+m+"','"+format_t+"','"+menu_s+"',"+total_m+",'"+ways+"')";
					stmt.execute(sql);
					String s_num="UPDATE member SET stamp="+n+" where m_id='"+member_num.getText()+"'";
					stmt.execute(s_num);
					model.setNumRows(0);
					total_m=0;
					String y=Integer.toString(total_m);
					totalbl.setText(y);
					//
					for(int i=0;i<n;i++) {
						for(int j=0;j<13;j++) {
							if(da[i].equals(hc_menu[j])) {
								int x=Integer.parseInt(hc_num[j]);
								int b=x-1;
								int z=Integer.parseInt(hc_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
							else if(da[i].equals(ic_menu[j])) {
								int x=Integer.parseInt(ic_num[j]);
								int b=x-1;
								int z=Integer.parseInt(ic_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
							else if(da[i].equals(hb_menu[j])) {
								int x=Integer.parseInt(hb_num[j]);
								int b=x-1;
								int z=Integer.parseInt(hb_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
							else if(da[i].equals(ib_menu[j])) {
								int x=Integer.parseInt(ib_num[j]);
								int b=x-1;
								int z=Integer.parseInt(ib_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
							else if(da[i].equals(tea_menu[j])) {
								int x=Integer.parseInt(tea_num[j]);
								int b=x-1;
								int z=Integer.parseInt(tea_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
							else if(da[i].equals(sh_menu[j])) {
								int x=Integer.parseInt(sh_num[j]);
								int b=x-1;
								int z=Integer.parseInt(sh_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
							else if(da[i].equals(add_menu[j])) {
								int x=Integer.parseInt(add_num[j]);
								int b=x-1;
								int z=Integer.parseInt(add_sales[j]);
								int c=z+1;
								String ss="UPDATE menu SET num="+b+","+" sales="+c+" WHERE menu='"+da[i]+"'";
								stmt.execute(ss);
							}
						}
					}
					JOptionPane.showMessageDialog(null, "결제완료");
					p.dispose(); 
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
		});
	}
	public void b_order() { //주문내역확인 
		JFrame or_f=new JFrame();
		or_f.setTitle("이전주문내역");
		or_f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		or_f.setBounds(150, 150, 584, 453);
		contentPane = new JPanel();
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
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
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
	
	public void stamp() {//스탬프 사용
		JFrame st_f=new JFrame();
		st_f.setTitle("스탬프사용");
		st_f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		st_f.setBounds(250, 250, 449, 278);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(160, 160, 350, 250);
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(130,194,245), 5), null));
		st_f.setContentPane(contentPane);
		contentPane.setLayout(null);
		st_f.setVisible(true);
		
		JTextField textField = new JTextField();
		textField.setBounds(142, 29, 164, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		String tm=textField.getText();
		
		JLabel lbl_member_num = new JLabel("\uD68C \uC6D0 \uBC88 \uD638");
		lbl_member_num.setFont(new Font("HY나무L", Font.PLAIN, 15));
		lbl_member_num.setBounds(42, 28, 88, 30);
		contentPane.add(lbl_member_num);
		
		JButton btnstampcancel = new JButton("취소");
		btnstampcancel.setFont(new Font("HY나무L", Font.PLAIN, 12));
		btnstampcancel.setBounds(60, 190, 91, 30);
		contentPane.add(btnstampcancel);
		btnstampcancel.addActionListener(new ActionListener() { //취소
			public void actionPerformed(ActionEvent e) {
				st_f.dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("적립된 스탬프 수 : ");
		lblNewLabel.setFont(new Font("HY나무L", Font.PLAIN, 15));
		lblNewLabel.setBounds(42, 78, 132, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(186, 80, 164, 38);
		lblNewLabel_1.setFont(new Font("HY나무L", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setText(Integer.toString(ss));
		
		JLabel lblNewLabel_2 = new JLabel("* 스탬프를 사용하시겠습니까? (사용시 10개 차감)");
		lblNewLabel_2.setFont(new Font("HY나무L", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(42, 127, 357, 45);
		contentPane.add(lblNewLabel_2);
		
		JButton btnstampuse = new JButton("사용");
		btnstampuse.setFont(new Font("HY나무L", Font.PLAIN, 12));
		btnstampuse.setBounds(259, 188, 91, 30);
		contentPane.add(btnstampuse);
		
		JButton btnSearch = new JButton("\uC870\uD68C"); //회원번호조회
		btnSearch.setFont(new Font("HY나무L", Font.PLAIN, 13));
		btnSearch.setBounds(325, 28, 74, 30);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				PreparedStatement ps=null;
				String m_num=textField.getText();
				String n = null;
				String y = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
					System.out.println("DB 연결 완료");
					Statement stmt=conn.createStatement();
					
					String sql="SELECT * FROM member WHERE m_id='"+m_num+"'";
					ResultSet rs=stmt.executeQuery(sql);
					//ps=con.prepareStatement(sql);

					while(rs.next()) {
						n=rs.getString(1);
						y=rs.getString(2);
						ss=rs.getInt(3);
					}
					if(m_num.equals(n)) {
						JOptionPane.showMessageDialog(null, "가입된 회원입니다.");	
						lblNewLabel_1.setText(Integer.toString(ss));
					}
					else {
						lblNewLabel.setVisible(false);
						lblNewLabel_1.setVisible(false);
						lblNewLabel_2.setVisible(false);
						JLabel lblNewLabel_x = new JLabel("회원등록을 먼저 진행해주세요.");
						lblNewLabel_x.setFont(new Font("HY나무L", Font.BOLD, 16));
						lblNewLabel_x.setBounds(115, 89, 280, 38);
						contentPane.add(lblNewLabel_x);
						JLabel lblNewLabel_Y = new JLabel("(취소 버튼을 눌러주세요)");
						lblNewLabel_Y.setFont(new Font("HY나무L", Font.PLAIN, 14));
						lblNewLabel_Y.setBounds(130, 120, 200, 38);
						contentPane.add(lblNewLabel_Y);
						lblNewLabel_x.setVisible(true);
						lblNewLabel_Y.setVisible(true);
						JOptionPane.showMessageDialog(null, "가입되지 않은 회원입니다.");
					}
				}catch(ClassNotFoundException e1) {
					System.out.println("JDBC 드라이버가 존재하지 않습니다"+e1);
					JOptionPane.showMessageDialog(null, "조회실패");
				} catch(java.sql.SQLException e1) {
					System.out.println("DB SQL문 오류:"+e1);
					JOptionPane.showMessageDialog(null, "조회");
				}catch(Exception e1) {
					e1.printStackTrace(); 
				}
			}
		});
		
		btnstampuse.addActionListener(new ActionListener() { //스탬프 사용
			public void actionPerformed(ActionEvent e) {
				if(ss>=10) {
					Connection con=null;
					PreparedStatement ps=null;
					ns=ss-10;
					String cValue=null;
					if(menutable.getSelectedRow()!=-1) {
						int nRow=menutable.getSelectedRow();
						int nColumn=menutable.getSelectedColumn();
						cValue=(String) model.getValueAt(nRow, nColumn);
						model.removeRow(menutable.getSelectedRow());
						total_m-=Integer.parseInt(cValue);
						String y=Integer.toString(total_m);
						totalbl.setText(y);
						n-=1;
						
						select_menu=menutable.getModel().getValueAt(nRow, 0);
					}
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soyeon?serverTimezone=UTC","root","1234");
						//System.out.println("DB 연결 완료");
						Statement stmt=conn.createStatement();
						String sq="UPDATE member SET stamp="+ns+" where m_id='"+textField.getText()+"'";
						stmt.execute(sq);
						System.out.println("업데이트완료");
						String q2="INSERT INTO sales (id, date,menu_s,price_s,way) VALUES('"+textField.getText()+"','"+format_t+"','"+select_menu+"',"+cValue+","+"'stamp')";
						stmt.execute(q2);
						lblNewLabel_1.setText(Integer.toString(ns));
						JOptionPane.showMessageDialog(null, "사용완료");
						st_f.dispose();
						conn.close();
					}catch(ClassNotFoundException e1) {
						System.out.println("JDBC 드라이버가 존재하지 않습니다"+e1);
						JOptionPane.showMessageDialog(null, "결제실패");
					} catch(java.sql.SQLException e1) {
						System.out.println("DB SQL문 오류:"+e1);
						JOptionPane.showMessageDialog(null, "결제실패");
					}catch(Exception e1) {
						e1.printStackTrace(); 
						JOptionPane.showMessageDialog(null, "결제실패");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "스탬프가 부족합니다.");
					st_f.dispose();
				}
			}
		});
	}
	user() {//사용자 ui
		user.setResizable(false);
		user.setTitle("이디야 pos기");
		user.getContentPane().setLayout(cards);
		user.setResizable(false);
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user.setBounds(100, 100, 847, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		user.setContentPane(contentPane);
		contentPane.setLayout(null);
		user.setVisible(true);
		db_menu();
		//ThreadConrol threadCnl=new ThreadConrol();
		//threadCnl.start();
		
		JButton order = new JButton("\uC8FC\uBB38\uB0B4\uC5ED");
		order.setBackground(new Color(130,194,245));
		order.setBounds(140, 395, 101, 33);
		contentPane.add(order);
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_order();
			}
		});
			
		JButton member = new JButton("\uD68C\uC6D0\uB4F1\uB85D");
		member.setBounds(264, 395, 101, 33);
		member.setBackground(new Color(130,194,245));
		contentPane.add(member);
		member.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_in();
			}
		});
		
		menutable = new JTable(model);
		menutable.setFillsViewportHeight(true);
		menutable.getTableHeader().setBackground(new Color(130,194,245));
		menutable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane=new JScrollPane(menutable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(menutable);
		scrollPane.setBounds(14, 42, 370, 225);
		DefaultTableCellRenderer dtct=new DefaultTableCellRenderer();
		dtct.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm=menutable.getColumnModel();
		for(int i=0;i<tcm.getColumnCount();i++) {
			tcm.getColumn(i).setCellRenderer(dtct);
		}
		contentPane.add(scrollPane);
			
		
		JLabel total_money = new JLabel("\uCD1D \uAE08\uC561 : ");
		total_money.setBounds(31, 286, 353, 44);
		contentPane.add(total_money);
			
		JButton btnbell = new JButton("\uC9C4\uB3D9\uBCA8");
		btnbell.setBackground(new Color(52,117,245));
		btnbell.setFont(new Font("HY나무L", Font.BOLD, 12));
		btnbell.setForeground(Color.WHITE);
		btnbell.setBounds(396, 17, 95, 44);
		contentPane.add(btnbell);
		//btnbell.setBackground(new Color(201, 231, 248));
		btnbell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(bell_p);
				hotc_p.setVisible(false);
				icec_p.setVisible(false);
				tea_p.setVisible(false);
				hotb_p.setVisible(false);
				iceb_p.setVisible(false);
				shake_p.setVisible(false);
				add_p.setVisible(false);
				bell_p.setVisible(true);
				bell();
			}
		});
		
		JButton btnhotcoffee = new JButton("HOT Coffee");
		btnhotcoffee.setFont(new Font("HY나무L", Font.BOLD, 10));
		btnhotcoffee.setBackground(new Color(52,117,245));
		btnhotcoffee.setForeground(Color.WHITE);
		btnhotcoffee.setBounds(503, 18, 95, 44);
		contentPane.add(btnhotcoffee);
		
		btnhotcoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(hotc_p);
				icec_p.setVisible(false);
				tea_p.setVisible(false);
				hotb_p.setVisible(false);
				iceb_p.setVisible(false);
				shake_p.setVisible(false);
				add_p.setVisible(false);
				bell_p.setVisible(false);
				hotc_p.setVisible(true);
				hotcoffee();
			}
		});
		
		JButton btnicecoffee = new JButton("ICED Coffee");
		btnicecoffee.setFont(new Font("HY나무L", Font.BOLD, 10));
		btnicecoffee.setBackground(new Color(52,117,245));
		btnicecoffee.setForeground(Color.WHITE);
		btnicecoffee.setBounds(610, 18, 95, 44);
		contentPane.add(btnicecoffee);
			
		btnicecoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(icec_p);
				tea_p.setVisible(false);
				hotb_p.setVisible(false);
				iceb_p.setVisible(false);
				shake_p.setVisible(false);
				add_p.setVisible(false);
				bell_p.setVisible(false);
				hotc_p.setVisible(false);
				icec_p.setVisible(true);
				icecoffee();
			}
		});
		
		JButton btnTea = new JButton("TEA");
		btnTea.setBackground(new Color(52,117,245));
		btnTea.setForeground(Color.WHITE);
		btnTea.setBounds(720, 17, 95, 44);
		contentPane.add(btnTea);
		
		btnTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(tea_p);
				hotb_p.setVisible(false);
				iceb_p.setVisible(false);
				shake_p.setVisible(false);
				add_p.setVisible(false);
				bell_p.setVisible(false);
				hotc_p.setVisible(false);
				icec_p.setVisible(false);
				tea_p.setVisible(true);
				Tea();
			}
		});
		
		JButton btnhotbeber = new JButton("HOT Beverage");
		btnhotbeber.setBackground(new Color(52,117,245));
		btnhotbeber.setForeground(Color.WHITE);
		btnhotbeber.setFont(new Font("HY나무L", Font.BOLD, 10));
		btnhotbeber.setBounds(396, 72, 95, 44);
		contentPane.add(btnhotbeber);
			
		btnhotbeber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(hotb_p);
				iceb_p.setVisible(false);
				shake_p.setVisible(false);
				add_p.setVisible(false);
				bell_p.setVisible(false);
				hotc_p.setVisible(false);
				icec_p.setVisible(false);
				tea_p.setVisible(false);
				hotb_p.setVisible(true);
				hot_be();
			}
		});
		
		JButton btnicebeber = new JButton("ICED Beverage");
		btnicebeber.setBackground(new Color(52,117,245));
		btnicebeber.setForeground(Color.WHITE);
		btnicebeber.setFont(new Font("HY나무L", Font.BOLD, 10));
		btnicebeber.setBounds(503, 72, 95, 44);
		contentPane.add(btnicebeber);
		
		btnicebeber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(iceb_p);
				shake_p.setVisible(false);
				add_p.setVisible(false);
				bell_p.setVisible(false);
				hotc_p.setVisible(false);
				icec_p.setVisible(false);
				tea_p.setVisible(false);
				hotb_p.setVisible(false);
				iceb_p.setVisible(true);
				ice_be();
			}
		});
		
		JButton btnshake = new JButton("\uC250\uC774\uD06C\uD50C\uB7AB\uCE58\uB178");
		btnshake.setBackground(new Color(52,117,245));
		btnshake.setForeground(Color.WHITE);
		btnshake.setFont(new Font("HY나무L", Font.BOLD, 10));
		btnshake.setBounds(610, 72, 95, 44);
		contentPane.add(btnshake);
		btnshake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(shake_p);
				add_p.setVisible(false);
				bell_p.setVisible(false);
				hotc_p.setVisible(false);
				icec_p.setVisible(false);
				tea_p.setVisible(false);
				hotb_p.setVisible(false);
				iceb_p.setVisible(false);
				shake_p.setVisible(true);
				shake();
			}
		});
		
		JButton btnadd = new JButton("\uCD94\uAC00");
		btnadd.setBackground(new Color(52,117,245));
		btnadd.setFont(new Font("HY나무L", Font.BOLD, 12));
		btnadd.setForeground(Color.WHITE);
		btnadd.setBounds(720, 71, 95, 44);
		contentPane.add(btnadd);
		
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.add(add_p);
				bell_p.setVisible(false);
				hotc_p.setVisible(false);
				icec_p.setVisible(false);
				tea_p.setVisible(false);
				hotb_p.setVisible(false);
				iceb_p.setVisible(false);
				shake_p.setVisible(false);
				add_p.setVisible(true);
				add();
			}
		});
		
		JButton cancel_c = new JButton("선택취소");
		cancel_c.setBackground(new Color(130,194,245));
		cancel_c.setBounds(20, 348, 101, 33);
		contentPane.add(cancel_c);
		cancel_c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menutable.getSelectedRow()!=-1) {
					int nRow=menutable.getSelectedRow();
					int nColumn=menutable.getSelectedColumn();
					String cValue=(String) model.getValueAt(nRow, nColumn);
					model.removeRow(menutable.getSelectedRow());
					
					total_m-=Integer.parseInt(cValue);
					String y=Integer.toString(total_m);
					totalbl.setText(y);
					if(Integer.parseInt(cValue)>500) {
						n-=1;
					}
				}
			}
		});
		
		JButton cancel_a = new JButton("전체취소");
		cancel_a.setBackground(new Color(130,194,245));
		cancel_a.setBounds(140, 348, 101, 33);
		contentPane.add(cancel_a);
		cancel_a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				total_m=0;
				String y=Integer.toString(total_m);
				totalbl.setText(y);
			}
		});
		
		JButton pay = new JButton("결제");
		pay.setBounds(264, 348, 101, 33);
		pay.setBackground(new Color(130,194,245));
		contentPane.add(pay);
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay();
			}
		});
		
		JButton stamp = new JButton("\uC2A4\uD0EC\uD504\uC0AC\uC6A9");
		stamp.setBounds(20, 395, 101, 33);
		stamp.setBackground(new Color(130,194,245));
		contentPane.add(stamp);
		stamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stamp();
			}
		});

		totalbl.setFont(new Font("HY나무L", Font.BOLD, 20));
		totalbl.setBounds(106, 291, 241, 33);
		contentPane.add(totalbl);
		totalbl.setVisible(true);
		
		//오늘 날짜 및 시간 출력
		SimpleDateFormat formatt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar time=Calendar.getInstance();
		format_t=formatt.format(time.getTime());
		
		infoClock = new JLabel();
		infoClock.setFont(new Font("HY나무L", Font.PLAIN, 14));
		infoClock.setBounds(25, 10, 172, 24);
		infoClock.setText(format_t);
		contentPane.add(infoClock);
	}
}
