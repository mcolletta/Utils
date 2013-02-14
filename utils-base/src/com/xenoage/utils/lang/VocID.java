package com.xenoage.utils.lang;


/**
 * This interface must be implemented by all enums that contain
 * vocabulary IDs.
 * 
 * There must be a method {@link #getDefaultValue()} to which
 * contains the English text (used for English language and fallback)
 * and a method {@link #getID()} that returns the ID
 * as a String needed to index the vocabulary in XML files
 * (like "Error_UnknownValue").
 * 
 * @author Andreas Wenger
 */
public interface VocID
{
	
	/**
	 * Gets the default English text.
	 */
	public String getDefaultValue();
	
	
	/**
	 * Gets the ID of the vocabulary as a String.
	 */
	public String getID();

}
