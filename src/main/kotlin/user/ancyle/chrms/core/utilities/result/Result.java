package user.ancyle.chrms.core.utilities.result;

import lombok.Getter;

@Getter
public class Result {

    public Result(String message, boolean success) {
        this(success);
        this.message = message;
    }

    public Result(boolean success) {
        this.success = success;
    }

    private String message;
    private boolean success;
}
