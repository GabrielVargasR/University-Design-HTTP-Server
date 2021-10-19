package requests;

public interface RequestParser {
    Request parse(String request) throws BadRequestException ;
}
