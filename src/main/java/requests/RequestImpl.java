package requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestImpl implements Request{

    private final Method method;
    private final String path;
    private final String protocolVersion;
    private final Map<String, String> headers;

    public RequestImpl(String method, String path, String protocolVersion, List<String> headers) {
        this.method = Method.valueOf(method);
        this.path = path;
        this.protocolVersion = protocolVersion;
        this.headers = this.buildHeaders(headers);
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getHeader(String key) {
        return this.headers.get(key);
    }

    @Override
    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    @Override
    public String toString() {
        return this.method + " " + this.path + " " + this.protocolVersion;
    }

    private Map<String, String> buildHeaders(List<String> rawHeaders) {
        Map<String, String> headers = new HashMap<String, String>();
        for (String header:
             rawHeaders) {
            String[] keyValuePair = header.split(": ");
            headers.put(keyValuePair[0], keyValuePair[1]);
        }
        return headers;
    }
}
