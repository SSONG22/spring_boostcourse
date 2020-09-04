package kr.or.study.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//import kr.or.study.project.config.DBConfig;
@Configuration
@ComponentScan(basePackages= {"kr.or.study.project.dao","kr.or.study.project.service"})
@Import(value={DBConfig.class})
public class ApplicationConfig {
}
