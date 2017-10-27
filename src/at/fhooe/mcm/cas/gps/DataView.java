package at.fhooe.mcm.cas.gps;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

/**
 * View for basic data about Satellites.
 * @author Oliver
 *
 */
public class DataView extends Panel {
	

	private static final long serialVersionUID = 1L;
	Label mLabelTimeV;
	Label mLabelLongV;
	Label mLabelLatV;
	Label mLabelAltV;
	Label mLabelFixModeV;
	Label mLabel3DAccV;
	Label mLabel2DAccV;
	Label mLabelPDOPV;
	Label mLabelHDOPV;
	Label mLabelSatellitesV;
	
	/**
	 * Constructor. Initializes all labels.
	 */
	public DataView() {
		
		Panel p = new Panel();
		
		// headers
		Label labelLong = new Label("Longitude");
		Label labelLat = new Label("Lattitude");
		Label labelAlt = new Label("Altitude");
		Label labelFixMode = new Label("FixMode");
		Label label3DAcc = new Label("3D Acc.");
		Label label2DAcc = new Label("2D Acc.");
		Label labelPDOP = new Label("PDOP");
		Label labelHDOP = new Label("HDOP");
		Label labelSatellites = new Label("Satellites");
		
		// values
		mLabelLongV = new Label();
		mLabelLatV = new Label();
		mLabelAltV = new Label();
		mLabelFixModeV = new Label();
		mLabel3DAccV = new Label();
		mLabel2DAccV = new Label();
		mLabelPDOPV = new Label();
		mLabelHDOPV = new Label();
		mLabelSatellitesV = new Label();
		
		GridLayout layout = new GridLayout(9, 2);
		p.setLayout(layout);

		
		p.add(labelLong);
		p.add(mLabelLongV);
		p.add(labelLat);
		p.add(mLabelLatV);
		p.add(labelAlt);
		p.add(mLabelAltV);
		p.add(labelFixMode);
		p.add(mLabelFixModeV);
		p.add(label3DAcc);
		p.add(mLabel3DAccV);
		p.add(label2DAcc);
		p.add(mLabel2DAccV);
		p.add(labelPDOP);
		p.add(mLabelPDOPV);
		p.add(labelHDOP);
		p.add(mLabelHDOPV);
		p.add(labelSatellites);
		p.add(mLabelSatellitesV);
		
		// time
		mLabelTimeV = new Label();
		mLabelTimeV.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20)); 
		this.setLayout(new BorderLayout());
		this.add(mLabelTimeV, BorderLayout.CENTER);
		this.add(p, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates view.
	 * @param _info new info to update.
	 */
	public void update(NMEAInfo _info) {
		
		System.out.println("DATA-VIEW");
		if (_info.getTime() != null) {
			mLabelTimeV.setText(_info.getTime().toString());
		}
		
		mLabelLongV.setText(String.valueOf(_info.getLat()));
		mLabelLatV.setText(String.valueOf(_info.getLng()));
		mLabelAltV.setText(String.valueOf(_info.getHeight()));
		mLabelFixModeV.setText(String.valueOf(_info.getFixQual()));
		// mLabel3DAccV.setText(String.valueOf();
		// mLabel2DAccV.setText(String.valueOf();
		mLabelPDOPV.setText(String.valueOf(_info.getPDOP()));
		mLabelHDOPV.setText(String.valueOf(_info.getHDOP()));
		mLabelSatellitesV.setText(String.valueOf(_info.getAmountSat()));
	}
}
