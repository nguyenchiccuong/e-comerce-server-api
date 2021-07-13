package com.rookies.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rookies.ecommerceapi.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BrandIdNotFoundException.class)
    public ResponseEntity<ResponseDto> BrandIdNotFoundException(BrandIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryExistInProductException.class)
    public ResponseEntity<ResponseDto> CategoryExistInProductException(CategoryExistInProductException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryHaveSubCategoryException.class)
    public ResponseEntity<ResponseDto> CategoryHaveSubCategoryException(CategoryHaveSubCategoryException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryIdNotFoundException.class)
    public ResponseEntity<ResponseDto> CategoryIdNotFoundException(CategoryIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNameExistException.class)
    public ResponseEntity<ResponseDto> CategoryNameExistException(CategoryNameExistException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<ResponseDto> OrderIdNotFoundException(OrderIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OriginIdNotFoundException.class)
    public ResponseEntity<ResponseDto> OriginIdNotFoundException(OriginIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductDetailIdExistInOrderDetail.class)
    public ResponseEntity<ResponseDto> ProductDetailIdExistInOrderDetail(ProductDetailIdExistInOrderDetail ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductDetailIdNotFoundException.class)
    public ResponseEntity<ResponseDto> ProductDetailIdNotFoundException(ProductDetailIdNotFoundException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductDetailNotValidException.class)
    public ResponseEntity<ResponseDto> ProductDetailNotValidException(ProductDetailNotValidException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductIdNotFoundException.class)
    public ResponseEntity<ResponseDto> ProductIdNotFoundException(ProductIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewIdNotFoundException.class)
    public ResponseEntity<ResponseDto> ReviewIdNotFoundException(ReviewIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNameNotFoundException.class)
    public ResponseEntity<ResponseDto> RoleNameNotFoundException(RoleNameNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<ResponseDto> UserIdNotFoundException(UserIdNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserLockedException.class)
    public ResponseEntity<ResponseDto> UserLockedException(UserLockedException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseDto> UsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameUnmatchWithReviewException.class)
    public ResponseEntity<ResponseDto> UsernameUnmatchWithReviewException(UsernameUnmatchWithReviewException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ResponseDto> UsernameAlreadyTakenException(UsernameAlreadyTakenException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ResponseDto> EmailAlreadyTakenException(EmailAlreadyTakenException ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhonenumberAlreadyTakenException.class)
    public ResponseEntity<ResponseDto> PhonenumberAlreadyTakenException(PhonenumberAlreadyTakenException ex,
            WebRequest request) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

}
