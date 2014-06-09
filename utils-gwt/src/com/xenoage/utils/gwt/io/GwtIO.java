package com.xenoage.utils.gwt.io;

import java.util.List;

import com.xenoage.utils.callback.AsyncCallback;
import com.xenoage.utils.gwt.GwtPlatformUtils;
import com.xenoage.utils.io.FileFilter;
import com.xenoage.utils.io.FilesystemInput;
import com.xenoage.utils.io.InputStream;

/**
 * Some useful input methods for a GWT client application.
 * 
 * Currently only reading a file is supported.
 * 
 * Use {@link GwtPlatformUtils#gwtIO()} to get an instance of this class.
 *
 * @author Andreas Wenger
 */
public class GwtIO
	implements FilesystemInput {
	
	@Override public void openFileAsync(String filePath, AsyncCallback<InputStream> callback) {
		GwtInputStream.open(filePath, callback);
	}

	@Override public void existsFileAsync(String filepath, AsyncCallback<Boolean> exists) {
		exists.onFailure(new UnsupportedOperationException()); //TODO
	}

	@Override public void existsDirectoryAsync(String directory, AsyncCallback<Boolean> exists) {
		exists.onFailure(new UnsupportedOperationException()); //TODO
	}

	@Override public void listFilesAsync(String directory, AsyncCallback<List<String>> files) {
		files.onFailure(new UnsupportedOperationException()); //TODO		
	}

	@Override public void listFilesAsync(String directory, FileFilter filter,
		AsyncCallback<List<String>> files) {
		files.onFailure(new UnsupportedOperationException()); //TODO
	}

	@Override public void listDirectoriesAsync(String directory,
		AsyncCallback<List<String>> directories) {
		directories.onFailure(new UnsupportedOperationException()); //TODO
	}

}
