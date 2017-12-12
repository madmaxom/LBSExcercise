package at.fhooe.mcm.cas.gps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

import at.fhooe.mcm.cas.IUIView;


/**
 * Basic view. Is the main window and includes all subviews.
 * @author Oliver
 *
 */
public class GPSReceiverView implements PositionUpdateListener {
	
	private Panel mOverallPanel;
	
	/**
	 * SubView for satellites position.
	 */
	private SatView mSatView;
	/**
	 * SubView for satellites info.
	 */
	private DataView mDataView;
	
	/**
	 * Controller who handles user interaction.
	 */
	private GPSReceiverController mController;
	
	public GPSReceiverView(GPSReceiverController _c) {
		mSatView = new SatView();
		mDataView = new DataView();
		
		mController = _c;
		createPanel();
	}
	
	/**
	 * Creates frame with panel and button, and sets some listeners.
	 */
	public void createPanel() {

		mOverallPanel = new Panel(new BorderLayout());
		
		
		// add view to frame
		mSatView.setBackground(Color.DARK_GRAY);
		mOverallPanel.add(mSatView, BorderLayout.CENTER);
		
		mDataView.setBackground(Color.orange);
		mOverallPanel.add(mDataView, BorderLayout.EAST);
	}


	@Override
	public void updatePosition(NMEAInfo _info) {
		mSatView.update(_info);
		mDataView.update(_info);
	}

	public Panel getPanel() {
		return mOverallPanel;
	}
	
	public void setDrawingContext(String str) {
		mSatView.setDrawingContext(str);
	}
}
