package IA;

import java.util.List;

import com.azure.core.credential.KeyCredential;
import com.azure.ai.vision.face.FaceClientBuilder;
import com.azure.ai.vision.face.models.FaceRecognitionModel;
import com.azure.ai.vision.face.models.FaceRectangle;
import com.azure.ai.vision.face.models.FaceDetectionResult;
import com.azure.ai.vision.face.FaceClient;
import com.azure.ai.vision.face.models.FaceDetectionModel;
import com.azure.core.exception.HttpResponseException;



public class FaceAnalizer {
	
	private static final String SUBSCRIPTION_KEY = "";
    private static final String ENDPOINT = "";
	
    private FaceClient faceClient;
    private List<FaceDetectionResult> facesOfLastImg;
    
    public FaceAnalizer() {
    	this.faceClient = new FaceClientBuilder().endpoint(ENDPOINT).credential(new KeyCredential(SUBSCRIPTION_KEY)).buildClient();
    	this.facesOfLastImg = null;
    }

    public List<FaceDetectionResult> detectFaces(String urlIMG) {
    	try {
    		this.facesOfLastImg = this.faceClient.detect(urlIMG, FaceDetectionModel.DETECTION_03, FaceRecognitionModel.RECOGNITION_04, false);
    		return this.facesOfLastImg;
    	}catch(HttpResponseException e) {
    		System.out.println("Error: " + e.getMessage());
    		return null;
    	}
        
    }
    
    public void printFacesInfo(List<FaceDetectionResult> faces) {
    	if(faces == null) {
    		System.out.println("faces can't be null");
    		return;
    	}
        
        System.out.println(faces.size() + " face(s) detected in the image");
        System.out.println("\nFace(s): ");

        int fcount = 0;
        for (FaceDetectionResult face : faces) {
            fcount++;
            FaceRectangle rect = face.getFaceRectangle();
            System.out.println("\nFace #" + fcount + ": ");
            System.out.printf("Position: Top=%d, Left=%d, Width=%d, Heigth=%d%n",
                rect.getTop(), rect.getLeft(), rect.getWidth(), rect.getHeight());
        }
    }
    
    public void printFacesInfo() {
    	this.printFacesInfo(this.facesOfLastImg);
    }
}
