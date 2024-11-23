package com.ssafy.gilbut.advice.status;

import com.ssafy.gilbut.advice.code.BaseErrorCode;
import com.ssafy.gilbut.advice.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 코스 관련 에러
    COURSE_NOT_FOUND(HttpStatus.BAD_REQUEST, "COURSE4001", "관련 코스가 없습니다."),

    // 여행 (Travel) 관련 에러
    TRAVEL_NOT_FOUND(HttpStatus.BAD_REQUEST, "TRAVEL4001", "ID와 일치하는 여행 계획이 없습니다."),

    // 여행 계획 (Plan) 관련 에러
    PLAN_ATTRACTION_DUPLICATED_ERROR(HttpStatus.BAD_REQUEST, "PLAN4001", "여행지와 둘렛길이 중복됩니다."),
    PLAN_ORDER_DUPLICATED_ERROR(HttpStatus.BAD_REQUEST, "PLAN4002", "여행지 탐방 순서가 중복됩니다. null값이나 key를 확인해주세요"),
    PLAN_COURSE_NOT_ONE_ERROR(HttpStatus.BAD_REQUEST, "PLAN4003", "코스의 개수는 무조건 1개만 선택가능합니다."),

    // 게시판 관련 에러
    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "BOARD4001", "게시판이 없습니다."),

    // 멤버 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    USER_NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    USER_DUPLICATED_LOGIN_ID(HttpStatus.BAD_REQUEST, "MEMBER4003", "아이디가 중복되었습니다."),
    USER_DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "MEMBER4004", "닉네임이 중복되었습니다."),
    USER_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "MEMBER4005", "로그인에 실패하였습니다."),
    USER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "MEMBER4006", "토큰 값과 요청한 아이디가 일치하지않습니다."),

    // 토큰 관련 에러
    TOKEN_EXPIRED_ERROR(HttpStatus.UNAUTHORIZED, "TOKEN4001", "토큰의 유효기간이 만료되었습니다."),
    TOKEN_MALFORMED_ERROR(HttpStatus.UNAUTHORIZED, "TOKEN4002", "토큰이 변형되었습니다."),
    TOKEN_UNSUPPORTED_ERROR(HttpStatus.BAD_REQUEST, "TOKEN4003", "지원되지 않는 형식의 토큰입니다."),
    TOKEN_SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED, "TOKEN4004", "토큰의 서명이 유효하지 않습니다."),
    TOKEN_ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "TOKEN4005", "토큰 값이 잘못되었습니다."),
    TOKEN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "TOKEN5000", "토큰 처리 중 알 수 없는 에러가 발생했습니다."),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "TOKEN4006", "유저의 토큰정보를 얻어올 수 없습니다. 헤더를 확인하세요"),

    // 데이터베이스 관련 에러
    DATABASE_DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "DB5001", "중복된 리소스가 존재합니다"),
    DATABASE_INVALID_FOREIGN_KEY(HttpStatus.BAD_REQUEST, "DB5002", "잘못된 외래 키 참조입니다"),
    DATABASE_CONNECTION_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "DB5003", "데이터베이스 연결 오류가 발생했습니다"),
    DATABASE_INVALID_COLUMN_REFERENCE(HttpStatus.BAD_REQUEST, "DB5004", "잘못된 컬럼을 참조했습니다"),
    DATABASE_TABLE_NOT_FOUND(HttpStatus.NOT_FOUND, "DB5005", "테이블을 찾을 수 없습니다"),
    DATABASE_DATA_TRUNCATION_ERROR(HttpStatus.BAD_REQUEST, "DB5006", "데이터 길이가 초과되었거나 타입이 일치하지 않습니다"),
    DATABASE_DEADLOCK_OCCURRED(HttpStatus.CONFLICT, "DB5007", "데드락이 발생했습니다"),
    DATABASE_LOCK_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "DB5008", "락 획득 대기 시간이 초과되었습니다"),
    DATABASE_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB5999", "내부 서버 오류가 발생했습니다");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}