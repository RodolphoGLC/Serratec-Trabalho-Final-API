package com.apiFinal.eCommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO {

	@NotBlank
	private String ownerRef;

	@Email
	@NotBlank
	private String emailFrom;

	@NotBlank
	@Email
	private String emailTo;
	@NotBlank
	private String subject;
	@NotBlank
	private String text;

	public EmailDTO() {
		super();
	}

	public EmailDTO(@NotBlank String ownerRef, @Email @NotBlank String emailFrom, @NotBlank @Email String emailTo,
			@NotBlank String subject, @NotBlank String text) {
		super();
		this.ownerRef = ownerRef;
		this.emailFrom = emailFrom;
		this.emailTo = emailTo;
		this.subject = subject;
		this.text = text;
	}

	public String getOwnerRef() {
		return ownerRef;
	}

	public void setOwnerRef(String ownerRef) {
		this.ownerRef = ownerRef;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
