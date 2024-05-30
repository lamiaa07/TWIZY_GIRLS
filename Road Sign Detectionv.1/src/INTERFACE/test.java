package INTERFACE;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import Basic.MaBibliothequeTraitementImage;
import Basic.MaBibliothequeTraitementImageEtendue;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class test {
	JLabel vidpanel = new JLabel("<html>Vérifiez que vous avez bien entré les paramètres et joint le fichier</html>\"");
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	static JFrame frame;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					test window = new test();
					window.frame.setVisible(true);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.darkGray);
		frame.setBounds(100, 100,500,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		vidpanel.setFont(new Font("Source Code Pro Black", Font.PLAIN, 13));
		vidpanel.setForeground(Color.ORANGE);
		vidpanel.setBounds(65, 32, 379, 71);
		frame.getContentPane().add(vidpanel);
		frame.setLocationRelativeTo(null) ;
		Button btnNewButton = new Button("Ok!");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
			
		});
		btnNewButton.setBounds(210, 131, 81, 29);
		frame.getContentPane().add(btnNewButton);
	}
	


}