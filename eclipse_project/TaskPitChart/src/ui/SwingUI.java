package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class SwingUI {
	private String url;
	private JFrame frame;
	
	public SwingUI(){
	}
	
	public SwingUI(String url){
		this.url = url;
		initComponents();
	}
	
	public void setURL(String url){
		this.url = url;
		initComponents();
	}
	
	public void run(){
		frame.pack();
        frame.setVisible(true);
	}
	
	private void initComponents(){
		try{
			frame = new JFrame();
			FlowLayout layout = new FlowLayout();
			frame.setLayout(layout);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        JLabel label = new JLabel(new ImageIcon(ImageIO.read(new URL(url))));
	        frame.getContentPane().add(label, BorderLayout.CENTER);
	        frame.pack();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void addChart(String url){
		try{
			JLabel label = new JLabel(new ImageIcon(ImageIO.read(new URL(url))));
			frame.getContentPane().add(label, BorderLayout.CENTER);
			frame.pack();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
