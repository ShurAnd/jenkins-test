package org.andrey.back2javawebapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NotEnoughCoreForTransferringException extends RuntimeException{
    @Getter
    @Setter
    private String message = "";
}
