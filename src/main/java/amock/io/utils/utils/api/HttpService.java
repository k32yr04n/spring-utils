package amock.io.utils.utils.api;

import amock.io.utils.dto.HttpRequest;
import amock.io.utils.dto.HttpResponse;

public interface HttpService {
	
	public HttpResponse Send(HttpRequest request);

}
