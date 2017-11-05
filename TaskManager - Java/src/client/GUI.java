package client;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import server.ChatMessage;
import server.MessageType;
import task.Task;
import task.TaskStatus;
import team.Account;
import team.Notification;
import team.Notification_type;
import team.Privacy;
import team.Team;
import team.TeamDatabase;
import team.TeamMember;
import technic.RegisteredUser;

public class GUI extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 7245564009336495566L;

	private TeamDatabase teamDatabaseInstance;
	private LocalTaskDatabase localTaskDatabaseInstance;
	private static RegisteredUser registeredUserInstance;
	private static GUI guiInstance = new GUI();
	private Client client;
	private CardLayout cardLayoutLogIn;
	private CardLayout cardLayoutStart;
	private CardLayout cardLayoutHelp;
	private JPanel cardPanel;
	private JPanel panel_LogIn = new JPanel();
	private JPanel panel_Register = new JPanel();
	private JPanel panel_PasswordLost = new JPanel();
	private JPasswordField txt_login_password;
	private JTextField txt_login_username;
	private JTextField txt_register_name;
	private JTextField txt_register_surname;
	private JTextField txt_register_country;
	private JTextField txt_register_username;
	private JTextField txt_register_email;
	private JTextField verify_name;
	private JTextField verify_username;
	private JTextField verify_email;
	private JTextField verify_password;
	private JTextField verify_password2;
	private JTextField verify_surname;
	private JTextField verify_country;
	private JTextArea notification_register = new JTextArea();
	private JPasswordField txt_register_password;
	private JPasswordField txt_register_password2;
	private JRadioButton rdbtn_accept_conditions;
	private JTextField txt_passwordLost_email;
	private JTextPane txt_login_message;
	private JButton btn_register_Register;
	private JLabel back_icon;
	private JButton btn_passwordLost_recover;
	private JTextPane txt_passwordLost_noemail;
	private JLabel icon_passwordLost_back;
	private JButton btn_logIn;
	private JButton btn_login_lostPassword;
	private JButton btn_login_registerNow;
	private JButton btn_start_search_group;
	private JButton btn_start_member_group;
	private JButton btn_start_admin_group;
	private JButton btn_start_create_group;
	private JButton btn_start_my_group_task;
	private JButton btn_start_my_local_task;
	private JLabel lbl_start_home;
	private JLabel lbl_start_settings;
	private JLabel lbl_start_log_out;
	private JLabel lbl_start_support;
	private JTextField txt_start_initial;
	private JTextField txt_start_logged_user;
	private JPanel start_cardLayoutPanel;
	private JPanel panel_search_group;
	private JTextField lbl_search_group;
	private JPanel panel_create_group;
	private JTextField lbl_create_group;
	private JPanel panel_member_group;
	private JTextField lbl_member_group;
	private JPanel panel_admin_groups;
	private JTextField lbl_admin_groups;
	private JPanel panel_local_tasks;
	private JTextField lbl_myTasks;
	private JPanel panel_group_tasks;
	private JTextField lbl_taskToDo;
	private JPanel panel_start;
	private String logged_in_user;
	private JPanel panel_settings;
	private JTextField lbl_settings;
	private JLabel label;
	private JLabel lblChangeYour;
	private JLabel lblOldPassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;
	private JLabel lblPrivacySettings;
	private JToggleButton tglbtnNewToggleButton;
	private JTextPane txtpnYouWillReceive;
	private JTextPane txtpnThisEmailAddress;
	private JTextPane txtpnDisableIfYou;
	private JTextField txt_search_field;
	private JTable table_search;
	private JButton btn_search_details;
	private JButton btn_search_SendMessage;
	private JButton btn_search_AskToJoin;
	private JTextField lbl_search_groupName;
	private JTextField lbl_search_nrmembers;
	private JTextField lbl_search_admin;
	private JTextField txt_create_groupName;
	private JRadioButton rdbtn_create_publicGroup;
	private JRadioButton rdbtn_create_privateGroup;
	private JButton btn_search_openGroup;
	private JTextPane txtpnTheListOf;
	private JScrollPane scrollPane_member;
	private JTable table_member;
	private JButton btn_member_groupDetails;
	private JButton btn_member_openGroup;
	private JButton btn_member_sendMessage;
	private JButton btn_member_leaveGroup;
	private JSeparator separator_1;
	private JButton btn_member_addTask;
	private JTextField txt_member_taskname;
	private JTextPane txtpnTaskName;
	private JTextPane txt_member_taskDescription;
	private JTextPane txtpnTaskDescription;
	private JTextPane txtpnTaskImportance;
	private JTextPane txtpnLowest;
	private JTextPane txtpnAddTask;
	private JLabel icon_member_add;
	private JTextPane msg_member_fillIn;
	private JTextField msg_member_taskName;
	private JTextField msg_member_taskDeadline;
	private JTextField msg_member_taskImportance;
	private JTextPane txtpnTheListOf_1;
	private JScrollPane scrollPane_admin_groups;
	private JTable table_admin_groups;
	private JTextPane txtpnTheListOf_2;
	private JScrollPane scrollPane_admin_members;
	private JTable table_admin_members;
	private JButton btn_admin_viewDetails;
	private JButton btn_admin_editInfo;
	private JButton btn_admin_openGroup;
	private JButton btn_admin_deleteGroup;
	private JButton btn_admin_removeMember;
	private JButton btn_admin_messageMember;
	private JButton btn_admin_inviteMember;
	private JTextField txt_admin_searchUsers;
	private JSeparator separator;
	private JScrollPane scrollPane_localTasks;
	private JTable table_local_tasks;
	private DefaultTableModel model_table_local_tasks;
	private DefaultTableModel model_table_my_groups;
	private DefaultTableModel model_table_my_adminGroups;
	private DefaultTableModel model_table_my_adminGroupMembers;
	private DefaultTableModel model_tabel_searchGroups;
	private DefaultTableModel model_table_invitations;
	private JButton btn_local_viewDetails;
	private JButton btn_local_quitTask;
	private JButton btn_local_requestTime;
	private JButton btn_local_submitTask;
	private JButton btn_local_addTask;
	private JTextField txt_local_taskName;
	private JLabel lblTaskName;
	private JLabel lblDueDate;
	private JTextPane txt_local_taskDescription;
	private JLabel lblTaskDescription;
	private JLabel lblTaskPriority;
	private JComboBox<String> cb_local_taskPriority;
	private JTextPane textPane_1;
	private JComboBox<String> cb_local_month;
	private JComboBox<String> cb_local_day;
	private JComboBox<String> cb_local_year;
	private JLabel label_4;
	private JComboBox<String> cb_member_day;
	private JComboBox<String> cb_member_month;
	private JComboBox<String> cb_member_year;
	private JComboBox<String> cb_member_taskImportance;
	private JTextPane txtpnPleaseFillIn;
	private JTable table_groupTask;
	private JButton btn_local_updateTask;
	private JTextPane txt_groupTask_comments;
	private JLabel msg_create_groupDescription;
	private JLabel msg_create_privacySettings;
	private JTextPane msg_create_fillIn;
	private JLabel msg_create_groupName;
	private JTextPane txt_create_groupDescription;
	private JButton btn_create_createGroup;
	private JTextArea verify_local_taskName;
	private JTextArea verify_local_dueDate;
	private JTextArea verify_local_taskPriority;
	private JTextArea verify_local_taskDescription;
	private JPanel panel_notification;
	private JButton btn_notification;
	private boolean bool_notification = false;
	private JTable table_notification;
	private JLabel btn_notification_accept;
	private JLabel btn_notification_reject;
	private JButton btn_search_search;
	private JTextPane txt_notification_messages;
	private Document notification_document;
	private String userSentTo;
	private JPanel panel_group_detail;
	private JTextField txt_group_groupName;
	private JScrollPane scrollPane_2;
	private JTable table_group;
	private JTextField txt_group_theGroupSelected;
	private JTextField txtId;
	private JTextPane txt_group_comments;
	private JScrollPane scrollPane_3;
	private JLabel label_5;
	private JTextField txt_group_taskName;
	private JLabel lblTaskDescription_1;
	private JScrollPane scrollPane_4;
	private JTextPane txt_group_taskDescription;
	private JPanel panel_addTask;
	private JButton btn_group_addTask;
	private JLabel label_7;
	private JComboBox<String> cb_group_priority;
	private JTextPane textPane_3;
	private DefaultTableModel model_group_table;
	private JButton btn_group_addComment;
	private JTextPane txt_group_addComment;
	private JComboBox<String> cb_group_day;
	private JComboBox<String> cb_group_month;
	private JComboBox<String> cb_group_year;
	private JButton btn_group_take;
	private JButton btn_group_update;
	private JButton btn_group_quit;
	private JButton btn_group_reschedule;
	private JButton btn_group_submit;
	private JButton btn_group_details;
	private JTextField taskDetail_group;
	private JTextField taskDetail_name;
	private JTextField taskDetail_author;
	private JTextField taskDetail_workperson;
	private JTextField taskDetail_datecreated;
	private JTextField taskDetail_priority;
	private JTextField taskDetail_status;
	private JTextField taskDetail_deadline;
	private JTextPane taskDetail_description;
	private JButton taskDetail_btn;
	private JPanel panel_taskdetails;
	private JPanel panel_detgrup;
	private JLabel lblGroupDetails;
	private JLabel lblTeamName;
	private JLabel lblTeamDescription;
	private JLabel lblTeamAdmin;
	private JLabel lblNrMembers;
	private JLabel lblTeamId;
	private JLabel lblTeamPrivacy;
	private JTextField detgroup_name;
	private JTextField detgroup_admin;
	private JTextField detgroup_privacy;
	private JTextField detgroup_id;
	private JTextField detgroup_nrmembers;
	private JScrollPane scrollPane_6;
	private JButton detgroup_btn;
	private JTextPane detgroup_description;
	private JButton btn_admin_makeAdmin;
	private JPanel panel_editgroup;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_14;
	private JTextPane edit_description;
	private JScrollPane scrollPane_7;
	private JButton edit_btn_close;
	private JLabel label_13;
	private JTextField edit_teamid;
	private JButton edit_btn_name;
	private JButton edit_btn_description;
	private JButton edit_btn_privacy;
	private JRadioButton edit_public;
	private JRadioButton edit_private;
	private JTextField edit_name;
	private DefaultTableModel model_fill_my_group_task_panel;
	private JButton btn_groupTask_details;
	private JButton btn_groupTask_submit;
	private JButton btn_groupTask_update;
	private JButton btn_groupTask_reschedule;
	private JButton btn_groupTask_quit;
	private JScrollPane scrollPane_8;
	private JTextField txt_groupTask_addComment;
	private JButton btn_groupTask_comment;
	private JTextField txt_gt_name;
	private JScrollPane scrollPane_9;
	private JTextPane txt_gt_description;
	private JLabel lblTaskName_2;
	private JLabel lblTaskDescription_3;
	private JLabel lblTaskPriority_1;
	private JComboBox<String> txt_cb_priority;

	private DefaultTableModel model_table_groupTask;
	private JPanel panel;
	private JLabel lblHelp;
	private JLabel lblE;
	private JLabel lblL;
	private JLabel lblP;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel label_11;
	private JPanel panel_4;
	private JLabel label_12;
	private JPanel panel_5;
	private JLabel label_15;
	private JPanel panel_6;
	private JLabel label_16;
	private JPanel panel_7;
	private JLabel label_17;
	private JPanel panel_8;
	private JLabel label_18;
	private JLabel help;
	private JButton close;

	private JPanel helpCard;

	/**
	 * Launch the application.
	 */

	public void draw() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					guiInstance.setVisible(true);
					startGui();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public GUI() {
		super("chat client");
	}

	public void startGui() {
		setResizable(false);

		this.client = Client.getInstance();

		setMinimumSize(new Dimension(700, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 560);
		cardPanel = new JPanel();
		cardPanel.setBackground(new Color(102, 0, 51));
		cardPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		cardLayoutLogIn = (CardLayout) (cardPanel.getLayout());

		create_panel_LogIn();
		create_panel_register();
		create_panel_passwordLost();
		create_panel_start();
		create_panel_help();
	}

	/**
	 * GUI method creating LOGIN panel
	 */
	private void create_panel_LogIn() {
		panel_LogIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_LogIn.setAutoscrolls(true);
		panel_LogIn.setForeground(new Color(0, 0, 0));
		cardPanel.add(panel_LogIn, "LOG_IN_PANEL");
		panel_LogIn.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_LogIn.setBackground(new Color(204, 204, 204));

		JLabel lbl_login_title = new JLabel("TeamUP");
		lbl_login_title.setBounds(328, 78, 308, 44);
		lbl_login_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login_title.setForeground(new Color(51, 51, 51));
		lbl_login_title.setFont(new Font("Myriad Pro", Font.BOLD, 53));

		txt_login_password = new JPasswordField();
		txt_login_password.setBounds(342, 219, 255, 20);

		JLabel lbl_login_logo = new JLabel("");
		lbl_login_logo.setBounds(252, 49, 100, 100);
		lbl_login_logo.setAlignmentX(5.0f);
		lbl_login_logo.setIcon(new ImageIcon("img/logo_se.png"));

		txt_login_username = new JTextField();
		txt_login_username.setBounds(342, 188, 255, 20);
		txt_login_username.setColumns(10);

		JLabel lbl_login_username = new JLabel("username");
		lbl_login_username.setBounds(215, 188, 117, 20);
		lbl_login_username.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_login_username.setForeground(new Color(51, 51, 51));
		lbl_login_username.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lbl_login_password = new JLabel("password");
		lbl_login_password.setBounds(215, 222, 117, 20);
		lbl_login_password.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_login_password.setForeground(new Color(51, 51, 51));
		lbl_login_password.setFont(new Font("Tahoma", Font.BOLD, 12));

		btn_logIn = new JButton("Log in");
		btn_logIn.setFocusPainted(false);
		btn_logIn.setBorderPainted(false);
		btn_logIn.setBorder(null);
		btn_logIn.addActionListener(this);
		btn_logIn.setBounds(508, 251, 89, 23);
		btn_logIn.setForeground(new Color(255, 255, 255));
		btn_logIn.setFont(new Font("Myriad Pro", Font.BOLD, 12));
		btn_logIn.setBackground(new Color(0, 51, 153));

		JLabel lbl_login_lostPassword = new JLabel("Lost your password?");
		lbl_login_lostPassword.setBounds(222, 329, 191, 20);
		lbl_login_lostPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_login_lostPassword.setForeground(new Color(51, 51, 51));
		lbl_login_lostPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btn_login_lostPassword = new JButton("Click here");
		btn_login_lostPassword.setBounds(428, 329, 135, 23);
		btn_login_lostPassword.addActionListener(this);
		btn_login_lostPassword.setMargin(new Insets(0, 0, 2, 14));
		btn_login_lostPassword.setHorizontalAlignment(SwingConstants.LEFT);
		btn_login_lostPassword.setForeground(new Color(51, 51, 51));
		btn_login_lostPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_login_lostPassword.setContentAreaFilled(false);
		btn_login_lostPassword.setBorder(null);

		JLabel lbl_login_registerNow = new JLabel("You don't have an account?");
		lbl_login_registerNow.setBounds(183, 361, 230, 20);
		lbl_login_registerNow.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_login_registerNow.setForeground(new Color(51, 51, 51));
		lbl_login_registerNow.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btn_login_registerNow = new JButton("Register now");
		btn_login_registerNow.setBounds(428, 361, 135, 23);
		btn_login_registerNow.addActionListener(this);
		btn_login_registerNow.setMargin(new Insets(0, 0, 2, 14));
		btn_login_registerNow.setHorizontalAlignment(SwingConstants.LEFT);
		btn_login_registerNow.setForeground(new Color(51, 51, 51));
		btn_login_registerNow.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_login_registerNow.setContentAreaFilled(false);
		btn_login_registerNow.setBorder(null);

		panel_LogIn.setLayout(null);
		panel_LogIn.add(lbl_login_logo);
		panel_LogIn.add(lbl_login_title);
		panel_LogIn.add(lbl_login_username);
		panel_LogIn.add(txt_login_username);
		panel_LogIn.add(lbl_login_password);
		panel_LogIn.add(txt_login_password);
		panel_LogIn.add(btn_logIn);
		panel_LogIn.add(lbl_login_lostPassword);
		panel_LogIn.add(btn_login_lostPassword);
		panel_LogIn.add(lbl_login_registerNow);
		panel_LogIn.add(btn_login_registerNow);

		txt_login_message = new JTextPane();
		txt_login_message.setFocusable(false);
		txt_login_message.setFocusTraversalKeysEnabled(false);
		txt_login_message.setFocusCycleRoot(false);
		txt_login_message.setEditable(false);
		txt_login_message.setForeground(new Color(204, 0, 51));
		txt_login_message.setBackground(new Color(204, 204, 204));
		txt_login_message.setBounds(262, 254, 236, 20);
		txt_login_message.setVisible(false);
		panel_LogIn.add(txt_login_message);
		panel_Register.setBackground(new Color(204, 204, 204));
	}

	/**
	 * GUI method creating REGISTER panel
	 */
	private void create_panel_register() {
		// TODO Auto-generated method stub
		cardPanel.add(panel_Register, "REGISTER_PANEL");
		panel_Register.setLayout(null);

		JLabel lbl_register_icon = new JLabel("");
		lbl_register_icon.setIcon(new ImageIcon("img/logo_se.png"));
		lbl_register_icon.setBounds(268, 12, 100, 100);
		panel_Register.add(lbl_register_icon);

		JLabel lbl_register_title = new JLabel("TeamUP");
		lbl_register_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_register_title.setForeground(new Color(51, 51, 51));
		lbl_register_title.setFont(new Font("Myriad Pro", Font.BOLD, 53));
		lbl_register_title.setBounds(347, 40, 308, 44);
		panel_Register.add(lbl_register_title);

		txt_register_name = new JTextField();
		txt_register_name.setBounds(220, 159, 147, 20);
		panel_Register.add(txt_register_name);
		txt_register_name.setColumns(10);

		txt_register_surname = new JTextField();
		txt_register_surname.setColumns(10);
		txt_register_surname.setBounds(547, 159, 147, 20);
		panel_Register.add(txt_register_surname);

		txt_register_country = new JTextField();
		txt_register_country.setColumns(10);
		txt_register_country.setBounds(547, 190, 147, 20);
		panel_Register.add(txt_register_country);

		txt_register_username = new JTextField();
		txt_register_username.setColumns(10);
		txt_register_username.setBounds(220, 190, 147, 20);
		panel_Register.add(txt_register_username);

		txt_register_email = new JTextField();
		txt_register_email.setColumns(10);
		txt_register_email.setBounds(220, 221, 194, 20);
		panel_Register.add(txt_register_email);

		JLabel lbl_register_name = new JLabel("name");
		lbl_register_name.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_name.setForeground(new Color(51, 51, 51));
		lbl_register_name.setBounds(95, 162, 118, 14);
		panel_Register.add(lbl_register_name);

		JLabel lbl_register_surname = new JLabel("surname");
		lbl_register_surname.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_surname.setForeground(new Color(51, 51, 51));
		lbl_register_surname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_surname.setBounds(447, 162, 90, 14);
		panel_Register.add(lbl_register_surname);

		JLabel lbl_register_Username = new JLabel("username");
		lbl_register_Username.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_Username.setForeground(new Color(51, 51, 51));
		lbl_register_Username.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_Username.setBounds(95, 196, 118, 14);
		panel_Register.add(lbl_register_Username);

		JLabel lbl_register_country = new JLabel("country");
		lbl_register_country.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_country.setForeground(new Color(51, 51, 51));
		lbl_register_country.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_country.setBounds(447, 196, 90, 14);
		panel_Register.add(lbl_register_country);

		JLabel lbl_register_Email = new JLabel("e-mail");
		lbl_register_Email.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_Email.setForeground(new Color(51, 51, 51));
		lbl_register_Email.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_Email.setBounds(95, 227, 118, 14);
		panel_Register.add(lbl_register_Email);

		JLabel lbl_register_Password = new JLabel("password");
		lbl_register_Password.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_Password.setForeground(new Color(51, 51, 51));
		lbl_register_Password.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_Password.setBounds(92, 280, 118, 14);
		panel_Register.add(lbl_register_Password);

		JLabel lbl_register_password2 = new JLabel("repeat password");
		lbl_register_password2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_register_password2.setForeground(new Color(51, 51, 51));
		lbl_register_password2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_register_password2.setBounds(415, 280, 122, 14);
		panel_Register.add(lbl_register_password2);

		JTextPane txtpnSugest = new JTextPane();
		txtpnSugest.setFocusable(false);
		txtpnSugest.setFocusTraversalKeysEnabled(false);
		txtpnSugest.setFocusCycleRoot(false);
		txtpnSugest.setEditable(false);
		txtpnSugest.setFont(new Font("Tahoma", Font.ITALIC, 9));
		txtpnSugest.setForeground(new Color(51, 51, 51));
		txtpnSugest.setBackground(new Color(204, 204, 204));
		txtpnSugest.setText(
				"* Please choose your password carefully. We suggest you to include capital letters, numbers and symbols too. ");
		txtpnSugest.setBounds(220, 305, 474, 44);
		panel_Register.add(txtpnSugest);

		rdbtn_accept_conditions = new JRadioButton("I accept the terms and conditions of using this application");
		rdbtn_accept_conditions.setFocusable(false);
		rdbtn_accept_conditions.setFocusPainted(false);
		rdbtn_accept_conditions.setContentAreaFilled(false);
		rdbtn_accept_conditions.setFont(new Font("Tahoma", Font.ITALIC, 10));
		rdbtn_accept_conditions.setForeground(new Color(51, 51, 51));
		rdbtn_accept_conditions.setBackground(new Color(0, 51, 51));
		rdbtn_accept_conditions.setBounds(188, 382, 334, 23);
		panel_Register.add(rdbtn_accept_conditions);
		notification_register.setEditable(false);
		notification_register.setFocusTraversalKeysEnabled(false);
		notification_register.setFocusable(false);
		notification_register.setWrapStyleWord(true);
		notification_register.setLineWrap(true);

		notification_register.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		notification_register.setForeground(new Color(255, 0, 0));
		notification_register.setBackground(new Color(204, 204, 204));
		notification_register.setBounds(220, 421, 474, 55);
		panel_Register.add(notification_register);

		/*
		 * THESE ARE THE ASTERISKS
		 */
		verify_name = new JTextField();
		verify_name.setFocusable(false);
		verify_name.setEditable(false);
		verify_name.setVisible(false);
		verify_name.setHorizontalAlignment(SwingConstants.CENTER);
		verify_name.setBorder(null);
		verify_name.setText("*");
		verify_name.setForeground(new Color(255, 51, 0));
		verify_name.setBackground(new Color(204, 204, 204));
		verify_name.setBounds(366, 159, 16, 20);
		panel_Register.add(verify_name);
		verify_name.setColumns(10);

		verify_username = new JTextField();
		verify_username.setFocusable(false);
		verify_username.setEditable(false);
		verify_username.setVisible(false);
		verify_username.setText("*");
		verify_username.setHorizontalAlignment(SwingConstants.CENTER);
		verify_username.setForeground(new Color(255, 51, 0));
		verify_username.setColumns(10);
		verify_username.setBorder(null);
		verify_username.setBackground(new Color(204, 204, 204));
		verify_username.setBounds(366, 190, 16, 20);
		panel_Register.add(verify_username);

		verify_email = new JTextField();
		verify_email.setFocusable(false);
		verify_email.setEditable(false);
		verify_email.setVisible(false);
		verify_email.setText("*");
		verify_email.setHorizontalAlignment(SwingConstants.CENTER);
		verify_email.setForeground(new Color(255, 51, 0));
		verify_email.setColumns(10);
		verify_email.setBorder(null);
		verify_email.setBackground(new Color(204, 204, 204));
		verify_email.setBounds(414, 221, 16, 20);
		panel_Register.add(verify_email);

		verify_password = new JTextField();
		verify_password.setFocusable(false);
		verify_password.setEditable(false);
		verify_password.setVisible(false);
		verify_password.setText("*");
		verify_password.setHorizontalAlignment(SwingConstants.CENTER);
		verify_password.setForeground(new Color(255, 51, 0));
		verify_password.setColumns(10);
		verify_password.setBorder(null);
		verify_password.setBackground(new Color(204, 204, 204));
		verify_password.setBounds(366, 274, 16, 20);
		panel_Register.add(verify_password);

		verify_password2 = new JTextField();
		verify_password2.setFocusable(false);
		verify_password2.setEditable(false);
		verify_password2.setVisible(false);
		verify_password2.setText("*");
		verify_password2.setHorizontalAlignment(SwingConstants.CENTER);
		verify_password2.setForeground(new Color(255, 51, 0));
		verify_password2.setColumns(10);
		verify_password2.setBorder(null);
		verify_password2.setBackground(new Color(204, 204, 204));
		verify_password2.setBounds(694, 274, 16, 20);
		panel_Register.add(verify_password2);

		verify_surname = new JTextField();
		verify_surname.setFocusable(false);
		verify_surname.setEditable(false);
		verify_surname.setVisible(false);
		verify_surname.setText("*");
		verify_surname.setHorizontalAlignment(SwingConstants.CENTER);
		verify_surname.setForeground(new Color(255, 51, 0));
		verify_surname.setColumns(10);
		verify_surname.setBorder(null);
		verify_surname.setBackground(new Color(204, 204, 204));
		verify_surname.setBounds(694, 159, 16, 20);
		panel_Register.add(verify_surname);

		verify_country = new JTextField();
		verify_country.setFocusable(false);
		verify_country.setEditable(false);
		verify_country.setVisible(false);
		verify_country.setText("*");
		verify_country.setHorizontalAlignment(SwingConstants.CENTER);
		verify_country.setForeground(new Color(255, 51, 0));
		verify_country.setColumns(10);
		verify_country.setBorder(null);
		verify_country.setBackground(new Color(204, 204, 204));
		verify_country.setBounds(694, 190, 16, 20);
		panel_Register.add(verify_country);
		/*
		 * END ASTERISKS
		 */

		btn_register_Register = new JButton("Register");
		btn_register_Register.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn_register_Register.setFocusTraversalKeysEnabled(false);
		btn_register_Register.setFocusPainted(false);
		btn_register_Register.setForeground(new Color(255, 255, 255));
		btn_register_Register.addActionListener(this);
		btn_register_Register.setBackground(new Color(0, 51, 153));
		btn_register_Register.setBounds(547, 382, 124, 23);
		panel_Register.add(btn_register_Register);

		txt_register_password = new JPasswordField();
		txt_register_password.setBounds(220, 274, 147, 20);
		panel_Register.add(txt_register_password);

		txt_register_password2 = new JPasswordField();
		txt_register_password2.setBounds(547, 274, 147, 20);
		panel_Register.add(txt_register_password2);

		back_icon = new JLabel("");
		back_icon.addMouseListener(this);
		back_icon.setHorizontalAlignment(SwingConstants.CENTER);
		back_icon.setBounds(12, 490, 29, 31);
		back_icon.setIcon(new ImageIcon("img/back_icon.png"));
		panel_Register.add(back_icon);

	}

	/**
	 * GUI method creating PASSLOST panel
	 */
	private void create_panel_passwordLost() {

		panel_PasswordLost.setBackground(new Color(204, 204, 204));
		cardPanel.add(panel_PasswordLost, "PASSWORD_LOST_PANEL");
		panel_PasswordLost.setLayout(null);

		txt_passwordLost_email = new JTextField();
		txt_passwordLost_email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_passwordLost_email.setBounds(231, 214, 386, 30);
		panel_PasswordLost.add(txt_passwordLost_email);
		txt_passwordLost_email.setColumns(10);

		JTextPane txtpnFillInYour = new JTextPane();
		txtpnFillInYour.setDisabledTextColor(new Color(51, 51, 51));
		txtpnFillInYour.setFocusable(false);
		txtpnFillInYour.setFocusTraversalKeysEnabled(false);
		txtpnFillInYour.setFocusCycleRoot(false);
		txtpnFillInYour.setEditable(false);
		txtpnFillInYour.setForeground(new Color(51, 51, 51));
		txtpnFillInYour.setBackground(new Color(204, 204, 204));
		txtpnFillInYour.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnFillInYour.setText(
				"Fill in your E-mail address. \r\nWe will send the a mail with the password of your account. ");
		txtpnFillInYour.setBounds(231, 151, 386, 62);
		panel_PasswordLost.add(txtpnFillInYour);

		txt_passwordLost_noemail = new JTextPane();
		txt_passwordLost_noemail.setVisible(false);
		txt_passwordLost_noemail.setFocusable(false);
		txt_passwordLost_noemail.setFocusTraversalKeysEnabled(false);
		txt_passwordLost_noemail.setFocusCycleRoot(false);
		txt_passwordLost_noemail.setEditable(false);
		txt_passwordLost_noemail.setText("There is no account linked to this E-mail.");
		txt_passwordLost_noemail.setForeground(new Color(255, 0, 51));
		txt_passwordLost_noemail.setBackground(new Color(204, 204, 204));
		txt_passwordLost_noemail.setBounds(231, 246, 386, 20);
		panel_PasswordLost.add(txt_passwordLost_noemail);

		btn_passwordLost_recover = new JButton("Recover");
		btn_passwordLost_recover.setFocusable(false);
		btn_passwordLost_recover.setFocusTraversalKeysEnabled(false);
		btn_passwordLost_recover.setFocusPainted(false);
		btn_passwordLost_recover.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn_passwordLost_recover.setBackground(new Color(0, 51, 153));
		btn_passwordLost_recover.setForeground(new Color(255, 255, 255));
		btn_passwordLost_recover.addActionListener(this);
		btn_passwordLost_recover.setBounds(521, 293, 96, 23);
		panel_PasswordLost.add(btn_passwordLost_recover);

		icon_passwordLost_back = new JLabel("");
		icon_passwordLost_back.addMouseListener(this);
		icon_passwordLost_back.setIcon(new ImageIcon("img/back_icon.png"));
		icon_passwordLost_back.setHorizontalAlignment(SwingConstants.CENTER);
		icon_passwordLost_back.setBounds(12, 490, 29, 31);
		panel_PasswordLost.add(icon_passwordLost_back);

	}

	private void create_panel_help() {
		panel = new JPanel();
		panel.setBackground(new Color(0, 51, 102));
		panel.setBorder(null);
		cardPanel.add(panel, "HELP");
		panel.setLayout(null);

		helpCard = new JPanel();
		helpCard.setBounds(52, 37, 740, 446);
		panel.add(helpCard);
		helpCard.setLayout(new CardLayout(0, 0));

		cardLayoutHelp = (CardLayout) (helpCard.getLayout());

		panel_2 = new JPanel();
		helpCard.add(panel_2, "H_SEARCH");
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(-25, 0, 782, 446);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\Search_g.jpg"));

		panel_3 = new JPanel();
		panel_3.setLayout(null);
		helpCard.add(panel_3, "H_CREATE");

		label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\create_g.jpg"));
		label_11.setHorizontalTextPosition(SwingConstants.CENTER);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(0, 0, 782, 446);
		panel_3.add(label_11);

		panel_4 = new JPanel();
		panel_4.setLayout(null);
		helpCard.add(panel_4, "H_MEMBER");

		label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\g_member.jpg"));
		label_12.setHorizontalTextPosition(SwingConstants.CENTER);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(0, 0, 740, 435);
		panel_4.add(label_12);

		panel_5 = new JPanel();
		panel_5.setLayout(null);
		helpCard.add(panel_5, "H_ADMIN");

		label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\g_admin.jpg"));
		label_15.setHorizontalTextPosition(SwingConstants.CENTER);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(0, 0, 740, 435);
		panel_5.add(label_15);

		panel_6 = new JPanel();
		panel_6.setLayout(null);
		helpCard.add(panel_6, "H_LOCAL");

		label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\g_local.jpg"));
		label_16.setHorizontalTextPosition(SwingConstants.CENTER);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(0, 0, 740, 446);
		panel_6.add(label_16);

		panel_7 = new JPanel();
		panel_7.setLayout(null);
		helpCard.add(panel_7, "H_GROUPT");

		label_17 = new JLabel("");
		label_17.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\g_groupt.jpg"));
		label_17.setHorizontalTextPosition(SwingConstants.CENTER);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(0, 0, 740, 446);
		panel_7.add(label_17);

		panel_8 = new JPanel();
		panel_8.setLayout(null);
		helpCard.add(panel_8, "H_TASKS");

		label_18 = new JLabel("");
		label_18.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\g_tasks.jpg"));
		label_18.setHorizontalTextPosition(SwingConstants.CENTER);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(0, 0, 740, 446);
		panel_8.add(label_18);

		lblHelp = new JLabel("H");
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHelp.setForeground(new Color(255, 255, 255));
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblHelp.setBounds(8, 37, 34, 47);
		panel.add(lblHelp);

		lblE = new JLabel("E");
		lblE.setHorizontalAlignment(SwingConstants.CENTER);
		lblE.setHorizontalTextPosition(SwingConstants.CENTER);
		lblE.setForeground(Color.WHITE);
		lblE.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblE.setBounds(8, 82, 34, 47);
		panel.add(lblE);

		lblL = new JLabel("L");
		lblL.setHorizontalAlignment(SwingConstants.CENTER);
		lblL.setHorizontalTextPosition(SwingConstants.CENTER);
		lblL.setForeground(Color.WHITE);
		lblL.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblL.setBounds(8, 126, 34, 47);
		panel.add(lblL);

		lblP = new JLabel("P");
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setHorizontalTextPosition(SwingConstants.CENTER);
		lblP.setForeground(Color.WHITE);
		lblP.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblP.setBounds(8, 170, 34, 47);
		panel.add(lblP);

		close = new JButton("CLOSE");
		close.setBorder(null);
		close.setFocusPainted(false);
		close.setFocusTraversalKeysEnabled(false);
		close.setFocusable(false);
		close.setBackground(new Color(255, 255, 255));
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setForeground(new Color(0, 51, 102));
		close.setFont(new Font("Tahoma", Font.BOLD, 20));
		close.setBounds(755, 491, 89, 30);
		close.addActionListener(this);
		panel.add(close);
	}

	/**
	 * 
	 * Create the start panel.
	 */
	private void create_panel_start() {
		panel_start = new JPanel();
		panel_start.setFocusTraversalKeysEnabled(false);
		panel_start.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_start.setBackground(new Color(204, 204, 204));
		cardPanel.add(panel_start, "START");
		panel_start.setLayout(null);

		panel_taskdetails = new JPanel();
		panel_taskdetails.setVisible(false);

		panel_detgrup = new JPanel();
		panel_detgrup.setVisible(false);
		panel_detgrup.addMouseListener(this);

		panel_editgroup = new JPanel();
		panel_editgroup.setVisible(false);
		panel_editgroup.addMouseListener(this);
		panel_editgroup.setBackground(new Color(0, 153, 255));
		panel_editgroup.setBounds(178, 144, 488, 233);
		panel_start.add(panel_editgroup);
		panel_editgroup.setLayout(null);

		label_8 = new JLabel("Group details");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_8.setFocusable(false);
		label_8.setFocusTraversalKeysEnabled(false);
		label_8.setBounds(10, 11, 108, 23);
		panel_editgroup.add(label_8);

		label_9 = new JLabel("Team name");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_9.setFocusable(false);
		label_9.setFocusTraversalKeysEnabled(false);
		label_9.setBounds(20, 35, 98, 23);
		panel_editgroup.add(label_9);

		label_10 = new JLabel("Team description");
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_10.setFocusable(false);
		label_10.setFocusTraversalKeysEnabled(false);
		label_10.setBounds(20, 82, 98, 23);
		panel_editgroup.add(label_10);

		label_14 = new JLabel("Team privacy");
		label_14.setHorizontalAlignment(SwingConstants.LEFT);
		label_14.setForeground(Color.WHITE);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_14.setFocusable(false);
		label_14.setFocusTraversalKeysEnabled(false);
		label_14.setBounds(20, 158, 90, 23);
		panel_editgroup.add(label_14);

		edit_name = new JTextField();
		edit_name.setFocusTraversalPolicyProvider(true);
		edit_name.setFocusCycleRoot(true);
		edit_name.setText("");
		edit_name.setForeground(Color.BLACK);
		edit_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit_name.setColumns(10);
		edit_name.setBorder(null);
		edit_name.setBackground(new Color(255, 255, 255));
		edit_name.setBounds(128, 37, 250, 30);
		panel_editgroup.add(edit_name);

		scrollPane_7 = new JScrollPane();
		scrollPane_7.setBorder(null);
		scrollPane_7.setBackground(new Color(0, 153, 204));
		scrollPane_7.setBounds(128, 82, 250, 72);
		panel_editgroup.add(scrollPane_7);

		edit_description = new JTextPane();
		edit_description.setLocation(138, 0);
		scrollPane_7.setViewportView(edit_description);

		edit_btn_close = new JButton("Close");
		edit_btn_close.setIcon(new ImageIcon("img/cancel_bl.png"));
		edit_btn_close.setForeground(Color.BLACK);
		edit_btn_close.setFont(new Font("Tahoma", Font.BOLD, 13));
		edit_btn_close.setFocusable(false);
		edit_btn_close.setFocusTraversalKeysEnabled(false);
		edit_btn_close.setFocusPainted(false);
		edit_btn_close.setBackground(Color.WHITE);
		edit_btn_close.setBounds(357, 190, 121, 30);
		edit_btn_close.addActionListener(this);
		panel_editgroup.add(edit_btn_close);

		label_13 = new JLabel("Team ID");
		label_13.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_13.setFocusable(false);
		label_13.setFocusTraversalKeysEnabled(false);
		label_13.setBounds(295, 11, 83, 23);
		panel_editgroup.add(label_13);

		edit_teamid = new JTextField();
		edit_teamid.setText("no info");
		edit_teamid.setForeground(Color.WHITE);
		edit_teamid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit_teamid.setFocusable(false);
		edit_teamid.setFocusTraversalKeysEnabled(false);
		edit_teamid.setEditable(false);
		edit_teamid.setColumns(10);
		edit_teamid.setBorder(null);
		edit_teamid.setBackground(new Color(0, 153, 255));
		edit_teamid.setBounds(388, 13, 90, 20);
		panel_editgroup.add(edit_teamid);

		edit_btn_name = new JButton("Change");
		edit_btn_name.setForeground(Color.BLACK);
		edit_btn_name.setFont(new Font("Tahoma", Font.BOLD, 13));
		edit_btn_name.setFocusable(false);
		edit_btn_name.setFocusTraversalKeysEnabled(false);
		edit_btn_name.setFocusPainted(false);
		edit_btn_name.setBackground(Color.WHITE);
		edit_btn_name.setBounds(388, 37, 83, 30);
		edit_btn_name.addActionListener(this);
		panel_editgroup.add(edit_btn_name);

		edit_btn_description = new JButton("Change");
		edit_btn_description.setForeground(Color.BLACK);
		edit_btn_description.setFont(new Font("Tahoma", Font.BOLD, 13));
		edit_btn_description.setFocusable(false);
		edit_btn_description.setFocusTraversalKeysEnabled(false);
		edit_btn_description.setFocusPainted(false);
		edit_btn_description.setBackground(Color.WHITE);
		edit_btn_description.setBounds(388, 124, 83, 30);
		edit_btn_description.addActionListener(this);
		panel_editgroup.add(edit_btn_description);

		edit_btn_privacy = new JButton("Change");
		edit_btn_privacy.setForeground(Color.BLACK);
		edit_btn_privacy.setFont(new Font("Tahoma", Font.BOLD, 13));
		edit_btn_privacy.setFocusable(false);
		edit_btn_privacy.setFocusTraversalKeysEnabled(false);
		edit_btn_privacy.setFocusPainted(false);
		edit_btn_privacy.setBackground(Color.WHITE);
		edit_btn_privacy.setBounds(20, 185, 83, 30);
		edit_btn_privacy.addActionListener(this);
		panel_editgroup.add(edit_btn_privacy);

		edit_public = new JRadioButton("Public group");
		edit_public.setBackground(new Color(0, 153, 255));
		edit_public.setFont(new Font("Tahoma", Font.BOLD, 12));
		edit_public.setFocusable(false);
		edit_public.setFocusPainted(false);
		edit_public.setBounds(122, 161, 147, 23);
		edit_public.addActionListener(this);
		panel_editgroup.add(edit_public);

		edit_private = new JRadioButton("Private group");
		edit_private.setBackground(new Color(0, 153, 255));
		edit_private.setFont(new Font("Tahoma", Font.BOLD, 12));
		edit_private.setFocusable(false);
		edit_private.setFocusPainted(false);
		edit_private.addActionListener(this);
		edit_private.setBounds(122, 190, 136, 23);
		panel_editgroup.add(edit_private);
		panel_detgrup.setBackground(new Color(0, 153, 204));
		panel_detgrup.setBounds(178, 144, 488, 233);
		panel_start.add(panel_detgrup);
		panel_detgrup.setLayout(null);

		lblGroupDetails = new JLabel("Group details");
		lblGroupDetails.setForeground(Color.WHITE);
		lblGroupDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGroupDetails.setFocusable(false);
		lblGroupDetails.setFocusTraversalKeysEnabled(false);
		lblGroupDetails.setBounds(10, 11, 108, 23);
		panel_detgrup.add(lblGroupDetails);

		lblTeamName = new JLabel("Team name");
		lblTeamName.setForeground(Color.WHITE);
		lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeamName.setFocusable(false);
		lblTeamName.setFocusTraversalKeysEnabled(false);
		lblTeamName.setBounds(20, 35, 98, 23);
		panel_detgrup.add(lblTeamName);

		lblTeamDescription = new JLabel("Team description");
		lblTeamDescription.setForeground(Color.WHITE);
		lblTeamDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeamDescription.setFocusable(false);
		lblTeamDescription.setFocusTraversalKeysEnabled(false);
		lblTeamDescription.setBounds(20, 58, 98, 23);
		panel_detgrup.add(lblTeamDescription);

		lblTeamAdmin = new JLabel("Team admin");
		lblTeamAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeamAdmin.setForeground(Color.WHITE);
		lblTeamAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeamAdmin.setFocusable(false);
		lblTeamAdmin.setFocusTraversalKeysEnabled(false);
		lblTeamAdmin.setBounds(10, 109, 90, 23);
		panel_detgrup.add(lblTeamAdmin);

		lblNrMembers = new JLabel("Nr. members");
		lblNrMembers.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNrMembers.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNrMembers.setForeground(Color.WHITE);
		lblNrMembers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNrMembers.setFocusable(false);
		lblNrMembers.setFocusTraversalKeysEnabled(false);
		lblNrMembers.setBounds(255, 131, 83, 23);
		panel_detgrup.add(lblNrMembers);

		lblTeamId = new JLabel("Team ID");
		lblTeamId.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTeamId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeamId.setForeground(Color.WHITE);
		lblTeamId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeamId.setFocusable(false);
		lblTeamId.setFocusTraversalKeysEnabled(false);
		lblTeamId.setBounds(255, 109, 83, 23);
		panel_detgrup.add(lblTeamId);

		lblTeamPrivacy = new JLabel("Team privacy");
		lblTeamPrivacy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeamPrivacy.setForeground(Color.WHITE);
		lblTeamPrivacy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeamPrivacy.setFocusable(false);
		lblTeamPrivacy.setFocusTraversalKeysEnabled(false);
		lblTeamPrivacy.setBounds(10, 131, 90, 23);
		panel_detgrup.add(lblTeamPrivacy);

		detgroup_name = new JTextField();
		detgroup_name.setText("no info");
		detgroup_name.setForeground(Color.WHITE);
		detgroup_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detgroup_name.setFocusable(false);
		detgroup_name.setFocusTraversalKeysEnabled(false);
		detgroup_name.setEditable(false);
		detgroup_name.setColumns(10);
		detgroup_name.setBorder(null);
		detgroup_name.setBackground(new Color(0, 153, 204));
		detgroup_name.setBounds(128, 37, 350, 20);
		panel_detgrup.add(detgroup_name);

		detgroup_admin = new JTextField();
		detgroup_admin.setText("no info");
		detgroup_admin.setForeground(Color.WHITE);
		detgroup_admin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detgroup_admin.setFocusable(false);
		detgroup_admin.setFocusTraversalKeysEnabled(false);
		detgroup_admin.setEditable(false);
		detgroup_admin.setColumns(10);
		detgroup_admin.setBorder(null);
		detgroup_admin.setBackground(new Color(0, 153, 204));
		detgroup_admin.setBounds(114, 111, 131, 20);
		panel_detgrup.add(detgroup_admin);

		detgroup_privacy = new JTextField();
		detgroup_privacy.setText("no info");
		detgroup_privacy.setForeground(Color.WHITE);
		detgroup_privacy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detgroup_privacy.setFocusable(false);
		detgroup_privacy.setFocusTraversalKeysEnabled(false);
		detgroup_privacy.setEditable(false);
		detgroup_privacy.setColumns(10);
		detgroup_privacy.setBorder(null);
		detgroup_privacy.setBackground(new Color(0, 153, 204));
		detgroup_privacy.setBounds(114, 133, 131, 20);
		panel_detgrup.add(detgroup_privacy);

		detgroup_id = new JTextField();
		detgroup_id.setText("no info");
		detgroup_id.setForeground(Color.WHITE);
		detgroup_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detgroup_id.setFocusable(false);
		detgroup_id.setFocusTraversalKeysEnabled(false);
		detgroup_id.setEditable(false);
		detgroup_id.setColumns(10);
		detgroup_id.setBorder(null);
		detgroup_id.setBackground(new Color(0, 153, 204));
		detgroup_id.setBounds(347, 111, 131, 20);
		panel_detgrup.add(detgroup_id);

		detgroup_nrmembers = new JTextField();
		detgroup_nrmembers.setText("no info");
		detgroup_nrmembers.setForeground(Color.WHITE);
		detgroup_nrmembers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detgroup_nrmembers.setFocusable(false);
		detgroup_nrmembers.setFocusTraversalKeysEnabled(false);
		detgroup_nrmembers.setEditable(false);
		detgroup_nrmembers.setColumns(10);
		detgroup_nrmembers.setBorder(null);
		detgroup_nrmembers.setBackground(new Color(0, 153, 204));
		detgroup_nrmembers.setBounds(347, 133, 131, 20);
		panel_detgrup.add(detgroup_nrmembers);

		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBorder(null);
		scrollPane_6.setBackground(new Color(0, 153, 204));
		scrollPane_6.setBounds(128, 58, 350, 48);
		panel_detgrup.add(scrollPane_6);

		detgroup_description = new JTextPane();
		detgroup_description.setBackground(new Color(0, 153, 204));
		detgroup_description.setForeground(new Color(255, 255, 255));
		scrollPane_6.setViewportView(detgroup_description);

		detgroup_btn = new JButton("Close");
		detgroup_btn.setIcon(new ImageIcon("img/cancel_bl.png"));
		detgroup_btn.setForeground(Color.BLACK);
		detgroup_btn.setFont(new Font("Tahoma", Font.BOLD, 13));
		detgroup_btn.setFocusable(false);
		detgroup_btn.setFocusTraversalKeysEnabled(false);
		detgroup_btn.setFocusPainted(false);
		detgroup_btn.setBackground(Color.WHITE);
		detgroup_btn.addActionListener(this);
		detgroup_btn.setBounds(357, 190, 121, 30);
		panel_detgrup.add(detgroup_btn);
		panel_taskdetails.setBackground(new Color(0, 51, 102));
		panel_taskdetails.setBounds(178, 144, 488, 233);
		panel_start.add(panel_taskdetails);
		panel_taskdetails.addMouseListener(this);
		panel_taskdetails.setLayout(null);

		JLabel lblTaskDetails = new JLabel("Task details");
		lblTaskDetails.setFocusTraversalKeysEnabled(false);
		lblTaskDetails.setFocusable(false);
		lblTaskDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTaskDetails.setForeground(new Color(255, 255, 255));
		lblTaskDetails.setBounds(10, 13, 108, 23);
		panel_taskdetails.add(lblTaskDetails);

		taskDetail_group = new JTextField();
		taskDetail_group.setForeground(new Color(255, 255, 255));
		taskDetail_group.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		taskDetail_group.setHorizontalAlignment(SwingConstants.RIGHT);
		taskDetail_group.setText("no info");
		taskDetail_group.setBorder(null);
		taskDetail_group.setBackground(new Color(0, 51, 102));
		taskDetail_group.setEditable(false);
		taskDetail_group.setFocusable(false);
		taskDetail_group.setFocusTraversalKeysEnabled(false);
		taskDetail_group.setBounds(255, 13, 223, 20);
		panel_taskdetails.add(taskDetail_group);
		taskDetail_group.setColumns(10);

		JLabel lblTaskName_1 = new JLabel("Task name");
		lblTaskName_1.setForeground(Color.WHITE);
		lblTaskName_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaskName_1.setFocusable(false);
		lblTaskName_1.setFocusTraversalKeysEnabled(false);
		lblTaskName_1.setBounds(20, 37, 98, 23);
		panel_taskdetails.add(lblTaskName_1);

		JLabel lblTaskDescription_2 = new JLabel("Task description");
		lblTaskDescription_2.setForeground(Color.WHITE);
		lblTaskDescription_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaskDescription_2.setFocusable(false);
		lblTaskDescription_2.setFocusTraversalKeysEnabled(false);
		lblTaskDescription_2.setBounds(20, 60, 98, 23);
		panel_taskdetails.add(lblTaskDescription_2);

		JLabel lblTaskAuthor = new JLabel("Task author");
		lblTaskAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTaskAuthor.setForeground(Color.WHITE);
		lblTaskAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaskAuthor.setFocusable(false);
		lblTaskAuthor.setFocusTraversalKeysEnabled(false);
		lblTaskAuthor.setBounds(10, 111, 90, 23);
		panel_taskdetails.add(lblTaskAuthor);

		JLabel lblTask = new JLabel("Task status");
		lblTask.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTask.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTask.setForeground(Color.WHITE);
		lblTask.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTask.setFocusable(false);
		lblTask.setFocusTraversalKeysEnabled(false);
		lblTask.setBounds(255, 133, 83, 23);
		panel_taskdetails.add(lblTask);

		JLabel label_2 = new JLabel("Task priority");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setFocusable(false);
		label_2.setFocusTraversalKeysEnabled(false);
		label_2.setBounds(255, 111, 83, 23);
		panel_taskdetails.add(label_2);

		JLabel lblTaskWorkingPers = new JLabel("Working person");
		lblTaskWorkingPers.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTaskWorkingPers.setForeground(Color.WHITE);
		lblTaskWorkingPers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaskWorkingPers.setFocusable(false);
		lblTaskWorkingPers.setFocusTraversalKeysEnabled(false);
		lblTaskWorkingPers.setBounds(10, 133, 90, 23);
		panel_taskdetails.add(lblTaskWorkingPers);

		JLabel lblDateCreated = new JLabel("Date created");
		lblDateCreated.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateCreated.setForeground(Color.WHITE);
		lblDateCreated.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateCreated.setFocusable(false);
		lblDateCreated.setFocusTraversalKeysEnabled(false);
		lblDateCreated.setBounds(10, 156, 90, 23);
		panel_taskdetails.add(lblDateCreated);

		JLabel lblDeadline = new JLabel("Deadline");
		lblDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeadline.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblDeadline.setForeground(Color.WHITE);
		lblDeadline.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeadline.setFocusable(false);
		lblDeadline.setFocusTraversalKeysEnabled(false);
		lblDeadline.setBounds(255, 156, 83, 23);
		panel_taskdetails.add(lblDeadline);

		taskDetail_name = new JTextField();
		taskDetail_name.setText("no info");
		taskDetail_name.setForeground(Color.WHITE);
		taskDetail_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_name.setFocusable(false);
		taskDetail_name.setFocusTraversalKeysEnabled(false);
		taskDetail_name.setEditable(false);
		taskDetail_name.setColumns(10);
		taskDetail_name.setBorder(null);
		taskDetail_name.setBackground(new Color(0, 51, 102));
		taskDetail_name.setBounds(128, 39, 350, 20);
		panel_taskdetails.add(taskDetail_name);

		taskDetail_author = new JTextField();
		taskDetail_author.setText("no info");
		taskDetail_author.setForeground(Color.WHITE);
		taskDetail_author.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_author.setFocusable(false);
		taskDetail_author.setFocusTraversalKeysEnabled(false);
		taskDetail_author.setEditable(false);
		taskDetail_author.setColumns(10);
		taskDetail_author.setBorder(null);
		taskDetail_author.setBackground(new Color(0, 51, 102));
		taskDetail_author.setBounds(114, 113, 131, 20);
		panel_taskdetails.add(taskDetail_author);

		taskDetail_workperson = new JTextField();
		taskDetail_workperson.setText("no info");
		taskDetail_workperson.setForeground(Color.WHITE);
		taskDetail_workperson.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_workperson.setFocusable(false);
		taskDetail_workperson.setFocusTraversalKeysEnabled(false);
		taskDetail_workperson.setEditable(false);
		taskDetail_workperson.setColumns(10);
		taskDetail_workperson.setBorder(null);
		taskDetail_workperson.setBackground(new Color(0, 51, 102));
		taskDetail_workperson.setBounds(114, 135, 131, 20);
		panel_taskdetails.add(taskDetail_workperson);

		taskDetail_datecreated = new JTextField();
		taskDetail_datecreated.setText("no info");
		taskDetail_datecreated.setForeground(Color.WHITE);
		taskDetail_datecreated.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_datecreated.setFocusable(false);
		taskDetail_datecreated.setFocusTraversalKeysEnabled(false);
		taskDetail_datecreated.setEditable(false);
		taskDetail_datecreated.setColumns(10);
		taskDetail_datecreated.setBorder(null);
		taskDetail_datecreated.setBackground(new Color(0, 51, 102));
		taskDetail_datecreated.setBounds(114, 158, 131, 20);
		panel_taskdetails.add(taskDetail_datecreated);

		taskDetail_priority = new JTextField();
		taskDetail_priority.setText("no info");
		taskDetail_priority.setForeground(Color.WHITE);
		taskDetail_priority.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_priority.setFocusable(false);
		taskDetail_priority.setFocusTraversalKeysEnabled(false);
		taskDetail_priority.setEditable(false);
		taskDetail_priority.setColumns(10);
		taskDetail_priority.setBorder(null);
		taskDetail_priority.setBackground(new Color(0, 51, 102));
		taskDetail_priority.setBounds(347, 113, 131, 20);
		panel_taskdetails.add(taskDetail_priority);

		taskDetail_status = new JTextField();
		taskDetail_status.setText("no info");
		taskDetail_status.setForeground(Color.WHITE);
		taskDetail_status.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_status.setFocusable(false);
		taskDetail_status.setFocusTraversalKeysEnabled(false);
		taskDetail_status.setEditable(false);
		taskDetail_status.setColumns(10);
		taskDetail_status.setBorder(null);
		taskDetail_status.setBackground(new Color(0, 51, 102));
		taskDetail_status.setBounds(347, 135, 131, 20);
		panel_taskdetails.add(taskDetail_status);

		taskDetail_deadline = new JTextField();
		taskDetail_deadline.setText("no info");
		taskDetail_deadline.setForeground(Color.WHITE);
		taskDetail_deadline.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_deadline.setFocusable(false);
		taskDetail_deadline.setFocusTraversalKeysEnabled(false);
		taskDetail_deadline.setEditable(false);
		taskDetail_deadline.setColumns(10);
		taskDetail_deadline.setBorder(null);
		taskDetail_deadline.setBackground(new Color(0, 51, 102));
		taskDetail_deadline.setBounds(347, 158, 131, 20);
		panel_taskdetails.add(taskDetail_deadline);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBackground(new Color(0, 51, 102));
		scrollPane_5.setBorder(null);
		scrollPane_5.setBounds(128, 60, 350, 48);
		panel_taskdetails.add(scrollPane_5);

		taskDetail_description = new JTextPane();
		taskDetail_description.setText("no info");
		taskDetail_description.setFont(new Font("Tahoma", Font.PLAIN, 12));
		taskDetail_description.setForeground(new Color(255, 255, 255));
		taskDetail_description.setBorder(null);
		taskDetail_description.setBackground(new Color(0, 51, 102));
		scrollPane_5.setViewportView(taskDetail_description);

		taskDetail_btn = new JButton("Close");
		taskDetail_btn.setFont(new Font("Tahoma", Font.BOLD, 13));
		taskDetail_btn.setIcon(new ImageIcon("img/cancel_bl.png"));
		taskDetail_btn.setForeground(new Color(0, 0, 0));
		taskDetail_btn.setBackground(new Color(255, 255, 255));
		taskDetail_btn.setFocusable(false);
		taskDetail_btn.setFocusPainted(false);
		taskDetail_btn.setFocusTraversalKeysEnabled(false);
		taskDetail_btn.setBounds(357, 192, 121, 30);
		taskDetail_btn.addActionListener(this);
		panel_taskdetails.add(taskDetail_btn);

		panel_notification = new JPanel();
		panel_notification.setFocusCycleRoot(true);
		panel_notification.setFocusTraversalPolicyProvider(true);
		panel_notification.setBorder(new LineBorder(new Color(0, 153, 255), 5));
		panel_notification.setBounds(232, 240, 380, 233);
		panel_start.add(panel_notification);
		panel_notification.setVisible(false);
		panel_notification.setForeground(new Color(255, 255, 255));
		panel_notification.setBackground(new Color(255, 255, 255));
		panel_notification.addMouseListener(this);
		panel_notification.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("img/add_member_bl.png"));
		lblNewLabel.setBounds(10, 11, 20, 20);
		panel_notification.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("img/message_bl.png"));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(10, 120, 20, 20);
		panel_notification.add(label_1);

		JLabel lblYouWereInvited = new JLabel("You were invited to join in the following groups");
		lblYouWereInvited.setFocusTraversalKeysEnabled(false);
		lblYouWereInvited.setFocusable(false);
		lblYouWereInvited.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYouWereInvited.setBounds(40, 17, 330, 14);
		panel_notification.add(lblYouWereInvited);

		JLabel lblYouReceivedThe = new JLabel("You received the following messages");
		lblYouReceivedThe.setFocusable(false);
		lblYouReceivedThe.setFocusTraversalKeysEnabled(false);
		lblYouReceivedThe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYouReceivedThe.setBounds(40, 126, 330, 14);
		panel_notification.add(lblYouReceivedThe);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 149, 330, 73);
		panel_notification.add(scrollPane);

		txt_notification_messages = new JTextPane();
		txt_notification_messages.setEditable(false);
		txt_notification_messages.setFocusable(false);
		scrollPane.setViewportView(txt_notification_messages);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 42, 284, 73);
		panel_notification.add(scrollPane_1);

		table_notification = new JTable();
		table_notification.setFocusable(false);
		table_notification.setFocusTraversalKeysEnabled(false);
		table_notification.setShowVerticalLines(false);
		table_notification.setShowHorizontalLines(false);
		table_notification.setShowGrid(false);
		table_notification.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 12));
		table_notification.getTableHeader().setBackground(new Color(0, 153, 204));
		table_notification.getTableHeader().setForeground(new Color(255, 255, 255));
		table_notification.getTableHeader().setReorderingAllowed(false);
		table_notification
				.setModel(new DefaultTableModel(
						new Object[][] { { null, null, null }, { null, null, null }, { null, null, null },
								{ null, null, null }, { null, null, null }, },
						new String[] { "Gr. Name", "Admin", "Date" }));
		table_notification.getColumnModel().getColumn(1).setMinWidth(75);
		table_notification.getColumnModel().getColumn(1).setMaxWidth(75);
		table_notification.getColumnModel().getColumn(2).setMinWidth(75);
		table_notification.getColumnModel().getColumn(2).setMaxWidth(75);
		scrollPane_1.setViewportView(table_notification);

		btn_notification_accept = new JLabel("");
		btn_notification_accept.addMouseListener(this);
		btn_notification_accept.setIcon(new ImageIcon("img/ok_bl.png"));
		btn_notification_accept.setBackground(Color.WHITE);
		btn_notification_accept.setBounds(334, 57, 25, 25);

		panel_notification.add(btn_notification_accept);

		btn_notification_reject = new JLabel("");
		btn_notification_reject.addMouseListener(this);
		btn_notification_reject.setIcon(new ImageIcon("img/cancel_bl.png"));
		btn_notification_reject.setBackground(Color.WHITE);
		btn_notification_reject.setBounds(334, 90, 25, 25);
		panel_notification.add(btn_notification_reject);

		JPanel lbl_start_group_menu = new JPanel();
		lbl_start_group_menu.setFocusable(false);
		lbl_start_group_menu.setFocusTraversalKeysEnabled(false);
		lbl_start_group_menu.setBorder(null);
		lbl_start_group_menu.setBackground(new Color(0, 51, 102));
		lbl_start_group_menu.setBounds(0, 0, 533, 34);
		panel_start.add(lbl_start_group_menu);
		lbl_start_group_menu.setLayout(null);

		JLabel lblGroupsMenu = new JLabel("GROUP MENU");
		lblGroupsMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroupsMenu.setForeground(new Color(255, 255, 255));
		lblGroupsMenu.setBounds(45, 0, 156, 34);
		lbl_start_group_menu.add(lblGroupsMenu);

		JPanel lbl_start_task_local_menu = new JPanel();
		lbl_start_task_local_menu.setBorder(null);
		lbl_start_task_local_menu.setBackground(new Color(0, 51, 102));
		lbl_start_task_local_menu.setBounds(535, 0, 308, 34);
		panel_start.add(lbl_start_task_local_menu);
		lbl_start_task_local_menu.setLayout(null);

		JLabel lblTaskMenu = new JLabel("LOCAL TASKS");
		lblTaskMenu.setForeground(Color.WHITE);
		lblTaskMenu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTaskMenu.setBounds(20, 0, 105, 34);
		lbl_start_task_local_menu.add(lblTaskMenu);

		btn_start_search_group = new JButton("Search group");
		btn_start_search_group.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_start_search_group.setBorderPainted(false);
		btn_start_search_group.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_start_search_group.setForeground(new Color(0, 0, 0));
		btn_start_search_group.setFocusable(false);
		btn_start_search_group.setFocusTraversalKeysEnabled(false);
		btn_start_search_group.setFocusPainted(false);
		btn_start_search_group.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_start_search_group.setBorder(null);
		btn_start_search_group.setBackground(new Color(255, 255, 255));
		btn_start_search_group.setBounds(10, 34, 119, 28);
		btn_start_search_group.addActionListener(this);
		panel_start.add(btn_start_search_group);

		btn_start_create_group = new JButton("Create group");
		btn_start_create_group.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_start_create_group.setForeground(Color.BLACK);
		btn_start_create_group.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_start_create_group.setFocusable(false);
		btn_start_create_group.setFocusTraversalKeysEnabled(false);
		btn_start_create_group.setFocusPainted(false);
		btn_start_create_group.setBorderPainted(false);
		btn_start_create_group.setBorder(null);
		btn_start_create_group.setBackground(Color.WHITE);
		btn_start_create_group.setAlignmentX(0.5f);
		btn_start_create_group.setBounds(145, 34, 114, 28);
		btn_start_create_group.addActionListener(this);
		panel_start.add(btn_start_create_group);

		btn_start_member_group = new JButton("Member groups");
		btn_start_member_group.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_start_member_group.setForeground(Color.BLACK);
		btn_start_member_group.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_start_member_group.setFocusable(false);
		btn_start_member_group.setFocusTraversalKeysEnabled(false);
		btn_start_member_group.setFocusPainted(false);
		btn_start_member_group.setBorderPainted(false);
		btn_start_member_group.setBorder(null);
		btn_start_member_group.setBackground(Color.WHITE);
		btn_start_member_group.setAlignmentX(0.5f);
		btn_start_member_group.setBounds(275, 34, 121, 28);
		btn_start_member_group.addActionListener(this);
		panel_start.add(btn_start_member_group);

		btn_start_admin_group = new JButton("Admin groups");
		btn_start_admin_group.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_start_admin_group.setForeground(Color.BLACK);
		btn_start_admin_group.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_start_admin_group.setFocusable(false);
		btn_start_admin_group.setFocusTraversalKeysEnabled(false);
		btn_start_admin_group.setFocusPainted(false);
		btn_start_admin_group.setBorderPainted(false);
		btn_start_admin_group.setBorder(null);
		btn_start_admin_group.setBackground(Color.WHITE);
		btn_start_admin_group.setAlignmentX(0.5f);
		btn_start_admin_group.setBounds(414, 34, 111, 28);
		btn_start_admin_group.addActionListener(this);
		panel_start.add(btn_start_admin_group);

		btn_start_my_local_task = new JButton("My local tasks");
		btn_start_my_local_task.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_start_my_local_task.setForeground(Color.BLACK);
		btn_start_my_local_task.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_start_my_local_task.setFocusable(false);
		btn_start_my_local_task.setFocusTraversalKeysEnabled(false);
		btn_start_my_local_task.setFocusPainted(false);
		btn_start_my_local_task.setBorderPainted(false);
		btn_start_my_local_task.setBorder(null);
		btn_start_my_local_task.setBackground(Color.WHITE);
		btn_start_my_local_task.setAlignmentX(0.5f);
		btn_start_my_local_task.setBounds(545, 34, 143, 28);
		btn_start_my_local_task.addActionListener(this);
		panel_start.add(btn_start_my_local_task);

		btn_start_my_group_task = new JButton("My group tasks");
		btn_start_my_group_task.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_start_my_group_task.setForeground(Color.BLACK);
		btn_start_my_group_task.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_start_my_group_task.setFocusable(false);
		btn_start_my_group_task.setFocusTraversalKeysEnabled(false);
		btn_start_my_group_task.setFocusPainted(false);
		btn_start_my_group_task.setBorderPainted(false);
		btn_start_my_group_task.setBorder(null);
		btn_start_my_group_task.setBackground(Color.WHITE);
		btn_start_my_group_task.setAlignmentX(0.5f);
		btn_start_my_group_task.setBounds(700, 34, 134, 28);
		btn_start_my_group_task.addActionListener(this);
		panel_start.add(btn_start_my_group_task);

		lbl_start_home = new JLabel("");
		lbl_start_home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_start_home.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_start_home.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_start_home.setIcon(new ImageIcon("img/home_icon.png"));
		lbl_start_home.setBounds(167, 484, 25, 25);
		lbl_start_home.addMouseListener(this);
		panel_start.add(lbl_start_home);

		lbl_start_settings = new JLabel("");
		lbl_start_settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_start_settings.setIcon(new ImageIcon("img/settings_icon.png"));
		lbl_start_settings.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_start_settings.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_start_settings.setBounds(267, 484, 25, 25);
		lbl_start_settings.addMouseListener(this);
		panel_start.add(lbl_start_settings);

		lbl_start_log_out = new JLabel("");
		lbl_start_log_out.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_start_log_out.setIcon(new ImageIcon("img/sign_out_icon.png"));
		lbl_start_log_out.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_start_log_out.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_start_log_out.setBounds(202, 484, 25, 25);
		lbl_start_log_out.addMouseListener(this);
		panel_start.add(lbl_start_log_out);

		lbl_start_support = new JLabel("");
		lbl_start_support.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_start_support.setIcon(new ImageIcon("img/support_icon.png"));
		lbl_start_support.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_start_support.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_start_support.setBounds(238, 484, 25, 25);
		lbl_start_support.addMouseListener(this);
		panel_start.add(lbl_start_support);

		txt_start_initial = new JTextField();
		txt_start_initial.setFocusable(false);
		txt_start_initial.setFocusTraversalKeysEnabled(false);
		txt_start_initial.setEditable(false);
		txt_start_initial.setBorder(null);
		txt_start_initial.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_start_initial.setForeground(new Color(255, 255, 255));
		txt_start_initial.setHorizontalAlignment(SwingConstants.CENTER);
		txt_start_initial.setBackground(new Color(0, 51, 102));
		txt_start_initial.setBounds(535, 480, 35, 35);
		panel_start.add(txt_start_initial);
		txt_start_initial.setColumns(10);

		txt_start_logged_user = new JTextField();
		txt_start_logged_user.setBorder(null);
		txt_start_logged_user.setFocusable(false);
		txt_start_logged_user.setEditable(false);
		txt_start_logged_user.setFocusTraversalKeysEnabled(false);
		txt_start_logged_user.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_start_logged_user.setForeground(new Color(0, 51, 102));
		txt_start_logged_user.setBackground(new Color(204, 204, 204));
		txt_start_logged_user.setBounds(576, 487, 143, 20);
		panel_start.add(txt_start_logged_user);
		txt_start_logged_user.setColumns(10);

		create_cardPanel_start();
		create_cardPanel_search();
		create_cardPanel_member();
		create_cardPanel_admin();
		create_cardPanel_create();
		create_cardPanel_localTasks();
		create_cardPanel_groupTasks();
		create_settings_panel();
		create_group_panel();

		// SCROLL BAR SETTINGS

		UIManager.put("ScrollBar.trackHighlightForeground", (new Color(0, 153, 204)));
		UIManager.put("scrollbar", (new Color(0, 153, 204)));
		UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.thumbHeight", 2);
		UIManager.put("ScrollBar.background", (new Color(0, 153, 204)));
		UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Color(255, 255, 255)));
		UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.trackForeground", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.foreground", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.shadow", new ColorUIResource(new Color(0, 153, 204)));
		UIManager.put("ScrollBar.highlight", new ColorUIResource(new Color(0, 153, 204)));
	}

	// THE FOLLOWING METHODS HAS EQUIVALENCE ON EACH TAB

	/**
	 * Create the cardPanel Start.
	 */
	private void create_cardPanel_start() {

		start_cardLayoutPanel = new JPanel();
		start_cardLayoutPanel.setBounds(56, 70, 732, 404);
		panel_start.add(start_cardLayoutPanel);
		start_cardLayoutPanel.setLayout(new CardLayout(0, 0));

		back_icon.setIcon(new ImageIcon("img/back_icon.png"));

		cardLayoutStart = (CardLayout) (start_cardLayoutPanel.getLayout());
		cardLayoutStart.show(start_cardLayoutPanel, "G");

		btn_notification = new JButton("No notifications");
		btn_notification.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_notification.setIcon(new ImageIcon("img/notification.png"));
		btn_notification.setFocusPainted(false);
		btn_notification.setFocusTraversalKeysEnabled(false);
		btn_notification.setFocusable(false);
		btn_notification.setForeground(new Color(255, 255, 255));
		btn_notification.setBorder(null);
		btn_notification.setBackground(new Color(0, 153, 255));
		btn_notification.setBounds(333, 482, 191, 33);
		btn_notification.addActionListener(this);
		panel_start.add(btn_notification);

		help = new JLabel("");
		help.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\SE_new\\img\\help.png"));
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.addMouseListener(this);
		help.setBounds(298, 484, 25, 25);
		panel_start.add(help);

	}

	/**
	 * Create cardPanel search
	 */

	private void create_cardPanel_search() {
		panel_search_group = new JPanel();
		panel_search_group.setBackground(UIManager.getColor("Button.background"));
		start_cardLayoutPanel.add(panel_search_group, "SEARCH_GROUP");
		panel_search_group.setLayout(null);

		lbl_search_group = new JTextField();
		lbl_search_group.setBounds(0, 0, 732, 30);
		lbl_search_group.setText("     Search group");
		lbl_search_group.setForeground(Color.WHITE);
		lbl_search_group.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_search_group.setFocusable(false);
		lbl_search_group.setFocusTraversalKeysEnabled(false);
		lbl_search_group.setEditable(false);
		lbl_search_group.setColumns(10);
		lbl_search_group.setBorder(null);
		lbl_search_group.setBackground(new Color(0, 153, 204));
		panel_search_group.add(lbl_search_group);

		txt_search_field = new JTextField();
		txt_search_field.setBounds(61, 51, 475, 30);
		panel_search_group.add(txt_search_field);
		txt_search_field.setColumns(10);

		JScrollPane search_scrollPane = new JScrollPane();
		search_scrollPane.setForeground(UIManager.getColor("Button.background"));
		search_scrollPane.setFocusTraversalKeysEnabled(false);
		search_scrollPane.setFocusable(false);
		search_scrollPane.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		search_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		search_scrollPane.setBounds(61, 101, 610, 150);
		panel_search_group.add(search_scrollPane);

		table_search = new JTable();
		table_search.addMouseListener(this);
		table_search.setFocusable(false);
		table_search.setFocusTraversalKeysEnabled(false);
		table_search.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_search.setBorder(new LineBorder(new Color(0, 153, 255)));
		table_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table_search.setBackground(UIManager.getColor("Button.background"));
		table_search.setGridColor(SystemColor.inactiveCaptionBorder);
		table_search.setShowVerticalLines(false);
		table_search.setRowHeight(23);
		table_search.setAutoCreateRowSorter(true);
		table_search.getTableHeader().setReorderingAllowed(false);
		table_search.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 12));
		table_search.getTableHeader().setBackground(new Color(0, 153, 204));
		table_search.getTableHeader().setForeground(new Color(255, 255, 255));
		table_search.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "Group ID", "Group name", "Admin", "Nb of Members" }));
		table_search.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_search.getColumnModel().getColumn(0).setMinWidth(100);
		table_search.getColumnModel().getColumn(0).setMaxWidth(100);
		table_search.getColumnModel().getColumn(2).setPreferredWidth(125);
		table_search.getColumnModel().getColumn(2).setMinWidth(125);
		table_search.getColumnModel().getColumn(2).setMaxWidth(125);
		table_search.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_search.getColumnModel().getColumn(3).setMinWidth(100);
		table_search.getColumnModel().getColumn(3).setMaxWidth(100);
		search_scrollPane.setViewportView(table_search);

		btn_search_search = new JButton("Search");
		btn_search_search.addActionListener(this);
		btn_search_search.setIcon(new ImageIcon("img/search.png"));
		btn_search_search.setMargin(new Insets(2, 2, 2, 2));
		btn_search_search.setFocusable(false);
		btn_search_search.setFocusPainted(false);
		btn_search_search.setFocusTraversalKeysEnabled(false);
		btn_search_search.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_search_search.setForeground(new Color(255, 255, 255));
		btn_search_search.setBackground(new Color(0, 51, 102));
		btn_search_search.setBounds(557, 51, 114, 30);
		panel_search_group.add(btn_search_search);

		btn_search_details = new JButton("Group details");
		btn_search_details.setIcon(new ImageIcon("img/info.png"));
		btn_search_details.setMargin(new Insets(2, 2, 2, 2));
		btn_search_details.setForeground(Color.WHITE);
		btn_search_details.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_search_details.setFocusable(false);
		btn_search_details.setFocusTraversalKeysEnabled(false);
		btn_search_details.setFocusPainted(false);
		btn_search_details.setBackground(new Color(0, 51, 102));
		btn_search_details.setBounds(61, 328, 140, 30);
		btn_search_details.addActionListener(this);
		panel_search_group.add(btn_search_details);

		btn_search_SendMessage = new JButton("Send message");
		btn_search_SendMessage.setIcon(new ImageIcon("img/message.png"));
		btn_search_SendMessage.setMargin(new Insets(2, 2, 2, 2));
		btn_search_SendMessage.setForeground(Color.WHITE);
		btn_search_SendMessage.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_search_SendMessage.setFocusable(false);
		btn_search_SendMessage.setFocusTraversalKeysEnabled(false);
		btn_search_SendMessage.setFocusPainted(false);
		btn_search_SendMessage.setBackground(new Color(0, 51, 102));
		btn_search_SendMessage.setBounds(376, 328, 140, 30);
		panel_search_group.add(btn_search_SendMessage);

		btn_search_AskToJoin = new JButton("Ask to join");
		btn_search_AskToJoin.setIcon(new ImageIcon("img/add_member.png"));
		btn_search_AskToJoin.setMargin(new Insets(2, 2, 2, 2));
		btn_search_AskToJoin.setForeground(Color.WHITE);
		btn_search_AskToJoin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_search_AskToJoin.setFocusable(false);
		btn_search_AskToJoin.setFocusTraversalKeysEnabled(false);
		btn_search_AskToJoin.setFocusPainted(false);
		btn_search_AskToJoin.setBackground(new Color(0, 51, 102));
		btn_search_AskToJoin.addActionListener(this);
		btn_search_AskToJoin.setBounds(531, 328, 140, 30);
		panel_search_group.add(btn_search_AskToJoin);

		lbl_search_groupName = new JTextField();
		lbl_search_groupName.setDisabledTextColor(new Color(255, 255, 255));
		lbl_search_groupName.setFocusable(false);
		lbl_search_groupName.setFocusTraversalKeysEnabled(false);
		lbl_search_groupName.setEditable(false);
		lbl_search_groupName.setBounds(61, 259, 195, 28);
		panel_search_group.add(lbl_search_groupName);
		lbl_search_groupName.setColumns(10);

		lbl_search_nrmembers = new JTextField();
		lbl_search_nrmembers.setDisabledTextColor(new Color(255, 255, 255));
		lbl_search_nrmembers.setFocusable(false);
		lbl_search_nrmembers.setFocusTraversalKeysEnabled(false);
		lbl_search_nrmembers.setEditable(false);
		lbl_search_nrmembers.setColumns(10);
		lbl_search_nrmembers.setBounds(476, 259, 195, 28);
		panel_search_group.add(lbl_search_nrmembers);

		lbl_search_admin = new JTextField();
		lbl_search_admin.setDisabledTextColor(new Color(255, 255, 255));
		lbl_search_admin.setFocusable(false);
		lbl_search_admin.setFocusTraversalKeysEnabled(false);
		lbl_search_admin.setEditable(false);
		lbl_search_admin.setColumns(10);
		lbl_search_admin.setBounds(268, 259, 195, 28);
		panel_search_group.add(lbl_search_admin);

		JSeparator search_eparator = new JSeparator();
		search_eparator.setBounds(61, 304, 610, 2);
		panel_search_group.add(search_eparator);

		btn_search_openGroup = new JButton("Open group");
		btn_search_openGroup.setIcon(new ImageIcon("img/open.png"));
		btn_search_openGroup.setMargin(new Insets(2, 2, 2, 2));
		btn_search_openGroup.setForeground(Color.WHITE);
		btn_search_openGroup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_search_openGroup.setFocusable(false);
		btn_search_openGroup.setFocusTraversalKeysEnabled(false);
		btn_search_openGroup.setFocusPainted(false);
		btn_search_openGroup.setBackground(new Color(0, 51, 102));
		btn_search_openGroup.setBounds(218, 328, 140, 30);
		btn_search_openGroup.addActionListener(this);
		panel_search_group.add(btn_search_openGroup);
	}

	/**
	 * Create cardPanel create
	 */
	private void create_cardPanel_create() {
		panel_create_group = new JPanel();
		panel_create_group.setLayout(null);
		start_cardLayoutPanel.add(panel_create_group, "CREATE_GROUP");

		lbl_create_group = new JTextField();
		lbl_create_group.setText("     Create group");
		lbl_create_group.setForeground(Color.WHITE);
		lbl_create_group.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_create_group.setFocusable(false);
		lbl_create_group.setFocusTraversalKeysEnabled(false);
		lbl_create_group.setEditable(false);
		lbl_create_group.setColumns(10);
		lbl_create_group.setBorder(null);
		lbl_create_group.setBackground(new Color(0, 153, 204));
		lbl_create_group.setBounds(0, 0, 732, 30);
		panel_create_group.add(lbl_create_group);

		txt_create_groupName = new JTextField();
		txt_create_groupName.setBorder(new LineBorder(new Color(204, 204, 204)));
		txt_create_groupName.setBounds(35, 78, 327, 30);
		panel_create_group.add(txt_create_groupName);
		txt_create_groupName.setColumns(10);

		txt_create_groupDescription = new JTextPane();
		txt_create_groupDescription.setBorder(new LineBorder(new Color(204, 204, 204)));
		txt_create_groupDescription.setBounds(35, 145, 327, 227);
		panel_create_group.add(txt_create_groupDescription);

		JLabel lblGroupName = new JLabel("Group name");
		lblGroupName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGroupName.setFocusTraversalKeysEnabled(false);
		lblGroupName.setFocusable(false);
		lblGroupName.setBounds(25, 52, 206, 26);
		panel_create_group.add(lblGroupName);

		JLabel lblGroupDescription = new JLabel("Group description");
		lblGroupDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGroupDescription.setFocusable(false);
		lblGroupDescription.setFocusTraversalKeysEnabled(false);
		lblGroupDescription.setBounds(25, 119, 206, 26);
		panel_create_group.add(lblGroupDescription);

		msg_create_groupName = new JLabel("*");
		msg_create_groupName.setVisible(false);
		msg_create_groupName.setForeground(new Color(204, 0, 0));
		msg_create_groupName.setHorizontalAlignment(SwingConstants.CENTER);
		msg_create_groupName.setFont(new Font("Tahoma", Font.BOLD, 12));
		msg_create_groupName.setFocusable(false);
		msg_create_groupName.setFocusTraversalKeysEnabled(false);
		msg_create_groupName.setBounds(101, 52, 17, 26);
		panel_create_group.add(msg_create_groupName);

		btn_create_createGroup = new JButton("Create group");
		btn_create_createGroup.setIcon(new ImageIcon("img/ok.png"));
		btn_create_createGroup.setMargin(new Insets(2, 2, 2, 2));
		btn_create_createGroup.setForeground(Color.WHITE);
		btn_create_createGroup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_create_createGroup.setFocusable(false);
		btn_create_createGroup.setFocusTraversalKeysEnabled(false);
		btn_create_createGroup.setFocusPainted(false);
		btn_create_createGroup.setBorder(null);
		btn_create_createGroup.setBackground(new Color(0, 51, 102));
		btn_create_createGroup.setBounds(472, 336, 163, 36);
		btn_create_createGroup.addActionListener(this);
		panel_create_group.add(btn_create_createGroup);

		JTextPane txtpnChooseThePrivacy = new JTextPane();
		txtpnChooseThePrivacy.setText(
				"Choose the privacy settings of the group.\r\nPublic \r\n- any user may find the group and its description\r\n- any user may ask to join the group\r\nPrivate \r\n- only invited users mai see the description\r\n- invitational group");
		txtpnChooseThePrivacy.setForeground(Color.BLACK);
		txtpnChooseThePrivacy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnChooseThePrivacy.setFocusable(false);
		txtpnChooseThePrivacy.setFocusTraversalKeysEnabled(false);
		txtpnChooseThePrivacy.setFocusCycleRoot(false);
		txtpnChooseThePrivacy.setEditable(false);
		txtpnChooseThePrivacy.setBackground(SystemColor.menu);
		txtpnChooseThePrivacy.setBounds(404, 79, 302, 111);
		panel_create_group.add(txtpnChooseThePrivacy);

		JLabel lblPrivacySettings_1 = new JLabel("Privacy settings");
		lblPrivacySettings_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrivacySettings_1.setFocusable(false);
		lblPrivacySettings_1.setFocusTraversalKeysEnabled(false);
		lblPrivacySettings_1.setBounds(394, 52, 206, 26);
		panel_create_group.add(lblPrivacySettings_1);

		rdbtn_create_publicGroup = new JRadioButton("Public group");
		rdbtn_create_publicGroup.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn_create_publicGroup.setFocusPainted(false);
		rdbtn_create_publicGroup.setFocusable(false);
		rdbtn_create_publicGroup.setBounds(403, 197, 147, 23);
		rdbtn_create_publicGroup.addActionListener(this);
		panel_create_group.add(rdbtn_create_publicGroup);

		rdbtn_create_privateGroup = new JRadioButton("Private group");
		rdbtn_create_privateGroup.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn_create_privateGroup.setFocusPainted(false);
		rdbtn_create_privateGroup.setFocusable(false);
		rdbtn_create_privateGroup.setBounds(566, 197, 136, 23);
		rdbtn_create_privateGroup.addActionListener(this);
		panel_create_group.add(rdbtn_create_privateGroup);

		msg_create_groupDescription = new JLabel("*");
		msg_create_groupDescription.setVisible(false);
		msg_create_groupDescription.setHorizontalAlignment(SwingConstants.CENTER);
		msg_create_groupDescription.setForeground(new Color(204, 0, 0));
		msg_create_groupDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		msg_create_groupDescription.setFocusable(false);
		msg_create_groupDescription.setFocusTraversalKeysEnabled(false);
		msg_create_groupDescription.setBounds(138, 119, 17, 26);
		panel_create_group.add(msg_create_groupDescription);

		msg_create_privacySettings = new JLabel("*");
		msg_create_privacySettings.setVisible(false);
		msg_create_privacySettings.setHorizontalAlignment(SwingConstants.CENTER);
		msg_create_privacySettings.setForeground(new Color(204, 0, 0));
		msg_create_privacySettings.setFont(new Font("Tahoma", Font.BOLD, 12));
		msg_create_privacySettings.setFocusable(false);
		msg_create_privacySettings.setFocusTraversalKeysEnabled(false);
		msg_create_privacySettings.setBounds(496, 52, 17, 26);
		panel_create_group.add(msg_create_privacySettings);

		msg_create_fillIn = new JTextPane();
		msg_create_fillIn.setVisible(false);
		msg_create_fillIn.setText("Please fill in all the boxex marked with \"*\"");
		msg_create_fillIn.setForeground(Color.RED);
		msg_create_fillIn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		msg_create_fillIn.setFocusable(false);
		msg_create_fillIn.setFocusTraversalKeysEnabled(false);
		msg_create_fillIn.setFocusCycleRoot(false);
		msg_create_fillIn.setEditable(false);
		msg_create_fillIn.setBackground(SystemColor.menu);
		msg_create_fillIn.setBounds(441, 270, 224, 36);
		panel_create_group.add(msg_create_fillIn);
	}

	/**
	 * Create cardPanel member.
	 */
	private void create_cardPanel_member() {

		panel_member_group = new JPanel();
		panel_member_group.setLayout(null);
		start_cardLayoutPanel.add(panel_member_group, "MEMBER_GROUP");

		lbl_member_group = new JTextField();
		lbl_member_group.setText("     Member group");
		lbl_member_group.setForeground(Color.WHITE);
		lbl_member_group.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_member_group.setFocusable(false);
		lbl_member_group.setFocusTraversalKeysEnabled(false);
		lbl_member_group.setEditable(false);
		lbl_member_group.setColumns(10);
		lbl_member_group.setBorder(null);
		lbl_member_group.setBackground(new Color(0, 153, 204));
		lbl_member_group.setBounds(0, 0, 732, 30);
		panel_member_group.add(lbl_member_group);

		txtpnTheListOf = new JTextPane();
		txtpnTheListOf.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTheListOf.setBackground(UIManager.getColor("Button.background"));
		txtpnTheListOf.setText("The list of groups in which you are member");
		txtpnTheListOf.setEditable(false);
		txtpnTheListOf.setBounds(10, 38, 342, 20);
		panel_member_group.add(txtpnTheListOf);

		scrollPane_member = new JScrollPane();
		scrollPane_member.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_member.setForeground(SystemColor.menu);
		scrollPane_member.setFocusable(false);
		scrollPane_member.setFocusTraversalKeysEnabled(false);
		scrollPane_member.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		scrollPane_member.setBounds(20, 61, 686, 142);
		panel_member_group.add(scrollPane_member);

		table_member = new JTable();
		table_member.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "Group id", "Group name", "Admin", "Nr. members", "Nr. Tasks open", "Nr. Your tasks" }));
		table_member.getColumnModel().getColumn(0).setMinWidth(75);
		table_member.getColumnModel().getColumn(0).setMaxWidth(75);
		table_member.getColumnModel().getColumn(2).setPreferredWidth(125);
		table_member.getColumnModel().getColumn(2).setMinWidth(125);
		table_member.getColumnModel().getColumn(2).setMaxWidth(125);
		table_member.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_member.getColumnModel().getColumn(3).setMinWidth(100);
		table_member.getColumnModel().getColumn(3).setMaxWidth(100);
		table_member.getColumnModel().getColumn(4).setPreferredWidth(100);
		table_member.getColumnModel().getColumn(4).setMinWidth(100);
		table_member.getColumnModel().getColumn(4).setMaxWidth(100);
		table_member.getColumnModel().getColumn(5).setPreferredWidth(100);
		table_member.getColumnModel().getColumn(5).setMinWidth(100);
		table_member.getColumnModel().getColumn(5).setMaxWidth(100);
		scrollPane_member.setViewportView(table_member);
		table_member.setShowVerticalLines(false);
		table_member.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_member.setRowHeight(23);
		table_member.setGridColor(SystemColor.inactiveCaptionBorder);
		table_member.setFocusable(false);
		table_member.setFocusTraversalKeysEnabled(false);
		table_member.setBorder(new LineBorder(new Color(0, 153, 255)));
		table_member.getTableHeader().setReorderingAllowed(false);
		table_member.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 12));
		table_member.getTableHeader().setBackground(new Color(0, 153, 204));
		table_member.getTableHeader().setForeground(new Color(255, 255, 255));
		table_member.setBackground(SystemColor.menu);
		table_member.setAutoCreateRowSorter(true);

		btn_member_groupDetails = new JButton("Group details");
		btn_member_groupDetails.setIcon(new ImageIcon("img/info.png"));
		btn_member_groupDetails.setMargin(new Insets(2, 2, 2, 2));
		btn_member_groupDetails.setForeground(Color.WHITE);
		btn_member_groupDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_member_groupDetails.setFocusable(false);
		btn_member_groupDetails.setFocusTraversalKeysEnabled(false);
		btn_member_groupDetails.setFocusPainted(false);
		btn_member_groupDetails.setBackground(new Color(0, 51, 102));
		btn_member_groupDetails.setBounds(61, 210, 140, 30);
		btn_member_groupDetails.addActionListener(this);
		panel_member_group.add(btn_member_groupDetails);

		btn_member_openGroup = new JButton("Open group");
		btn_member_openGroup.setIcon(new ImageIcon("img/open.png"));
		btn_member_openGroup.setMargin(new Insets(2, 2, 2, 2));
		btn_member_openGroup.setForeground(Color.WHITE);
		btn_member_openGroup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_member_openGroup.setFocusable(false);
		btn_member_openGroup.setFocusTraversalKeysEnabled(false);
		btn_member_openGroup.setFocusPainted(false);
		btn_member_openGroup.setBackground(new Color(0, 51, 102));
		btn_member_openGroup.setBounds(218, 210, 140, 30);
		btn_member_openGroup.addActionListener(this);
		panel_member_group.add(btn_member_openGroup);

		btn_member_sendMessage = new JButton("Send message");
		btn_member_sendMessage.setMargin(new Insets(2, 2, 2, 2));
		btn_member_sendMessage.setIcon(new ImageIcon("img/message.png"));
		btn_member_sendMessage.setForeground(Color.WHITE);
		btn_member_sendMessage.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_member_sendMessage.setFocusable(false);
		btn_member_sendMessage.setFocusTraversalKeysEnabled(false);
		btn_member_sendMessage.setFocusPainted(false);
		btn_member_sendMessage.setBackground(new Color(0, 51, 102));
		btn_member_sendMessage.addActionListener(this);
		btn_member_sendMessage.setBounds(376, 210, 140, 30);
		panel_member_group.add(btn_member_sendMessage);

		btn_member_leaveGroup = new JButton("Leave group");
		btn_member_leaveGroup.setMargin(new Insets(2, 2, 2, 2));
		btn_member_leaveGroup.setIcon(new ImageIcon("img/leave.png"));
		btn_member_leaveGroup.setForeground(Color.WHITE);
		btn_member_leaveGroup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_member_leaveGroup.setFocusable(false);
		btn_member_leaveGroup.setFocusTraversalKeysEnabled(false);
		btn_member_leaveGroup.setFocusPainted(false);
		btn_member_leaveGroup.setBackground(new Color(0, 51, 102));
		btn_member_leaveGroup.setBounds(531, 210, 140, 30);
		btn_member_leaveGroup.addActionListener(this);
		panel_member_group.add(btn_member_leaveGroup);

		separator_1 = new JSeparator();
		separator_1.setBounds(20, 250, 686, 10);
		panel_member_group.add(separator_1);

		btn_member_addTask = new JButton("Add task");
		btn_member_addTask.setIcon(new ImageIcon("img/add_task.png"));
		btn_member_addTask.setForeground(Color.WHITE);
		btn_member_addTask.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_member_addTask.setFocusable(false);
		btn_member_addTask.setFocusTraversalKeysEnabled(false);
		btn_member_addTask.setFocusPainted(false);
		btn_member_addTask.setBackground(new Color(0, 51, 102));
		btn_member_addTask.addActionListener(this);
		btn_member_addTask.setBounds(531, 363, 140, 30);
		panel_member_group.add(btn_member_addTask);

		txt_member_taskname = new JTextField();
		txt_member_taskname.setBounds(61, 291, 199, 30);
		panel_member_group.add(txt_member_taskname);
		txt_member_taskname.setColumns(10);

		txtpnTaskName = new JTextPane();
		txtpnTaskName.setText("Task Name");
		txtpnTaskName.setEditable(false);
		txtpnTaskName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTaskName.setBackground(SystemColor.menu);
		txtpnTaskName.setBounds(61, 271, 106, 20);
		panel_member_group.add(txtpnTaskName);

		txt_member_taskDescription = new JTextPane();
		txt_member_taskDescription.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		txt_member_taskDescription.setBounds(61, 344, 428, 49);
		panel_member_group.add(txt_member_taskDescription);

		txtpnTaskDescription = new JTextPane();
		txtpnTaskDescription.setText("Task Description");
		txtpnTaskDescription.setEditable(false);
		txtpnTaskDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTaskDescription.setBackground(SystemColor.menu);
		txtpnTaskDescription.setBounds(61, 324, 106, 20);
		panel_member_group.add(txtpnTaskDescription);

		cb_member_taskImportance = new JComboBox<String>();
		cb_member_taskImportance.setBorder(new LineBorder(new Color(171, 173, 179)));
		cb_member_taskImportance.setFont(new Font("Tahoma", Font.BOLD, 11));
		cb_member_taskImportance.setForeground(new Color(0, 0, 0));
		cb_member_taskImportance.setFocusable(false);
		cb_member_taskImportance.setFocusTraversalKeysEnabled(false);
		cb_member_taskImportance.setBackground(new Color(255, 255, 255));
		cb_member_taskImportance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cb_member_taskImportance.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5" }));
		cb_member_taskImportance.setBounds(531, 291, 67, 30);
		panel_member_group.add(cb_member_taskImportance);

		txtpnTaskImportance = new JTextPane();
		txtpnTaskImportance.setText("Task Importance");
		txtpnTaskImportance.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTaskImportance.setEditable(false);
		txtpnTaskImportance.setBackground(SystemColor.menu);
		txtpnTaskImportance.setBounds(531, 271, 140, 20);
		panel_member_group.add(txtpnTaskImportance);

		txtpnLowest = new JTextPane();
		txtpnLowest.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtpnLowest.setForeground(UIManager.getColor("Button.darkShadow"));
		txtpnLowest.setFocusable(false);
		txtpnLowest.setFocusTraversalKeysEnabled(false);
		txtpnLowest.setFocusCycleRoot(false);
		txtpnLowest.setEditable(false);
		txtpnLowest.setBorder(null);
		txtpnLowest.setBackground(UIManager.getColor("Button.background"));
		txtpnLowest.setText("1 - lowest\r\n5 - highest");
		txtpnLowest.setBounds(604, 291, 57, 30);
		panel_member_group.add(txtpnLowest);

		txtpnAddTask = new JTextPane();
		txtpnAddTask.setForeground(new Color(0, 51, 102));
		txtpnAddTask.setText("Add task");
		txtpnAddTask.setEditable(false);
		txtpnAddTask.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnAddTask.setBackground(SystemColor.menu);
		txtpnAddTask.setBounds(43, 251, 106, 20);
		panel_member_group.add(txtpnAddTask);

		icon_member_add = new JLabel("");
		icon_member_add.setIcon(new ImageIcon("img/add.png"));
		icon_member_add.setBounds(20, 251, 20, 20);
		panel_member_group.add(icon_member_add);

		msg_member_fillIn = new JTextPane();
		msg_member_fillIn.setVisible(false);
		msg_member_fillIn.setEditable(false);
		msg_member_fillIn.setFocusCycleRoot(false);
		msg_member_fillIn.setFocusTraversalKeysEnabled(false);
		msg_member_fillIn.setFocusable(false);
		msg_member_fillIn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		msg_member_fillIn.setForeground(Color.RED);
		msg_member_fillIn.setBorder(null);
		msg_member_fillIn.setBackground(UIManager.getColor("Button.background"));
		msg_member_fillIn.setText("Please fill in all the boxex marked with \"*\"");
		msg_member_fillIn.setBounds(507, 330, 164, 30);
		panel_member_group.add(msg_member_fillIn);

		msg_member_taskName = new JTextField();
		msg_member_taskName.setVisible(false);
		msg_member_taskName.setBounds(259, 296, 14, 20);
		panel_member_group.add(msg_member_taskName);
		msg_member_taskName.setForeground(Color.RED);
		msg_member_taskName.setHorizontalAlignment(SwingConstants.CENTER);
		msg_member_taskName.setBorder(null);
		msg_member_taskName.setBackground(UIManager.getColor("Button.background"));
		msg_member_taskName.setText("*");
		msg_member_taskName.setColumns(10);

		msg_member_taskDeadline = new JTextField();
		msg_member_taskDeadline.setVisible(false);
		msg_member_taskDeadline.setText("*");
		msg_member_taskDeadline.setHorizontalAlignment(SwingConstants.CENTER);
		msg_member_taskDeadline.setForeground(Color.RED);
		msg_member_taskDeadline.setColumns(10);
		msg_member_taskDeadline.setBorder(null);
		msg_member_taskDeadline.setBackground(SystemColor.menu);
		msg_member_taskDeadline.setBounds(502, 296, 14, 20);
		panel_member_group.add(msg_member_taskDeadline);

		msg_member_taskImportance = new JTextField();
		msg_member_taskImportance.setVisible(false);
		msg_member_taskImportance.setText("*");
		msg_member_taskImportance.setHorizontalAlignment(SwingConstants.CENTER);
		msg_member_taskImportance.setForeground(Color.RED);
		msg_member_taskImportance.setColumns(10);
		msg_member_taskImportance.setBorder(null);
		msg_member_taskImportance.setBackground(SystemColor.menu);
		msg_member_taskImportance.setBounds(488, 369, 14, 20);
		panel_member_group.add(msg_member_taskImportance);

		label_4 = new JLabel("Due date ( d/m/y )");
		label_4.setBounds(283, 274, 147, 14);
		panel_member_group.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		cb_member_day = new JComboBox<String>();
		cb_member_day.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		cb_member_day.setBounds(283, 294, 48, 25);
		panel_member_group.add(cb_member_day);
		cb_member_day.setMaximumRowCount(5);
		cb_member_day.setFocusable(false);
		cb_member_day.setFocusTraversalKeysEnabled(false);
		cb_member_day.setBorder(null);
		cb_member_day.setBackground(SystemColor.menu);

		cb_member_month = new JComboBox<String>();
		cb_member_month.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

		cb_member_month.setBounds(341, 294, 89, 25);
		panel_member_group.add(cb_member_month);
		cb_member_month.setMaximumRowCount(5);
		cb_member_month.setFocusable(false);
		cb_member_month.setFocusTraversalKeysEnabled(false);
		cb_member_month.setBorder(null);
		cb_member_month.setBackground(SystemColor.menu);

		cb_member_year = new JComboBox<String>();
		cb_member_year.setModel(new DefaultComboBoxModel<String>(new String[] { "2017", "2018", "2019", "2020" }));
		cb_member_year.setBounds(438, 294, 59, 25);
		panel_member_group.add(cb_member_year);
		cb_member_year.setMaximumRowCount(5);
		cb_member_year.setFocusable(false);
		cb_member_year.setFocusTraversalKeysEnabled(false);
		cb_member_year.setBorder(null);
		cb_member_year.setBackground(SystemColor.menu);
	}

	/**
	 * Create cardPanel admin
	 */
	private void create_cardPanel_admin() {
		panel_admin_groups = new JPanel();
		panel_admin_groups.setLayout(null);
		start_cardLayoutPanel.add(panel_admin_groups, "ADMIN_GROUP");

		lbl_admin_groups = new JTextField();
		lbl_admin_groups.setText("     Admin group");
		lbl_admin_groups.setForeground(Color.WHITE);
		lbl_admin_groups.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_admin_groups.setFocusable(false);
		lbl_admin_groups.setFocusTraversalKeysEnabled(false);
		lbl_admin_groups.setEditable(false);
		lbl_admin_groups.setColumns(10);
		lbl_admin_groups.setBorder(null);
		lbl_admin_groups.setBackground(new Color(0, 153, 204));
		lbl_admin_groups.setBounds(0, 0, 732, 30);
		panel_admin_groups.add(lbl_admin_groups);

		txtpnTheListOf_1 = new JTextPane();
		txtpnTheListOf_1.setText("The list of groups in which you are admin");
		txtpnTheListOf_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTheListOf_1.setEditable(false);
		txtpnTheListOf_1.setFocusable(false);
		txtpnTheListOf_1.setBackground(SystemColor.menu);
		txtpnTheListOf_1.setBounds(10, 41, 342, 20);
		panel_admin_groups.add(txtpnTheListOf_1);

		scrollPane_admin_groups = new JScrollPane();
		scrollPane_admin_groups.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_admin_groups.setForeground(SystemColor.menu);
		scrollPane_admin_groups.setFocusable(false);
		scrollPane_admin_groups.setFocusTraversalKeysEnabled(false);
		scrollPane_admin_groups.setBorder(new LineBorder(new Color(0, 153, 255)));
		scrollPane_admin_groups.setBounds(20, 68, 355, 142);
		panel_admin_groups.add(scrollPane_admin_groups);

		table_admin_groups = new JTable();
		table_admin_groups.addMouseListener(this);
		table_admin_groups.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "Group ID", "Group name", "Nr. of tasks", "Nr. of members" }));
		table_admin_groups.getColumnModel().getColumn(0).setMinWidth(75);
		table_admin_groups.getColumnModel().getColumn(0).setMaxWidth(75);
		table_admin_groups.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_admin_groups.getColumnModel().getColumn(1).setMinWidth(120);
		table_admin_groups.setShowVerticalLines(false);
		table_admin_groups.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_admin_groups.setRowHeight(23);
		table_admin_groups.setGridColor(SystemColor.inactiveCaptionBorder);
		table_admin_groups.setFocusable(false);
		table_admin_groups.setFocusTraversalKeysEnabled(false);
		table_admin_groups.setBorder(null);
		table_admin_groups.setBackground(SystemColor.menu);
		table_admin_groups.setAutoCreateRowSorter(true);
		table_admin_groups.getTableHeader().setReorderingAllowed(false);
		table_admin_groups.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 11));
		table_admin_groups.getTableHeader().setBackground(new Color(0, 153, 204));
		table_admin_groups.getTableHeader().setForeground(new Color(255, 255, 255));
		scrollPane_admin_groups.setViewportView(table_admin_groups);

		txtpnTheListOf_2 = new JTextPane();
		txtpnTheListOf_2.setText("The list of members in the selected group");
		txtpnTheListOf_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnTheListOf_2.setBackground(SystemColor.menu);
		txtpnTheListOf_2.setEditable(false);
		txtpnTheListOf_2.setFocusable(false);
		txtpnTheListOf_2.setBounds(10, 221, 342, 20);
		panel_admin_groups.add(txtpnTheListOf_2);

		scrollPane_admin_members = new JScrollPane();
		scrollPane_admin_members.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_admin_members.setForeground(SystemColor.menu);
		scrollPane_admin_members.setFocusable(false);
		scrollPane_admin_members.setFocusTraversalKeysEnabled(false);
		scrollPane_admin_members.setBorder(new LineBorder(new Color(0, 51, 102)));
		scrollPane_admin_members.setBounds(20, 248, 355, 142);
		panel_admin_groups.add(scrollPane_admin_members);

		table_admin_members = new JTable();
		table_admin_members
				.setModel(new DefaultTableModel(
						new Object[][] { { null, null, null }, { null, null, null }, { null, null, null },
								{ null, null, null }, { null, null, null }, },
						new String[] { "Username", "Nr Active tasks", "Nr. Tasks done" }));
		table_admin_members.setShowVerticalLines(false);
		table_admin_members.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_admin_members.setRowHeight(23);
		table_admin_members.setGridColor(SystemColor.inactiveCaptionBorder);
		table_admin_members.setFocusable(false);
		table_admin_members.setFocusTraversalKeysEnabled(false);
		table_admin_members.setBorder(null);
		table_admin_members.setBackground(SystemColor.menu);
		table_admin_members.getTableHeader().setReorderingAllowed(false);
		table_admin_members.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 11));
		table_admin_members.getTableHeader().setBackground(new Color(0, 51, 102));
		table_admin_members.getTableHeader().setForeground(new Color(255, 255, 255));
		table_admin_members.setAutoCreateRowSorter(true);
		scrollPane_admin_members.setViewportView(table_admin_members);

		btn_admin_viewDetails = new JButton("View info");
		btn_admin_viewDetails.setIcon(new ImageIcon("img/info.png"));
		btn_admin_viewDetails.setBounds(398, 68, 140, 30);
		panel_admin_groups.add(btn_admin_viewDetails);
		btn_admin_viewDetails.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_viewDetails.setForeground(Color.WHITE);
		btn_admin_viewDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_viewDetails.setFocusable(false);
		btn_admin_viewDetails.setFocusTraversalKeysEnabled(false);
		btn_admin_viewDetails.setFocusPainted(false);
		btn_admin_viewDetails.addActionListener(this);
		btn_admin_viewDetails.setBackground(new Color(0, 153, 255));

		btn_admin_editInfo = new JButton("Edit info");
		btn_admin_editInfo.setIcon(new ImageIcon("img/edit.png"));
		btn_admin_editInfo.setBounds(560, 68, 140, 30);
		panel_admin_groups.add(btn_admin_editInfo);
		btn_admin_editInfo.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_editInfo.setForeground(Color.WHITE);
		btn_admin_editInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_editInfo.setFocusable(false);
		btn_admin_editInfo.setFocusTraversalKeysEnabled(false);
		btn_admin_editInfo.setFocusPainted(false);
		btn_admin_editInfo.addActionListener(this);
		btn_admin_editInfo.setBackground(new Color(0, 153, 255));

		btn_admin_openGroup = new JButton("Open group");
		btn_admin_openGroup.setIcon(new ImageIcon("img/open.png"));
		btn_admin_openGroup.setBounds(398, 109, 140, 30);
		panel_admin_groups.add(btn_admin_openGroup);
		btn_admin_openGroup.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_openGroup.setForeground(Color.WHITE);
		btn_admin_openGroup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_openGroup.setFocusable(false);
		btn_admin_openGroup.setFocusTraversalKeysEnabled(false);
		btn_admin_openGroup.setFocusPainted(false);
		btn_admin_openGroup.addActionListener(this);
		btn_admin_openGroup.setBackground(new Color(0, 153, 255));

		btn_admin_makeAdmin = new JButton("Make admin");
		btn_admin_makeAdmin.setIcon(new ImageIcon("img/edit.png"));
		btn_admin_makeAdmin.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_makeAdmin.setForeground(Color.WHITE);
		btn_admin_makeAdmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_makeAdmin.setFocusable(false);
		btn_admin_makeAdmin.setFocusTraversalKeysEnabled(false);
		btn_admin_makeAdmin.setFocusPainted(false);
		btn_admin_makeAdmin.setBackground(new Color(0, 153, 255));
		btn_admin_makeAdmin.setBounds(560, 109, 140, 30);
		btn_admin_makeAdmin.addActionListener(this);
		panel_admin_groups.add(btn_admin_makeAdmin);

		btn_admin_deleteGroup = new JButton("Delete group");
		btn_admin_deleteGroup.setIcon(new ImageIcon("img/delete.png"));
		btn_admin_deleteGroup.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_deleteGroup.setForeground(Color.WHITE);
		btn_admin_deleteGroup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_deleteGroup.setFocusable(false);
		btn_admin_deleteGroup.setFocusTraversalKeysEnabled(false);
		btn_admin_deleteGroup.setFocusPainted(false);
		btn_admin_deleteGroup.setBackground(new Color(0, 153, 255));
		btn_admin_deleteGroup.setBounds(560, 150, 140, 30);
		btn_admin_deleteGroup.addActionListener(this);
		panel_admin_groups.add(btn_admin_deleteGroup);

		btn_admin_removeMember = new JButton("Remove");
		btn_admin_removeMember.setIcon(new ImageIcon("img/delete.png"));
		btn_admin_removeMember.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_removeMember.setForeground(Color.WHITE);
		btn_admin_removeMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_removeMember.setFocusable(false);
		btn_admin_removeMember.setFocusTraversalKeysEnabled(false);
		btn_admin_removeMember.setFocusPainted(false);
		btn_admin_removeMember.setBackground(new Color(0, 51, 102));
		btn_admin_removeMember.setBounds(398, 248, 140, 30);
		btn_admin_removeMember.addActionListener(this);
		panel_admin_groups.add(btn_admin_removeMember);

		btn_admin_messageMember = new JButton("Message");
		btn_admin_messageMember.setIcon(new ImageIcon("img/message.png"));
		btn_admin_messageMember.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_messageMember.setForeground(Color.WHITE);
		btn_admin_messageMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_messageMember.setFocusable(false);
		btn_admin_messageMember.setFocusTraversalKeysEnabled(false);
		btn_admin_messageMember.setFocusPainted(false);
		btn_admin_messageMember.setBackground(new Color(0, 51, 102));
		btn_admin_messageMember.setBounds(560, 248, 140, 30);
		btn_admin_messageMember.addActionListener(this);
		panel_admin_groups.add(btn_admin_messageMember);

		btn_admin_inviteMember = new JButton("Invite");
		btn_admin_inviteMember.setIcon(new ImageIcon("img/add_member.png"));
		btn_admin_inviteMember.setMargin(new Insets(2, 2, 2, 2));
		btn_admin_inviteMember.setForeground(Color.WHITE);
		btn_admin_inviteMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_admin_inviteMember.setFocusable(false);
		btn_admin_inviteMember.setFocusTraversalKeysEnabled(false);
		btn_admin_inviteMember.setFocusPainted(false);
		btn_admin_inviteMember.setBackground(new Color(0, 51, 102));
		btn_admin_inviteMember.setBounds(599, 289, 101, 30);
		btn_admin_inviteMember.addActionListener(this);
		panel_admin_groups.add(btn_admin_inviteMember);

		txt_admin_searchUsers = new JTextField();
		txt_admin_searchUsers.setBounds(398, 289, 179, 30);
		panel_admin_groups.add(txt_admin_searchUsers);
		txt_admin_searchUsers.setColumns(10);

		separator = new JSeparator();
		separator.setBounds(399, 210, 301, 12);
		panel_admin_groups.add(separator);
	}

	/**
	 * Create cardPanel localTasks
	 */
	private void create_cardPanel_localTasks() {
		panel_local_tasks = new JPanel();
		panel_local_tasks.setLayout(null);
		start_cardLayoutPanel.add(panel_local_tasks, "LOCAL_TASKS");

		lbl_myTasks = new JTextField();
		lbl_myTasks.setSelectionColor(new Color(0, 51, 102));
		lbl_myTasks.setText("     My tasks");
		lbl_myTasks.setForeground(Color.WHITE);
		lbl_myTasks.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_myTasks.setFocusable(false);
		lbl_myTasks.setFocusTraversalKeysEnabled(false);
		lbl_myTasks.setEditable(false);
		lbl_myTasks.setColumns(10);
		lbl_myTasks.setBorder(null);
		lbl_myTasks.setBackground(new Color(0, 51, 102));
		lbl_myTasks.setBounds(0, 0, 732, 30);
		panel_local_tasks.add(lbl_myTasks);

		scrollPane_localTasks = new JScrollPane();
		scrollPane_localTasks.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_localTasks.setForeground(SystemColor.menu);
		scrollPane_localTasks.setFocusable(false);
		scrollPane_localTasks.setFocusTraversalKeysEnabled(false);
		scrollPane_localTasks.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		scrollPane_localTasks.setBounds(23, 44, 518, 194);
		panel_local_tasks.add(scrollPane_localTasks);

		table_local_tasks = new JTable();
		table_local_tasks.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Task", "Status", "Priority", "Time left" }));

		table_local_tasks.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_local_tasks.getColumnModel().getColumn(1).setMinWidth(100);
		table_local_tasks.getColumnModel().getColumn(1).setMaxWidth(100);
		table_local_tasks.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_local_tasks.getColumnModel().getColumn(2).setMinWidth(100);
		table_local_tasks.getColumnModel().getColumn(2).setMaxWidth(100);
		table_local_tasks.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_local_tasks.getColumnModel().getColumn(3).setMinWidth(100);
		table_local_tasks.getColumnModel().getColumn(3).setMaxWidth(100);

		table_local_tasks.setShowVerticalLines(false);
		table_local_tasks.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_local_tasks.setRowHeight(23);
		table_local_tasks.setGridColor(SystemColor.inactiveCaptionBorder);
		table_local_tasks.setFocusable(false);
		table_local_tasks.setFocusTraversalKeysEnabled(false);
		table_local_tasks.setBorder(new LineBorder(new Color(0, 51, 102)));
		table_local_tasks.setBackground(SystemColor.menu);
		table_local_tasks.setAutoCreateRowSorter(true);
		table_local_tasks.getTableHeader().setReorderingAllowed(false);
		table_local_tasks.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 11));
		table_local_tasks.getTableHeader().setBackground(new Color(0, 51, 102));
		table_local_tasks.getTableHeader().setForeground(new Color(255, 255, 255));
		scrollPane_localTasks.setViewportView(table_local_tasks);

		btn_local_viewDetails = new JButton("View details");
		btn_local_viewDetails.setIcon(new ImageIcon("img/info.png"));
		btn_local_viewDetails.setMargin(new Insets(2, 2, 2, 2));
		btn_local_viewDetails.setForeground(Color.WHITE);
		btn_local_viewDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_local_viewDetails.setFocusable(false);
		btn_local_viewDetails.setFocusTraversalKeysEnabled(false);
		btn_local_viewDetails.setFocusPainted(false);
		btn_local_viewDetails.setBorder(null);
		btn_local_viewDetails.setBackground(new Color(0, 51, 102));
		btn_local_viewDetails.setBounds(562, 41, 147, 30);
		btn_local_viewDetails.addActionListener(this);
		panel_local_tasks.add(btn_local_viewDetails);

		btn_local_quitTask = new JButton("Quit task");
		btn_local_quitTask.setIcon(new ImageIcon("img/leave.png"));
		btn_local_quitTask.setMargin(new Insets(2, 2, 2, 2));
		btn_local_quitTask.setForeground(Color.WHITE);
		btn_local_quitTask.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_local_quitTask.setFocusable(false);
		btn_local_quitTask.setFocusTraversalKeysEnabled(false);
		btn_local_quitTask.setFocusPainted(false);
		btn_local_quitTask.setBorder(null);
		btn_local_quitTask.setBackground(new Color(0, 51, 102));
		btn_local_quitTask.setBounds(562, 123, 147, 30);
		btn_local_quitTask.addActionListener(this);
		panel_local_tasks.add(btn_local_quitTask);

		btn_local_requestTime = new JButton("Reschedule");
		btn_local_requestTime.setIcon(new ImageIcon("img/reschedule.png"));
		btn_local_requestTime.setMargin(new Insets(2, 2, 2, 2));
		btn_local_requestTime.setForeground(Color.WHITE);
		btn_local_requestTime.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_local_requestTime.setFocusable(false);
		btn_local_requestTime.setFocusTraversalKeysEnabled(false);
		btn_local_requestTime.setFocusPainted(false);
		btn_local_requestTime.setBorder(null);
		btn_local_requestTime.setBackground(new Color(0, 51, 102));
		btn_local_requestTime.setBounds(562, 164, 147, 30);
		btn_local_requestTime.addActionListener(this);
		panel_local_tasks.add(btn_local_requestTime);

		btn_local_submitTask = new JButton("Submit task");
		btn_local_submitTask.setIcon(new ImageIcon("img/ok.png"));
		btn_local_submitTask.setMargin(new Insets(2, 2, 2, 2));
		btn_local_submitTask.setForeground(Color.WHITE);
		btn_local_submitTask.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_local_submitTask.setFocusable(false);
		btn_local_submitTask.setFocusTraversalKeysEnabled(false);
		btn_local_submitTask.setFocusPainted(false);
		btn_local_submitTask.setBorder(null);
		btn_local_submitTask.setBackground(new Color(0, 51, 102));
		btn_local_submitTask.setBounds(562, 205, 147, 30);
		btn_local_submitTask.addActionListener(this);
		panel_local_tasks.add(btn_local_submitTask);

		btn_local_addTask = new JButton("Add local task");
		btn_local_addTask.setIcon(new ImageIcon("img/add_task.png"));
		btn_local_addTask.setMargin(new Insets(2, 2, 2, 2));
		btn_local_addTask.setForeground(Color.WHITE);
		btn_local_addTask.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_local_addTask.setFocusable(false);
		btn_local_addTask.setFocusTraversalKeysEnabled(false);
		btn_local_addTask.setFocusPainted(false);
		btn_local_addTask.setBorder(null);
		btn_local_addTask.setBackground(new Color(0, 51, 102));
		btn_local_addTask.setBounds(511, 358, 198, 30);
		btn_local_addTask.addActionListener(this);
		panel_local_tasks.add(btn_local_addTask);

		btn_local_updateTask = new JButton("Update task");
		btn_local_updateTask.setIcon(new ImageIcon("img/update.png"));
		btn_local_updateTask.setMargin(new Insets(2, 2, 2, 2));
		btn_local_updateTask.setForeground(Color.WHITE);
		btn_local_updateTask.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_local_updateTask.setFocusable(false);
		btn_local_updateTask.setFocusTraversalKeysEnabled(false);
		btn_local_updateTask.setFocusPainted(false);
		btn_local_updateTask.setBorder(null);
		btn_local_updateTask.setBackground(new Color(0, 51, 102));
		btn_local_updateTask.setBounds(562, 82, 147, 30);
		btn_local_updateTask.addActionListener(this);
		panel_local_tasks.add(btn_local_updateTask);

		txt_local_taskName = new JTextField();
		txt_local_taskName.setBounds(33, 264, 214, 30);
		panel_local_tasks.add(txt_local_taskName);
		txt_local_taskName.setColumns(10);

		lblTaskName = new JLabel("Task name");
		lblTaskName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTaskName.setBounds(33, 249, 147, 14);
		panel_local_tasks.add(lblTaskName);

		lblDueDate = new JLabel("Due date ( d/m/y )");
		lblDueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDueDate.setBounds(33, 301, 147, 14);
		panel_local_tasks.add(lblDueDate);

		txt_local_taskDescription = new JTextPane();
		txt_local_taskDescription.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		txt_local_taskDescription.setBounds(291, 264, 418, 83);
		panel_local_tasks.add(txt_local_taskDescription);

		lblTaskDescription = new JLabel("Task description");
		lblTaskDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTaskDescription.setBounds(291, 249, 147, 14);
		panel_local_tasks.add(lblTaskDescription);

		lblTaskPriority = new JLabel("Task priority");
		lblTaskPriority.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTaskPriority.setBounds(33, 360, 89, 14);
		panel_local_tasks.add(lblTaskPriority);

		cb_local_taskPriority = new JComboBox<String>();
		cb_local_taskPriority.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5" }));
		cb_local_taskPriority.setBounds(121, 352, 59, 30);
		panel_local_tasks.add(cb_local_taskPriority);

		textPane_1 = new JTextPane();
		textPane_1.setText("1 - lowest\r\n5 - highest");
		textPane_1.setForeground(SystemColor.controlDkShadow);
		textPane_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBorder(null);
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(190, 352, 57, 30);
		panel_local_tasks.add(textPane_1);

		cb_local_month = new JComboBox<String>();
		cb_local_month.setMaximumRowCount(5);
		cb_local_month.setFocusTraversalKeysEnabled(false);
		cb_local_month.setFocusable(false);
		cb_local_month.setBorder(null);
		cb_local_month.setBackground(SystemColor.menu);
		cb_local_month.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cb_local_month.setBounds(91, 316, 89, 25);
		panel_local_tasks.add(cb_local_month);

		cb_local_day = new JComboBox<String>();
		cb_local_day.setMaximumRowCount(5);
		cb_local_day.setFocusTraversalKeysEnabled(false);
		cb_local_day.setFocusable(false);
		cb_local_day.setBorder(null);
		cb_local_day.setBackground(SystemColor.menu);
		cb_local_day.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		cb_local_day.setBounds(33, 316, 48, 25);
		panel_local_tasks.add(cb_local_day);

		cb_local_year = new JComboBox<String>();
		cb_local_year.setMaximumRowCount(5);
		cb_local_year.setFocusTraversalKeysEnabled(false);
		cb_local_year.setFocusable(false);
		cb_local_year.setBorder(null);
		cb_local_year.setBackground(SystemColor.menu);
		cb_local_year.setModel(new DefaultComboBoxModel<String>(new String[] { "2017", "2018", "2019", "2020" }));
		cb_local_year.setBounds(188, 316, 59, 25);
		panel_local_tasks.add(cb_local_year);

		txtpnPleaseFillIn = new JTextPane();
		txtpnPleaseFillIn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtpnPleaseFillIn.setForeground(Color.RED);
		txtpnPleaseFillIn.setEditable(false);
		txtpnPleaseFillIn.setFocusCycleRoot(false);
		txtpnPleaseFillIn.setFocusTraversalKeysEnabled(false);
		txtpnPleaseFillIn.setFocusable(false);
		txtpnPleaseFillIn.setVisible(false);
		txtpnPleaseFillIn.setBackground(UIManager.getColor("Button.background"));
		txtpnPleaseFillIn.setText("Please fill in all the boxex marked with \"*\"");
		txtpnPleaseFillIn.setBounds(291, 354, 210, 34);
		panel_local_tasks.add(txtpnPleaseFillIn);

		verify_local_taskName = new JTextArea();
		verify_local_taskName.setVisible(false);
		verify_local_taskName.setFocusTraversalKeysEnabled(false);
		verify_local_taskName.setFocusable(false);
		verify_local_taskName.setEditable(false);
		verify_local_taskName.setBackground(UIManager.getColor("Button.background"));
		verify_local_taskName.setForeground(Color.RED);
		verify_local_taskName.setText("*");
		verify_local_taskName.setBounds(22, 245, 12, 22);
		panel_local_tasks.add(verify_local_taskName);

		verify_local_dueDate = new JTextArea();
		verify_local_dueDate.setVisible(false);
		verify_local_dueDate.setText("*");
		verify_local_dueDate.setForeground(Color.RED);
		verify_local_dueDate.setFocusable(false);
		verify_local_dueDate.setFocusTraversalKeysEnabled(false);
		verify_local_dueDate.setEditable(false);
		verify_local_dueDate.setBackground(SystemColor.menu);
		verify_local_dueDate.setBounds(22, 297, 12, 22);
		panel_local_tasks.add(verify_local_dueDate);

		verify_local_taskPriority = new JTextArea();
		verify_local_taskPriority.setVisible(false);
		verify_local_taskPriority.setText("*");
		verify_local_taskPriority.setForeground(Color.RED);
		verify_local_taskPriority.setFocusable(false);
		verify_local_taskPriority.setFocusTraversalKeysEnabled(false);
		verify_local_taskPriority.setEditable(false);
		verify_local_taskPriority.setBackground(SystemColor.menu);
		verify_local_taskPriority.setBounds(22, 355, 12, 22);
		panel_local_tasks.add(verify_local_taskPriority);

		verify_local_taskDescription = new JTextArea();
		verify_local_taskDescription.setVisible(false);
		verify_local_taskDescription.setText("*");
		verify_local_taskDescription.setForeground(Color.RED);
		verify_local_taskDescription.setFocusable(false);
		verify_local_taskDescription.setFocusTraversalKeysEnabled(false);
		verify_local_taskDescription.setEditable(false);
		verify_local_taskDescription.setBackground(SystemColor.menu);
		verify_local_taskDescription.setBounds(282, 245, 12, 22);
		panel_local_tasks.add(verify_local_taskDescription);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(23, 240, 685, 2);
		panel_local_tasks.add(separator_2);
	}

	/**
	 * Create cardPanel group Tasks
	 */
	private void create_cardPanel_groupTasks() {
		panel_group_tasks = new JPanel();
		panel_group_tasks.setLayout(null);
		start_cardLayoutPanel.add(panel_group_tasks, "GROUP_TASKS");

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(23, 274, 684, 8);
		panel_group_tasks.add(separator_2);

		btn_groupTask_update = new JButton("Update");
		btn_groupTask_update.setIcon(new ImageIcon("img/update.png"));
		btn_groupTask_update.setMargin(new Insets(2, 2, 2, 2));
		btn_groupTask_update.setForeground(Color.WHITE);
		btn_groupTask_update.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_groupTask_update.setFocusable(false);
		btn_groupTask_update.setFocusTraversalKeysEnabled(false);
		btn_groupTask_update.setFocusPainted(false);
		btn_groupTask_update.setBorder(null);
		btn_groupTask_update.setBackground(new Color(0, 153, 204));
		btn_groupTask_update.setBounds(561, 322, 147, 30);
		btn_groupTask_update.addActionListener(this);
		panel_group_tasks.add(btn_groupTask_update);

		btn_groupTask_quit = new JButton("Quit");
		btn_groupTask_quit.setIcon(new ImageIcon("img/leave.png"));
		btn_groupTask_quit.setMargin(new Insets(2, 2, 2, 2));
		btn_groupTask_quit.setForeground(Color.WHITE);
		btn_groupTask_quit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_groupTask_quit.setFocusable(false);
		btn_groupTask_quit.setFocusTraversalKeysEnabled(false);
		btn_groupTask_quit.setFocusPainted(false);
		btn_groupTask_quit.setBorder(null);
		btn_groupTask_quit.setBackground(new Color(0, 153, 204));
		btn_groupTask_quit.setBounds(404, 322, 147, 30);
		btn_groupTask_quit.addActionListener(this);
		panel_group_tasks.add(btn_groupTask_quit);

		btn_groupTask_submit = new JButton("Submit");
		btn_groupTask_submit.setIcon(new ImageIcon("img/ok.png"));
		btn_groupTask_submit.setMargin(new Insets(2, 2, 2, 2));
		btn_groupTask_submit.setForeground(Color.WHITE);
		btn_groupTask_submit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_groupTask_submit.setFocusable(false);
		btn_groupTask_submit.setFocusTraversalKeysEnabled(false);
		btn_groupTask_submit.setFocusPainted(false);
		btn_groupTask_submit.setBorder(null);
		btn_groupTask_submit.setBackground(new Color(0, 153, 204));
		btn_groupTask_submit.setBounds(404, 281, 147, 30);
		btn_groupTask_submit.addActionListener(this);
		panel_group_tasks.add(btn_groupTask_submit);

		btn_groupTask_details = new JButton("Details");
		btn_groupTask_details.setIcon(new ImageIcon("img/info.png"));
		btn_groupTask_details.setMargin(new Insets(2, 2, 2, 2));
		btn_groupTask_details.setForeground(Color.WHITE);
		btn_groupTask_details.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_groupTask_details.setFocusable(false);
		btn_groupTask_details.setFocusTraversalKeysEnabled(false);
		btn_groupTask_details.setFocusPainted(false);
		btn_groupTask_details.setBorder(null);
		btn_groupTask_details.setBackground(new Color(0, 153, 204));
		btn_groupTask_details.setBounds(561, 281, 147, 30);
		btn_groupTask_details.addActionListener(this);
		panel_group_tasks.add(btn_groupTask_details);

		btn_groupTask_reschedule = new JButton("Reschedule");
		btn_groupTask_reschedule.setIcon(new ImageIcon("img/reschedule.png"));
		btn_groupTask_reschedule.setMargin(new Insets(2, 2, 2, 2));
		btn_groupTask_reschedule.setForeground(Color.WHITE);
		btn_groupTask_reschedule.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_groupTask_reschedule.setFocusable(false);
		btn_groupTask_reschedule.setFocusTraversalKeysEnabled(false);
		btn_groupTask_reschedule.setFocusPainted(false);
		btn_groupTask_reschedule.setBorder(null);
		btn_groupTask_reschedule.setBackground(new Color(0, 153, 204));
		btn_groupTask_reschedule.setBounds(561, 363, 147, 30);
		btn_groupTask_reschedule.addActionListener(this);
		panel_group_tasks.add(btn_groupTask_reschedule);

		lbl_taskToDo = new JTextField();
		lbl_taskToDo.setText("     Tasks to be done");
		lbl_taskToDo.setSelectionColor(new Color(0, 51, 102));
		lbl_taskToDo.setForeground(Color.WHITE);
		lbl_taskToDo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_taskToDo.setFocusable(false);
		lbl_taskToDo.setFocusTraversalKeysEnabled(false);
		lbl_taskToDo.setEditable(false);
		lbl_taskToDo.setColumns(10);
		lbl_taskToDo.setBorder(null);
		lbl_taskToDo.setBackground(new Color(0, 51, 102));
		lbl_taskToDo.setBounds(0, 0, 732, 30);
		panel_group_tasks.add(lbl_taskToDo);

		JScrollPane scrollPane_groupTasks = new JScrollPane();
		scrollPane_groupTasks.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_groupTasks.setForeground(SystemColor.menu);
		scrollPane_groupTasks.setFocusable(false);
		scrollPane_groupTasks.setFocusTraversalKeysEnabled(false);
		scrollPane_groupTasks.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		scrollPane_groupTasks.setBounds(23, 51, 684, 148);
		panel_group_tasks.add(scrollPane_groupTasks);

		table_groupTask = new JTable();
		table_groupTask.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "Task", "Group id", "Group name", "Status", "Priority", "Time left" }));
		table_groupTask.getColumnModel().getColumn(1).setMinWidth(75);
		table_groupTask.getColumnModel().getColumn(1).setMaxWidth(75);
		table_groupTask.getColumnModel().getColumn(2).setPreferredWidth(125);
		table_groupTask.getColumnModel().getColumn(2).setMinWidth(125);
		table_groupTask.getColumnModel().getColumn(2).setMaxWidth(125);
		table_groupTask.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_groupTask.getColumnModel().getColumn(3).setMinWidth(100);
		table_groupTask.getColumnModel().getColumn(3).setMaxWidth(100);
		table_groupTask.getColumnModel().getColumn(4).setMinWidth(75);
		table_groupTask.getColumnModel().getColumn(4).setMaxWidth(75);
		table_groupTask.getColumnModel().getColumn(5).setPreferredWidth(100);
		table_groupTask.getColumnModel().getColumn(5).setMinWidth(100);
		table_groupTask.getColumnModel().getColumn(5).setMaxWidth(100);
		table_groupTask.setShowVerticalLines(false);
		table_groupTask.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_groupTask.setRowHeight(23);
		table_groupTask.setGridColor(SystemColor.inactiveCaptionBorder);
		table_groupTask.setFocusable(false);
		table_groupTask.setFocusTraversalKeysEnabled(false);
		table_groupTask.setBorder(new LineBorder(new Color(0, 51, 102)));
		table_groupTask.setBackground(SystemColor.menu);
		table_groupTask.getTableHeader().setReorderingAllowed(false);
		table_groupTask.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 11));
		table_groupTask.getTableHeader().setBackground(new Color(0, 51, 102));
		table_groupTask.getTableHeader().setForeground(new Color(255, 255, 255));
		table_groupTask.setAutoCreateRowSorter(true);
		table_groupTask.addMouseListener(this);
		scrollPane_groupTasks.setViewportView(table_groupTask);

		scrollPane_8 = new JScrollPane();
		scrollPane_8.setBorder(new LineBorder(new Color(0, 51, 102), 3));
		scrollPane_8.setBounds(24, 281, 370, 71);
		panel_group_tasks.add(scrollPane_8);

		txt_groupTask_comments = new JTextPane();
		txt_groupTask_comments.setFocusable(false);
		txt_groupTask_comments.setFocusTraversalKeysEnabled(false);
		txt_groupTask_comments.setFocusCycleRoot(false);
		txt_groupTask_comments.setText("");
		txt_groupTask_comments.setEditable(false);
		scrollPane_8.setViewportView(txt_groupTask_comments);
		txt_groupTask_comments.setBorder(null);

		txt_groupTask_addComment = new JTextField();
		txt_groupTask_addComment.setFocusCycleRoot(true);
		txt_groupTask_addComment.setFocusTraversalPolicyProvider(true);
		txt_groupTask_addComment.setBorder(new LineBorder(new Color(0, 51, 102), 3));
		txt_groupTask_addComment.setBounds(24, 363, 370, 30);
		panel_group_tasks.add(txt_groupTask_addComment);
		txt_groupTask_addComment.setColumns(10);

		btn_groupTask_comment = new JButton("Add comment");
		btn_groupTask_comment.setIcon(new ImageIcon("img/message.png"));
		btn_groupTask_comment.setMargin(new Insets(2, 2, 2, 2));
		btn_groupTask_comment.setForeground(Color.WHITE);
		btn_groupTask_comment.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_groupTask_comment.setFocusable(false);
		btn_groupTask_comment.setFocusTraversalKeysEnabled(false);
		btn_groupTask_comment.setFocusPainted(false);
		btn_groupTask_comment.setBorder(null);
		btn_groupTask_comment.setBackground(new Color(0, 51, 102));
		btn_groupTask_comment.setBounds(404, 363, 147, 30);
		btn_groupTask_comment.addActionListener(this);
		panel_group_tasks.add(btn_groupTask_comment);

		txt_gt_name = new JTextField();
		txt_gt_name.setBounds(38, 210, 193, 25);
		panel_group_tasks.add(txt_gt_name);
		txt_gt_name.setColumns(10);

		scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(269, 210, 305, 53);
		panel_group_tasks.add(scrollPane_9);

		txt_gt_description = new JTextPane();
		txt_gt_description.setLocation(0, 210);
		scrollPane_9.setViewportView(txt_gt_description);

		lblTaskName_2 = new JLabel("Task name");
		lblTaskName_2.setBounds(38, 196, 69, 14);
		panel_group_tasks.add(lblTaskName_2);

		lblTaskDescription_3 = new JLabel("Task description");
		lblTaskDescription_3.setBounds(269, 196, 103, 14);
		panel_group_tasks.add(lblTaskDescription_3);

		lblTaskPriority_1 = new JLabel("Task priority");
		lblTaskPriority_1.setBounds(612, 196, 80, 14);
		panel_group_tasks.add(lblTaskPriority_1);

		txt_cb_priority = new JComboBox<String>();
		txt_cb_priority.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5" }));
		txt_cb_priority.setBounds(612, 210, 80, 20);
		panel_group_tasks.add(txt_cb_priority);
	}

	/**
	 * Create panel settings
	 */
	private void create_settings_panel() {
		panel_settings = new JPanel();
		panel_settings.setLayout(null);
		start_cardLayoutPanel.add(panel_settings, "SETTINGS");

		lbl_settings = new JTextField();
		lbl_settings.setText("     Settings");
		lbl_settings.setSelectionColor(new Color(0, 51, 102));
		lbl_settings.setForeground(Color.WHITE);
		lbl_settings.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_settings.setFocusable(false);
		lbl_settings.setFocusTraversalKeysEnabled(false);
		lbl_settings.setEditable(false);
		lbl_settings.setColumns(10);
		lbl_settings.setBorder(null);
		lbl_settings.setBackground(new Color(0, 0, 0));
		lbl_settings.setBounds(37, 0, 695, 30);
		panel_settings.add(lbl_settings);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("img/settings_icon.png"));
		label.setBounds(0, 0, 37, 30);
		panel_settings.add(label);

		lblChangeYour = new JLabel("Change your password");
		lblChangeYour.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangeYour.setBounds(37, 59, 147, 14);
		panel_settings.add(lblChangeYour);

		lblOldPassword = new JLabel("Old password");
		lblOldPassword.setBounds(47, 84, 137, 14);
		panel_settings.add(lblOldPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(47, 101, 137, 20);
		panel_settings.add(passwordField);

		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setBounds(247, 84, 123, 14);
		panel_settings.add(lblNewPassword);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(247, 101, 123, 20);
		panel_settings.add(passwordField_1);

		JLabel lblRewriteNewPassword = new JLabel("Rewrite new password");
		lblRewriteNewPassword.setBounds(401, 84, 153, 14);
		panel_settings.add(lblRewriteNewPassword);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(401, 101, 123, 20);
		panel_settings.add(passwordField_2);

		JButton btnChange = new JButton("Change");
		btnChange.setFocusable(false);
		btnChange.setFocusTraversalKeysEnabled(false);
		btnChange.setFocusPainted(false);
		btnChange.setBorderPainted(false);
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChange.setForeground(new Color(255, 255, 255));
		btnChange.setBackground(new Color(0, 51, 153));
		btnChange.setBounds(590, 99, 89, 23);
		panel_settings.add(btnChange);

		JLabel label_1 = new JLabel("*");
		label_1.setVisible(false);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setForeground(new Color(255, 0, 0));
		label_1.setBounds(188, 104, 15, 14);
		panel_settings.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setVisible(false);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(527, 104, 15, 14);
		panel_settings.add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setVisible(false);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(372, 104, 15, 14);
		panel_settings.add(label_3);

		JLabel lblChangeYourEmail = new JLabel("Change your e-mail");
		lblChangeYourEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangeYourEmail.setBounds(37, 161, 147, 14);
		panel_settings.add(lblChangeYourEmail);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setFocusable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(255, 0, 0));
		textField.setBorder(null);
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setBounds(47, 125, 467, 20);
		panel_settings.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(47, 203, 185, 20);
		panel_settings.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewEmailAddress = new JLabel("New e-mail address");
		lblNewEmailAddress.setBounds(47, 186, 193, 14);
		panel_settings.add(lblNewEmailAddress);

		button = new JButton("Change");
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setFocusTraversalKeysEnabled(false);
		button.setFocusable(false);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(new Color(0, 51, 153));
		button.setBounds(47, 234, 89, 23);
		panel_settings.add(button);

		lblPrivacySettings = new JLabel("Privacy settings");
		lblPrivacySettings.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrivacySettings.setBounds(37, 293, 147, 14);
		panel_settings.add(lblPrivacySettings);

		tglbtnNewToggleButton = new JToggleButton("");
		tglbtnNewToggleButton.setFocusTraversalKeysEnabled(false);
		tglbtnNewToggleButton.setFocusable(false);
		tglbtnNewToggleButton.setFocusPainted(false);
		tglbtnNewToggleButton.setContentAreaFilled(false);
		tglbtnNewToggleButton.setBorderPainted(false);
		tglbtnNewToggleButton.setActionCommand("");
		tglbtnNewToggleButton.setBounds(321, 357, 90, 25);
		tglbtnNewToggleButton.setSelected(true);
		tglbtnNewToggleButton.setIcon(new ImageIcon("img/on.png"));
		tglbtnNewToggleButton.addActionListener(this);

		panel_settings.add(tglbtnNewToggleButton);

		txtpnYouWillReceive = new JTextPane();
		txtpnYouWillReceive.setFocusable(false);
		txtpnYouWillReceive.setFocusTraversalKeysEnabled(false);
		txtpnYouWillReceive.setFocusCycleRoot(false);
		txtpnYouWillReceive.setEditable(false);
		txtpnYouWillReceive.setBackground(UIManager.getColor("Button.background"));
		txtpnYouWillReceive.setText(
				"You will receive a confirmation e-mail on this address. In order to activate the new E-mail address, please open the link from the e-mail received.");
		txtpnYouWillReceive.setBounds(237, 186, 446, 46);
		panel_settings.add(txtpnYouWillReceive);

		txtpnThisEmailAddress = new JTextPane();
		txtpnThisEmailAddress.setVisible(false);
		txtpnThisEmailAddress.setEditable(false);
		txtpnThisEmailAddress.setFocusCycleRoot(false);
		txtpnThisEmailAddress.setFocusTraversalKeysEnabled(false);
		txtpnThisEmailAddress.setFocusable(false);
		txtpnThisEmailAddress.setForeground(Color.RED);
		txtpnThisEmailAddress.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtpnThisEmailAddress.setBackground(UIManager.getColor("Button.background"));
		txtpnThisEmailAddress.setText(
				"This e-mail address is already linked to an account on TeamUp. Only one account for an address is allowed.");
		txtpnThisEmailAddress.setBounds(146, 234, 533, 42);
		panel_settings.add(txtpnThisEmailAddress);

		txtpnDisableIfYou = new JTextPane();
		txtpnDisableIfYou.setText(
				"Disable if you want your personal data (such as name, surname, country and e-mail) to be hidden from other users. By default, the data is visible (after creating the account).");
		txtpnDisableIfYou.setFocusable(false);
		txtpnDisableIfYou.setFocusTraversalKeysEnabled(false);
		txtpnDisableIfYou.setFocusCycleRoot(false);
		txtpnDisableIfYou.setEditable(false);
		txtpnDisableIfYou.setBackground(SystemColor.menu);
		txtpnDisableIfYou.setBounds(37, 312, 642, 34);
		panel_settings.add(txtpnDisableIfYou);

	}

	private void create_group_panel() {
		panel_group_detail = new JPanel();
		panel_group_detail.setLayout(null);
		start_cardLayoutPanel.add(panel_group_detail, "G");

		txt_group_groupName = new JTextField();
		txt_group_groupName.setText("     Group           :::   ");
		txt_group_groupName.setSelectionColor(new Color(0, 51, 102));
		txt_group_groupName.setForeground(Color.WHITE);
		txt_group_groupName.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_group_groupName.setFocusable(false);
		txt_group_groupName.setFocusTraversalKeysEnabled(false);
		txt_group_groupName.setEditable(false);
		txt_group_groupName.setColumns(10);
		txt_group_groupName.setBorder(null);
		txt_group_groupName.setBackground(new Color(0, 153, 255));
		txt_group_groupName.setBounds(0, 0, 188, 30);
		panel_group_detail.add(txt_group_groupName);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setForeground(SystemColor.menu);
		scrollPane_2.setFocusable(false);
		scrollPane_2.setFocusTraversalKeysEnabled(false);
		scrollPane_2.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		scrollPane_2.setBounds(0, 30, 732, 139);
		panel_group_detail.add(scrollPane_2);

		table_group = new JTable();
		table_group.setRowHeight(23);
		table_group.setShowGrid(false);
		table_group.setShowHorizontalLines(false);
		table_group.setShowVerticalLines(false);
		table_group.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "Task Name", "Author", "Task Status", "Assigned to", "Priority", "Days left" }));
		table_group.getColumnModel().getColumn(1).setPreferredWidth(125);
		table_group.getColumnModel().getColumn(1).setMinWidth(125);
		table_group.getColumnModel().getColumn(1).setMaxWidth(125);
		table_group.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_group.getColumnModel().getColumn(2).setMinWidth(100);
		table_group.getColumnModel().getColumn(2).setMaxWidth(100);
		table_group.getColumnModel().getColumn(3).setPreferredWidth(125);
		table_group.getColumnModel().getColumn(3).setMinWidth(125);
		table_group.getColumnModel().getColumn(3).setMaxWidth(125);
		table_group.getColumnModel().getColumn(4).setMinWidth(75);
		table_group.getColumnModel().getColumn(4).setMaxWidth(75);
		table_group.getColumnModel().getColumn(5).setMinWidth(75);
		table_group.getColumnModel().getColumn(5).setMaxWidth(75);
		table_group.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		table_group.getTableHeader().setBackground(new Color(0, 153, 204));
		table_group.getTableHeader().setForeground(new Color(255, 255, 255));
		table_group.getTableHeader().setReorderingAllowed(false);
		table_group.addMouseListener(this);
		scrollPane_2.setViewportView(table_group);

		btn_group_take = new JButton("Take");
		btn_group_take.setIcon(new ImageIcon("img/add_task.png"));
		btn_group_take.setMargin(new Insets(2, 2, 2, 2));
		btn_group_take.setForeground(Color.WHITE);
		btn_group_take.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_take.setFocusable(false);
		btn_group_take.setFocusTraversalKeysEnabled(false);
		btn_group_take.setFocusPainted(false);
		btn_group_take.setBorder(null);
		btn_group_take.setBackground(new Color(0, 153, 204));
		btn_group_take.setBounds(564, 363, 147, 30);
		btn_group_take.addActionListener(this);
		panel_group_detail.add(btn_group_take);

		btn_group_update = new JButton("Update");
		btn_group_update.setIcon(new ImageIcon("img/update.png"));
		btn_group_update.setMargin(new Insets(2, 2, 2, 2));
		btn_group_update.setForeground(Color.WHITE);
		btn_group_update.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_update.setFocusable(false);
		btn_group_update.setFocusTraversalKeysEnabled(false);
		btn_group_update.setFocusPainted(false);
		btn_group_update.setBorder(null);
		btn_group_update.setBackground(new Color(0, 153, 204));
		btn_group_update.setBounds(564, 252, 147, 30);
		btn_group_update.addActionListener(this);
		panel_group_detail.add(btn_group_update);

		btn_group_quit = new JButton("Quit");
		btn_group_quit.setIcon(new ImageIcon("img/leave.png"));
		btn_group_quit.setMargin(new Insets(2, 2, 2, 2));
		btn_group_quit.setForeground(Color.WHITE);
		btn_group_quit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_quit.setFocusable(false);
		btn_group_quit.setFocusTraversalKeysEnabled(false);
		btn_group_quit.setFocusPainted(false);
		btn_group_quit.setBorder(null);
		btn_group_quit.setBackground(new Color(0, 153, 204));
		btn_group_quit.setBounds(564, 326, 147, 30);
		btn_group_quit.addActionListener(this);
		panel_group_detail.add(btn_group_quit);

		btn_group_reschedule = new JButton("Reschedule");
		btn_group_reschedule.setIcon(new ImageIcon("img/reschedule.png"));
		btn_group_reschedule.setMargin(new Insets(2, 2, 2, 2));
		btn_group_reschedule.setForeground(Color.WHITE);
		btn_group_reschedule.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_reschedule.setFocusable(false);
		btn_group_reschedule.setFocusTraversalKeysEnabled(false);
		btn_group_reschedule.setFocusPainted(false);
		btn_group_reschedule.setBorder(null);
		btn_group_reschedule.setBackground(new Color(0, 153, 204));
		btn_group_reschedule.setBounds(564, 289, 147, 30);
		btn_group_reschedule.addActionListener(this);
		panel_group_detail.add(btn_group_reschedule);

		btn_group_submit = new JButton("Submit");
		btn_group_submit.setIcon(new ImageIcon("img/ok.png"));
		btn_group_submit.setMargin(new Insets(2, 2, 2, 2));
		btn_group_submit.setForeground(Color.WHITE);
		btn_group_submit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_submit.setFocusable(false);
		btn_group_submit.setFocusTraversalKeysEnabled(false);
		btn_group_submit.setFocusPainted(false);
		btn_group_submit.setBorder(null);
		btn_group_submit.setBackground(new Color(0, 153, 204));
		btn_group_submit.setBounds(564, 215, 147, 30);
		btn_group_submit.addActionListener(this);
		panel_group_detail.add(btn_group_submit);

		txt_group_theGroupSelected = new JTextField();
		txt_group_theGroupSelected.setSelectionColor(new Color(0, 51, 102));
		txt_group_theGroupSelected.setForeground(Color.WHITE);
		txt_group_theGroupSelected.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_group_theGroupSelected.setFocusable(false);
		txt_group_theGroupSelected.setFocusTraversalKeysEnabled(false);
		txt_group_theGroupSelected.setEditable(false);
		txt_group_theGroupSelected.setColumns(10);
		txt_group_theGroupSelected.setBorder(null);
		txt_group_theGroupSelected.setBackground(new Color(0, 153, 255));
		txt_group_theGroupSelected.setBounds(187, 0, 327, 30);
		panel_group_detail.add(txt_group_theGroupSelected);

		JTextField txtIdInac = new JTextField();
		txtIdInac.setText("ID : ");
		txtIdInac.setSelectionColor(new Color(0, 51, 102));
		txtIdInac.setForeground(Color.WHITE);
		txtIdInac.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtIdInac.setFocusable(false);
		txtIdInac.setFocusTraversalKeysEnabled(false);
		txtIdInac.setEditable(false);
		txtIdInac.setColumns(10);
		txtIdInac.setBorder(null);
		txtIdInac.setBackground(new Color(0, 153, 255));
		txtIdInac.setBounds(514, 0, 50, 30);
		panel_group_detail.add(txtIdInac);

		txtId = new JTextField();
		txtId.setSelectionColor(new Color(0, 51, 102));
		txtId.setForeground(Color.WHITE);
		txtId.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtId.setFocusable(false);
		txtId.setFocusTraversalKeysEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBorder(null);
		txtId.setBackground(new Color(0, 153, 255));
		txtId.setBounds(564, 0, 218, 30);
		panel_group_detail.add(txtId);

		JPanel panel_comments = new JPanel();
		panel_comments.setBackground(new Color(0, 51, 102));
		panel_comments.setBounds(0, 170, 278, 234);
		panel_group_detail.add(panel_comments);
		panel_comments.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 13, 257, 120);
		panel_comments.add(scrollPane);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 51), 3));

		txt_group_comments = new JTextPane();
		txt_group_comments.setEditable(false);
		txt_group_comments.setFocusCycleRoot(false);
		txt_group_comments.setFocusTraversalKeysEnabled(false);
		txt_group_comments.setFocusable(false);
		txt_group_comments.setBorder(null);
		txt_group_comments.setMargin(new Insets(8, 8, 8, 8));
		scrollPane.setViewportView(txt_group_comments);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(11, 144, 257, 38);
		panel_comments.add(scrollPane_3);
		scrollPane_3.setBorder(new LineBorder(new Color(0, 0, 51), 3));

		txt_group_addComment = new JTextPane();
		txt_group_addComment.setMargin(new Insets(8, 8, 8, 8));
		scrollPane_3.setViewportView(txt_group_addComment);
		txt_group_addComment.setBorder(null);

		btn_group_addComment = new JButton("Add comment");
		btn_group_addComment.setBounds(121, 193, 147, 30);
		panel_comments.add(btn_group_addComment);
		btn_group_addComment.setIcon(new ImageIcon("img/message_bl.png"));
		btn_group_addComment.setMargin(new Insets(2, 2, 2, 2));
		btn_group_addComment.setForeground(new Color(0, 51, 102));
		btn_group_addComment.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_addComment.setFocusable(false);
		btn_group_addComment.setFocusTraversalKeysEnabled(false);
		btn_group_addComment.setFocusPainted(false);
		btn_group_addComment.setBorder(null);
		btn_group_addComment.addActionListener(this);
		btn_group_addComment.setBackground(new Color(255, 255, 255));

		panel_addTask = new JPanel();
		panel_addTask.setBackground(new Color(0, 153, 204));
		panel_addTask.setBounds(278, 170, 265, 234);
		panel_group_detail.add(panel_addTask);
		panel_addTask.setLayout(null);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(9, 76, 247, 60);
		panel_addTask.add(scrollPane_4);
		scrollPane_4.setBorder(new LineBorder(new Color(255, 255, 255), 3));

		txt_group_taskDescription = new JTextPane();

		scrollPane_4.setViewportView(txt_group_taskDescription);

		lblTaskDescription_1 = new JLabel("Task description");
		lblTaskDescription_1.setBounds(10, 59, 125, 14);
		panel_addTask.add(lblTaskDescription_1);
		lblTaskDescription_1.setForeground(new Color(255, 255, 255));
		lblTaskDescription_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		txt_group_taskName = new JTextField();
		txt_group_taskName.setBounds(9, 26, 247, 30);
		panel_addTask.add(txt_group_taskName);
		txt_group_taskName.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		txt_group_taskName.setColumns(10);

		label_5 = new JLabel("Task name");
		label_5.setBounds(10, 11, 125, 14);
		panel_addTask.add(label_5);
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel label_6 = new JLabel("Due date ( d/m/y )");
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(9, 140, 147, 14);
		panel_addTask.add(label_6);

		cb_group_day = new JComboBox<String>();
		cb_group_day.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		cb_group_day.setMaximumRowCount(5);
		cb_group_day.setFocusable(false);
		cb_group_day.setFocusTraversalKeysEnabled(false);
		cb_group_day.setBorder(null);
		cb_group_day.setBackground(SystemColor.menu);
		cb_group_day.setBounds(17, 157, 48, 25);
		panel_addTask.add(cb_group_day);

		cb_group_month = new JComboBox<String>();
		cb_group_month.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

		cb_group_month.setMaximumRowCount(5);
		cb_group_month.setFocusable(false);
		cb_group_month.setFocusTraversalKeysEnabled(false);
		cb_group_month.setBorder(null);
		cb_group_month.setBackground(SystemColor.menu);
		cb_group_month.setBounds(82, 157, 89, 25);
		panel_addTask.add(cb_group_month);

		cb_group_year = new JComboBox<String>();
		cb_group_year.setModel(new DefaultComboBoxModel<String>(new String[] { "2017", "2018", "2019", "2020" }));
		cb_group_year.setMaximumRowCount(5);
		cb_group_year.setFocusable(false);
		cb_group_year.setFocusTraversalKeysEnabled(false);
		cb_group_year.setBorder(null);
		cb_group_year.setBackground(SystemColor.menu);
		cb_group_year.setBounds(188, 157, 59, 25);
		panel_addTask.add(cb_group_year);

		btn_group_addTask = new JButton("Add task");
		btn_group_addTask.setIcon(new ImageIcon("img/add.png"));
		btn_group_addTask.setMargin(new Insets(2, 2, 2, 2));
		btn_group_addTask.setForeground(new Color(0, 153, 204));
		btn_group_addTask.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_addTask.setFocusable(false);
		btn_group_addTask.setFocusTraversalKeysEnabled(false);
		btn_group_addTask.setFocusPainted(false);
		btn_group_addTask.setBorder(null);
		btn_group_addTask.setBackground(Color.WHITE);
		btn_group_addTask.setBounds(134, 193, 113, 30);
		btn_group_addTask.addActionListener(this);
		panel_addTask.add(btn_group_addTask);

		label_7 = new JLabel("Task priority");
		label_7.setForeground(new Color(255, 255, 255));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(9, 183, 89, 14);
		panel_addTask.add(label_7);

		cb_group_priority = new JComboBox<String>();
		cb_group_priority.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5" }));
		cb_group_priority.setBounds(17, 200, 40, 23);
		panel_addTask.add(cb_group_priority);

		textPane_3 = new JTextPane();
		textPane_3.setText("1 - lowest\r\n5 - highest");
		textPane_3.setForeground(new Color(204, 255, 255));
		textPane_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textPane_3.setFocusable(false);
		textPane_3.setFocusTraversalKeysEnabled(false);
		textPane_3.setFocusCycleRoot(false);
		textPane_3.setEditable(false);
		textPane_3.setBorder(null);
		textPane_3.setBackground(new Color(0, 153, 204));
		textPane_3.setBounds(65, 197, 57, 30);
		panel_addTask.add(textPane_3);

		btn_group_details = new JButton("Details");
		btn_group_details.setIcon(new ImageIcon("img/info.png"));
		btn_group_details.setMargin(new Insets(2, 2, 2, 2));
		btn_group_details.setForeground(Color.WHITE);
		btn_group_details.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_group_details.setFocusable(false);
		btn_group_details.setFocusTraversalKeysEnabled(false);
		btn_group_details.setFocusPainted(false);
		btn_group_details.setBorder(null);
		btn_group_details.setBackground(new Color(0, 153, 204));
		btn_group_details.setBounds(564, 178, 147, 30);
		btn_group_details.addActionListener(this);
		panel_group_detail.add(btn_group_details);
	}

	// START METHODS
	/**
	 * Checks if all fields in registration are completed
	 * 
	 * @return true if so.
	 */
	private boolean verify_registration_filled_in() {
		boolean all_filled = true;
		boolean pass_match = true;
		boolean condition_agreed = true;

		if (txt_register_country.getText().isEmpty()) {
			all_filled = false;
			verify_country.setVisible(true);
		} else {
			verify_country.setVisible(false);
		}

		if (txt_register_name.getText().isEmpty()) {
			all_filled = false;
			verify_name.setVisible(true);
		} else {
			verify_name.setVisible(false);
		}

		if (txt_register_surname.getText().isEmpty()) {
			all_filled = false;
			verify_surname.setVisible(true);
		} else {
			verify_surname.setVisible(false);
		}

		if (txt_register_username.getText().isEmpty()) {
			all_filled = false;
			verify_username.setVisible(true);
		} else {
			verify_username.setVisible(false);
		}

		if (txt_register_email.getText().isEmpty()) {
			all_filled = false;
			verify_email.setVisible(true);
		} else {
			verify_email.setVisible(false);
		}

		if (txt_register_password.getPassword().length == 0) {
			all_filled = false;
			verify_password.setVisible(true);
		} else {
			verify_password.setVisible(false);
		}

		if (txt_register_password2.getPassword().length == 0) {
			all_filled = false;
			verify_password2.setVisible(true);
		} else {
			verify_password2.setVisible(false);
		}

		if (!Arrays.equals(txt_register_password.getPassword(), txt_register_password2.getPassword())) {
			pass_match = false;
		}

		if (!rdbtn_accept_conditions.isSelected()) {
			condition_agreed = false;
		}

		if (!all_filled) {
			registration_notification(1);
			return false;
		}
		if (!pass_match) {
			registration_notification(2);
			return false;
		}
		if (!condition_agreed) {
			registration_notification(3);
			return false;
		}
		return true;
	}

	/**
	 * Checks if all the fields are completed when creating a new group
	 * 
	 * @return
	 */
	private boolean verify_create_fields() {
		boolean create_check_fill = true;
		if (txt_create_groupName.getText().isEmpty()) {
			msg_create_groupName.setVisible(true);
			msg_create_fillIn.setVisible(true);
			create_check_fill = false;
		} else {
			msg_create_groupName.setVisible(false);
			msg_create_fillIn.setVisible(false);
		}
		if (txt_create_groupDescription.getText().isEmpty()) {
			msg_create_groupDescription.setVisible(true);
			msg_create_fillIn.setVisible(true);
			create_check_fill = false;
		} else {
			msg_create_groupDescription.setVisible(false);
			msg_create_fillIn.setVisible(false);
		}
		if (!(rdbtn_create_privateGroup.isSelected() || rdbtn_create_publicGroup.isSelected())) {
			msg_create_privacySettings.setVisible(true);
			msg_create_fillIn.setVisible(true);
			create_check_fill = false;
		} else {
			msg_create_privacySettings.setVisible(false);
			msg_create_fillIn.setVisible(false);
		}

		return create_check_fill;
	}

	/**
	 * Checks if all the fields are completed when adding a local task
	 * 
	 * @return true if so.
	 */
	private boolean verify_local_addTask() {
		boolean verify = true;
		if (txt_local_taskName.getText().isEmpty()) {
			verify_local_taskName.setVisible(true);
			txtpnPleaseFillIn.setVisible(true);
			verify = false;
		} else {
			verify_local_taskName.setVisible(false);
			txtpnPleaseFillIn.setVisible(false);
		}
		if (txt_local_taskDescription.getText().isEmpty()) {
			verify_local_taskDescription.setVisible(true);
			txtpnPleaseFillIn.setVisible(true);
			verify = false;
		} else {
			verify_local_taskDescription.setVisible(false);
			txtpnPleaseFillIn.setVisible(false);
		}

		return verify;
	}

	/**
	 * Checks if all the fields are completed when adding a new task
	 * 
	 * @return true if so
	 */
	private boolean verify_member_addTask() {
		boolean verify = true;
		if (txt_member_taskname.getText().isEmpty()) {
			msg_member_taskName.setVisible(true);
			msg_member_fillIn.setVisible(true);
			verify = false;
		} else {
			msg_member_taskName.setVisible(false);
			msg_member_fillIn.setVisible(false);
		}
		if (txt_member_taskDescription.getText().isEmpty()) {
			msg_member_taskImportance.setVisible(true);
			msg_member_fillIn.setVisible(true);
			verify = false;
		} else {
			msg_member_taskImportance.setVisible(false);
			msg_member_fillIn.setVisible(false);
		}
		return verify;
	}

	private boolean verify_group_addTask() {

		boolean verify = true;
		if (txt_group_taskDescription.getText().isEmpty()) {
			verify = false;
		}
		if (txt_group_taskDescription.getText().isEmpty()) {
			verify = false;
		}
		return verify;
	}

	/**
	 * Prints different messages depending on which error type it recives
	 * 
	 * @param type
	 *            the error number
	 */
	private void registration_notification(int type) {
		notification_register.setVisible(true);
		if (type == 1) {
			notification_register.setText("Please fill in all the filds. The incompleted fields are marked with *");
		}
		if (type == 2) {
			notification_register.setText("The passwords should match. Please rewrite the passwords.");
		}
		if (type == 3) {
			notification_register.setText("You must accept the terms and conditions");
		}
		if (type == 4) {
			notification_register.setText(
					"There is already an account with this E-mail. If you forget your password, please recover it.");
		}
		if (type == 5) {
			notification_register.setText(
					"There is already an account with this username. If you already use an account, log in with it. Otherwise, choose a different username");
		}
	}

	/**
	 * Prints different messages depending on which error type it recives
	 * 
	 * @param type
	 *            the error number
	 */
	private void log_in_notification(int type) {
		if (type == 3) {
			txt_login_message.setVisible(true);
			txt_login_message.setText("Incorrect username");
		}
		if (type == 1) {
			txt_login_message.setVisible(true);
			txt_login_message.setText("Please insert an username");
		}
		if (type == 2) {
			txt_login_message.setVisible(true);
			txt_login_message.setText("Please insert the password");
		}
		if (type == 4) {
			txt_login_message.setVisible(true);
			txt_login_message.setText("Incorrect password");
		}
	}

	/**
	 * Creates a new Account with the data specified as parameters
	 * 
	 * @param name
	 * @param surname
	 * @param email
	 * @param country
	 * @param username
	 * @param passwordChar
	 */
	private void createAccount(String name, String surname, String email, String country, String username,
			char[] passwordChar) {
		String password = String.valueOf(passwordChar);
		int wrigths = 0;
		Account newAccount = new Account(username, password, wrigths);
		TeamMember newMember = new TeamMember(newAccount, username, surname, email, country);
		client.sendMessage(new ChatMessage(MessageType.NEWACCOUNT, newMember));
	}

	// METHODS FOR POPULATING TABLES

	/**
	 * Searches the teams which names match the argument. It is not necessary to
	 * be the full name but the team name has to start with the text sent as
	 * parameter.
	 * 
	 * @param tName
	 *            the name to be matched.
	 * @return list of teams
	 */
	private List<Team> getSearchedTeams(String tName) {
		List<Team> myTm = new ArrayList<Team>();
		for (Team t : teamDatabaseInstance.getTeams()) {
			if (t.getTeamName().startsWith(tName) || t.getTeamName().startsWith(tName.toLowerCase())
					|| t.getTeamName().startsWith(tName.toUpperCase())) {
				myTm.add(t);
			}
		}
		return myTm;
	}

	/**
	 * Searches the teams of logged in user. A team is consider to be yours if
	 * you are member in that team, or you are admin of that team
	 * 
	 * @return List of teams
	 */
	private List<Team> getMyTeams() {
		List<Team> myTeam = new ArrayList<Team>();
		for (Team t : teamDatabaseInstance.getTeams()) {
			for (TeamMember memb : t.getMembers()) {
				if (memb.getName().equals(logged_in_user))
					myTeam.add(t);
			}
			if (t.getAdmin().equals(logged_in_user) && !myTeam.contains(t))
				myTeam.add(t);
		}

		return myTeam;
	}

	/**
	 * Searches the groups where you are administrator.
	 * 
	 * @return The list of teams.
	 */
	private List<Team> getMyAdminGroups() {
		List<Team> myTeam = new ArrayList<Team>();
		for (Team t : teamDatabaseInstance.getTeams()) {
			if (t.getAdmin().contentEquals(logged_in_user))
				myTeam.add(t);
		}
		return myTeam;
	}

	/**
	 * Returns the number of the logged in user's active local tasks in the team
	 * given as parameter
	 * 
	 * @param t
	 *            the team in the search will take place
	 * @return the number of tasks .
	 */
	private int nbOfMyTasks(Team t) {
		int s = 0;
		for (Task tsk : t.getTasks()) {
			if (tsk.getWorkingPers().contentEquals(logged_in_user) && tsk.getStatus().equals(TaskStatus.WORKING))
				++s;
		}

		return s;
	}

	/**
	 * Returns the number of finished task by the user specified as parameter.
	 * 
	 * @param username
	 *            the user
	 * @param tm
	 *            the team
	 * @return Number of tasks which meet the conditions.
	 */
	private int nbOfPersonalFinishedTasks(String username, Team tm) {
		int s = 0;

		for (Task tsk : tm.getTasks())
			if (tsk.getStatus().equals(TaskStatus.FINISHED) && tsk.getWorkingPers().contentEquals(username))
				++s;

		return s;
	}

	/**
	 * Returns the number of active task for the user specified as parameter.
	 * 
	 * @param username
	 * @param tm
	 * @return The number of tasks which meet the conditions.
	 */
	private int nbOfPersonalWorkingTasks(String username, Team tm) {
		int s = 0;

		for (Task tsk : tm.getTasks())
			if (tsk.getStatus().equals(TaskStatus.WORKING) && tsk.getWorkingPers().contentEquals(username))
				++s;

		return s;
	}

	/**
	 * 
	 * @param table
	 *            table from which we will get the name
	 * @param col
	 *            the column we are interested in
	 * @return The name of the member.
	 */
	private String getSelectedMember(JTable table, int col) {

		int index = table.getSelectedRow();
		if (index >= 0) {
			return String.valueOf(table.getValueAt(index, col));
		}

		return null;
	}

	/**
	 * Checks the notification for the team member.
	 * 
	 * @param tm
	 *            The team member.
	 */
	private void checkNotificationStatus(TeamMember tm) {
		if (tm.isNotificationChecked()) {
			make_notification_inactive();
		} else if (!tm.isNotificationChecked()) {
			make_notification_active();
		}
	}

	/**
	 * Changes the display of the notification area in case there are no
	 * notifications.
	 */
	private void make_notification_inactive() {
		btn_notification.setBackground(new Color(0, 153, 255));
		btn_notification.setText("No notifications");
	}

	/**
	 * Changes the display of the notification area in case there are
	 * notifications.
	 */
	private void make_notification_active() {
		btn_notification.setBackground(Color.RED);
		btn_notification.setText("CHECK NOTIFICATIONS");
	}

	/**
	 * Makes the button of tab blue.
	 * 
	 * @param type
	 *            which tab to be changed.
	 */
	private void make_button_blue(String type) {
		btn_start_search_group.setBackground(new Color(255, 255, 255));
		btn_start_search_group.setForeground(new Color(0, 0, 0));
		btn_start_admin_group.setBackground(new Color(255, 255, 255));
		btn_start_admin_group.setForeground(new Color(0, 0, 0));
		btn_start_create_group.setBackground(new Color(255, 255, 255));
		btn_start_create_group.setForeground(new Color(0, 0, 0));
		btn_start_member_group.setBackground(new Color(255, 255, 255));
		btn_start_member_group.setForeground(new Color(0, 0, 0));
		btn_start_my_local_task.setBackground(new Color(255, 255, 255));
		btn_start_my_local_task.setForeground(new Color(0, 0, 0));
		btn_start_my_group_task.setBackground(new Color(255, 255, 255));
		btn_start_my_group_task.setForeground(new Color(0, 0, 0));
		switch (type) {
		case "SEARCH":
			btn_start_search_group.setBackground(new Color(0, 51, 102));
			btn_start_search_group.setForeground(new Color(255, 255, 255));
			break;
		case "CREATE":
			btn_start_create_group.setBackground(new Color(0, 51, 102));
			btn_start_create_group.setForeground(new Color(255, 255, 255));
			break;
		case "MEMBER":
			btn_start_member_group.setBackground(new Color(0, 51, 102));
			btn_start_member_group.setForeground(new Color(255, 255, 255));
			break;
		case "ADMIN":
			btn_start_admin_group.setBackground(new Color(0, 51, 102));
			btn_start_admin_group.setForeground(new Color(255, 255, 255));
			break;
		case "LOCAL":
			btn_start_my_local_task.setBackground(new Color(0, 51, 102));
			btn_start_my_local_task.setForeground(new Color(255, 255, 255));
			break;
		case "GROUP":
			btn_start_my_group_task.setBackground(new Color(0, 51, 102));
			btn_start_my_group_task.setForeground(new Color(255, 255, 255));
			break;
		}

	}

	public void connectionFailed() {
		System.out.println("CONNECTION FAILED");
	}

	public void setUser_logged_in(String user_logged_in) {
		txt_start_initial.setText(String.valueOf(user_logged_in.charAt(0)).toUpperCase());
		txt_start_logged_user.setText(user_logged_in);
		this.logged_in_user = user_logged_in;
	}

	// FILL TABLES
	/**
	 * Returns the first team for the user given as parameter
	 * 
	 * @param user
	 *            the user
	 * @return The first team of the user.
	 */
	public Team getTeam(String user) {
		for (Team t : teamDatabaseInstance.getTeams()) {
			for (TeamMember tm : t.getMembers()) {
				if (tm.getAccount().getUserName().contentEquals(user)) {
					return t;
				}
			}
		}
		return null;
	}

	/**
	 * Fills the local tasks table.
	 */
	private void fill_localTasks_table() {

		model_table_local_tasks = (DefaultTableModel) table_local_tasks.getModel();
		int rowCount = model_table_local_tasks.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_table_local_tasks.removeRow(i);
		}
		for (LocalTask lt : localTaskDatabaseInstance.getAllLocalTasks()) {
			if (lt.getUser().getUserName().contentEquals(logged_in_user)) {
				for (Task t : lt.getLocalTasks()) {
					Object[] row = { t.getTaskName(), String.valueOf(t.getStatus()), String.valueOf(t.getPriority()),
							String.valueOf(t.getDaysToDeadline()) };
					model_table_local_tasks.addRow(row);
				}
			}
		}
	}

	/**
	 * Fills my groups table.
	 */
	private void fill_my_groups_table() {
		List<Team> myTeams = getMyTeams();

		model_table_my_groups = (DefaultTableModel) table_member.getModel();
		int rowCount = model_table_my_groups.getRowCount();

		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_table_my_groups.removeRow(i);
		}

		for (Team t : myTeams) {
			// "Group id","Group name", "Admin", "Nr. members", "Nr. Tasks
			// open", "Nr. Your tasks"
			Object[] row = { t.getId(), t.getTeamName(), t.getAdmin(), t.getMembers().size(), t.getTasks().size(),
					nbOfMyTasks(t) };
			model_table_my_groups.addRow(row);
		}
	}

	/**
	 * Fills my groups admin table.
	 */
	private void fill_my_admin_groupTable() {
		List<Team> myTeams = getMyAdminGroups();

		model_table_my_adminGroups = (DefaultTableModel) table_admin_groups.getModel();
		int rowCount = model_table_my_adminGroups.getRowCount();

		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_table_my_adminGroups.removeRow(i);
		}

		for (Team t : myTeams) {

			Object[] row = { t.getTeamId(), t.getTeamName(), t.getTasks().size(), t.getMembers().size() };
			model_table_my_adminGroups.addRow(row);

		}
	}

	/**
	 * Fills the table of members of the selected group
	 */
	private void fill_my_admin_groupMembers() {
		int index = table_admin_groups.getSelectedRow();

		if (index >= 0) {
			String teamN = String.valueOf(table_admin_groups.getValueAt(index, 1));
			model_table_my_adminGroupMembers = (DefaultTableModel) table_admin_members.getModel();
			int rowCount = model_table_my_adminGroupMembers.getRowCount();

			// Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				model_table_my_adminGroupMembers.removeRow(i);
			}
			Team aux = new Team();

			for (Team t : teamDatabaseInstance.getTeams())
				if (t.getTeamName().contentEquals(teamN))
					aux = t;
			for (TeamMember memb : aux.getMembers()) {
				String name = memb.getAccount().getUserName();
				Object[] row = { name, nbOfPersonalWorkingTasks(name, aux), nbOfPersonalFinishedTasks(name, aux), };
				model_table_my_adminGroupMembers.addRow(row);
			}
		}
	}

	/**
	 * Fills the table with searched groups.
	 */
	private void fill_searched_groups() {

		List<Team> myTeams = getSearchedTeams(txt_search_field.getText());

		model_tabel_searchGroups = (DefaultTableModel) table_search.getModel();

		int rowCount = model_tabel_searchGroups.getRowCount();

		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_tabel_searchGroups.removeRow(i);
		}

		for (Team t : myTeams) {
			if (t.getPrivacy() == Privacy.PUBLIC) {
				Object[] row = { t.getId(), t.getTeamName(), t.getAdmin(), t.getMembers().size() };
				model_tabel_searchGroups.addRow(row);
			}

		}
	}

	/**
	 * Fills the invitations table.
	 */
	private void fillInvitationsTable() {

		client.sendMessage(new ChatMessage(MessageType.GET_USERS, ""));
		client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
		model_table_invitations = (DefaultTableModel) table_notification.getModel();
		int rowCount = model_table_invitations.getRowCount();

		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_table_invitations.removeRow(i);
		}

		for (TeamMember t : registeredUserInstance.getMembers()) {

			if (t.getAccount().getUserName().contentEquals(logged_in_user)) {
				for (Notification notr : t.getNotifications()) {
					if (notr.getType().equals(Notification_type.INVITE)) {
						Team tm = teamDatabaseInstance.getTeamById(notr.getTeamId());
						Object[] row = { tm.getTeamName(), tm.getAdmin(), notr.getInvDate() };
						model_table_invitations.addRow(row);
					}
				}
			}

		}

	}

	/**
	 * Fills the JText area where the messages of the user are displayed.
	 */
	private void fillNotification() {
		bool_notification = !bool_notification;
		panel_notification.setVisible(bool_notification);
		txt_notification_messages.setText("");
		notification_document = txt_notification_messages.getDocument();
		client.sendMessage(new ChatMessage(MessageType.GET_USERS, ""));

		for (TeamMember tm : registeredUserInstance.getMembers()) {
			if (tm.getAccount().getUserName().contentEquals(logged_in_user)) {
				for (Notification notr : tm.getNotifications()) {
					try {
						if (notr.getType().equals(Notification_type.MESSAGE))
							notification_document.insertString(notification_document.getLength(),
									"FROM: " + notr.getSender() + "::: " + notr.getText() + "\n", null);
					} catch (BadLocationException e1) {

						e1.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Fills the table with the group's tasks.
	 * 
	 * @param groupName
	 *            the group's name
	 * @param groupId
	 *            the group's id
	 */
	private void fill_group_panel(String groupName, String groupId) {
		txt_group_theGroupSelected.setText(groupName);
		txtId.setText(groupId);

		model_group_table = (DefaultTableModel) table_group.getModel();
		int rowCount = model_group_table.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_group_table.removeRow(i);
		}

		Team aux = new Team();

		for (Team t : teamDatabaseInstance.getTeams()) {
			if (String.valueOf(t.getId()).contentEquals(groupId)) {
				aux = t;
			}
		}

		for (Task t : aux.getTasks()) {
			String daysLeft = null;
			if (t.getStatus() == TaskStatus.FINISHED || t.getStatus() == TaskStatus.CANCELED) {
				daysLeft = "-";
			} else {
				daysLeft = String.valueOf(t.getDaysToDeadline());
			}
			Object[] row = { t.getTaskName(), t.getAuthor(), t.getStatus(), t.getWorkingPers(), t.getPriority(),
					daysLeft };
			model_group_table.addRow(row);
		}

		cardLayoutStart.show(start_cardLayoutPanel, "G");
	}

	/**
	 * Fills the table with logged in user local tasks.
	 */
	private void fill_my_group_task_panel() {

		model_fill_my_group_task_panel = (DefaultTableModel) table_groupTask.getModel();
		int rowCount = model_fill_my_group_task_panel.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model_fill_my_group_task_panel.removeRow(i);
		}
		String time = "";
		for (Team t : teamDatabaseInstance.getTeams()) {
			for (Task ta : t.getTasks()) {

				if (ta.getWorkingPers().contentEquals(logged_in_user)) {
					if (ta.getStatus().equals(TaskStatus.FINISHED)) {
						time = "-";
					} else {
						time = String.valueOf(ta.getDaysToDeadline());
					}
					Object[] row = { ta.getTaskName(), t.getId(), t.getTeamName(), String.valueOf(ta.getStatus()),
							String.valueOf(ta.getPriority()), time };
					model_fill_my_group_task_panel.addRow(row);

				}
			}
		}
	}

	/**
	 * Shows the details of the task given as parameter.
	 * 
	 * @param ta
	 *            the task of which details will be displayed.
	 * @param group
	 *            the team in which the task is.
	 */
	public void showTaskDetails(Task ta, String group) {
		taskDetail_name.setText(ta.getTaskName());
		taskDetail_description.setText(ta.getTaskDescription());
		taskDetail_author.setText(ta.getAuthor());
		taskDetail_datecreated.setText(String.valueOf(new SimpleDateFormat("dd/mm/yyyy").format(ta.getDateCreated())));
		taskDetail_deadline.setText(String.valueOf(new SimpleDateFormat("dd/mm/yyyy").format(ta.getDeadline())));
		taskDetail_priority.setText(String.valueOf(ta.getPriority()));
		taskDetail_status.setText(String.valueOf(ta.getStatus()));
		taskDetail_workperson.setText(ta.getWorkingPers());
		taskDetail_group.setText(group);

		panel_taskdetails.setVisible(true);
	}

	/**
	 * * Read the message from the server and ,depending on the case we decode
	 * it properly
	 * 
	 * 
	 * @param msg
	 *            the message to be decoded
	 */
	public void readMsg(ChatMessage msg) {

		switch (msg.getType()) {
		case LOGIN:
			// sends a message to server to logg in
			if (msg.getMessage().contentEquals("UTATARATATA")) {
				Team teamFound = getTeam(logged_in_user);
				if (teamFound == null) {
					cardLayoutLogIn.show(cardPanel, "START");
					cardLayoutHelp.show(helpCard, "H_TASKS");
				} else {
					fill_group_panel(teamFound.getTeamName(), String.valueOf(teamFound.getId()));
					cardLayoutLogIn.show(cardPanel, "START");
					cardLayoutHelp.show(helpCard, "H_TASKS");
				}
			} else if (!msg.getMessage2().equals("IGNORE")) {

				setUser_logged_in(msg.getMessage());
				JOptionPane.showMessageDialog(panel_LogIn, msg.getMessage2());
				cardLayoutStart.show(start_cardLayoutPanel, "G");
				cardLayoutHelp.show(helpCard, "H_TASKS");
				// request the updated databases.
				client.sendMessage(new ChatMessage(MessageType.GET_USERS, ""));
				client.sendMessage(new ChatMessage(MessageType.GET_LOCAL_TASKS, ""));
				client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
				client.sendMessage(new ChatMessage(MessageType.LOGIN, "UTATARATATA"));
			}
			break;
		case LOGIN_ERR:
			log_in_notification(Integer.parseInt(msg.getMessage()));
			break;
		case REGISTER:
			// registers the account
			notification_register.setText("");
			createAccount(txt_register_name.getText(), txt_register_surname.getText(), txt_register_email.getText(),
					txt_register_country.getText(), txt_register_username.getText(),
					txt_register_password.getPassword());

			break;
		case REGISTER_ERROR:
			registration_notification(Integer.parseInt(msg.getMessage()));
			break;
		case NEWACCOUNT:
			JOptionPane.showMessageDialog(panel_Register, "Your account is now set up");
			txt_register_country.setText("");
			txt_register_name.setText("");
			txt_register_surname.setText("");
			txt_register_email.setText("");
			txt_register_username.setText("");
			txt_register_password.setText("");
			txt_register_password2.setText("");
			cardLayoutLogIn.show(cardPanel, "LOG_IN_PANEL");
			break;
		case EMAIL:
			if (msg.getMessage().equals("")) {
				// it means there is such an email address found
				txt_passwordLost_noemail.setVisible(false);
				sendMail(txt_passwordLost_email.getText());
				JOptionPane.showMessageDialog(panel_PasswordLost, "Verify Your E-mail");
				cardLayoutLogIn.show(cardPanel, "LOG_IN_PANEL");
			} else {
				txt_passwordLost_noemail.setVisible(true);
			}
			break;
		case CREATE_GROUP:
			JOptionPane.showMessageDialog(panel_create_group, msg.getMessage());
			break;
		case GET_GROUPS:
			this.teamDatabaseInstance = msg.getTeamDatabase();
			fill_my_groups_table();
			break;
		case ADD_LOCAL_TASK:
			JOptionPane.showMessageDialog(panel_local_tasks, msg.getMessage());
			break;
		case GET_LOCAL_TASKS:

			this.localTaskDatabaseInstance = msg.getLocalTaskDatabase();
			fill_localTasks_table();
			break;
		case ADD_TASK:
			this.teamDatabaseInstance = msg.getTeamDatabase();
			fill_my_groups_table();
			break;
		case USER_MESSAGE:
			client.sendMessage(new ChatMessage(MessageType.GET_USERS, ""));
			txt_notification_messages.setText("FROM " + msg.getMessage() + " :: " + msg.getMessage2() + "\n");
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "false", userSentTo));
			break;
		case GET_USERS:
			registeredUserInstance = msg.getRegisteredUser();
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
			break;

		case ERROR_ALREADY_IN_TM:
			// JOptionPane.showMessageDialog(panel_search_group, "Already in
			// team!");
			break;
		case ADD_USER:
			JOptionPane.showMessageDialog(panel_search_group, "Welcome! " + msg.getMessage());
			client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
			break;
		case NOT_STATUS:
			checkNotificationStatus(msg.getTeamMember());
			break;
		case LEAVE_GROUP:
			JOptionPane.showMessageDialog(panel_admin_groups, "User removed");
			break;
		case CHECK_IF_MEMBER:
			if (msg.getMessage().contentEquals("true")) {
				client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
				String groupSelectedID = msg.getMessage2();
				String groupSelectedName = msg.getMessage3();
				fill_group_panel(groupSelectedName, groupSelectedID);
			} else if (msg.getMessage().contentEquals("false")) {
				JOptionPane.showMessageDialog(panel_group_detail, "You should be a member in order to open");
			} else {
				System.out.println("SOMETHING BAD AT CHECK IF MEMBER");
			}
			break;
		case INVITE_MEMBER:
			fillInvitationsTable();
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "false", userSentTo));
			break;
		case INVITATION_ACTION:
			String input = new String();
			input = "A";
			if (msg.getMessage().contentEquals("true")) {
				input = "User accepted invitation";
			} else
				input = "User refused invitation";
			int rowCount = model_table_invitations.getRowCount();

			String grAdm = getSelectedMember(table_notification, 1);
			String grName = getSelectedMember(table_notification, 0);

			client.sendMessage(new ChatMessage(MessageType.DELETE_NOTIFICATION, logged_in_user, grAdm, grName));
			userSentTo = getSelectedMember(table_notification, 1);
			for (int i = rowCount - 1; i >= 0; i--) {
				model_table_invitations.removeRow(i);
			}
			fillInvitationsTable();
			client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, input, userSentTo, logged_in_user));
			client.sendMessage(new ChatMessage(MessageType.GET_USERS, ""));

			break;

		case GET_TASK:
			if (msg.getMessage().contentEquals("ERROR")) {
				System.out.println("ERROR IN GETTING TASK");
			} else {
				Task t = msg.getTask();
				for (String s : t.getComments()) {

					String newLine = System.getProperty("line.separator");
					if (txt_group_comments.getText().isEmpty()) {
						txt_group_comments.setText(s);
					} else {
						txt_group_comments.setText(txt_group_comments.getText() + newLine + s);
					}
					if (txt_groupTask_comments.getText().isEmpty()) {
						txt_groupTask_comments.setText(s);
					} else {
						txt_groupTask_comments.setText(txt_groupTask_comments.getText() + newLine + s);
					}
				}
			}
			break;
		case SUBMIT_TASK:
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			client.sendMessage(new ChatMessage(MessageType.GET_TASK, msg.getMessage3(), taskSelected, taskAuthor));
			break;
		case TAKE_TASK:
			String taskSelected1 = getSelectedMember(table_group, 0);
			String taskAuthor1 = getSelectedMember(table_group, 1);
			client.sendMessage(new ChatMessage(MessageType.GET_TASK, msg.getMessage3(), taskSelected1, taskAuthor1));
			break;
		case QUIT_TASK:
			String taskSelected2 = getSelectedMember(table_group, 0);
			String taskAuthor2 = getSelectedMember(table_group, 1);
			client.sendMessage(new ChatMessage(MessageType.GET_TASK, msg.getMessage3(), taskSelected2, taskAuthor2));
			break;
		case RESC_TASK:
			String taskSelected3 = getSelectedMember(table_group, 0);
			String taskAuthor3 = getSelectedMember(table_group, 1);
			client.sendMessage(new ChatMessage(MessageType.GET_TASK, msg.getMessage3(), taskSelected3, taskAuthor3));
			break;
		default:
			break;
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btn_register_Register) {
			if (verify_registration_filled_in()) {
				client.sendMessage(new ChatMessage(MessageType.REGISTER, txt_register_email.getText(),
						txt_register_username.getText()));
			}
		}
		if (o == btn_passwordLost_recover) {
			if (txt_passwordLost_email.getText().isEmpty()) {
				txt_passwordLost_noemail.setVisible(true);
			} else {
				client.sendMessage(new ChatMessage(MessageType.EMAIL, txt_passwordLost_email.getText()));
			}
		}
		if (o == btn_logIn) {
			client.sendMessage(new ChatMessage(MessageType.LOGIN, txt_login_username.getText(),
					String.valueOf(txt_login_password.getPassword())));
			client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));

		}
		if (o == btn_login_lostPassword) {
			cardLayoutLogIn.show(cardPanel, "PASSWORD_LOST_PANEL");
		}
		if (o == btn_login_registerNow) {
			cardLayoutLogIn.show(cardPanel, "REGISTER_PANEL");
		}
		if (o == close) {
			cardLayoutLogIn.show(cardPanel, "START");
		}
		// START PANEL NOW ON
		if (o == btn_start_search_group) {
			cardLayoutStart.show(start_cardLayoutPanel, "SEARCH_GROUP");
			cardLayoutHelp.show(helpCard, "H_SEARCH");
			make_button_blue("SEARCH");
			client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == btn_start_create_group) {
			cardLayoutStart.show(start_cardLayoutPanel, "CREATE_GROUP");
			cardLayoutHelp.show(helpCard, "H_CREATE");
			make_button_blue("CREATE");
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == btn_start_admin_group) {
			cardLayoutStart.show(start_cardLayoutPanel, "ADMIN_GROUP");
			cardLayoutHelp.show(helpCard, "H_ADMIN");
			make_button_blue("ADMIN");
			client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
			fill_my_admin_groupTable();
			fill_my_admin_groupMembers();
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == btn_start_member_group) {

			cardLayoutStart.show(start_cardLayoutPanel, "MEMBER_GROUP");
			cardLayoutHelp.show(helpCard, "H_MEMBER");
			client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
			make_button_blue("MEMBER");
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == btn_start_my_local_task) {
			client.sendMessage(new ChatMessage(MessageType.GET_LOCAL_TASKS, ""));
			cardLayoutHelp.show(helpCard, "H_LOCAL");
			cardLayoutStart.show(start_cardLayoutPanel, "LOCAL_TASKS");
			make_button_blue("LOCAL");
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == btn_start_my_group_task) {
			cardLayoutStart.show(start_cardLayoutPanel, "GROUP_TASKS");
			cardLayoutHelp.show(helpCard, "H_GROUPT");
			make_button_blue("GROUP");
			client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
			fill_my_group_task_panel();
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == tglbtnNewToggleButton) {
			if (((AbstractButton) o).isSelected()) {
				((AbstractButton) o).setIcon(new ImageIcon("img/on.png"));
			} else {
				((AbstractButton) o).setIcon(new ImageIcon("img/off.png"));
			}
		}
		if (o == rdbtn_create_privateGroup) {
			if (((AbstractButton) o).isSelected()) {
				rdbtn_create_publicGroup.setSelected(false);
			}
		}
		if (o == rdbtn_create_publicGroup) {
			if (((AbstractButton) o).isSelected()) {
				rdbtn_create_privateGroup.setSelected(false);
			}
		}
		if (o == edit_public) {
			if (((AbstractButton) o).isSelected()) {
				edit_private.setSelected(false);
			}
		}
		if (o == edit_private) {
			if (((AbstractButton) o).isSelected()) {
				edit_public.setSelected(false);
			}
		}
		if (o == btn_create_createGroup) {
			if (verify_create_fields()) {
				Random rn = new Random();
				int id = rn.nextInt(89999) + 10000;
				Privacy privacy = null;
				if (rdbtn_create_privateGroup.isSelected()) {
					privacy = Privacy.PRIVATE;
				} else if (rdbtn_create_publicGroup.isSelected()) {
					privacy = Privacy.PUBLIC;
				}
				Team newTeam = new Team(id, txt_create_groupName.getText(), txt_create_groupDescription.getText(),
						privacy, logged_in_user);

				client.sendMessage(new ChatMessage(MessageType.CREATE_GROUP, newTeam));
				client.sendMessage(new ChatMessage(MessageType.GET_GROUPS, ""));
			}
		}
		if (o == btn_local_addTask) {
			if (verify_local_addTask()) {
				int priority = Integer.parseInt(cb_local_taskPriority.getSelectedItem().toString());
				String year = (cb_local_year.getSelectedItem().toString());
				String month = (cb_local_month.getSelectedItem().toString());
				String day = (cb_local_day.getSelectedItem().toString());
				Task localTask = new Task(txt_local_taskName.getText(), txt_local_taskDescription.getText(),
						logged_in_user, TaskStatus.WORKING, priority, year, month, day, logged_in_user);
				client.sendMessage(new ChatMessage(MessageType.ADD_LOCAL_TASK, localTask, logged_in_user));
				client.sendMessage(new ChatMessage(MessageType.GET_LOCAL_TASKS, ""));

			}
		}
		if (o == btn_member_addTask) {
			if (verify_member_addTask()) {
				int index = table_member.getSelectedRow();
				if (index >= 0) {
					String teamId = String.valueOf(table_member.getValueAt(index, 0));
					int priority = Integer.parseInt(cb_member_taskImportance.getSelectedItem().toString());
					String year = (cb_member_year.getSelectedItem().toString());
					String month = (cb_member_month.getSelectedItem().toString());
					String day = (cb_member_day.getSelectedItem().toString());
					Task task = new Task(txt_member_taskname.getText(), txt_member_taskDescription.getText(),
							logged_in_user, TaskStatus.WAITING_USER, priority, year, month, day, "N?A");
					client.sendMessage(new ChatMessage(MessageType.ADD_TASK, task, teamId));
				} else {
					JOptionPane.showMessageDialog(panel_member_group, "Please select a team!");
				}

			}
		}
		if (o == btn_notification) {
			fillNotification();
			fillInvitationsTable();
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "true", logged_in_user));
		}
		if (o == btn_search_search) {
			fill_searched_groups();
		}

		if (o == btn_admin_messageMember) {

			String multiLineMsg[] = { "Write a message:" };

			userSentTo = getSelectedMember(table_admin_members, 0);

			if (userSentTo != null) {
				String input = JOptionPane.showInputDialog(null, multiLineMsg, "Message", JOptionPane.QUESTION_MESSAGE);
				client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, input, userSentTo, logged_in_user));
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a memeber!");
			}
		}

		if (o == btn_member_sendMessage) {

			String multiLineMsg[] = { "Write a message:" };

			userSentTo = getSelectedMember(table_member, 2);

			if (userSentTo != null) {
				String input = JOptionPane.showInputDialog(null, multiLineMsg, "Message", JOptionPane.QUESTION_MESSAGE);
				client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, input, userSentTo, logged_in_user));
			} else {
				JOptionPane.showMessageDialog(panel_member_group, "Please select a memeber!");
			}
		}

		if (o == btn_search_AskToJoin) {
			int index = table_search.getSelectedRow();
			if (index >= 0) {
				String tmName = (String) table_search.getValueAt(index, 1);
				client.sendMessage(new ChatMessage(MessageType.ADD_USER, logged_in_user, tmName));
			} else {
				JOptionPane.showMessageDialog(panel_search_group, "Please select a team!");
			}
		}
		if (o == btn_member_leaveGroup) {
			String idOfGroup = getSelectedMember(table_member, 0);
			String groupToLeave = getSelectedMember(table_member, 1);
			String groupAdmin = getSelectedMember(table_member, 2);
			if (groupToLeave != null) {
				if (groupAdmin.contentEquals(logged_in_user)) {
					String multiLineMsg[] = { "You cannot leave this group because you are an admin.",
							"If you really want to leave, please edit the informations",
							"of the group and chenge the admin first", "in the adnim panel" };
					JOptionPane.showMessageDialog(panel_search_group, multiLineMsg);
				} else {
					client.sendMessage(
							new ChatMessage(MessageType.LEAVE_GROUP, idOfGroup, logged_in_user, groupToLeave));
					model_table_my_groups.removeRow(table_member.getSelectedRow());
				}
			} else {
				JOptionPane.showMessageDialog(panel_member_group, "Please select a team!");
			}
		}
		if (o == btn_admin_removeMember) {
			String userToDelete = getSelectedMember(table_admin_members, 0);
			String idOfGroup = getSelectedMember(table_admin_groups, 0);
			String groupToLeave = getSelectedMember(table_admin_groups, 1);
			if (userToDelete == null) {
				JOptionPane.showMessageDialog(panel_member_group, "Please select a member!");
			} else if (userToDelete.contentEquals(logged_in_user)) {
				JOptionPane.showMessageDialog(panel_member_group, "Cannot delete your own account");
			} else {
				client.sendMessage(new ChatMessage(MessageType.LEAVE_GROUP, idOfGroup, userToDelete, groupToLeave));
				model_table_my_adminGroupMembers.removeRow(table_admin_members.getSelectedRow());
			}
		}
		if (o == btn_local_quitTask) {
			String taskToQuit = getSelectedMember(table_local_tasks, 0);
			String status = getSelectedMember(table_local_tasks, 1);
			if (taskToQuit == null) {
				JOptionPane.showMessageDialog(panel_local_tasks, "Please select a task!");
			} else {
				if (status.contentEquals("CANCELED")) {
					JOptionPane.showMessageDialog(panel_local_tasks, "Task already canceled");
				} else if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(panel_local_tasks, "Task already finished");
				} else {
					client.sendMessage(new ChatMessage(MessageType.QUIT_LOCAL_TASK, taskToQuit, logged_in_user));
					model_table_local_tasks.setValueAt("CANCELED", table_local_tasks.getSelectedRow(), 1);
				}
			}
		}
		if (o == btn_local_updateTask) {
			String taskSelected = getSelectedMember(table_local_tasks, 0);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_local_tasks, "Please select a task!");
			} else {
				String newTitle = txt_local_taskName.getText();
				String newDescription = txt_local_taskDescription.getText();
				String priority = String.valueOf(cb_local_taskPriority.getSelectedItem());
				if (newTitle.contentEquals("")) {
					String multiLineMsg[] = { "Please fill in the Task name box", "If you do not want to change it,",
							"fill in with the actual version." };
					JOptionPane.showMessageDialog(panel_local_tasks, multiLineMsg);
				} else if (newDescription.contentEquals("")) {
					String multiLineMsg[] = { "Please fill in the Task description box",
							"If you do not want to change it,", "fill in with the actual version." };
					JOptionPane.showMessageDialog(panel_local_tasks, multiLineMsg);
				} else {
					client.sendMessage(new ChatMessage(MessageType.UPDATE_LOCAL_TASK, taskSelected, newTitle,
							newDescription, priority, logged_in_user));
					model_table_local_tasks.setValueAt(newTitle, table_local_tasks.getSelectedRow(), 0);
					model_table_local_tasks.setValueAt(priority, table_local_tasks.getSelectedRow(), 2);
				}
			}
		}
		if (o == btn_local_requestTime) {
			String taskSelected = getSelectedMember(table_local_tasks, 0);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_local_tasks, "Please select a task!");
			} else {
				String input = JOptionPane.showInputDialog(panel_local_tasks, "How many days would you need to add?",
						"Add time", JOptionPane.QUESTION_MESSAGE);
				if (!input.matches("[0-9]+") || input == null) {
					JOptionPane.showMessageDialog(panel_local_tasks, "Please enter a number!");
				} else {
					client.sendMessage(
							new ChatMessage(MessageType.UPDATE_TASK_DATE, taskSelected, logged_in_user, input));
					int i = Integer.parseInt(getSelectedMember(table_local_tasks, 3));
					i += Integer.parseInt(input);
					model_table_local_tasks.setValueAt(i, table_local_tasks.getSelectedRow(), 3);
				}
			}
		}
		if (o == btn_local_submitTask) {
			String taskSelected = getSelectedMember(table_local_tasks, 0);
			String status = getSelectedMember(table_local_tasks, 1);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_local_tasks, "Please select a task!");
			} else {
				if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(panel_local_tasks, "Task already submited");
				} else {
					String input = JOptionPane.showInputDialog(panel_local_tasks, "Write your submiting message",
							"Submision", JOptionPane.QUESTION_MESSAGE);
					if (input == null || input.contentEquals("")) {
						JOptionPane.showMessageDialog(panel_local_tasks, "Please write a message!");
					} else {
						client.sendMessage(
								new ChatMessage(MessageType.SUBMIT_LOCAL_TASK, taskSelected, logged_in_user));
						client.sendMessage(
								new ChatMessage(MessageType.ADD_LOCAL_COMMENT, logged_in_user, taskSelected, input));
						model_table_local_tasks.setValueAt("FINISHED", table_local_tasks.getSelectedRow(), 1);
					}

				}
			}
		}
		if (o == btn_search_openGroup) {
			String groupSelectedId = getSelectedMember(table_search, 0);
			String groupSelectedName = getSelectedMember(table_search, 1);
			if (groupSelectedId == null) {
				JOptionPane.showMessageDialog(table_search, "Please select a group!");
			} else {
				client.sendMessage(new ChatMessage(MessageType.CHECK_IF_MEMBER, groupSelectedId, logged_in_user,
						groupSelectedName));
			}
		}
		if (o == btn_member_openGroup) {
			String groupSelectedId = getSelectedMember(table_member, 0);
			String groupSelectedName = getSelectedMember(table_member, 1);
			if (groupSelectedId == null) {
				JOptionPane.showMessageDialog(table_member, "Please select a group!");
			} else {
				client.sendMessage(new ChatMessage(MessageType.CHECK_IF_MEMBER, groupSelectedId, logged_in_user,
						groupSelectedName));
			}
		}
		if (o == btn_admin_openGroup) {
			String groupSelectedId = getSelectedMember(table_admin_groups, 0);
			String groupSelectedName = getSelectedMember(table_admin_groups, 1);
			if (groupSelectedId == null) {
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a group!");
			} else {
				client.sendMessage(new ChatMessage(MessageType.CHECK_IF_MEMBER, groupSelectedId, logged_in_user,
						groupSelectedName));
			}
		}

		if (o == btn_admin_inviteMember) {
			int index = table_admin_groups.getSelectedRow();
			if (index >= 0) {
				String inv = txt_admin_searchUsers.getText();
				String grp = String.valueOf(table_admin_groups.getValueAt(index, 0));
				userSentTo = inv;
				client.sendMessage(new ChatMessage(MessageType.INVITE_MEMBER, inv, logged_in_user, grp));
				client.sendMessage(new ChatMessage(MessageType.GET_USERS, ""));
			} else
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a team!");
		}
		if (o == btn_group_addComment) {
			int index = table_group.getSelectedRow();

			if (index >= 0) {
				String taskName = String.valueOf(table_group.getValueAt(index, 0));
				String taskAuthor = String.valueOf(table_group.getValueAt(index, 1));
				String comment = txt_group_addComment.getText();

				txt_group_comments.setText("");
				client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskName, taskAuthor,
						logged_in_user, comment));
				client.sendMessage(new ChatMessage(MessageType.GET_TASK, txtId.getText(), taskName, taskAuthor));
			} else {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			}
		}
		if (o == btn_groupTask_comment) {
			int index = table_groupTask.getSelectedRow();

			if (index >= 0) {
				String taskName = getSelectedMember(table_groupTask, 0);
				String groupId = getSelectedMember(table_groupTask, 1);
				String comment = txt_groupTask_addComment.getText();

				txt_groupTask_comments.setText("");
				client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, groupId, taskName, "PASS_123",
						logged_in_user, comment));
				client.sendMessage(new ChatMessage(MessageType.GET_TASK, groupId, taskName, "PASS_123"));
			} else {
				JOptionPane.showMessageDialog(table_groupTask, "Please select a task!");
			}
		}
		if (o == btn_group_addTask) {
			if (verify_group_addTask()) {

				String groupId = txtId.getText();
				int priority = Integer.parseInt(cb_group_priority.getSelectedItem().toString());
				String year = (cb_group_year.getSelectedItem().toString());
				String month = (cb_group_month.getSelectedItem().toString());
				String day = (cb_group_day.getSelectedItem().toString());
				Task task = new Task(txt_group_taskName.getText(), txt_group_taskDescription.getText(), logged_in_user,
						TaskStatus.WAITING_USER, priority, year, month, day, "N?A");
				client.sendMessage(new ChatMessage(MessageType.ADD_TASK, task, groupId));

				Object[] row = { task.getTaskName(), task.getAuthor(), task.getStatus(), task.getWorkingPers(),
						task.getPriority(), task.getDaysToDeadline() };
				model_group_table.addRow(row);

			} else {
				JOptionPane.showMessageDialog(table_group, "Please fill all the fields!");
			}
		}
		if (o == btn_group_submit) {
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			String status = getSelectedMember(table_group, 2);
			String assigned = getSelectedMember(table_group, 3);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			} else {
				if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(table_group, "Task already submited");
				} else if (!assigned.contentEquals(logged_in_user)) {
					String multiLineMsg[] = { "This task was not assigned to you",
							"If you still want to submit this task,", "please take the task first" };
					JOptionPane.showMessageDialog(table_group, multiLineMsg);
				} else {
					String input = JOptionPane.showInputDialog(table_group, "Write your submiting message", "Submision",
							JOptionPane.QUESTION_MESSAGE);
					if (input == null || input.contentEquals("")) {
						JOptionPane.showMessageDialog(table_group, "Please write a message!");
					} else {
						txt_group_comments.setText("");
						client.sendMessage(new ChatMessage(MessageType.SUBMIT_TASK, taskSelected, logged_in_user,
								txtId.getText()));
						client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskSelected,
								taskAuthor, logged_in_user, "SUBMITED " + input));

						model_group_table.setValueAt("FINISHED", table_group.getSelectedRow(), 2);
					}

				}
			}
		}
		if (o == btn_groupTask_submit) {
			String taskSelected = getSelectedMember(table_groupTask, 0);
			String groupId = getSelectedMember(table_groupTask, 1);
			String taskStatus = getSelectedMember(table_groupTask, 3);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_group_tasks, "Please select a task!");
			} else {
				if (taskStatus.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(panel_group_tasks, "Task already submited");
				} else {
					String input = JOptionPane.showInputDialog(panel_group_tasks, "Write your submiting message",
							"Submision", JOptionPane.QUESTION_MESSAGE);
					if (input == null || input.contentEquals("")) {
						JOptionPane.showMessageDialog(panel_group_tasks, "Please write a message!");
					} else {
						txt_groupTask_comments.setText("");
						client.sendMessage(
								new ChatMessage(MessageType.SUBMIT_TASK, taskSelected, logged_in_user, groupId));
						client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, groupId, taskSelected, "PASS_123",
								logged_in_user, "SUBMITED " + input));

						model_table_groupTask.setValueAt("FINISHED", table_groupTask.getSelectedRow(), 3);
					}

				}
			}
		}
		if (o == btn_group_take) {
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			String status = getSelectedMember(table_group, 2);
			String assigned = getSelectedMember(table_group, 3);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			} else {
				if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(table_group, "Task already submited");
				} else if (assigned.contentEquals(logged_in_user)) {
					String multiLineMsg[] = { "This task is already assigned to you",
							"You can submit it or take another task" };
					JOptionPane.showMessageDialog(table_group, multiLineMsg);
				} else if (status.contentEquals("WORKING")) {

					String multiLineMsg[] = { "This task is already taken", "You can take another task" };
					JOptionPane.showMessageDialog(table_group, multiLineMsg);

				} else {
					txt_group_comments.setText("");
					client.sendMessage(
							new ChatMessage(MessageType.TAKE_TASK, taskSelected, logged_in_user, txtId.getText()));
					client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskSelected,
							taskAuthor, logged_in_user, "task taken by me"));

					model_group_table.setValueAt("WORKING", table_group.getSelectedRow(), 2);
					model_group_table.setValueAt(logged_in_user, table_group.getSelectedRow(), 3);
				}
			}
		}
		if (o == btn_group_quit) {
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			String status = getSelectedMember(table_group, 2);
			String assigned = getSelectedMember(table_group, 3);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			} else {
				if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(table_group, "Task already submited");
				} else if (!assigned.contentEquals(logged_in_user)) {
					String multiLineMsg[] = { "This task was not assigned to you" };
					JOptionPane.showMessageDialog(table_group, multiLineMsg);
				} else {
					String input = JOptionPane.showInputDialog(table_group, "Write your quiting message", "Quit task",
							JOptionPane.QUESTION_MESSAGE);
					if (input == null || input.contentEquals("")) {
						JOptionPane.showMessageDialog(table_group, "Please write a message!");
					} else {
						txt_group_comments.setText("");
						client.sendMessage(
								new ChatMessage(MessageType.QUIT_TASK, taskSelected, logged_in_user, txtId.getText()));
						client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskSelected,
								taskAuthor, logged_in_user, "QUIT " + input));

						model_group_table.setValueAt("WAITING_USER", table_group.getSelectedRow(), 2);
						model_group_table.setValueAt("N?A", table_group.getSelectedRow(), 3);
					}

				}
			}
		}
		if (o == btn_groupTask_quit) {
			String taskSelected = getSelectedMember(table_groupTask, 0);
			String groupId = getSelectedMember(table_groupTask, 1);
			String taskStatus = getSelectedMember(table_groupTask, 3);
			model_table_groupTask = (DefaultTableModel) table_groupTask.getModel();
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_group_tasks, "Please select a task!");
			} else {
				if (taskStatus.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(panel_group_tasks, "Task already submited");
				} else {
					String input = JOptionPane.showInputDialog(panel_group_tasks, "Write your quiting message",
							"Quit task", JOptionPane.QUESTION_MESSAGE);
					if (input == null || input.contentEquals("")) {
						JOptionPane.showMessageDialog(panel_group_tasks, "Please write a message!");
					} else {
						txt_groupTask_comments.setText("");
						client.sendMessage(
								new ChatMessage(MessageType.QUIT_TASK, taskSelected, logged_in_user, groupId));
						client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, groupId, taskSelected, "PASS_123",
								logged_in_user, "QUIT " + input));

						model_table_groupTask.removeRow(table_groupTask.getSelectedRow());
					}

				}
			}
		}
		if (o == btn_group_reschedule) {
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			String status = getSelectedMember(table_group, 2);
			String assigned = getSelectedMember(table_group, 3);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			} else {
				if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(table_group, "Task already submited");
				} else if (!assigned.contentEquals(logged_in_user)) {
					String multiLineMsg[] = { "This task was not assigned to you" };
					JOptionPane.showMessageDialog(table_group, multiLineMsg);
				} else {
					String input = JOptionPane.showInputDialog(table_group, "Insert the days needed", "Reschedule task",
							JOptionPane.QUESTION_MESSAGE);
					if (!input.matches("[0-9]+") || input == null) {
						JOptionPane.showMessageDialog(table_group, "Please write a number!");
					} else {
						txt_group_comments.setText("");
						client.sendMessage(new ChatMessage(MessageType.RESC_TASK, taskSelected, logged_in_user,
								txtId.getText(), input));
						client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskSelected,
								taskAuthor, logged_in_user, " rescheduled the deadline"));

						int daysBefore = Integer.parseInt(getSelectedMember(table_group, 5));
						int daysAfter = daysBefore + Integer.parseInt(input);
						model_group_table.setValueAt(String.valueOf(daysAfter), table_group.getSelectedRow(), 5);
					}
				}
			}
		}
		if (o == btn_groupTask_reschedule) {
			String taskSelected = getSelectedMember(table_groupTask, 0);
			String groupId = getSelectedMember(table_groupTask, 1);
			String taskStatus = getSelectedMember(table_groupTask, 3);
			model_table_groupTask = (DefaultTableModel) table_groupTask.getModel();
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_group_tasks, "Please select a task!");
			} else {
				if (taskStatus.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(panel_group_tasks, "Task already submited");
				} else {
					String input = JOptionPane.showInputDialog(panel_group_tasks, "Insert the days needed",
							"Reschedule task", JOptionPane.QUESTION_MESSAGE);
					if (!input.matches("[0-9]+") || input == null) {
						JOptionPane.showMessageDialog(panel_group_tasks, "Please write a number!");
					} else {
						txt_groupTask_comments.setText("");
						client.sendMessage(
								new ChatMessage(MessageType.RESC_TASK, taskSelected, logged_in_user, groupId, input));
						client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, groupId, taskSelected, "PASS_123",
								logged_in_user, " rescheduled the deadline"));

						int daysBefore = Integer.parseInt(getSelectedMember(table_groupTask, 5));
						int daysAfter = daysBefore + Integer.parseInt(input);

						model_table_groupTask.setValueAt(String.valueOf(daysAfter), table_groupTask.getSelectedRow(),
								5);
					}
				}
			}
		}
		if (o == btn_group_update) {
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			String status = getSelectedMember(table_group, 2);
			String assigned = getSelectedMember(table_group, 3);
			String admin = null;

			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(txtId.getText())) {
						userSentTo = t.getAdmin();
						admin = t.getAdmin();
					}
				}
				if (status.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(table_group, "Task already submited");
				} else {
					if (admin.contentEquals(logged_in_user)) {
						String newName = txt_group_taskName.getText();
						String newDescription = txt_group_taskDescription.getText();
						String newPriority = String.valueOf(cb_group_priority.getSelectedItem());
						client.sendMessage(new ChatMessage(MessageType.UPDATE_TASK, txtId.getText(), taskSelected,
								newName, newDescription, newPriority));
						boolean changed = false;
						if (!newName.contentEquals("")) {
							model_group_table.setValueAt(newName, table_group.getSelectedRow(), 0);
							changed = true;
						}
						if (!newPriority.contentEquals("")) {
							model_group_table.setValueAt(newPriority, table_group.getSelectedRow(), 4);
							changed = true;
						}
						if (changed) {
							client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskSelected,
									taskAuthor, logged_in_user, " updated the task"));
						}

					} else if (!assigned.contentEquals(logged_in_user)) {

						String multiLineMsg[] = { "This task was not assigned to you" };
						JOptionPane.showMessageDialog(table_group, multiLineMsg);
					} else {
						String multiLineMsg[] = { "Send your task update request", "by sending a message to the admin",
								"please write explicitly your requests in form: ", "Task Name : new task name",
								"Task Description : new task description", "Task Priority : new taask priority",
								"-you can also request update for only one field" };
						String input = JOptionPane.showInputDialog(table_group, multiLineMsg, "Update request",
								JOptionPane.QUESTION_MESSAGE);
						if (input == null) {
							JOptionPane.showMessageDialog(table_group, "Please write an update message!");
						} else {
							txt_group_comments.setText("");
							client.sendMessage(
									new ChatMessage(MessageType.USER_MESSAGE, input, userSentTo, logged_in_user));
							client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, txtId.getText(), taskSelected,
									taskAuthor, logged_in_user, " requested an update"));

						}
					}
				}
			}
		}
		if (o == btn_groupTask_update) {
			String taskSelected = getSelectedMember(table_groupTask, 0);
			String groupId = getSelectedMember(table_groupTask, 1);
			String taskStatus = getSelectedMember(table_groupTask, 3);
			String admin = null;

			model_table_groupTask = (DefaultTableModel) table_groupTask.getModel();

			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_group_tasks, "Please select a task!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(groupId)) {
						userSentTo = t.getAdmin();
						admin = t.getAdmin();
					}
				}
				if (taskStatus.contentEquals("FINISHED")) {
					JOptionPane.showMessageDialog(panel_group_tasks, "Task already submited");
				} else {
					if (admin.contentEquals(logged_in_user)) {
						String newName = txt_gt_name.getText();
						String newDescription = txt_gt_description.getText();
						String newPriority = String.valueOf(txt_cb_priority.getSelectedItem());
						client.sendMessage(new ChatMessage(MessageType.UPDATE_TASK, groupId, taskSelected, newName,
								newDescription, newPriority));
						boolean changed = false;
						if (!newName.contentEquals("")) {
							model_table_groupTask.setValueAt(newName, table_groupTask.getSelectedRow(), 0);
							changed = true;
						}
						if (!newPriority.contentEquals("")) {
							model_table_groupTask.setValueAt(newPriority, table_groupTask.getSelectedRow(), 4);
							changed = true;
						}
						if (changed) {
							client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, groupId, taskSelected,
									"PASS_123", logged_in_user, " updated the task"));
						}

					} else {
						String multiLineMsg[] = { "Send your task update request", "by sending a message to the admin",
								"please write explicitly your requests in form: ", "Task Name : new task name",
								"Task Description : new task description", "Task Priority : new taask priority",
								"-you can also request update for only one field" };
						String input = JOptionPane.showInputDialog(panel_group_tasks, multiLineMsg, "Update request",
								JOptionPane.QUESTION_MESSAGE);
						if (input == null) {
							JOptionPane.showMessageDialog(panel_group_tasks, "Please write an update message!");
						} else {
							txt_group_comments.setText("");
							client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, input, admin, logged_in_user));
							client.sendMessage(new ChatMessage(MessageType.ADD_COMMENT, groupId, taskSelected,
									"PASS_123", logged_in_user, " requested an update"));

						}
					}
				}
			}
		}
		if (o == btn_group_details) {
			String taskSelected = getSelectedMember(table_group, 0);
			String taskAuthor = getSelectedMember(table_group, 1);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_group, "Please select a task!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(txtId.getText())) {
						for (Task ta : t.getTasks()) {
							if (ta.getTaskName().contentEquals(taskSelected)
									&& ta.getAuthor().contentEquals(taskAuthor)) {
								showTaskDetails(ta, txt_group_theGroupSelected.getText());
							}
						}
					}
				}
			}
		}
		if (o == btn_groupTask_details) {
			String taskSelected = getSelectedMember(table_groupTask, 0);
			String groupId = getSelectedMember(table_groupTask, 1);
			String groupName = getSelectedMember(table_groupTask, 2);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(table_groupTask, "Please select a task!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(groupId)) {
						for (Task ta : t.getTasks()) {
							if (ta.getTaskName().contentEquals(taskSelected)) {
								showTaskDetails(ta, groupName);
							}
						}
					}
				}
			}
		}
		if (o == btn_local_viewDetails) {
			String taskSelected = getSelectedMember(table_local_tasks, 0);
			if (taskSelected == null) {
				JOptionPane.showMessageDialog(panel_local_tasks, "Please select a task!");
			} else {

				for (LocalTask ta : localTaskDatabaseInstance.getAllLocalTasks()) {
					if (ta.getUser().getUserName().contentEquals(logged_in_user)) {
						for (Task t : ta.getLocalTasks()) {
							if (t.getTaskName().contentEquals(taskSelected)) {
								showTaskDetails(t, "LOCAL TASK");
							}
						}
					}

				}

			}
		}
		if (o == btn_search_details) {
			String groupSelectedID = getSelectedMember(table_search, 0);
			if (groupSelectedID == null) {
				JOptionPane.showMessageDialog(table_search, "Please select a group!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(groupSelectedID)) {
						showGroupDetails(t);
					}
				}
			}
		}
		if (o == btn_member_groupDetails) {
			String groupSelectedID = getSelectedMember(table_member, 0);
			if (groupSelectedID == null) {
				JOptionPane.showMessageDialog(panel_member_group, "Please select a group!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(groupSelectedID)) {
						showGroupDetails(t);
					}
				}
			}
		}
		if (o == btn_admin_viewDetails) {
			String groupSelectedID = getSelectedMember(table_admin_groups, 0);
			if (groupSelectedID == null) {
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a group!");
			} else {
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(groupSelectedID)) {
						showGroupDetails(t);
					}
				}
			}
		}
		if (o == btn_admin_makeAdmin) {
			userSentTo = getSelectedMember(table_admin_members, 0);
			String groupSelectedId = getSelectedMember(table_admin_groups, 0);
			String groupSelectedName = getSelectedMember(table_admin_groups, 1);
			if (userSentTo != null) {
				String input = "You were made admin of " + groupSelectedName;
				client.sendMessage(new ChatMessage(MessageType.CHANGE_ADMIN, groupSelectedId, userSentTo));
				client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, input, userSentTo, logged_in_user));
				JOptionPane.showMessageDialog(panel_admin_groups, "Changes will occur shortly");
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a memeber!");
			}
		}
		if (o == btn_admin_deleteGroup) {
			String groupToDeleteId = getSelectedMember(table_admin_groups, 0);
			String groupToDeleteName = getSelectedMember(table_admin_groups, 1);
			if (groupToDeleteId != null) {
				String input = "The group " + groupToDeleteName + " was deleted by admin";
				client.sendMessage(new ChatMessage(MessageType.DELETE_GROUP, groupToDeleteId));
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(groupToDeleteId)) {
						for (TeamMember tm : t.getMembers()) {
							userSentTo = logged_in_user;
							client.sendMessage(
									new ChatMessage(MessageType.NOT_STATUS, "false", tm.getAccount().getUserName()));
							client.sendMessage(
									new ChatMessage(MessageType.USER_MESSAGE, input, tm.getName(), logged_in_user));
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a group!");
			}
		}

		if (o == btn_admin_editInfo) {
			String groupid = getSelectedMember(table_admin_groups, 0);
			if (groupid != null) {
				edit_teamid.setText(groupid);
				panel_editgroup.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Please select a group!");

			}
		}
		if (o == edit_btn_name) {
			if (!edit_name.getText().contentEquals("")) {

				client.sendMessage(new ChatMessage(MessageType.EDIT_GROUP, edit_teamid.getText(), edit_name.getText(),
						"NO", "NO"));
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(edit_teamid.getText())) {
						for (TeamMember tm : t.getMembers()) {
							userSentTo = logged_in_user;
							client.sendMessage(
									new ChatMessage(MessageType.NOT_STATUS, "false", tm.getAccount().getUserName()));
							client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, " changed the group name",
									tm.getName(), logged_in_user));
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Team name cannot be null");
			}
		}
		if (o == edit_btn_description) {
			if (!edit_description.getText().contentEquals("")) {

				client.sendMessage(new ChatMessage(MessageType.EDIT_GROUP, edit_teamid.getText(), "NO",
						edit_description.getText(), "NO"));
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(edit_teamid.getText())) {
						for (TeamMember tm : t.getMembers()) {
							userSentTo = logged_in_user;
							client.sendMessage(
									new ChatMessage(MessageType.NOT_STATUS, "false", tm.getAccount().getUserName()));
							client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE,
									" changed the group description", tm.getName(), logged_in_user));
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Team description cannot be null");
			}
		}
		if (o == edit_btn_privacy) {
			if ((edit_private.isSelected() || edit_public.isSelected())) {

				String privacy = null;
				if (edit_public.isSelected()) {
					privacy = "PUBLIC";
				} else if (edit_private.isSelected()) {
					privacy = "PRIVATE";
				}
				client.sendMessage(new ChatMessage(MessageType.EDIT_GROUP, edit_teamid.getText(), "NO", "NO", privacy));
				for (Team t : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(t.getId()).contentEquals(edit_teamid.getText())) {
						for (TeamMember tm : t.getMembers()) {
							userSentTo = logged_in_user;
							client.sendMessage(
									new ChatMessage(MessageType.NOT_STATUS, "false", tm.getAccount().getUserName()));
							client.sendMessage(new ChatMessage(MessageType.USER_MESSAGE, " changed the group privacy",
									tm.getName(), logged_in_user));
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(panel_admin_groups, "Team privacy cannot be null");
			}
		}
		if (o == edit_btn_close) {
			panel_editgroup.setVisible(false);
			edit_description.setText("");
			edit_name.setText("");
			edit_public.setSelected(false);
			edit_private.setSelected(false);
		}
		if (o == taskDetail_btn) {
			panel_taskdetails.setVisible(false);
		}
		if (o == detgroup_btn) {
			panel_detgrup.setVisible(false);

		}

	}

	private void showGroupDetails(Team t) {
		detgroup_admin.setText(t.getAdmin());
		detgroup_description.setText(t.getTeamDescription());
		detgroup_id.setText(String.valueOf(t.getId()));
		detgroup_name.setText(t.getTeamName());
		detgroup_nrmembers.setText(String.valueOf(t.getMembers().size()));
		detgroup_privacy.setText(String.valueOf(t.getPrivacy()));
		panel_detgrup.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		// refresh
		Object o = e.getSource();
		if (o != null && o != back_icon && o != icon_passwordLost_back) {
			client.sendMessage(new ChatMessage(MessageType.NOT_STATUS, "CHECK_ONLY_ONCE", logged_in_user));
		}
		if (o == back_icon) {
			txt_register_country.setText("");
			txt_register_name.setText("");
			txt_register_surname.setText("");
			txt_register_email.setText("");
			txt_register_username.setText("");
			txt_register_password.setText("");
			txt_register_password2.setText("");
			notification_register.setVisible(false);
			verify_country.setVisible(false);
			verify_name.setVisible(false);
			verify_surname.setVisible(false);
			verify_username.setVisible(false);
			verify_email.setVisible(false);
			verify_password.setVisible(false);
			verify_password2.setVisible(false);
			cardLayoutLogIn.show(cardPanel, "LOG_IN_PANEL");
		}
		if (o == help) {

			cardLayoutLogIn.show(cardPanel, "HELP");
		}
		if (o == icon_passwordLost_back) {
			txt_passwordLost_noemail.setVisible(false);
			txt_passwordLost_email.setText("");
			cardLayoutLogIn.show(cardPanel, "LOG_IN_PANEL");
		}
		if (o == lbl_start_home) {
			cardLayoutStart.show(start_cardLayoutPanel, "G");
			make_button_blue("");
		}
		if (o == lbl_start_log_out) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out?", "Sign out",
					dialogButton);
			if (dialogResult == 0) {
				logged_in_user = "";
				cardLayoutStart.show(start_cardLayoutPanel, "G");
				cardLayoutLogIn.show(cardPanel, "LOG_IN_PANEL");
				txt_login_username.setText("");
				txt_login_password.setText("");
			}
		}
		if (o == lbl_start_support) {
			String multiLineMsg[] = { "If you have any kind of troubles, you can contact us by",
					"phone : +40 748 630798", "or by sending a message :" };
			String input = JOptionPane.showInputDialog(null, multiLineMsg, "Support contact",
					JOptionPane.QUESTION_MESSAGE);
			client.sendMessage(new ChatMessage(MessageType.SUPPORT, logged_in_user, input));
		}
		if (o == lbl_start_settings) {
			make_button_blue("");
			cardLayoutStart.show(start_cardLayoutPanel, "SETTINGS");
		}
		if (o == table_admin_groups) {
			fill_my_admin_groupMembers();
		}
		// alex
		if (o == btn_notification_accept) {
			int index = table_notification.getSelectedRow();
			if (index >= 0) {
				String grName = (String) table_notification.getValueAt(index, 0);
				String grAdm = (String) table_notification.getValueAt(index, 1);
				client.sendMessage(
						new ChatMessage(MessageType.INVITATION_ACTION, "true", logged_in_user, grName, grAdm, ""));

			} else
				JOptionPane.showMessageDialog(panel_member_group, "Please select an invitation!", "Invite accepted",
						JOptionPane.PLAIN_MESSAGE);
		}
		if (o == btn_notification_reject) {
			int index = table_notification.getSelectedRow();
			if (index >= 0) {
				String grName = (String) table_notification.getValueAt(index, 0);
				String grAdm = (String) table_notification.getValueAt(index, 1);
				client.sendMessage(
						new ChatMessage(MessageType.INVITATION_ACTION, "false", logged_in_user, grName, grAdm, ""));

			} else
				JOptionPane.showMessageDialog(panel_member_group, "Please select an invitation!", "Invite accepted",
						JOptionPane.PLAIN_MESSAGE);
		}
		if (o == table_group) {
			int index = table_group.getSelectedRow();

			if (index >= 0) {
				String taskName = String.valueOf(table_group.getValueAt(index, 0));
				String taskAuthor = String.valueOf(table_group.getValueAt(index, 1));

				txt_group_comments.setText("");
				client.sendMessage(new ChatMessage(MessageType.GET_TASK, txtId.getText(), taskName, taskAuthor));
			}
		}
		if (o == table_groupTask) {
			int index = table_groupTask.getSelectedRow();

			if (index >= 0) {
				String taskName = String.valueOf(table_groupTask.getValueAt(index, 0));
				String groupId = String.valueOf(table_groupTask.getValueAt(index, 1));
				String taskAuthor = "PASS_123";
				txt_groupTask_comments.setText("");
				client.sendMessage(new ChatMessage(MessageType.GET_TASK, groupId, taskName, taskAuthor));
			}
		}
		if (o == table_search) {
			int index = table_search.getSelectedRow();
			lbl_search_groupName.setText(String.valueOf(table_search.getValueAt(index, 1)));
			lbl_search_admin.setText("Admin " + String.valueOf(table_search.getValueAt(index, 2)));
			lbl_search_nrmembers.setText(String.valueOf(table_search.getValueAt(index, 3)) + " members");
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == panel_notification) {
			panel_notification.repaint();
		}
		if (e.getSource() == panel_taskdetails) {
			panel_taskdetails.repaint();
		}
		if (e.getSource() == panel_detgrup) {
			panel_detgrup.repaint();
		}
		if (e.getSource() == panel_editgroup) {
			panel_editgroup.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void sendMail(String email) {
		// final String username = "dezbatericluj@gmail.com";
		// final String password = "sbf240895trans";
		//
		// Properties props = new Properties();
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.host", "smtp.gmail.com");
		// props.put("mail.smtp.auth", "587");
		//
		// Session session = Session.getInstance(props, new Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication(username, password);
		// }
		// });
		// try {
		// Message message = new MimeMessage(session);
		// message.setFrom(new InternetAddress(username));
		// message.setRecipients(Message.RecipientType.TO,
		// InternetAddress.parse("bogdan.stupariu0@gmail.com"));
		// message.setSubject("lalala");
		// message.setContent("mergeeeeee", "text/html;charset=utf-8");
		// Transport.send(message);
		// System.out.println(("Was the mail sent: Done"));
		//
		// } catch (MessagingException e) {
		// throw new RuntimeException(e);
		// }
	}

	public static GUI getInstance() {
		return guiInstance;
	}
}