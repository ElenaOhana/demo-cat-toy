package com.example.web.democattoy.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrDetails {
    private final String key = "Cat-toy Miaou";
    private String message;
}
