package com.hvho1119.springboot.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
