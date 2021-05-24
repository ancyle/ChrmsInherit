package user.ancyle.chrms.business.abstracts;


import user.ancyle.chrms.core.utilities.result.Result;

public interface UserConfirmationService {

    public Result verifyAccount(String code);
}
