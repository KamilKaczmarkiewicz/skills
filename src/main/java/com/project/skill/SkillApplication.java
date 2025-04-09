package com.project.skill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SkillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillApplication.class, args);
	}

}
