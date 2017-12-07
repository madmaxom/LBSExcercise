package at.fhooe.mcm.cas.rule;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class RuleController extends WindowAdapter implements ActionListener {

	private RuleModel mModel;
	
	public RuleController(RuleModel model) {
		mModel = model;
	}

	@Override
	public void actionPerformed(ActionEvent _e) {
		Object src = _e.getSource();

		if (src instanceof Button) {
			Button b = (Button) _e.getSource();
			switch (b.getName()) {
			case "btnLoadFileSystem": {
				System.out.println("Loading rules from Filesystem");
				mModel.getRulesFromFilesystem();
				break;
			}
			}
		}
	}
}
