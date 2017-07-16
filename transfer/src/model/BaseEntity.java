package model;

import java.io.Serializable;

public class BaseEntity<E> implements Serializable{
	private int code;
    private String message;
    private E data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public BaseEntity(int code, String message, E data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public BaseEntity() {
		super();
	}
    
}
