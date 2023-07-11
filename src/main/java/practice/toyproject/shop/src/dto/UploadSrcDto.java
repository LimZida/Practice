package practice.toyproject.shop.src.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * title : UploadSrcDto
 *
 * description : s3에 저장할 사진 json request 매핑용 dto
 *
 * reference : cannot deserialize from object value 해결법 : https://charactermail.tistory.com/488, https://azurealstn.tistory.com/74
 *             request 받았는데 dto 매핑이 안되는 경우 : https://velog.io/@ssol_916/RequestBody%EB%A1%9C-%EB%B0%9B%EC%95%98%EB%8A%94%EB%8D%B0-null%EC%9D%B8-%EA%B2%BD%EC%9A%B0
 *             기본 생성자의 의미 : https://velog.io/@jakeseo_me/%EA%B0%84%EB%8B%A8%EC%A0%95%EB%A6%AC-%EC%9E%90%EB%B0%94%EC%97%90%EC%84%9C-%EA%B8%B0%EB%B3%B8-%EC%83%9D%EC%84%B1%EC%9E%90%EC%9D%98-%EC%9D%98%EB%AF%B8-feat.-Java-Reflection-Jackson-JPA
 *             빌더 : https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
 *                   https://pamyferret.tistory.com/67
 *                   https://velog.io/@taegon1998/Spring-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0DTO-builder
 *             Setter와 Builder의 차이 : https://mjoo1106.tistory.com/entry/Spring-Setter-vs-Builder
 *
 * author : 임현영
 * date : 2023.06.30
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UploadSrcDto {
    private MultipartFile image;
    private String type;

    @Builder
    private UploadSrcDto(MultipartFile image, String type){
        this.image = image;
        this.type=type;
    }
}
