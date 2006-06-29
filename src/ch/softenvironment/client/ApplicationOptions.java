package ch.softenvironment.client;

/*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

import ch.softenvironment.util.ParserCSV;
import ch.softenvironment.util.StringUtils;
import ch.softenvironment.util.Tracer;

/**
 * Manage the Application Settings by Properties file.
 *
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.5 $ $Date: 2006-06-29 22:24:57 $
 */
public class ApplicationOptions extends java.util.Properties implements UserSettings {
	// values for Key-Values
	private final static String SEPARATOR = ";";
	protected final static String HOME_DIRECTORY = "user.home";

	// Property Keys (non-NLS)
	// @see getKeySet()
	private final static String LOOK_AND_FEEL = "LOOK_AND_FEEL";
	private final static String BACKGROUND_COLOR = "BACKGROUND_COLOR";
	private final static String FONT = "FONT";
	private final static String FOREGROUND_COLOR = "FOREGROUND_COLOR";
	private final static String IMPORT_DIRECTORY = "IMPORT_DIRECTORY";
	private final static String LANGUAGE = "LANGUAGE";
	private final static String COUNTRY = "COUNTRY";
	private final static String SHOW_STATUS_BAR = "SHOW_STATUS_BAR";
	private final static String SHOW_TOOLBAR = "SHOW_TOOLBAR";
	private final static String WORKING_DIRECTORY = "WORKING_DIRECTORY";
	private final static String LAST_FILES = "LAST_FILES";
//	private final static String QUERY_DELETION = "QUERY_DELETION";
	private final static String WINDOW_HEIGHT = "WINDOW_HEIGHT";
	private final static String WINDOW_WIDTH = "WINDOW_WIDTH";
	private final static String WINDOW_X = "WINDOW_X";
	private final static String WINDOW_Y = "WINDOW_Y";

