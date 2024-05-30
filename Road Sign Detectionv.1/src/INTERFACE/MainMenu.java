 package INTERFACE;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import Basic.AnalyseVideo;
import Basic.MaBibliothequeTraitementImage;
import Basic.MaBibliothequeTraitementImageEtendue;



public class MainMenu {
	//JLabel vidpanel = new JLabel();
	
	static {
		System.load("C:\\Users\\HP\\eclipse-workspace\\opencv\\build\\x64\\vc12\\bin\\opencv_ffmpeg2413_64.dll");
	}
	 

	static JLabel label;
	static String path;
	static String refpann;
	int running=1;
	JLabel video= new JLabel();
	int choice;
	int DB=10;
	static JLabel label_2;
	private JFrame frame;
	private final Action action_1 = new SwingAction_1();
	//private final Action action_2 = new SwingAction_2();
	static JTextField textField;
	String password1;
	String User;

	/**
	 * Launch the application.
	 */
	class Multi extends Thread{  
		public void run(){  
			System.out.println("thread is running...");
			//int s=0;
			Mat Jframe = new Mat();
			VideoCapture camera = new VideoCapture(path);
			Mat PanneauAAnalyser = null;
			double [] scores=new double [6];
			while (camera.read(Jframe)&&running==1) {
				//s++;
			//if (s>3){
				Mat transformee = MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(Jframe);
				//la methode seuillage est ici extraite de l'archivage jar du meme nom 
				Mat saturee = MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
				
				//MaBibliothequeTraitementImageEtendue.afficheImage("Image testee", saturee);	
				Mat objetrond = null;
				//Cr ation d'une liste des contours   partir de l'image satur e
				List<MatOfPoint> ListeContours = MaBibliothequeTraitementImageEtendue .ExtractContours(saturee);
				//Pour tous les contours de la liste
				
				int i=0; 
				int a =0;  //compteur de null
				
				for (MatOfPoint contour: ListeContours  ){
					// isole la forme
					objetrond=MaBibliothequeTraitementImageEtendue.DetectForm(Jframe,contour);
					i++;
					
					//int indexemax = identifiepanneau(objetrond) ;
					
					if (objetrond!=null){
					MaBibliothequeTraitementImage.afficheImage("Objet rond detécté", objetrond);
					scores[0]+=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref30.jpg");
					scores[1]+=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref50.jpg");
					scores[2]+=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref70.jpg");
					scores[3]+=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref90.jpg");
					scores[4]+=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref110.jpg");
					scores[5]+=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"refdouble.jpg");						
					double scoremax=-1;
					int indexmax=0;
					for(int j=0;j<scores.length;j++){
						if (scores[j]>scoremax){
							scoremax=scores[j];
							indexmax=j;}
						}
				
				System.out.println(indexmax);
					
					Image img;
					Image dimg;
					ImageIcon imageIcon;
					//MaBibliothequeTraitementImageEtendue.afficheImage("Image testee", Jframe);				
					switch(indexmax){
					case -1:break;
					case 0:
						refpann="ref30.jpg";
						textField.setText("C'est le panneau 30!");
						try {
							img = ImageIO.read(new File(refpann));
							dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
				                    Image.SCALE_SMOOTH);
				            imageIcon = new ImageIcon(dimg);
				            label_2.setIcon(imageIcon);//MaBibliothequeTraitementImage.afficheImage("Objet rond det ct ", objetrond);
							break;
						} catch (IOException e5) {
							// TODO Auto-generated catch block
							e5.printStackTrace();
						}
				           
					case 1:
						refpann="ref50.jpg";
						textField.setText("C'est le panneau 50!");
						  try {
							img = ImageIO.read(new File(refpann));
							dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
						            Image.SCALE_SMOOTH);
						            imageIcon = new ImageIcon(dimg);
						            label_2.setIcon(imageIcon);//MaBibliothequeTraitementImage.afficheImage("Objet rond det ct ", objetrond);
						            break;
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}
				           
					case 2:
						refpann="ref70.jpg";
						textField.setText("C'est le panneau 70!");
						 try {
							img = ImageIO.read(new File(refpann));
							  dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
							            Image.SCALE_SMOOTH);
							          imageIcon = new ImageIcon(dimg);
							            label_2.setIcon(imageIcon);
							            break;
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
				           
					case 3:
						refpann="ref90.jpg";
						textField.setText("C'est le panneau 90!");
						 try {
							img = ImageIO.read(new File(refpann));
							dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
						            Image.SCALE_SMOOTH);
						           imageIcon = new ImageIcon(dimg);
						            label_2.setIcon(imageIcon);
						            break;
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				           
						
	
					case 4:
						refpann="ref110.jpg";
						textField.setText("C'est le panneau 110!");
						 try {
							img = ImageIO.read(new File(refpann));
							dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
						            Image.SCALE_SMOOTH);
						           imageIcon = new ImageIcon(dimg);
						            label_2.setIcon(imageIcon);
						            break;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				           
					case 5:
						refpann="refdouble.jpg";
						textField.setText("C'est le panneau de l'interdiction de dépasser!");
						 try {
							img = ImageIO.read(new File(refpann));
							 dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
							            Image.SCALE_SMOOTH);
							            imageIcon = new ImageIcon(dimg);
							            label_2.setIcon(imageIcon);
							            break;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				          
					}}
					if(objetrond==null) {
						a++;
					}
					if(a==6) {
						scores[0]=0;
						scores[1]=0;
						scores[2]=0;
						scores[3]=0;
						scores[4]=0;
						scores[5]=0;
						a=0;	
					}	
				}
			
