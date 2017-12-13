package at.fhooe.mcm.cas.compfactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.IComponent;
import at.fhooe.mcm.cas.IMediator;
import at.fhooe.mcm.cas.IUIView;
import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.contexttype.ContextElement;

public class ComponentFactory {
	public ComponentFactory() {
		
	}
	
	public IComponent[] buildComponents(String input) {
		List<IComponent> components = new ArrayList();
		
		IComponent c;
		List<ComponentInfo> componentInfos = new ComponentParser().parse(input);
		for(ComponentInfo component : componentInfos) {
			c = null;
			try {
				Class <?> clazz = Class.forName(component.getName());
				if(!isEmptyString(component.getUiView())) {
					Class<?> uiViewClass = Class.forName(component.getUiView());
					IUIView uiview = (IUIView) uiViewClass.getConstructor().newInstance();
					c = (IComponent)clazz.getConstructor(IMediator.class, String.class, IUIView.class).newInstance(null,"", uiview);
				}else {
					c = (IComponent)clazz
							.getConstructor(IMediator.class, String.class).newInstance(null, "");	
				}
				if(!isEmptyString(component.getDrawingContext())) {
					Method m = clazz.getMethod("setDrawingContext", String.class);
					m.invoke(c, component.getDrawingContext());
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			if(c != null) {
				components.add(c);
			}
		}
		
		return components.toArray(new IComponent[] { });
	}

	public String getComponentCompositionFromFile(String filename) {
		Path pathAbs = FileSystems.getDefault().getPath(new File(filename).getAbsolutePath());
		String componentComposition = null;
		try {
			componentComposition = new String(Files.readAllBytes(pathAbs.toAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return componentComposition;
	}
	
	private static boolean isEmptyString(String s) {
		return s == null || "".equals(s);
	}
}
