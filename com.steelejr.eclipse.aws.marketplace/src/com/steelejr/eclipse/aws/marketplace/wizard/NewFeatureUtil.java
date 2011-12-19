package com.steelejr.eclipse.aws.marketplace.wizard;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.equinox.internal.p2.updatesite.metadata.UpdateSiteMetadataRepositoryFactory;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.steelejr.eclipse.aws.marketplace.Activator;

public class NewFeatureUtil {
	
	/**
	 * The update site file name.
	 */
	public static final String UPDATE_SITE_FILE = "updateSites.xml";
	
	/**
	 * The update-site file top-level entry.
	 */
	public static final String P_TOP_ENTRY = "sites";
	
	/**
	 * The update site memento name.
	 */
	public static final String P_SITE_ENTRY = "site";
	
	/**
	 * The update site URL memento property. 
	 */
	public static final String P_SITE_URL = "url";
	
	
	public static List<IInstallableUnit> getNewFeatures () {
		
		// The new features available to install.
		List<IInstallableUnit> newFeatures = new ArrayList<IInstallableUnit>();
		
		// The features currently installed.
		List<IInstallableUnit> installedFeatures = getInstalledIU();
		
		// Get the update site URLs.
		List<String> updateSites = getUpdateSites();

		// Go through each URL.
		for (int i = 0; i < updateSites.size(); i++) {
			List<IInstallableUnit> featuresFromSite = getIUforSite(updateSites.get(i));
			
			for (IInstallableUnit unit : featuresFromSite) {
				//if (!installedFeatures.contains(unit)) {
					newFeatures.add(unit);
				//}
			}
		}
		
		return newFeatures;
	}
	
	
	/**
	 * Returns the list of InstallableUnit's for the provided site.
	 * 
	 * @see http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Fguide%2Fp2_api_overview.htm
	 *      for details on how I figured this out.
	 *      
	 * @param site
	 * @return
	 */
	public static List<IInstallableUnit> getIUforSite (String site) {
		List<IInstallableUnit> features = new ArrayList<IInstallableUnit> ();
		
		IProvisioningAgent agent = getAgent();
		if (agent == null) {
			System.out.println("P2 Agent is NULL!!");
			return null;
		}
		
		IMetadataRepositoryManager metaManager = (IMetadataRepositoryManager)agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
		try {
			IMetadataRepository metaRepository = metaManager.loadRepository(new URI(site), new NullProgressMonitor());
			
			// Get the Profile Registry.
			IProfileRegistry profileRegistry = (IProfileRegistry) agent.getService(IProfileRegistry.class.getName());

			// Get the Running System Profile.
			IProfile runningSystemProfile = profileRegistry.getProfile(IProfileRegistry.SELF);
			
			// Query all Installable Units available at the site.
			IQuery<IInstallableUnit> query = QueryUtil.createIUAnyQuery();
			
			IQueryResult<IInstallableUnit> collector = metaRepository.query(query, new NullProgressMonitor());
			
			for (IInstallableUnit unit : collector.toUnmodifiableSet()) {
				features.add(unit);
				//System.out.println(unit.getProperty(IInstallableUnit.PROP_NAME, null));
			}
			
			// IQueryResult<IInstallableUnit> collector = runningSystemProfile.query(query, new NullProgressMonitor());
			
			
		} catch (ProvisionException e) {
			e.printStackTrace();
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return features;
	}
	
	
	
	/**
	 * Returns the list of update sites.
	 * 
	 * @return The list of update sites.
	 */
	public static List<String> getUpdateSites () {
		
		List<String> sites = new ArrayList<String>();

		URL url = Activator.getDefault().getBundle().getEntry("updateSites.xml");
//		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
//		IPath path = new Path("updateSites.xml");
		
		//URL fileURL = FileLocator.find(bundle, path, null);
		
//		Document document;
		IMemento memento;
		URL absoluteURL;
		try {
			// get the /home/user/.../updateSites.xml URL
			absoluteURL = FileLocator.resolve(url);
			// get the file.
			File file = new File(absoluteURL.getFile());
			//InputStream input = platformURL.openStream();
			
			
			//InputSource source = new InputSource(input);
			//DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//DocumentBuilder parser = factory.newDocumentBuilder();
			//document = parser.parse(source);
			//Node node = document.getFirstChild();
			FileReader reader = new FileReader(file);
			memento = XMLMemento.createReadRoot(reader);
			//memento = new XMLMemento(document, (Element) node);
			IMemento[] children = memento.getChildren(P_SITE_ENTRY);
			for (int i = 0; i < children.length; i++) {
				String url1 = children[i].getString(P_SITE_URL);
				sites.add(url1);
			}
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//InputSource source = new InputSource (rea)
		
	//	File file = Activator.getDefault().getStateLocation().append(UPDATE_SITE_FILE).toFile();
//		XMLMemento memento;
//		
//		try {
//			if (!file.exists())	file.createNewFile();
//			FileReader reader = new FileReader (file);
//			
//			memento = XMLMemento.createReadRoot(reader);
//			IMemento[] children = memento.getChildren("sites");
//			for (int i = 0; i < children.length; i++) {
//				String url = children[0].getString("name");
//				sites.add(url);
//			}
//			
//		} catch (Exception e) {
//			System.out.println("Error Getting Update Sites: ");
//			e.printStackTrace();
//		}
		
		return sites;		
	}
	
	
	/**
	 * Returns an array of the installed InstallableUnit's.
	 * 
	 * @return Installed InstallableUnit's.
	 */
	public static List<IInstallableUnit> getInstalledIU() {

		List<IInstallableUnit> installedList = new ArrayList<IInstallableUnit>();

		// Get the Agent Service Reference
		ServiceReference<?> agentSr = Activator.getDefault().getBundle()
				.getBundleContext()
				.getServiceReference(IProvisioningAgent.SERVICE_NAME);
		if (agentSr == null) {
			return null;
		}

		// Get the Agent Service.
		IProvisioningAgent agent = (IProvisioningAgent) Activator.getDefault()
				.getBundle().getBundleContext().getService(agentSr);

		// Get the Profile Registry.
		IProfileRegistry profileRegistry = (IProfileRegistry) agent
				.getService(IProfileRegistry.class.getName());

		// Get the Running System Profile.
		IProfile runningSystemProfile = profileRegistry
				.getProfile(IProfileRegistry.SELF);

		// Query all Installable Units.
		IQuery<IInstallableUnit> query = QueryUtil.createIUAnyQuery();
		IQueryResult<IInstallableUnit> collector = runningSystemProfile.query(
				query, new NullProgressMonitor());

		// Go through installed features, add them to installed list.
		Iterator<IInstallableUnit> iter = collector.iterator();
		while (iter.hasNext()) {
			installedList.add(iter.next());
		}

		// Un-get the Agent Service.
		Activator.getDefault().getBundle().getBundleContext()
				.ungetService(agentSr);
		
		

		return installedList;
	}
	
	
	/**
	 * Returns the ProvisioningAgent for the currently running system.
	 * 
	 * @return ProvisioningAgent for currently running system.
	 */
	public static IProvisioningAgent getAgent () {
		
		ServiceReference<?> agentServiceReference = Activator.getDefault().getBundle().getBundleContext().getServiceReference(IProvisioningAgent.SERVICE_NAME);
		
		if (agentServiceReference == null) {
			System.out.println("P2 Agent Service is NULL!");
			return null;
		}
		
		IProvisioningAgent agent = (IProvisioningAgent) Activator.getDefault().getBundle().getBundleContext().getService(agentServiceReference);
		
		if (agent == null) {
			System.out.println("P2 Agent it NULL!");
			return null;
		}
		
		try {
			return agent;
		} finally {
			Activator.getDefault().getBundle().getBundleContext().ungetService(agentServiceReference);
		}
	}
}
