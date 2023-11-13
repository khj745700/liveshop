package com.liveshop.application.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * <h2>ResponseConstants 를 분리한 이유?</h2>
 * <p>
 *     매번 Controller가 ResponseEntity를 생성하게 되면 메모리가 빨리 쌓여 GC이 많이 도는 환경이 구성될 수 있습니다.
 *     따라서 상수로 응답객체를 미리 생성해놓음으로서 메모리 소모를 최소화 하기 위해 작성하였습니다.
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseConstants {
    /** Http Status Code = 200 OK */
    public static final ResponseEntity<Void> OK = new ResponseEntity<>(HttpStatus.OK);
    /** Http Status Code = 201 CREATED*/
    public static final ResponseEntity<Void> CREATED = new ResponseEntity<>(HttpStatus.CREATED);
    /** Http Status Code = 202 ACCEPTED*/
    public static final ResponseEntity<Void> ACCEPTED = new ResponseEntity<>(HttpStatus.ACCEPTED);

    /** Http Status Code = 302 FOUND*/
    public static final ResponseEntity<Void> FOUND = new ResponseEntity<>(HttpStatus.FOUND);
    /** Http Status Code = 400 BAD_REQUEST */
    public static final ResponseEntity<Void> BAD_REQUEST = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    /** Http Status Code = 401 UNAUTHORIZED */
    public static final ResponseEntity<Void> UNAUTHORIZED = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    /** Http Status Code = 403 FORBIDDEN */
    public static final ResponseEntity<Void> FORBIDDEN = new ResponseEntity<>(HttpStatus.FORBIDDEN);
    /** Http Status Code = 404 NOT_FOUND */
    public static final ResponseEntity<Void> NOT_FOUND = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    /** Http Status Code = 409 CONFLICT */
    public static final ResponseEntity<Void> CONFLICT = new ResponseEntity<>(HttpStatus.CONFLICT);
}