	// variables
	private String filename = null;
/**
 * Create new Default Settings.
 */
protected ApplicationOptions() {
	super();

	// create Default
	setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
	setBackgroundColor(java.awt.Color.white);
//	setFont("Default-PLAIN-9");
	setForegroundColor(java.awt.Color.black);
	setImportDirectory(System.getProperty(HOME_DIRECTORY));
//	setLanguage(java.util.Locale.GERMAN.getLanguage());
//	setCountry("CH");
	
	setShowStatusBar(Boolean.TRUE);
	setShowToolBar(Boolean.TRUE);
	setWorkingDirectory(System.getProperty(HOME_DIRECTORY));
	setLastFiles(new ArrayList());

	setWindowHeight(new Integer(600));
	setWindowWidth(new Integer(800));
	setWindowX(new Integer(10));
	setWindowY(new Integer(10));
}
/**
 * Load given Settings by filename.
 */
public ApplicationOptions(String filename) {
	this(filename, new ApplicationOptions());
}
/**
 * Load given Settings by filename.
 */
protected ApplicationOptions(String filename, java.util.Properties defaults) {
	// create Default
	super(defaults);

	try {
		// load the persistent Properties -> overwrite keys
		this.filename = filename;

		FileInputStream inputStream = new FileInputStream(filename);
		/*tmp=*/ super.load(inputStream);
	} catch(FileNotFoundException fe) {
		Tracer.getInstance().runtimeWarning("File not found: " + fe.getLocalizedMessage());
	} catch(IOException ioe) {
	    Tracer.getInstance().runtimeWarning("IO failure: " + ioe.getLocalizedMessage());
	}
}
/**
 * Return whether the User is allowed to use Application or not.
 * @return boolean
 */
public boolean getActive() {
	return true;
}
/**
 * Return whether the User is the Administrator himself.
 * @return boolean
 */
public boolean getAdmin() {
	return false;
}
/**
 * Gets the backgroundColor property (java.awt.Color) value.
 * @return The backgroundColor property value.
 * @see #setBackgroundColor
 */
public java.awt.Color getBackgroundColor() {
	return new java.awt.Color(new Integer(getProperty(BACKGROUND_COLOR)).intValue());
}
/**
 * Gets the Country property (java.lang.String) value.
 * @return The Country String
 * @see #setCountry
 */
public java.lang.String getCountry() {
    String country = getProperty(COUNTRY);
	if (StringUtils.isNullOrEmpty(country)) {
	    return Locale.getDefault().getCountry();
	} else {
	    return country;
	}
}
/**
 * Return font.
 * @return The font property value.
 * @see #setFont
 */
public Font getFont() {
    if (StringUtils.isNullOrEmpty(getFontString())) {
        return null;
    } else {
		return Font.decode(getFontString());
    }
}
/**
 * Return font in a descriptive String.
 * @return The font property value.
 * @see #setFont
 */
public String getFontString() {
	return getProperty(FONT);
}
/**
 * Gets the foregroundColor property (java.awt.Color) value.
 * @return The foregroundColor property value.
 * @see #setForegroundColor
 */
public java.awt.Color getForegroundColor() {
	return new java.awt.Color(new Integer(getProperty(FOREGROUND_COLOR)).intValue());
}
/**
 * Gets the importDirectory property (java.lang.String) value.
 * @return The importDirectory property value.
 * @see #setImportDirectory
 */
public java.lang.String getImportDirectory() {
	return getProperty(IMPORT_DIRECTORY);
}
/**
 * Gets the language property (java.lang.String) value.
 * @return The language property value.
 * @see #setLanguage
 */
public java.lang.String getLanguage() {
	String language = getProperty(LANGUAGE);
	if (StringUtils.isNullOrEmpty(language)) {
	    return Locale.getDefault().getLanguage();
	} else {
	    return language;
	}
}
/**
 * Gets the lastFiles opened property (java.lang.String) value.
 * @see #setWorkingDirectory
 */
public java.util.List getLastFiles() {
	return ParserCSV.stringToArray((String)getProperty(LAST_FILES), SEPARATOR);
}
/**
 * Gets the 'Look & Feel' property (java.lang.String) value.
 * @return The language property value.
 * @see #setLookAndFeel(String)
 */
public java.lang.String getLookAndFeel() {
	return getProperty(LOOK_AND_FEEL);
}
/**
 * Return the e-Mail Provider host to send e-Mails.
 * @return java.lang.String (for e.g. "mail.bluewin.ch")
 */
public java.lang.String getProviderSMTP() {
	return null;
}
/**
 * Gets the showStatusBar property (java.lang.Boolean) value.
 * @return The showStatusBar property value.
 * @see #setShowStatusBar
 */
public java.lang.Boolean getShowStatusBar() {
	return Boolean.valueOf(getProperty(SHOW_STATUS_BAR));
}
/**
 * Gets the showToolBar property (java.lang.Boolean) value.
 * @return The showToolBar property value.
 * @see #setShowToolBar
 */
public java.lang.Boolean getShowToolBar() {
	return Boolean.valueOf(getProperty(SHOW_TOOLBAR));
}
/**
 * Return the User's id, by means the login Id to the current application.
 * @return String (for e.g. "phirzel")
 * @see java.util.Locale
 */
public java.lang.String getUserId() {
	// there is no specific User or Login-Id
	return "<NONE>";
}
/**
 * Return property.
 */
public java.lang.Integer getWindowHeight() {
	return new Integer(getProperty(WINDOW_HEIGHT));
}
/**
 * Return property.
 */
public java.lang.Integer getWindowWidth() {
	return new Integer(getProperty(WINDOW_WIDTH));
}
/**
 * Return property.
 */
public java.lang.Integer getWindowX() {
	return new Integer(getProperty(WINDOW_X));
}
/**
 * Return property.
 */
public java.lang.Integer getWindowY() {
	return new Integer(getProperty(WINDOW_Y));
}
/**
 * Gets the workingDirectory property (java.lang.String) value.
 * @return The workingDirectory property value.
 * @see #setWorkingDirectory
 */
public java.lang.String getWorkingDirectory() {
	return getProperty(WORKING_DIRECTORY);
}
/**
 * Save the UserSettings.
 */
public final void save() {
	try {
	    FileOutputStream outputStream = new FileOutputStream(filename);
 	   super.store(outputStream, "User Properties <" + filename + ">");
	} catch(Throwable e) {
		Tracer.getInstance().runtimeWarning("IGNORE: Failed for User Properties <" + filename + ">");
	}
}
/**
 * Sets the backgroundColor property (java.awt.Color) value.
 * @param backgroundColor The new value for the property.
 * @see #getBackgroundColor
 */
public void setBackgroundColor(java.awt.Color backgroundColor) {
	setProperty(BACKGROUND_COLOR, (new Integer(backgroundColor.getRGB())).toString());
}
/**
 * Sets the Country property (java.lang.String) value.
 * @param language (for e.g. "CH"; "FR", etc)
 * @see #getCountry
 */
public void setCountry(java.lang.String country) {
	setProperty(COUNTRY, country);
}
/**
 * Transform given font into String-Description.
 * @param font
 * @see setFont(String)
 */
public void setFont(java.awt.Font font) {
    if (font == null) {
        setProperty(FONT, null);
    } else {
        String s = font.getFamily() + "-";
        switch (font.getStyle()) {
        case Font.BOLD:
            s = s + "BOLD";
            break;
        case Font.ITALIC:
            s = s + "ITALIC";
            break;
        case (Font.BOLD + Font.ITALIC):
            s = s + "BOLDITALIC";
            break;
        default:
            s = s + "PLAIN";
            break;
        }
        setProperty(FONT, s + "-" + font.getSize());
    }
}
/**
 * Sets the foregroundColor property (java.awt.Color) value.
 * @param foregroundColor The new value for the property.
 * @see #getForegroundColor
 */
public void setForegroundColor(java.awt.Color foregroundColor) {
	setProperty(FOREGROUND_COLOR, (new Integer(foregroundColor.getRGB())).toString());
}
/**
 * Sets the importDirectory property (java.lang.String) value.
 * @param importDirectory The new value for the property.
 * @see #getImportDirectory
 */
public void setImportDirectory(java.lang.String importDirectory) {
	setProperty(IMPORT_DIRECTORY, importDirectory);
}
/**
 * Sets the language property (java.lang.String) value.
 * @param language (for e.g. "de"; "fr", etc)
 * @see #getLanguage
 */
public void setLanguage(java.lang.String language) {
	setProperty(LANGUAGE, language);
}
/**
 * Sets the lastFiles opened property (java.lang.String) value.
 * @param 0..n Files separated by Semikolon ';'.
 * @see #getLastFiles
 */
public void setLastFiles(java.util.List lastFiles) {
	setProperty(LAST_FILES, ParserCSV.arrayToString(lastFiles, SEPARATOR));
}
/**
 * Sets the 'Look & Feel' property (java.lang.String) value.
 * This Font is used for graphical nodes and edges.
 * @param font The new value for the property.
 * @see #getLookAndFeel
 */
public void setLookAndFeel(String string) {
	setProperty(LOOK_AND_FEEL, string);
}
/**
 * Sets the showStatusBar property (java.lang.Boolean) value.
 * @param showStatusBar The new value for the property.
 * @see #getShowStatusBar
 */
public void setShowStatusBar(java.lang.Boolean showStatusBar) {
	setProperty(SHOW_STATUS_BAR, showStatusBar.toString());
}
/**
 * Sets the showToolBar property (java.lang.Boolean) value.
 * @param showToolBar The new value for the property.
 * @see #getShowToolBar
 */
public void setShowToolBar(java.lang.Boolean showToolBar) {
	setProperty(SHOW_TOOLBAR, showToolBar.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowHeight(java.lang.Integer value) {
	setProperty(WINDOW_HEIGHT, value.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowWidth(java.lang.Integer value) {
	setProperty(WINDOW_WIDTH, value.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowX(java.lang.Integer value) {
	setProperty(WINDOW_X, value.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowY(java.lang.Integer value) {
	setProperty(WINDOW_Y, value.toString());
}
/**
 * Sets the workingDirectory property (java.lang.String) value.
 * @param workingDirectory The new value for the property.
 * @see #getWorkingDirectory
 */
public void setWorkingDirectory(java.lang.String workingDirectory) {
	setProperty(WORKING_DIRECTORY, workingDirectory);
}
/*
 * Overwrites.
 */
public synchronized Object setProperty(String key, String value) {
    return super.setProperty(key, value == null ? "" : value);
}
}
