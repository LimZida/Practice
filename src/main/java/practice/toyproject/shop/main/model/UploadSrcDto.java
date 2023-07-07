package practice.toyproject.shop.main.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * title : UploadSrcDto
 *
 * description : s3에 저장할 사진 json request 매핑용 dto
 *
 * reference : cannot deserialize from object value 해결법 : https://charactermail.tistory.com/488, https://azurealstn.tistory.com/74
 *             request 받았는데 dto 매핑이 안되는 경우 : https://velog.io/@ssol_916/RequestBody%EB%A1%9C-%EB%B0%9B%EC%95%98%EB%8A%94%EB%8D%B0-null%EC%9D%B8-%EA%B2%BD%EC%9A%B0
 *             빌더 : https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
 *                   https://pamyferret.tistory.com/67
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
    public UploadSrcDto(MultipartFile image, String type){
        this.image = image;
        this.type=type;
    }
}
