package com.apiFinal.eCommerce.services;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Email;
import com.apiFinal.eCommerce.enums.StatusEmail;
import com.apiFinal.eCommerce.repositories.EmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;

	//adiocionado nas dependencias
	@Autowired
	private JavaMailSender emailSender;

	@Transactional
	public Email saveEmail(Email email) {
		email.setSendDateEmail(LocalDateTime.now());
	
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getText());
			emailSender.send(message);
			
			
			email.setStatusEmail(StatusEmail.ENVIADO);
			return emailRepository.save(email);
		

		} catch (MailException e) {
			email.setStatusEmail(StatusEmail.ERRO);
		} finally {
			return emailRepository.save(email);
		}
	}

	public List<Email> getAllEmails() {
		return emailRepository.findAll(Email);
	}
	
	public Email getEmailById(Integer id) {
		return emailRepository.findById(id).orElse(null);
	}

	public Email updateEmail(Email email) {
		return emailRepository.save(email);
	}
	
	public Boolean deleteEmail(Integer id) {
		emailRepository.deleteById(id);
		Email emailDeletado = emailRepository.findById(id).orElse(null);
		if(emailDeletado == null) {
			return true;
		} else {
			return false;
		}
	}
	
}


}