			//s=0;
			//}
			
					ImageIcon image = new ImageIcon(Mat2bufferedImage(Jframe));
				
					video.setIcon(image);
					video.repaint();
				
					//super.setContentPane(contentPane);
					Image image1 = new ImageIcon(Mat2bufferedImage(Jframe)).getImage();
					frame.setIconImage(image1);
				
			}
	
		}  
		
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		});
		
		/*//MaBibliothequeTraitementImageEtendue.afficheImage("Image testee", m);
		String imagePath = "p3.jpg";

        // Read the image
        Mat m = Highgui.imread(imagePath);
		Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
		MaBibliothequeTraitementImageEtendue.afficheImage("RGBTOHSV", transformee);
		//MaBibliothequeTraitementImageEtendue.afficheImage("ImageHSV", transformee);
		//la methode seuillage est ici extraite de l'archivage jar du meme nom 
		//Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);*/
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	private void initialize() { //GUI
		frame = new JFrame();
		frame.setBounds(1100, 1100, 1500,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Load the background image
		try {
		    Image backgroundImage = ImageIO.read(new File("ville2.jpg"));
		    backgroundImage = backgroundImage.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
		    JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		    backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		    
		    // Create a layered pane to hold the background and other components
		    JLayeredPane layeredPane = new JLayeredPane();
		    layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		    layeredPane.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		    
		    frame.setContentPane(layeredPane);
		    frame.getContentPane().setLayout(null);
		} catch (IOException e) {
		    e.printStackTrace();
		}

		
		 
		
		JLabel lblNewLabel = new JLabel("RECONNAISSANCE DES PANNEAUX");
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(400, 16, 708, 68); // Title
		frame.getContentPane().add(lblNewLabel);
		   label = new JLabel();
		   label.setBounds(630,260,447,368);  // Affichage image/vid o selcetionn e
		   frame.getContentPane().add(label);
		   JLabel label_1 = new JLabel("Panneau détecté");
		   label_1.setFont(new Font("Source Serif Pro Black", Font.BOLD | Font.ITALIC, 15));
		   label_1.setForeground(Color.WHITE);
		   label_1.setBackground(Color.WHITE);
			label_1.setBounds(99, 490, 177, 20); //Panneau d tect 
			frame.getContentPane().add(label_1);
			
			JLabel label_1bis = new JLabel(" Affichage:"); 
			   label_1bis.setFont(new Font("Source Serif Pro Black", Font.BOLD | Font.ITALIC, 21));
			   label_1bis.setForeground(Color.WHITE);
			   label_1bis.setBackground(Color.BLACK);
				label_1bis.setBounds(600, 150, 177, 68); // Text Affichage
				frame.getContentPane().add(label_1bis);
				 
				
			label_2 = new JLabel();
			label_2.setBounds(99, 510, 167, 136); //Image panneau détecté
			frame.getContentPane().add(label_2);
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.LEFT);
			 
			
			
		// Boutons photo	
		Button btnNewButton = new Button("Telecharger la photo");
		btnNewButton.setActionCommand("Telecharger la photo");
		btnNewButton.setFont(new Font("Showcard Gothic", Font.BOLD, 16));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 running=0;
				 label_2.setIcon(null);
				 textField.setText("");
				//label=new JLabel();
				  JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.dir")));
		          //filter the files
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png","jpeg");
		          file.addChoosableFileFilter(filter);
		          int result = file.showSaveDialog(null);
		          label.setIcon(null) ;
		          video.setIcon(null) ;
		           //if the user click on save in Jfilechooser
		          if(result == JFileChooser.APPROVE_OPTION){
		              File selectedFile = file.getSelectedFile();
		              path = selectedFile.getAbsolutePath();
		              label.setIcon(ResizeImage(path));
		              System.out.println(path);
		              choice=1;
		   
		          }
		          


		          else if(result == JFileChooser.CANCEL_OPTION){
		              System.out.println("No File Select");
		          }
		        }
		    });
		btnNewButton.setBounds(80, 100, 232, 39); // Bouton télécharger photo
		frame.getContentPane().add(btnNewButton);
		
		
		
		// Boutons Vidéo
		Button btnNewButton_1 = new Button("Telecharger la video");
		btnNewButton_1.setActionCommand("Telecharger la video");
		btnNewButton_1.setFont(new Font("Snap ITC", Font.BOLD, 16));
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					  JFileChooser file = new JFileChooser();
					  running=0;
					  label.setIcon(null) ;
					  video.setIcon(null) ;
					  label_2.setIcon(null);
					  textField.setText("");
			          file.setCurrentDirectory(new File(System.getProperty("user.dir")));
			          //filter the files
			          FileNameExtensionFilter filter = new FileNameExtensionFilter("video","mp4","avi");
			          file.addChoosableFileFilter(filter);
			          int result = file.showSaveDialog(null);
			           //if the user click on save in Jfilechooser
			          if(result == JFileChooser.APPROVE_OPTION){
			              File selectedFile = file.getSelectedFile();
			              path = selectedFile.getAbsolutePath();
			              System.out.println(path);
			             choice=2;
			          }
				
			}
		});
		btnNewButton_1.setBounds(80, 150, 232, 39);  
		frame.getContentPane().add(btnNewButton_1);
		
		// afficher HSV
		
		Button btnTransformToHSV = new Button("Afficher HSV");
		btnTransformToHSV.setBackground(Color.CYAN);
		btnTransformToHSV.setFont(new Font("Elephant", Font.BOLD, 16));
		btnTransformToHSV.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (path != null && !path.isEmpty()) {
		            // Read the image
		            Mat m = Highgui.imread(path);

		            // Check if the image was successfully loaded
		            if (m.empty()) {
		                System.out.println("Error: Could not load image!");
		                return;
		            }

		            // Transform the image from BGR to HSV
		            Mat transformee = MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);

		            // Convert the transformed Mat to BufferedImage
		            BufferedImage transformedImage = Mat2bufferedImage(transformee);

		            // Display the transformed HSV image
		            label.setIcon(new ImageIcon(transformedImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
		        } else {
		            System.out.println("No image selected!");
		        }
		    }
		});
		btnTransformToHSV.setBounds(80, 300, 232, 39); // Adjust the position as needed
		frame.getContentPane().add(btnTransformToHSV);
		
		// Afficher Seuillage
		
		Button btnAfficherSeuillage = new Button("Afficher Seuillage");
		btnAfficherSeuillage.setBackground(Color.CYAN);
		btnAfficherSeuillage.setFont(new Font("Elephant", Font.BOLD, 16));
		btnAfficherSeuillage.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (path != null && !path.isEmpty()) {
		            // Read the image
		            Mat m = Highgui.imread(path);

		            // Check if the image was successfully loaded
		            if (m.empty()) {
		                System.out.println("Error: Could not load image!");
		                return;
		            }

		            // Transform the image from BGR to HSV
		            Mat transformee = MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);

		            // Apply thresholding (seuillage)
		            Mat saturee = MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);

		            // Convert the thresholded Mat to BufferedImage
		            BufferedImage thresholdedImage = Mat2bufferedImage(saturee);

		            label.setIcon(new ImageIcon(thresholdedImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
		        } else {
		            System.out.println("No image selected!");
		        }
		    }
		});
		btnAfficherSeuillage.setBounds(80, 350, 232, 39); // Adjust the position as needed
		frame.getContentPane().add(btnAfficherSeuillage);
		
		// Afficher Contours
		
				Button btnAfficherContours = new Button("Afficher Contours");
				btnAfficherContours.setBackground(Color.CYAN);
				btnAfficherContours.setFont(new Font("Elephant", Font.BOLD, 16));
				btnAfficherContours.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        if (path != null && !path.isEmpty()) {
				            // Read the image
				            Mat m = Highgui.imread(path);

				            // Check if the image was successfully loaded
				            if (m.empty()) {
				                System.out.println("Error: Could not load image!");
				                return;
				            }

				            // Transform the image from BGR to HSV
				            Mat transformee = MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);

				            // Apply thresholding (seuillage)
				            Mat saturee = MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);

				            /// Extract contours
				            List<MatOfPoint> contours = MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);

				            // Draw contours on the image
				            Mat drawing = Mat.zeros(saturee.size(), CvType.CV_8UC3);
				            Random rand = new Random();
				            for (int i = 0; i < contours.size(); i++) {
				                Scalar color = new Scalar(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
				                Imgproc.drawContours(drawing, contours, i, color, 2);
				            }

				            // Convert the Mat with contours to BufferedImage
				            BufferedImage contourImage = Mat2bufferedImage(drawing);

				            label.setIcon(new ImageIcon(contourImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
				        } else {
				            System.out.println("No image selected!");
				        }
				    }
				});
				btnAfficherContours.setBounds(80, 400, 232, 39); // Adjust the position as needed
				frame.getContentPane().add(btnAfficherContours);
				
				// Afficher Forme
				
				Button btnAfficherForme = new Button("Afficher Forme");
				btnAfficherForme.setBackground(Color.CYAN);
				btnAfficherForme.setFont(new Font("Elephant", Font.BOLD, 16));
				btnAfficherForme.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        if (path != null && !path.isEmpty()) {
				            // Read the image
				            Mat m = Highgui.imread(path);

				            // Check if the image was successfully loaded
				            if (m.empty()) {
				                System.out.println("Error: Could not load image!");
				                return;
				            }

				            // Transform the image from BGR to HSV
				            Mat transformee = MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);

				            // Apply thresholding (seuillage)
				            Mat saturee = MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);

				            /// Extract contours
				            List<MatOfPoint> contours = MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);

				         // Detect form and draw it
				            Mat detectedFormImage = m.clone();
				            for (MatOfPoint contour : contours) {
				                Mat form = MaBibliothequeTraitementImageEtendue.DetectForm(detectedFormImage, contour);
				                if (form != null) {
				                    Imgproc.drawContours(detectedFormImage, contours, -1, new Scalar(0, 255, 0), 3);
				                }
				            }

				            // Convert the Mat with detected forms to BufferedImage
				            BufferedImage formImage = Mat2bufferedImage(detectedFormImage);

				            label.setIcon(new ImageIcon(formImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
				        } else {
				            System.out.println("No image selected!");
				        }
				    }
				});
				btnAfficherForme.setBounds(80, 450, 232, 39); // Adjust the position as needed
				frame.getContentPane().add(btnAfficherForme);

		
		// Bouton détecter panneau
		
		Button btnNewButton_2 = new Button("Detecter Panneau");
		btnNewButton_2.setBackground(Color.ORANGE);
		btnNewButton_2.setFont(new Font("Elephant", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choice==1) {
				try {
					
					String panref;
		            panref=Imageref.main(path);
		            BufferedImage img = null;
		            img = ImageIO.read(new File(panref));
		            Image dimg = img.getScaledInstance(label_2.getWidth(), label_2.getHeight(),
		                    Image.SCALE_SMOOTH);
		            ImageIcon imageIcon = new ImageIcon(dimg);
		            label_2.setIcon(imageIcon);
		            
		            
		            
					
				}
				    catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				 
				else if (choice==2 ) {
					try {
						Multi t1=new Multi(); 
						 running=1;
						 t1.start();
						 String[] args = {"C:\\Users\\DELL\\Downloads\\212Twizy_2022-main (2)\\212Twizy_2022-main\\Road Sign Detection\\video1.avi"};

						 AnalyseVideo.main(args);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
				}
				else {
					test window = new test();
					window.frame.setVisible(true);
					System.out.println("Vous n'avez pas séléctionné un fichier!");
				}
				}
			
		});
		btnNewButton_2.setBounds(80, 200, 232, 39); // Bouton Détecter panneau
		frame.getContentPane().add(btnNewButton_2);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Détecter");
		rdbtnNewRadioButton.setForeground( Color.BLUE);
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton.setBackground(Color.BLUE);
		rdbtnNewRadioButton.setFont(new Font("Source Code Pro Semibold", Font.BOLD | Font.ITALIC, 30));
		rdbtnNewRadioButton.setAction(action_1);
		buttonGroup.add(rdbtnNewRadioButton);
		
		
	 
		
		video.setBounds(760,200,1000,500); // Vid o panel
		frame.getContentPane().add(video);
		
		Button btnNewButton_3 = new Button("Stop");
		btnNewButton_3.setBackground(Color.GREEN);
		btnNewButton_3.setFont(new Font("Elephant", Font.BOLD, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				running=0;
				 label.setIcon(null) ;
				  video.setIcon(null) ;
				  label_2.setIcon(null);
				  textField.setText("");
			}
		});
		btnNewButton_3.setBounds(80, 250, 232, 39); // Bouton STOP
		frame.getContentPane().add(btnNewButton_3);
	}
	

	 // Methode to resize imageIcon with the same size of a Jlabel
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Detecter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			 
		}
    }
   

    public static BufferedImage Mat2bufferedImage(Mat image) {
		MatOfByte bytemat = new MatOfByte();
		Highgui.imencode(".jpg", image, bytemat);
		byte[] bytes = bytemat.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage img = null;
		try {
			img = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
    
 

	
	public static BufferedImage Mat2BufferedImage(Mat m) {
		
		// Method converts a Mat to a Buffered Image
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;
	}
}
	
	
