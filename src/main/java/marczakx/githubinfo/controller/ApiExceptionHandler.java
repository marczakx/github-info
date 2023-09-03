package marczakx.githubinfo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import marczakx.githubinfo.model.error.ErrorResponse;

import javax.management.AttributeNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleApiException(Exception ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	public ResponseEntity<ErrorResponse> handleNotFoundApiException(HttpClientErrorException.NotFound ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AttributeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAttributeNotFoundException(AttributeNotFoundException ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptableException() {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.toString(),
				"acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE), HttpStatus.NOT_ACCEPTABLE);

	}

}