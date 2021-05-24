package user.ancyle.chrms.core.utilities.mail;

import user.ancyle.chrms.core.utilities.result.Result;

public interface MailService {
    public Result sendMail(String mail,String code);
    public String createCode();
}
