package amock.io.utils.dto;

public class Mail {

	private String subject;
	
	private String body;
	
	private String attachment;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString(){
		return "Subject: " + this.subject
				+", Body: "+ this.body
				+", Attachment: " + this.attachment;
	}
	
}
