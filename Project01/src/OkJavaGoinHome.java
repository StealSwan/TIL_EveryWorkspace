import javax.swing.JOptionPane;

import org.opentutorials.iot.Elevator;
import org.opentutorials.iot.Lighting;
import org.opentutorials.iot.Security;

public class OkJavaGoinHome {

	public static void main(String[] args) {
	

		
		String id = "JAVA APT 507";
		
		 // Elevator call
		Elevator myElevator = new Elevator(id);
		myElevator.callForUp(1);
		
		 // Security off
		Security mySecurity = new Security(id);
		mySecurity.off();
		
		// House Light on
		Lighting hallLamp = new Lighting(id + "/ Hall Lamp");
		hallLamp.on();

	}

}
