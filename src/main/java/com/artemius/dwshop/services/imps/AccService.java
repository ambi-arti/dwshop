package com.artemius.dwshop.services.imps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.repositories.AccountRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.EmailService;



@Service
public class AccService implements AccountService {

    @Autowired
    AccountRepository as;
    @Autowired
    EmailService es;
    
    
    public boolean registration(Map<String,Object> model, Account user) throws ParseException {
	boolean flawed = false;
	if (isUsernameUnique(user.getUsername())==false) {
	    model.put("nicknameExists","Данный email уже занят!");
	    flawed=true;
	}
	if (isUsernameMatchingPattern(user.getUsername())==false) {
	    model.put("notAnEmail","Неверный формат E-mail!");
	    flawed=true;
	}
	if (isCityMatchingPattern(user.getCity())==false) {
	    model.put("wrongCity","Неверный формат названия!");
	    flawed=true;
	}
	if (!isDateOkay(user.getBirthdate())) {
	    model.put("badDate","Неверный формат даты!");
	    flawed=true;
	}
	if (isPasswordLongEnough(user.getPassword())==false) {
	    model.put("passwordTooShort","Пароль слишком короткий!");
	    flawed=true;
	}
	if (!flawed) {
	    user.setPassword(new Pbkdf2PasswordEncoder().encode(user.getPassword()));
	    user.setRoles(Role.CONSUMER);	    
	    as.save(user);
	    String fullName = user.getSurname()+" "+user.getFirstname();
	    sendCongratsEmail(user.getUsername(),fullName);
	    user.setActive(true);
	    return true;
	}
	model.put("surname",user.getSurname());
	model.put("firstname",user.getFirstname());
	model.put("patronymic",user.getPatronymic());
	model.put("city",user.getCity());
	model.put("birthdate",user.getBirthdate());
	model.put("address",user.getAddress());
	model.put("username",user.getUsername());
	return false;
    }
    
    public boolean isUsernameUnique(String username) {
	try {
	Account u = as.findByUsername(username);
	if (u!=null)
	    return false;
	}
	catch (NullPointerException npe) {
	    //do sweet nothing, gotta ignore it
	}
	return true;
    }
    
    public boolean isUsernameMatchingPattern(String username) {
	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(username);
	return matcher.matches();
    }
    
    public boolean isCityMatchingPattern(String city) {
	//String regex = "/^[A-ZА-ЯЁ\\s-]+$/i";
	String regex = "|^[-А-Я-а-я]+$|i";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(city);
	return matcher.matches();
    }
    
    public boolean isPasswordLongEnough(String password) {
	return (password.length()>=8);
    }
    
    public boolean isPasswordUnique(String password, Account account) {
	return !password.equals(account.getPassword());
    }


    @Override
    public Account saveNewAccount(Account account) {
	return as.save(account);
    }

    @Override
    public Account findByUsername(String username) {
	return as.findByUsername(username);
    }

    @Override
    public List<Account> findAllByCity(String city) {
	return findAllByCity(city);
    }

    @Override
    public boolean isDateOkay(String date) {
	DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
	Date d = new Date();
	try {
	    d = f.parse(date);
	} 
	catch (ParseException e) {
	} 
	Date c = new Date();
	return c.after(d);
    }

    @Override
   public int sendCongratsEmail(String sendTo, String fullName) {
	String message = "			<html>\r\n" + 
		"				<body style = \"font-family: Franklin Gothic Book; font-size: 2.5vh\">\r\n" + 
		"					<b style = \"font-size: 3vh; text-align: center;\">Здравствуйте, "+fullName+"!</b>\r\n" + 
		"					<p style = \"text-align: justify\">Регистрация прошла успешно. Мы благодарим вас за то, что выбрали наш магазин. Теперь у вас есть доступ к приобретению качественной и недорогой мужской одежды.</p>\r\n" + 
		"					<b style = \"text-align: center\">Приятных покупок!</b>\r\n" + 
		"				</body>\r\n" + 
		"			</html>";
	return es.sendHtmlEmail("Регистрация прошла успешно!",message,sendTo);
   }

}
