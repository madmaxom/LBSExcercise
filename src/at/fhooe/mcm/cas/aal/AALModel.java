package at.fhooe.mcm.cas.aal;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.aal.parser.ParserMode;
import at.fhooe.mcm.cas.aal.parserfactory.DOMParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.GsonParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.IParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.KXmlParserFactory;
import at.fhooe.mcm.cas.contexttype.ContextElement;

public class AALModel {

	private List<AALModelObserver> mObservers;
	private IContextParser mParser;
	private ParserMode mMode;
	private String mFileSytemPath;
	
	
	public AALModel() {
		mObservers = new ArrayList<>();
		
		// default values
		mMode = ParserMode.DOM;
		createParser(mMode);
		mFileSytemPath = "files/XML";
		
		
	}
	
	public void setFileSystemPath(String path) {
		mFileSytemPath = path;
	}
	
	public void addObserver(AALModelObserver observer) {
		mObservers.add(observer);
	}
	
	public void removeObserver(AALModelObserver observer) {
		mObservers.remove(observer);
	}
	
	public void getContextElementsFromFilesystem() {
		
		if (mParser == null || mFileSytemPath == null) {
			System.out.println("No parser or path attached");
			return;
		}
		
		readAllFiles(mParser, mFileSytemPath);
	}
	
	private void createParser(ParserMode mode) {
	
		// create a concrete factory for producing a parser
		IParserFactory factory = null;
		switch (mode) {
			case STREAM : factory = new KXmlParserFactory(); break;
			case DOM : factory = new DOMParserFactory(); break;
			case JSON : factory = new GsonParserFactory(); break;
		default:
			factory = new KXmlParserFactory();
		} // switch mode
		
		
		mParser = factory.createParser();
		
	
	}

	private void readAllFiles(IContextParser parser, String path) {
	
		List<ContextElement> elements = new ArrayList<>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
	
		for (int i = 0; i < listOfFiles.length; i++) {
			File f = listOfFiles[i];
			if (f.isFile()) {
	
				try {
					System.out.println("Parsing file " + f.getName());
					Path pathAbs = FileSystems.getDefault().getPath(f.getAbsolutePath());
					String context = new String(Files.readAllBytes(pathAbs.toAbsolutePath()));
					elements.addAll(parser.parse(context));
	
				} catch (IOException e) {
					e.printStackTrace();
				}
	
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + f.getName());
			}
		}
	
		// notify
		for (ContextElement e : elements) {
			notifyObservers(e);
		}
	}
	
	private void notifyObservers(ContextElement contextElement) {
		for (AALModelObserver obs : mObservers) {
			obs.update(contextElement);
		}
	}

	public void setParserMode(ParserMode mode) {
		mMode = mode;
		createParser(mode);
	}
	
}
