package com.apiFinal.eCommerce.exceptions;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler   {

//	@ExceptionHandler(IncompleteArgumentException.class)
//    ProblemDetail handleNoSuchElementException(IncompleteArgumentException e) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
//        
//        problemDetail.setTitle("Requisição incompleta");
//        problemDetail.setType(URI.create("https://api.eCommerce.com/errors/bad-request"));
//        return problemDetail;
//    }
//	
	@ExceptionHandler(UnmatchingIdsException.class)
    ProblemDetail handleUnmatchingIdsException(UnmatchingIdsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        
        problemDetail.setTitle("Problema na declaração do id.");
        problemDetail.setType(URI.create("https://api.eCommerce.com/errors/bad-request"));
        return problemDetail;
    }
	
	@ExceptionHandler(UniqueElementException.class)
    ProblemDetail handleUniqueElementException(UniqueElementException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        
        problemDetail.setTitle("Elemento unico");
        problemDetail.setType(URI.create("https://api.eCommerce.com/errors/bad-request"));
        return problemDetail;
    }
		
	@Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, 
    		HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

        if (response.getBody() instanceof ProblemDetail problemDetailBody) {
            problemDetailBody.setProperty("message", ex.getMessage());
            if (ex instanceof MethodArgumentNotValidException subEx) {
                BindingResult result = subEx.getBindingResult();
                problemDetailBody.setTitle("Erro na requisição");
                problemDetailBody.setDetail("Ocorreu um erro ao processar a Requisição");
                problemDetailBody.setProperty("message", "Validation failed for object='" + 
                		result.getObjectName() + "'. " + "Error count: " + result.getErrorCount());
                
                problemDetailBody.setProperty("errors", result.getAllErrors());
            }
        }
        return response;
    }
}
