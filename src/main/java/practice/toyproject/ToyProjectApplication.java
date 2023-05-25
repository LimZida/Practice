package practice.toyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * title : configuration
 * description : 빈 등록, 스캔 관리
 * reference : configuration 생성자 관련 https://hseungyeon.tistory.com/403, https://hseungyeon.tistory.com/404
 * author : 임현영
 * date : 2023.05.25
 **/
@SpringBootApplication // 내부에 ComponentScan 존재
public class ToyProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToyProjectApplication.class, args);
	}

}
