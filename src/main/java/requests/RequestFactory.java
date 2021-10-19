package requests;

import java.util.List;

public class RequestFactory {
    public RequestFactory(){}

    public Request getInstance(List<String> tokens) throws BadRequestException {
        String[] firstLine = tokens.get(0).split(" ");
        tokens.remove(0);

        if (firstLine.length != 3 || tokens.isEmpty()) {
            throw new requests.BadRequestException();
        }

        return new RequestImpl(firstLine[0], firstLine[1], firstLine[2], tokens);
    }
}
