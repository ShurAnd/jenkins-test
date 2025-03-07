package org.andrey.back2javawebapp.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotEnoughCoreException extends RuntimeException{
    private String message = "";
}
