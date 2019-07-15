package com.dao.setup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao.setup.mapper")
public class ProcedureDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcedureDaoApplication.class, args);
    }

}
