package requests;

import method.response.Response;

public interface RequestController {
    Response process(String request);
}
