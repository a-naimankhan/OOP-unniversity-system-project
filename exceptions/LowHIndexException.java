package exceptions;

public class LowHIndexException extends RuntimeException {
    //TODO
    //OR TO TO DO IT in another way I just don't know what and didn't understand what is H index actually
    public LowHIndexException() {super("H-index is too low.");}

    public LowHIndexException(String message) {
        super(message);
    }
}
