package com.apiFinal.eCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${spring.mail.host}")
    private String mailHost;
    
    @Value("${spring.mail.port}")
    private String mailPort;
    
    @Value("${spring.mail.username}")
    private String userName;
    
    @Value("${spring.mail.password}")
    private String password;
    
    @Value("${mail.from}")
    private String mailFrom;
    
    public EmailService(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }
	
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
        var mailMessage = new SimpleMailMessage();
        
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(assunto);
        mailMessage.setText(mensagem);
        mailMessage.setFrom(mailFrom);
        
        try {
            emailSender.send(mailMessage);
        }catch(Exception ex) {
            System.out.println("Ocorreu um erro ao tentar enviar o e-mail: " + ex.getMessage());
        }
    }
	/*
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
	}*/
/*
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
*/

}
