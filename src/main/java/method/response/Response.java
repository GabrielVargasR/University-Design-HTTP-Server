package method.response;

import java.io.InputStream;

public interface Response {
    String getHeader(String headerKey);
    String getProtocolVersion();
    HTTPStatus getStatus();
    InputStream getBody();
    String toString();
}
