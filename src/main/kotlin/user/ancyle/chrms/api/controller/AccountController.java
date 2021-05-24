package user.ancyle.chrms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import user.ancyle.chrms.business.abstracts.UserConfirmationService;
import user.ancyle.chrms.business.abstracts.UserService;
import user.ancyle.chrms.core.utilities.result.ErrorResult;
import user.ancyle.chrms.core.utilities.result.Result;
import user.ancyle.chrms.core.utilities.result.SuccessResult;

@RestController
@RequestMapping("/acc")
public class AccountController {

    private final UserConfirmationService userConfirmationService;

    @Autowired
    public AccountController(UserConfirmationService userConfirmationService){
        this.userConfirmationService=userConfirmationService;
    }

    @GetMapping("/act")
    public Result confirm(@RequestParam String code){
        var result=this.userConfirmationService.verifyAccount(code);
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        return new SuccessResult("User Activated.");
    }
}
