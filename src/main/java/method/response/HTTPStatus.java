package method.response;

public enum HTTPStatus {
    NOT_FOUND (404,"Not Found"),
    OK(200,"OK"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    NOT_IMPLEMENTED(501,"Not Implemented"),
    BAD_REQUEST(400,"Bad Request");

    private Integer code;
    private String message;

    HTTPStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
