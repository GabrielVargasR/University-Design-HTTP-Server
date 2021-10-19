package logger;

import method.response.Response;
import requests.Request;

public interface Logger {
    void log(Request request);
    void log(Response response);
}
