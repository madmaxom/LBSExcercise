package at.fhooe.mcm.cas.rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.aal.AALModelObserver;
import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.aal.parser.ParserMode;
import at.fhooe.mcm.cas.aal.parserfactory.DOMParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.GsonParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.IParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.KXmlParserFactory;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;
import at.fhooe.mcm.cas.rule.parser.IRuleParser;

public class RuleModel {
	private static final String FILE_STRUCTURE_XML = "XML";
	private static final String FILE_STRUCTURE_JSON = "JSON";
	
	private List<RuleModelObserver> mObservers;
	private IRuleParser mParser;
	private ParserMode mMode;
	private String mFileSytemPath;
	private String mFileStructure;

	public RuleModel() {
		mObservers = new ArrayList<>();

		// default values
		mMode = ParserMode.DOM;
		createParser(mMode);
		mFileStructure = FILE_STRUCTURE_XML;
		mFileSytemPath = "files/rules/rules.xml";

	}

	public void setFileSystemPath(String path) {
		mFileSytemPath = path;
	}

	public void addObserver(RuleModelObserver observer) {
		mObservers.add(observer);
	}

	public void removeObserver(AALModelObserver observer) {
		mObservers.remove(observer);
	}

	public void getRulesFromFilesystem() {
		readAllFiles(mParser, mFileSytemPath);
	}


	private void createParser(ParserMode mode) {

		// create a concrete factory for producing a parser
		IParserFactory factory = null;
		switch (mode) {
		case STREAM:
			factory = new KXmlParserFactory();
			break;
		case DOM:
			factory = new DOMParserFactory();
			break;
		case JSON:
			factory = new GsonParserFactory();
			break;
		default:
			factory = new KXmlParserFactory();
		} // switch mode

		mParser = factory.createRuleParser();

	}

	private void readAllFiles(IRuleParser parser, String path) {

	
		RuleEvaluator ruleEvaluator = null;
		File f = new File(path); // mFileStructure not needed

		if (f.isFile()) {

			try {
				System.out.println("Parsing rule file " + f.getAbsolutePath());
				Path pathAbs = FileSystems.getDefault().getPath(f.getAbsolutePath());
				String context = new String(Files.readAllBytes(pathAbs.toAbsolutePath()));
				ruleEvaluator = parser.parse(context);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// notify
		notifyObservers(ruleEvaluator);
	
	}

	private void notifyObservers(RuleEvaluator ruleEvaluator) {
		for (RuleModelObserver obs : mObservers) {
			obs.update(ruleEvaluator);
		}
	}

	public void setParserMode(ParserMode mode) {
		mMode = mode;
		createParser(mode);
	}

	public void setStructureJSON() {
		mFileStructure = FILE_STRUCTURE_JSON;
		createParser(ParserMode.JSON);
		
		
	}

	public void setStructureXML() {
		mFileStructure = FILE_STRUCTURE_XML;
		createParser(ParserMode.DOM);
	}
}
