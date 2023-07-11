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
    private String dirCd;
    @Builder
    private MenuDto(Long dirDepth,String dirCd){
        this.dirDepth=dirDepth;
        this.dirCd=dirCd;
    }
}
