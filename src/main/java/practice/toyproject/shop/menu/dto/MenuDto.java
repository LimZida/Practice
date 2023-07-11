package practice.toyproject.shop.menu.dto;

import lombok.*;
/**
 * title : MenuDto
 *
 * description : 메뉴 디렉토리 json request 매핑용 dto
 *
 * reference :
 *
 * author : 임현영
 *
 * date : 2023.07.11
 **/
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 생성자 생성
public class MenuDto {
    private Long dirDepth;
    private String dirCdUpper;
    private String dirCdLower;
    private String dirCdName;
    private String dirUseYn;
    @Builder
    private MenuDto(Long dirDepth,String dirCdUpper,String dirCdLower, String dirCdName,String dirUseYn){
        this.dirDepth=dirDepth;
        this.dirCdUpper = dirCdUpper;
        this.dirCdLower=dirCdLower;
        this.dirCdName=dirCdName;
        this.dirUseYn=dirUseYn;
    }
}
