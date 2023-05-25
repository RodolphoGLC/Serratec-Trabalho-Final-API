package com.apiFinal.eCommerce.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.apiFinal.eCommerce.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//gerar automaticamente os métodos básicos de acesso a dados, como getters e setters, construtor, equals, hashCode e toString.
//@Data
@Entity
@Table(name = "email")
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long emailId;

	@Column(name = "owner_ref")
	// para ser mais facil a filtragem, o propietario
	private String ownerRef;

	@Column(name = "email-from")
	// remetente
	private String emailFrom;

	@Column(name = "email_to")
	// destinario
	private String emailTo;
	
	@Column(name = "subject")
	private String subject;

	// para poder enviar mensagens longas
	@Column(columnDefinition = "TEXT")
	private String text;

	@Column(name = "dd")
	private LocalDateTime sendDateEmail;
	
	@Column(name = "status_email")
	private StatusEmail statusEmail;

	public Email() {
		super();
	}

	public Email(Long emailId, String ownerRef, String emailFrom, String emailTo, String subject, String text,
			LocalDateTime sendDateEmail, StatusEmail statusEmail) {
		super();
		this.emailId = emailId;
		this.ownerRef = ownerRef;
		this.emailFrom = emailFrom;
		this.emailTo = emailTo;
		this.subject = subject;
		this.text = text;
		this.sendDateEmail = sendDateEmail;
		this.statusEmail = statusEmail;
	}

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
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

	public LocalDateTime getSendDateEmail() {
		return sendDateEmail;
	}

	public void setSendDateEmail(LocalDateTime sendDateEmail) {
		this.sendDateEmail = sendDateEmail;
	}

	public StatusEmail getStatusEmail() {
		return statusEmail;
	}

	public void setStatusEmail(StatusEmail statusEmail) {
		this.statusEmail = statusEmail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}