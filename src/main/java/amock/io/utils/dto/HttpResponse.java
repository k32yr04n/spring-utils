package amock.io.utils.dto;

import java.util.List;
import java.util.Map;

public class HttpResponse {
	
	protected boolean httpSuccess;
	
	protected Integer code;
	
	protected String message;
	
	protected Map<String, List<String>> headers;
	
	protected String body;

	public boolean isHttpSuccess() {
		return httpSuccess;
	}

	public void setHttpSuccess(boolean httpSuccess) {
		this.httpSuccess = httpSuccess;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
