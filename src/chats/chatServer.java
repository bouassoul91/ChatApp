package chats;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class chatServer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static JTextArea msgArea;
	private static ServerSocket ss;
	private static Socket s;
	private static  DataInputStream dIn;
	private static  DataOutputStream dOut;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatServer frame = new chatServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		try{
			ss = new ServerSocket(1201);
			s = ss.accept();
			
			dIn = new DataInputStream(s.getInputStream());
			dOut = new DataOutputStream(s.getOutputStream());
			String msgIn ="";
			while(true){
				msgIn= dIn.readUTF();
				msgArea.setText(msgArea.getText().trim() + "\n Client : \t" + msgIn);
			}
		}catch(Exception e){
			
		}
	}

	/**
	 * Create the frame.
	 */
	public chatServer() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msgArea = new JTextArea();
		msgArea.setBounds(10, 11, 414, 192);
		contentPane.add(msgArea);
		
		textField = new JTextField();
		textField.setBounds(10, 214, 293, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgOut = "";
					msgOut = textField.getText().trim();
					dOut.writeUTF(msgOut);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(314, 214, 110, 36);
		contentPane.add(btnNewButton);
	}
}
