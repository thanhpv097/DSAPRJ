package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.SMController;
import model.SMModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SMView extends JFrame {
	public ControllerView contr;
	public JPanel contentPane;
	public SMModel model;
	public JTextField textField_SelectedProgramNameSearch;
	public JTable table;
	JTextField textField_ProgramCode;
	public JTextField textField_ProgramName;
	public JTextField textField_UniversityCode;
	public JTextField textField_AdmissionScore;
	public JTextField textField_Address;
	public JTextField textField_TuitionFee;
	public JTextField textField_UniversityName;
	public JTextField textField_SelectedSchoolNameSearch;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMView frame = new SMView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SMView() {
		this.model = new SMModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 683);
		
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(5, 5, 985, 27);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuFile);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuFile.add(menuOpen);
		
		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuFile.add(menuSave);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuFile.add(menuExit);
		
		JMenu menuAbout = new JMenu("About");
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuAbout);
		
		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuAbout.add(menuAboutMe);
		
		JLabel lable_TenTruong = new JLabel("Tên Trường");
		lable_TenTruong.setBounds(15, 62, 89, 34);
		lable_TenTruong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lable_TenNganhDaChon = new JLabel("Tên Ngành");
		lable_TenNganhDaChon.setBounds(379, 62, 89, 34);
		lable_TenNganhDaChon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_SelectedProgramNameSearch = new JTextField();
		textField_SelectedProgramNameSearch.setBounds(486, 62, 158, 35);
		textField_SelectedProgramNameSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_SelectedProgramNameSearch.setColumns(10);
		
		JButton button_TimKiem = new JButton("Tìm Kiếm");
		button_TimKiem.setBounds(668, 62, 118, 35);
		button_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.setVisible(true);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(5, 110, 985, 6);
		horizontalStrut.setBackground(Color.BLACK);
		
		JLabel lable_dsNganhHoc = new JLabel("Danh Sách Ngành Học");
		lable_dsNganhHoc.setBounds(15, 126, 169, 34);
		lable_dsNganhHoc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Ng\u00E0nh", "T\u00EAn Ng\u00E0nh", "\u0110i\u1EC3m chu\u1EA9n", "M\u00E3 Tr\u01B0\u1EDDng", "T\u00EAn Tr\u01B0\u1EDDng", "\u0110\u1ECBa Ch\u1EC9", "H\u1ECDc Ph\u00ED"
			}
		));
		table.setRowHeight(24);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 170, 985, 181);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 361, 985, 6);
		horizontalStrut_1.setBackground(Color.BLACK);
		
		JLabel lable_ThongTin = new JLabel("Thông Tin Ngành Học");
		lable_ThongTin.setBounds(15, 377, 169, 34);
		lable_ThongTin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lable_MaNganh_1 = new JLabel("Mã Ngành");
		lable_MaNganh_1.setBounds(25, 422, 95, 34);
		lable_MaNganh_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_ProgramCode = new JTextField();
		textField_ProgramCode.setBounds(149, 422, 232, 35);
		textField_ProgramCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_ProgramCode.setColumns(10);
		
		JLabel lable_TenNganh = new JLabel("Tên Ngành");
		lable_TenNganh.setBounds(25, 467, 95, 34);
		lable_TenNganh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_ProgramName = new JTextField();
		textField_ProgramName.setBounds(149, 466, 232, 35);
		textField_ProgramName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_ProgramName.setColumns(10);
		
		JLabel lable_MaTruong = new JLabel("Mã Trường");
		lable_MaTruong.setBounds(25, 518, 95, 34);
		lable_MaTruong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lable_TenTruong_1_1 = new JLabel("Tên Trường");
		lable_TenTruong_1_1.setBounds(25, 562, 95, 34);
		lable_TenTruong_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_UniversityCode = new JTextField();
		textField_UniversityCode.setBounds(149, 511, 232, 35);
		textField_UniversityCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_UniversityCode.setColumns(10);
		
		JLabel lable_DiemChuan = new JLabel("Điểm chuẩn");
		lable_DiemChuan.setBounds(486, 421, 95, 34);
		lable_DiemChuan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_AdmissionScore = new JTextField();
		textField_AdmissionScore.setBounds(609, 420, 232, 35);
		textField_AdmissionScore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_AdmissionScore.setColumns(10);
		
		JLabel lable_DiaChi = new JLabel("Địa Chỉ");
		lable_DiaChi.setBounds(486, 468, 95, 34);
		lable_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lable_HocPhi = new JLabel("Học Phí");
		lable_HocPhi.setBounds(486, 515, 95, 34);
		lable_HocPhi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_Address = new JTextField();
		textField_Address.setBounds(609, 466, 232, 35);
		textField_Address.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_Address.setColumns(10);
		
		textField_TuitionFee = new JTextField();
		textField_TuitionFee.setBounds(609, 511, 232, 35);
		textField_TuitionFee.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_TuitionFee.setColumns(10);
		
		JButton button_Xoa = new JButton("Xóa");
		button_Xoa.setBounds(114, 606, 118, 35);
		button_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton button_Sua = new JButton("Sửa");
		button_Sua.setBounds(347, 606, 118, 35);
		button_Sua.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton button_HuyBo = new JButton("Hủy Bỏ");
		button_HuyBo.setBounds(817, 608, 118, 35);
		button_HuyBo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton button_SapXepTheoDiem = new JButton("Sắp Xếp Theo Điểm");
		button_SapXepTheoDiem.setBounds(311, 126, 232, 35);
		
		button_SapXepTheoDiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton button_SapXepTheoHocPhi = new JButton("Sắp Xếp Theo Học Phí");
		button_SapXepTheoHocPhi.setBounds(651, 126, 239, 35);
		
		button_SapXepTheoHocPhi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton button_Luu = new JButton("Lưu");
		button_Luu.setBounds(591, 606, 118, 35);
	
		button_Luu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_UniversityName = new JTextField();
		textField_UniversityName.setBounds(149, 562, 232, 35);
		textField_UniversityName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_UniversityName.setColumns(10);
		
		JButton button_HuyTimKiem = new JButton("Quay về danh sách");
		button_HuyTimKiem.setBounds(796, 62, 184, 35);
		
		button_HuyTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField_SelectedSchoolNameSearch = new JTextField();
		textField_SelectedSchoolNameSearch.setBounds(126, 62, 219, 35);
		textField_SelectedSchoolNameSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_SelectedSchoolNameSearch.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(menuBar);
		contentPane.add(lable_TenTruong);
		contentPane.add(lable_TenNganhDaChon);
		contentPane.add(textField_SelectedProgramNameSearch);
		contentPane.add(button_TimKiem);
		contentPane.add(horizontalStrut);
		contentPane.add(lable_dsNganhHoc);
		contentPane.add(scrollPane);
		contentPane.add(horizontalStrut_1);
		contentPane.add(lable_ThongTin);
		contentPane.add(lable_MaNganh_1);
		contentPane.add(textField_ProgramCode);
		contentPane.add(lable_TenNganh);
		contentPane.add(textField_ProgramName);
		contentPane.add(lable_MaTruong);
		contentPane.add(lable_TenTruong_1_1);
		contentPane.add(textField_UniversityCode);
		contentPane.add(lable_DiemChuan);
		contentPane.add(textField_AdmissionScore);
		contentPane.add(lable_DiaChi);
		contentPane.add(lable_HocPhi);
		contentPane.add(textField_Address);
		contentPane.add(textField_TuitionFee);
		contentPane.add(button_Xoa);
		contentPane.add(button_Sua);
		contentPane.add(button_HuyBo);
		contentPane.add(button_SapXepTheoDiem);
		contentPane.add(button_SapXepTheoHocPhi);
		contentPane.add(button_Luu);
		contentPane.add(textField_UniversityName);
		contentPane.add(button_HuyTimKiem);
		contentPane.add(textField_SelectedSchoolNameSearch);
		
		
		contr = new ControllerView(this);
		Action action = new SMController(contr);
		
		menuOpen.addActionListener(action);
		menuSave.addActionListener(action);
		menuExit.addActionListener(action);
		menuAboutMe.addActionListener(action);
		button_TimKiem.addActionListener(action);
		button_Xoa.addActionListener(action);
		button_Sua.addActionListener(action);
		button_HuyBo.addActionListener(action);
		button_SapXepTheoDiem.addActionListener(action);
		button_SapXepTheoHocPhi.addActionListener(action);
		button_Luu.addActionListener(action);
		button_HuyTimKiem.addActionListener(action);
		button_HuyTimKiem.addActionListener(action);
	}
}