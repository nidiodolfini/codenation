package challenge;

public class EstacionamentoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String msg;

    public EstacionamentoException() {
        super();
    }

    public EstacionamentoException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public EstacionamentoException(String message) {
        super(message);
        this.msg = message;
    }

    public EstacionamentoException(Throwable cause) {
        super(cause);
        this.msg = cause.getMessage();
    }

    public String getMessage(){
        return msg;
    }
}
