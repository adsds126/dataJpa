package com.study.data_jpa.repository;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UsernameOnlyDto {
    private final String username;

    public UsernameOnlyDto(String username){
        this.username = username;
    }
}
