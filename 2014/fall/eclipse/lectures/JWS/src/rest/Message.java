package rest;

public class Message {
	public Message() {
		super();
	}
	public Message(String message, String receiver) {
		super();
		this.message = message;
		this.receiver = receiver;
	}
	private String message;
	private String receiver;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
}
