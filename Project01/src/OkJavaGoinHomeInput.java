import javax.swing.JOptionPane;

import org.opentutorials.iot.DimmingLights;
import org.opentutorials.iot.Elevator;
import org.opentutorials.iot.Lighting;
import org.opentutorials.iot.Security;

public class OkJavaGoinHomeInput {

	public static void main(String[] args) {
	
		String id = JOptionPane.showInputDialog("Enter a ID");
		String bright = JOptionPane.showInputDialog("Enter a Bright level");
		
		double doublevalue = Double.parseDouble(bright);
		
		
		 // Elevator call
		Elevator myElevator = new Elevator(id);
		myElevator.callForUp(1);
		
		 // Security off
		Security mySecurity = new Security(id);
		mySecurity.off();
		
		// House Light on
		Lighting hallLamp = new Lighting(id + "/ Hall Lamp");
		hallLamp.on();
		
		DimmingLights moodLamp = new DimmingLights(id+ "mood Lamp");
		moodLamp.setBright(doublevalue);
		moodLamp.on();

	}

}
