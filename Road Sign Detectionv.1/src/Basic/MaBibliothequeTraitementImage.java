package Basic;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;

import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class MaBibliothequeTraitementImage {
	//Contient toutes les m�thodes necessaires � la transformation des images


	//Methode qui permet de transformer une matrice intialement au  format BGR au format HSV
	public static Mat transformeBGRversHSV(Mat matriceBGR){
		Mat matriceHSV=new Mat(matriceBGR.height(),matriceBGR.cols(),matriceBGR.type());
		Imgproc.cvtColor(matriceBGR,matriceHSV,Imgproc.COLOR_BGR2HSV);
		return matriceHSV;

	}

	//Methode qui convertit une matrice avec 3 cannaux en un vecteur de 3 matrices monocanal (un canal par couleur)
	public static Vector<Mat> splitHSVChannels(Mat input) {
		Vector<Mat> channels = new Vector<Mat>(); 
		Core.split(input, channels);
		return channels;
	}

	//Methode qui permet d'afficher une image sur un panel
	public static void afficheImage(String title, Mat img){
		MatOfByte matOfByte=new MatOfByte();
		Highgui.imencode(".png",img,matOfByte);
		byte[] byteArray=matOfByte.toArray();
		BufferedImage bufImage=null;
		try{
			InputStream in=new ByteArrayInputStream(byteArray);
			bufImage=ImageIO.read(in);
			JFrame frame=new JFrame();
			frame.setTitle(title);
			frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
			frame.pack();
			frame.setVisible(true);

		}
		catch(Exception e){
			e.printStackTrace();
		}


	}

	//Methode qui permet de saturer les couleurs rouges � partir de 3 seuils
/*	public static Mat seuillage(Mat input, int seuilRougeOrange, int seuilRougeViolet,int seuilSaturation){
	
		// Decomposition en 3 cannaux HSV
		Vector<Mat> channels = splitHSVChannels(input);
		//cr�ation d'un seuil 
		Scalar rougeviolet = new Scalar(seuilRougeViolet);
		Scalar rougeOrange = new Scalar(seuilRougeOrange);
		Scalar saturation= new Scalar(seuilSaturation);
		
		
		//Cr�ation d'une matrice
		Mat Rouges=new Mat();
		//Mat RougeViolet=new Mat();
		//Mat Saturation=new Mat();
	
	//Comparaison et saturation des pixels dont la composante rouge est plus grande que le seuil rougeViolet
		Core.compare(channels.get(0), rougeviolet, Rouges, Core.CMP_GT);
		Core.compare(channels.get(2), rougeOrange, Rouges, Core.CMP_GT);
		Core.compare(channels.get(1), saturation, Rouges, Core.CMP_GT);
		
		
		
		return Rouges;

	} 
*/
	
	//Methode d'exemple qui permet de saturer les couleurs rouges � partir d'un seul seuil 
	public static Mat seuillage_exemple(Mat input, int seuilRougeViolet){
		// Decomposition en 3 cannaux HSV
		Vector<Mat> channels = splitHSVChannels(input);
		//cr�ation d'un seuil 
		Scalar rougeviolet = new Scalar(seuilRougeViolet);
		//Cr�ation d'une matrice
		Mat rouges=new Mat();
		//Comparaison et saturation des pixels dont la composante rouge est plus grande que le seuil rougeViolet
		Core.compare(channels.get(0), rougeviolet, rouges, Core.CMP_GT);
		//image satur�e � retourner
		return rouges;

	}
	
	
	public static Mat seuillage(Mat input, int seuilRougeOrange, int seuilRougeViolet,int seuilSaturation){
		Vector<Mat> channels = splitHSVChannels(input);
		//cr�ation d'un seuil 
		Scalar rougeviolet = new Scalar(seuilRougeViolet);
		Scalar rougeorange = new Scalar(seuilRougeOrange);
		Scalar seuilsaturation = new Scalar(seuilSaturation);
		//Cr�ation d'une matrice
		Mat rouges=new Mat();
		Mat rougeOrange=new Mat();
		Mat rougeViolet=new Mat();
		Mat Saturation=new Mat();
		//Comparaison et saturation des pixels dont la composante rouge est plus grande que le seuil rougeViolet
		Core.compare(channels.get(0), rougeviolet, rougeViolet, Core.CMP_GT); 
		Core.compare(channels.get(0), rougeorange, rougeOrange, Core.CMP_GT);
		//donner 1 au pixel inf�rieur au seuil rougeorange et 0 � l'inverse
		Core.bitwise_not(rougeOrange, rougeOrange);
		Core.compare(channels.get(1), seuilsaturation, Saturation, Core.CMP_GT);
		//on recup�re tous les rouges  
		Core.bitwise_or(rougeOrange, rougeViolet, rouges);
		//on concat�ne le crit�re de saturation et de la couleur rouge 
		Core.bitwise_and(rouges, Saturation, rouges);
		return rouges;
	}
	
	
	
	
	

	public static Mat DetectForm(Mat m, MatOfPoint contour) {
	
		return null;
	}




}