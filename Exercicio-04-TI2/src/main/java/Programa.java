import IA.FaceAnalizer;
import java.util.Scanner;

public class Programa {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		FaceAnalizer faceAnalizer = new FaceAnalizer();
		System.out.println("######### FACE DETECTOR ########\n");
		System.out.print("Type the img url: ");
		String url = sc.nextLine();
		System.out.println();
		faceAnalizer.detectFaces(url);
		faceAnalizer.printFacesInfo();

	}

}
