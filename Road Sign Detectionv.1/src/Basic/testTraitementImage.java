package Basic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.highgui.Highgui;



class testTraitementImage {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDetectionP1() {
	Mat m=Highgui.imread("p1.jpg");
	Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
	Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
	List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);
	assertNotNull(ListeContours);

}

	@Test
	void testDetectionRef30() {
	Mat m=Highgui.imread("ref30.jpg");
	Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
	Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
	List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);
	assertNotNull(ListeContours);

}
	@Test
	void testDetectiontrain() {
	Mat m=Highgui.imread("train.jpg");
	Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
	Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
	List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);
	assertNotNull(ListeContours);

}
	@Test
	void Reconnaissanceref30() {
		Mat m=Highgui.imread("ref30.jpg");
		Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
		Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
		Mat objetrond = null;
		List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);	
		int i=0;
		double [] scores=new double [7];
		for (MatOfPoint contour: ListeContours  ){
			i++;
			objetrond=MaBibliothequeTraitementImageEtendue.DetectFormim(m,contour);
		    
			if (objetrond!=null){
				
				scores[0]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref30.jpg");
				scores[1]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref50.jpg");
				scores[2]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref70.jpg");
				scores[3]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref90.jpg");
				scores[4]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref110.jpg");
				scores[5]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"refdouble.jpg");
				double scoremax=-1;
				int indexmax=0;
				for(int j=0;j<scores.length;j++){
					if (scores[j]>scoremax){scoremax=scores[j];indexmax=j;}}
		assertEquals(indexmax,0);
	}
	}
	}
	
	@Test
	void Reconnaissanceref50() {
		Mat m=Highgui.imread("ref50.jpg");
		Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
		Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
		Mat objetrond = null;
		List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);	
		int i=0;
		double [] scores=new double [7];
		for (MatOfPoint contour: ListeContours  ){
			i++;
			objetrond=MaBibliothequeTraitementImageEtendue.DetectFormim(m,contour);
		    
			if (objetrond!=null){
				
				scores[0]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref30.jpg");
				scores[1]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref50.jpg");
				scores[2]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref70.jpg");
				scores[3]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref90.jpg");
				scores[4]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref110.jpg");
				scores[5]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"refdouble.jpg");
				double scoremax=-1;
				int indexmax=0;
				for(int j=0;j<scores.length;j++){
					if (scores[j]>scoremax){scoremax=scores[j];indexmax=j;}}
		assertEquals(indexmax,1);
	}
	}
	}	
	
	@Test
	void Reconnaissanceref70() {
		Mat m=Highgui.imread("ref70.jpg");
		Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
		Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
		Mat objetrond = null;
		List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);	
		int i=0;
		double [] scores=new double [7];
		for (MatOfPoint contour: ListeContours  ){
			i++;
			objetrond=MaBibliothequeTraitementImageEtendue.DetectFormim(m,contour);
		    
			if (objetrond!=null){
				
				scores[0]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref30.jpg");
				scores[1]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref50.jpg");
				scores[2]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref70.jpg");
				scores[3]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref90.jpg");
				scores[4]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref110.jpg");
				scores[5]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"refdouble.jpg");
				double scoremax=-1;
				int indexmax=0;
				for(int j=0;j<scores.length;j++){
					if (scores[j]>scoremax){scoremax=scores[j];indexmax=j;}}
		assertEquals(indexmax,2);
	}
	}
	}
	@Test
	void Reconnaissanceref90() {
		Mat m=Highgui.imread("ref90.jpg");
		Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
		Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
		Mat objetrond = null;
		List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);	
		int i=0;
		double [] scores=new double [7];
		for (MatOfPoint contour: ListeContours  ){
			i++;
			objetrond=MaBibliothequeTraitementImageEtendue.DetectFormim(m,contour);
		    
			if (objetrond!=null){
				scores[0]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref30.jpg");
				scores[1]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref50.jpg");
				scores[2]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref70.jpg");
				scores[3]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref90.jpg");
				scores[4]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref110.jpg");
				scores[5]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"refdouble.jpg");
				double scoremax=-1;
				int indexmax=0;
				for(int j=0;j<scores.length;j++){
					if (scores[j]>scoremax){scoremax=scores[j];indexmax=j;}}
		assertEquals(indexmax,3);
	}
	}
	}
	
	@Test
	void Reconnaissanceref110() {
		Mat m=Highgui.imread("ref110.jpg");
		Mat transformee=MaBibliothequeTraitementImageEtendue.transformeBGRversHSV(m);
		Mat saturee=MaBibliothequeTraitementImage.seuillage(transformee, 6, 170, 110);
		Mat objetrond = null;
		List<MatOfPoint> ListeContours= MaBibliothequeTraitementImageEtendue.ExtractContours(saturee);	
		int i=0;
		double [] scores=new double [7];
		for (MatOfPoint contour: ListeContours  ){
			i++;
			objetrond=MaBibliothequeTraitementImageEtendue.DetectFormim(m,contour);
		    
			if (objetrond!=null){
				
				scores[0]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref30.jpg");
				scores[1]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref50.jpg");
				scores[2]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref70.jpg");
				scores[3]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref90.jpg");
				scores[4]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"ref110.jpg");
				scores[5]=MaBibliothequeTraitementImageEtendue.Similitude(objetrond,"refdouble.jpg");
				double scoremax=-1;
				int indexmax=0;
				for(int j=0;j<scores.length;j++){
					if (scores[j]>scoremax){scoremax=scores[j];indexmax=j;}}
		assertEquals(indexmax,4);
	}
	}
	}	
	
}	
