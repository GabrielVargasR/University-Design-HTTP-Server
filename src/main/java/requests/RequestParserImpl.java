package requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestParserImpl implements RequestParser{

    private final RequestFactory requestFactory;

    public RequestParserImpl(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public Request parse(String request) throws BadRequestException  {

        return this.requestFactory.getInstance(tokenize(request));
    }

    private List<String> tokenize(String request) {
        String[] tokenList = request.split("\n");
        return new ArrayList<>(Arrays.asList(tokenList));
    }
}
