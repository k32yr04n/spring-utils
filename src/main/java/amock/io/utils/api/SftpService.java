package amock.io.utils.api;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface SftpService {
		
	public void upload(InputStream fis) throws Exception;
	
	public List<File> download() throws Exception;
	
}
