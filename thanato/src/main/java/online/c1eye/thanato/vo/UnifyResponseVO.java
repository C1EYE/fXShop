package online.c1eye.thanato.vo;


import io.github.talelin.autoconfigure.bean.Code;
import io.github.talelin.autoconfigure.util.RequestUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import online.c1eye.thanato.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class UnifyResponseVO<T> {

    private Integer code;

    private T message;

    private String request;

    public UnifyResponseVO() {
        this.code = Code.SUCCESS.getCode();
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVO(int code) {
        this.code = code;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVO(T message) {
        this.code = Code.SUCCESS.getCode();
        this.message = message;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVO(HttpStatus httpStatus) {
        this.code = Code.SUCCESS.getCode();
        this.message = (T) Code.SUCCESS.getDescription();
        this.request = RequestUtil.getSimpleRequest();
        ResponseUtil.setCurrentResponseHttpStatus(httpStatus.value());
    }

    public UnifyResponseVO(int code, T message) {
        this.code = code;
        this.message =  message;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVO(int code, HttpStatus httpStatus) {
        this.code = code;
        this.message = (T) Code.SUCCESS.getDescription();
        this.request = RequestUtil.getSimpleRequest();
        ResponseUtil.setCurrentResponseHttpStatus(httpStatus.value());
    }

    public UnifyResponseVO(T message, HttpStatus httpStatus) {
        this.code = Code.SUCCESS.getCode();
        this.message = message;
        this.request = RequestUtil.getSimpleRequest();
        ResponseUtil.setCurrentResponseHttpStatus(httpStatus.value());
    }

    public UnifyResponseVO(int code, T message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.request = RequestUtil.getSimpleRequest();
        ResponseUtil.setCurrentResponseHttpStatus(httpStatus.value());
    }

}
