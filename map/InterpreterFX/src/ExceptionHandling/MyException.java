package ExceptionHandling;



public class MyException extends Exception {

    public MyException(String ex){
        super(ex);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
