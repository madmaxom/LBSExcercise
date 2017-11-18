package at.fhooe.mcm.cas.aal;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

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
			}
		}
		
	}
}
