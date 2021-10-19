package requests;

import method.response.Response;

public interface RequestProcessor {
    Response process(Request request);
}
