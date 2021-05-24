package user.ancyle.chrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.ancyle.chrms.business.abstracts.UserConfirmationService;
import user.ancyle.chrms.business.abstracts.UserService;
import user.ancyle.chrms.core.utilities.result.ErrorResult;
import user.ancyle.chrms.core.utilities.result.Result;
import user.ancyle.chrms.core.utilities.result.SuccessResult;

@Service
public class UserConfirmationManager implements UserConfirmationService {

    private final UserService userService;

    @Autowired
    public UserConfirmationManager(UserService userService){this.userService=userService;}


    @Override
    public Result verifyAccount(String code) {
        var result=userService.getUserByActivationCode(code);
        if(!result.isSuccess()) return new ErrorResult("User or code invalid");
        var key = userService.getUserByActivationCode(code).getData().getUserType();

        switch (key){
            case "JS":
                var js=this.userService.getJobSeekerByConfirmationCode(code);
                if(!js.isSuccess()|| js.getData().getUserConfirmation().isMailVerify())
                    return new ErrorResult("Process invalid");
                js.getData().getUserConfirmation().setMailVerify(true);
                this.userService.updateJobSeeker(js.getData(),js.getData().getUserPass());
                return new SuccessResult();
            case "EMP":
                var emp=this.userService.getEmployerByConfirmationCode(code);
                if(!emp.isSuccess()|| emp.getData().getUserConfirmation().isMailVerify())
                    return new ErrorResult("Process invalid");
                emp.getData().getUserConfirmation().setMailVerify(true);
                this.userService.updateEmployer(emp.getData(),emp.getData().getUserPass());
                return new SuccessResult();
            default:
                return new ErrorResult("Process invalid");
        }
    }
}
