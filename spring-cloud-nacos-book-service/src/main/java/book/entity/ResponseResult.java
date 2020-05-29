package book.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wbc
 * @date 2020/05/15
 * @desc
 **/
@NoArgsConstructor
@Data
public class ResponseResult<T> {

    private T data;

    private String message;

    private int code;

    public ResponseResult(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public ResponseResult(String message, Integer code) {
        this(null, message, code);
    }

    public ResponseResult(T data) {
        this(data, "操作成功", 200);
    }


}
