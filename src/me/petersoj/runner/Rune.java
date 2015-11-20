package me.petersoj.runner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Rune extends JFrame implements ActionListener{
	JPanel panel;
	JTextArea textArea;
	JButton executeB;
	JButton computerInfo;
	JTextField text;
	
	public Rune(){
		super("Run Shells");
		panel = new JPanel();
		
		text = new JTextField("http://checkip.amazonaws.com/");
		
		textArea = new JTextArea();
		textArea.setColumns(47);
		textArea.setRows(20);
		textArea.setEditable(false);
		
		computerInfo = new JButton("Get HostNames");
		computerInfo.addActionListener(this);
		
		executeB = new JButton("Execute Command");
		executeB.addActionListener(this);
		
		panel.add(text);
		panel.add(executeB, BorderLayout.WEST);
		panel.add(computerInfo, BorderLayout.SOUTH);
		panel.add(new JScrollPane(textArea), BorderLayout.EAST);
		add(panel);
		this.setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == executeB){
			if(text.getText() == "" || text.getText() == null){
				return;
			}else{
				this.executeCommand(text.getText());
			}
		}
		if(e.getSource() == computerInfo){
			String format = new SimpleDateFormat("h:mm:ss").format(new Date());
			try {
				InetAddress inet = InetAddress.getLocalHost();
				textArea.append("(" + format +") HostName: " + inet.getHostName() + "\n");
				textArea.append("(" + format +") HostAddress: " + inet.getHostAddress() + "\n");
				textArea.append("(" + format +") HostCanonicalHostName: " + inet.getCanonicalHostName() + "\n");
				textArea.append("(" + format +") SystemProperty(os.name): " + System.getProperty("os.name") + "\n");
				textArea.append("(" + format +") SystemProperty(os.arch): " + System.getProperty("os.arch") + "\n");
				textArea.append("(" + format +") SystemProperty(os.version): " + System.getProperty("os.version") + "\n");
				textArea.append("(" + format +") SystemProperty(user.name): " + System.getProperty("user.name") + "\n");
				textArea.append("(" + format +") SystemProperty(user.home): " + System.getProperty("user.home") + "\n");
				textArea.append("(" + format +") SystemProperty(user.dir): " + System.getProperty("user.dir") + "\n");
			} catch (UnknownHostException e1) {
				textArea.append(e1.getMessage());
			}
		}
	}
	
	
	private void executeCommand(String command) {
		String format = new SimpleDateFormat("h:mm:ss").format(new Date());
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";			
			while ((line = reader.readLine())!= null) {
				textArea.append("(" + format +")" + line + "\n");
			}
		} catch (Exception e) {
			textArea.append("(" + format + ")" + e.getMessage());
		}
	}
}
