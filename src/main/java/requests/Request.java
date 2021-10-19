package requests;

public interface Request {
    Method getMethod();
    String getPath();
    String getHeader(String key);
    String getProtocolVersion();
}
