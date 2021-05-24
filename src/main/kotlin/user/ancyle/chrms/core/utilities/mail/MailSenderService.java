package user.ancyle.chrms.core.utilities.mail;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import user.ancyle.chrms.core.utilities.result.ErrorResult;
import user.ancyle.chrms.core.utilities.result.Result;
import user.ancyle.chrms.core.utilities.result.SuccessResult;
import user.ancyle.chrms.entities.concretes.User;

import java.util.UUID;
@Service
public class MailSenderService implements MailService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderService(JavaMailSender mailSender){this.javaMailSender=mailSender;}

    @Override
    public Result sendMail(String mail, String code) {
        var mime=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mime);
        try{
            helper.setTo(mail);
            helper.setSubject("Account Activation");
            helper.setText("Thanks for using this api. To activate account follow this link\n"
            +"https://localhost:8080/acc/act?code="+code);
            javaMailSender.send(mime);
            return new SuccessResult("Activation link has been sent.");
        }
        catch(Exception ex){return new ErrorResult(ex.getMessage());}
    }


    public String createCode() {
        return UUID.randomUUID().toString();
    }
}
