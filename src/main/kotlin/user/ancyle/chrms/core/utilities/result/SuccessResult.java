package user.ancyle.chrms.core.utilities.result;

public class SuccessResult extends Result {

    public SuccessResult(String message) {
        super(message, true);
    }

    public SuccessResult() {
        super(true);
    }
}
