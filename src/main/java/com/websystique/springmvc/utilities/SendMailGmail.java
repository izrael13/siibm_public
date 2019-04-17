package com.websystique.springmvc.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailGmail {
    
	public void sendMail(String mail,String msg)
	{
		final String username = "serversap@barcademexico.com";
		final String password = "Sistemas#04";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("serversap@barcademexico.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail));
				message.setSubject("Recupareación de contraseña SIIBM");
				message.setContent(msg, "text/html");
	
				Transport.send(message);
	

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public String generaMensage(String base,String sso,String email,Date fecha_sol, Date fecha_caducidad) throws UnsupportedEncodingException
	{
		Crypto crypto = new Crypto();
		
		String param,msg = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		param = sso;
		param = param + "||" + email;
		param = param + "||" + dateFormat.format(fecha_sol);
		param = param + "||" + dateFormat.format(fecha_caducidad);
		param = crypto.encrypt(param);
		param = URLEncoder.encode(param, "utf-8");
		
		msg = "<html>\n" +
                "<body>\n" +
                "<br> <br>" +
                "Hola! <br> <br>"+
                "Usted, con usuario ("+sso+"), le ha llegado este email porque hemos recibido una solicitud de recuperación de contraseña para el Sistema de Información Integral de Barca de México (SIIBM). <br> <br>" +
                "<a href=\" "+base+"resetp?xyqedt="+param+" \"> Recuperar contraseña </a>  <br> <br>" +
                "Si no reconoce esta solicitud favor de notificarlo al Administrador del sistema. <br> <br>" +
                "Saludos. <br> <br>" +
                "</body>" +
                "</html>";
		return msg;
	}
	
	public Map<String, String> getParams(String param) throws UnsupportedEncodingException
	{
		Crypto crypto = new Crypto();
		
		String p = "";
		//System.out.println("paramGet: "+param);
		p = crypto.decrypt(param);
		
		
		final String[] tokens = p.split(Pattern.quote("||"));
		Map<String,String> mapParam = new HashMap<String,String>();
		mapParam.put("sso", tokens[0]);
		mapParam.put("email", tokens[1]);
		mapParam.put("fecha_sol",tokens[2]);
		mapParam.put("fecha_caducidad", tokens[3]);
		
		/*System.out.println(mapParam.get("sso"));
		System.out.println(mapParam.get("email"));
		System.out.println(mapParam.get("fecha_sol"));
		System.out.println(mapParam.get("fecha_caducidad"));*/
		
		return mapParam;
	}
	/*public static void main( String [] args)
	{
		SendMailGmail  s = new SendMailGmail();
		Date date =new Date();
		System.out.println(s.generaMensage("idm", "israel", date, date));
	}*/

}
