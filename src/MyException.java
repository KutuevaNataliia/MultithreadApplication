public class MyException extends RuntimeException{

    public MyException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Оба потока были остановлены";
    }
}
