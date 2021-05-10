package com.upgrad.FoodOrderingApp.api.exception;

import com.upgrad.FoodOrderingApp.api.model.ErrorResponse;
import com.upgrad.FoodOrderingApp.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// Controller advice to handle exceptions
@ControllerAdvice
public class RestExceptionHandler {

  // handle SignUpRestrictedException
  @ExceptionHandler(SignUpRestrictedException.class)
  public ResponseEntity<ErrorResponse> signUpRestrictedExceptionExceptionHandler(
      SignUpRestrictedException exe, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exe.getCode()).message(exe.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  // handle AuthenticationFailedException
  @ExceptionHandler(AuthenticationFailedException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationFailedException(
      AuthenticationFailedException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.UNAUTHORIZED);
  }

  // handle AuthorizationFailedException
  @ExceptionHandler(AuthorizationFailedException.class)
  public ResponseEntity<ErrorResponse> handleAuthorizationFailedException(
      AuthorizationFailedException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.FORBIDDEN);
  }

  // handle UpdateCustomerException
  @ExceptionHandler(UpdateCustomerException.class)
  public ResponseEntity<ErrorResponse> handleUpdateCustomerException(
      UpdateCustomerException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  // handle SaveAddressException
  @ExceptionHandler(SaveAddressException.class)
  public ResponseEntity<ErrorResponse> handleSaveAddressException(
      SaveAddressException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  // handle AddressNotFoundException
  @ExceptionHandler(AddressNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleAddressNotFoundException(
      AddressNotFoundException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  // handle CategoryNotFoundException
  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(
      CategoryNotFoundException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  // handle RestaurantNotFoundException
  @ExceptionHandler(RestaurantNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(
      RestaurantNotFoundException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  // handle InvalidRatingException
  @ExceptionHandler(InvalidRatingException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRatingException(
      InvalidRatingException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  // handle CouponNotFoundException
  @ExceptionHandler(CouponNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleCouponNotFoundException(
      CouponNotFoundException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }
  // handle PaymentMethodNotFoundException
  @ExceptionHandler(PaymentMethodNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlePaymentMethodNotFoundException(
      PaymentMethodNotFoundException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  // handle ItemNotFoundException
  @ExceptionHandler(ItemNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleItemNotFoundException(
      ItemNotFoundException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }
}
