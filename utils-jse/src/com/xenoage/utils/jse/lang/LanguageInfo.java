package com.xenoage.utils.jse.lang;

import static com.xenoage.utils.jse.io.DesktopIO.desktopIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xenoage.utils.jse.io.DesktopIO;
import com.xenoage.utils.jse.xml.XMLReader;

/**
 * Contains the most important information about a language pack.
 * 
 * For example, this may be needed in a desktop application for displaying
 * the available languages in a menu.
 * 
 * This class uses the {@link DesktopIO}, which must be initialized before.
 *
 * @author Andreas Wenger
 */
public class LanguageInfo {

	private String path;
	private String id;
	private String localName;
	private String internationalName;
	private ImageIcon flag16;


	/**
	 * Loads information from the given language pack information file.
	 * @param path  path to the language pack directory (without trailing slash)
	 * @param id    id of the language pack
	 */
	public LanguageInfo(String path, String id)
		throws Exception {
		this.path = path;
		this.id = id;
		File file = desktopIO().findFile(path + "/" + id + "/id.xml");
		if (file == null)
			throw new FileNotFoundException("id.xml not found for language " + id);
		Document doc = XMLReader.readFile(new FileInputStream(file));
		Element root = XMLReader.root(doc);
		Element intName = XMLReader.element(root, "intname");
		Element localName = XMLReader.element(root, "localname");
		if (intName != null)
			this.internationalName = XMLReader.text(intName);
		else
			throw new Exception("intname must be set!");
		if (localName != null)
			this.localName = XMLReader.text(localName);
		else
			this.localName = null;
		loadFlagImage();
	}

	/**
	 * Gets the ID of this language pack.
	 */
	public String getID() {
		return id;
	}

	/**
	 * Gets the international name of this language file,
	 * e.g. "German" or "French".
	 */
	public String getInternationalName() {
		return internationalName;
	}

	/**
	 * Gets the local name of this language file,
	 * e.g. "Deutsch" or "Français". If not set, null
	 * is returned. This may be the case if the local
	 * name and the international name is the same
	 * (like "English").
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * Gets the 16px flag icon of this language, or null.
	 */
	public Icon getFlag16() {
		return flag16;
	}

	/**
	 * Gets a list of the available languages.
	 * @param path  path to the language pack directory (without trailing slash)
	 */
	public static List<LanguageInfo> getAvailableLanguages(String path)
		throws Exception {
		ArrayList<LanguageInfo> ret = new ArrayList<LanguageInfo>();
		Set<String> langs = desktopIO().listDirectories(path);
		if (langs.size() < 1) {
			throw new Exception("No language pack installed!");
		}
		else {
			for (String lang : langs) {
				try {
					ret.add(new LanguageInfo(path, lang));
				} catch (Exception ex) {
					throw new Exception("Language pack \"" + lang + "\" could not be loaded!", ex);
				}
			}
		}
		return ret;
	}

	/**
	 * Gets the ID of the default language, e.g. "de" if German is the
	 * user's default language. If it doesn't exist, "en" is returned.
	 */
	public static String getDefaultID(List<LanguageInfo> availableLanguages) {
		Locale locale = Locale.getDefault();
		String id = locale.getLanguage();
		//language available?
		boolean available = false;
		for (LanguageInfo info : availableLanguages) {
			if (info.getID().equals(id)) {
				available = true;
				break;
			}
		}
		return (available ? id : "en");
	}

	/**
	 * Loads the 16px flag, if there.
	 */
	private void loadFlagImage() {
		String flagPath = path + "/" + id + "/flag16.png";
		try {
			File iconFile = desktopIO().findFile(flagPath);
			if (iconFile != null)
				flag16 = new ImageIcon(ImageIO.read(iconFile));
			else
				flag16 = null;
		} catch (IOException ex) {
			flag16 = null;
		}
	}

}