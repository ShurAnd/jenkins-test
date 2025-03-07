package org.andrey.back2javawebapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MagicCoreEntryNotFoundException extends RuntimeException{
    @Getter
    @Setter
    private String message = "";
}
