package com.xsslong.weflux.multiple.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : xutong
 * @desc :
 * @date : 2020/12/16 18:35
 * @update :
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6855117564028385865L;

    public static final Integer RESULT_SUCCESS_CODE = 0;
    private static final String RESULT_SUCCESS_MSG = "响应成功";

    private Integer errorCode;
    private String errorMsg;
    private transient T data;

    /**
     * 响应成功返回--无实体响应
     *
     * @return Result 响应返回
     */
    public static <T> Result<T> onSuccess() {
        return onSuccess(null);
    }

    /**
     * 响应成功返回--有实体响应
     *
     * @return Result 响应返回
     */
    public static <T> Result<T> onSuccess(T data) {
        return onResponse(RESULT_SUCCESS_CODE, RESULT_SUCCESS_MSG, data);
    }

    /**
     * 响应失败返回--无实体响应
     *
     * @return Result 响应返回
     */
//    public static <T> Result<T> onError(ErrorCodeEnum errorCodeEnum) {
//        Integer errorCode = errorCodeEnum.getErrorCode();
//        String errorMsg = errorCodeEnum.getErrorMsg();
//        return onError(errorCode, errorMsg);
//    }

    /**
     * 响应失败返回
     *
     * @return Result 响应返回
     */
//    public static <T> Result<T> onError(ErrorCodeEnum errorCodeEnum, String... errorMsg) {
//        Integer errorCode = errorCodeEnum.getErrorCode();
//        String errorMsgEnum = errorCodeEnum.getErrorMsg();
//        return onError(errorCode, String.format(errorMsgEnum, Arrays.stream(errorMsg).toArray()));
//    }

    /**
     * 响应失败返回
     *
     * @return Result 响应返回
     */
    public static <T> Result<T> onError(Integer resultCode, String resultMsg) {
        return onError(resultCode, resultMsg, null);
    }

    /**
     * 响应失败返回--有实体响应
     *
     * @return Result 响应返回
     */
    public static <T> Result<T> onError(Integer resultCode, String resultMsg, T data) {
        return onResponse(resultCode, resultMsg, data);
    }

    /**
     * 响应失败返回
     *
     * @return String 响应返回
     */
//    public static String onErrorString(Integer resultCode, String resultMsg) {
//        return JSON.toJSONString(onError(resultCode, resultMsg, null));
//    }

    /**
     * 响应返回
     *
     * @param resultCode 响应码
     * @param resultMsg  响应信息
     * @param data       响应数据体
     * @return Result 响应返回
     */
    public static <T> Result<T> onResponse(Integer resultCode, String resultMsg, T data) {
        Result<T> result = new Result<>();
        result.setErrorCode(resultCode);
        result.setErrorMsg(resultMsg);
        result.setData(data);
        return result;
    }

    public static boolean isSuccess(Result<?> result) {
        return Objects.equals(result.getErrorCode(), Result.RESULT_SUCCESS_CODE);
    }

    public static boolean isFail(Result<?> result) {
        return !Objects.equals(result.getErrorCode(), Result.RESULT_SUCCESS_CODE);
    }
}

