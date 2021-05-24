package user.ancyle.chrms.core.utilities.business;

import user.ancyle.chrms.core.utilities.result.ErrorResult;
import user.ancyle.chrms.core.utilities.result.Result;

public class RuleVerifier {

    public static Result verify(Result ...results){
        for(Result result:results){
            if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        }
        return null;
    }
}
