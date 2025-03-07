package org.andrey.back2javawebapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andrey.back2javawebapp.exceptions.MagicCoreEntryNotFoundException;
import org.andrey.back2javawebapp.exceptions.NotEnoughCoreException;
import org.andrey.back2javawebapp.exceptions.NotEnoughCoreForTransferringException;
import org.andrey.back2javawebapp.model.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private ObjectMapper objectMapper;

    @Autowired
    public ExceptionControllerAdvice(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(NotEnoughCoreException.class)
    public ResponseEntity<String> processNotEnoughCoreException(NotEnoughCoreException ex) throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());

        return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(errorDetails));
    }

    @ExceptionHandler(MagicCoreEntryNotFoundException.class)
    public ResponseEntity<String> processMagicCoreEntryNotFoundException(MagicCoreEntryNotFoundException ex) throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());

        return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(errorDetails));
    }

    @ExceptionHandler(NotEnoughCoreForTransferringException.class)
    public ResponseEntity<String> processNotEnoughCoreForTransferringException(NotEnoughCoreForTransferringException ex) throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());

        return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(errorDetails));
    }
}
