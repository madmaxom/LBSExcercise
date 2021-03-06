package at.fhooe.mcm.cas.aal;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JRadioButton;

public class AALController extends WindowAdapter implements ActionListener {

	private AALModel mModel;
	
	public AALController(AALModel model) {
		mModel = model;
	}

	@Override
	public void actionPerformed(ActionEvent _e) {
		Object src = _e.getSource();

		if (src instanceof Button) {
			Button b = (Button) _e.getSource();
			switch (b.getName()) {
			case "btnLoadFileSystem": {
				System.out.println("Loading from Filesystem");
				mModel.getContextElementsFromFilesystem();
				break;
			}
			case "btnLoadServer": {
			System.out.println("Loading from Server");
			mModel.getContextElementsFromServer();
			break;
		}
			}
		}
			
			if (src instanceof JRadioButton) {
				JRadioButton b = (JRadioButton) _e.getSource();
				switch (b.getName()) {
				case "rbXml": {
					System.out.println("XML");
					mModel.setStructureXML();
					break;
				}
				case "rbJson": {
				System.out.println("JSON");
				mModel.setStructureJSON();
				break;
			}
		}
			
		}
		
	}
}
