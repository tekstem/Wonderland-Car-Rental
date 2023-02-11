package com.edu.miu.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {
    private Object error;
}
