package com.study.data_jpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    @Value("#{target.username + ' ' + target.age}") // open projection
    String getUsername();
}
//인터페이스만 만들어도 spring data jpa가 구현체를 만들어준다.