package practice.toyproject.shop.menu.dto;

import lombok.*;
/**
 * title : MenuDto
 *
 * description : 메뉴 json request 매핑용 dto
 *
 * reference :   dto 깔끔히 관리하기 : https://velog.io/@p4rksh/Spring-Boot%EC%97%90%EC%84%9C-%EA%B9%94%EB%81%94%ED%95%98%EA%B2%8C-DTO-%EA%B4%80%EB%A6%AC%ED%95%98%EA%B8%B0
 *                                  https://velog.io/@aidenshin/DTO%EC%97%90-%EA%B4%80%ED%95%9C-%EA%B3%A0%EC%B0%B0
 *
 * author : 임현영
 *
 * date : 2023.07.11
 **/
public class MenuDto {
    //메뉴 목록 등록 dto
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 생성자 생성
    public static class saveInfo {
        private Long dirDepth;
        private String dirCdUpper;
        private String dirCdLower;
        private String dirCdName;
        private String dirUseYn;
        @Builder
        private saveInfo(Long dirDepth, String dirCdUpper, String dirCdLower, String dirCdName, String dirUseYn){
            this.dirDepth=dirDepth;
            this.dirCdUpper = dirCdUpper;
            this.dirCdLower=dirCdLower;
            this.dirCdName=dirCdName;
            this.dirUseYn=dirUseYn;
        }
    }
    //메뉴 뎁스 조회 dto
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 생성자 생성
    public static class depthInfo {
        private Long dirDepth;
        @Builder
        private depthInfo(Long dirDepth){
            this.dirDepth=dirDepth;
        }
    }
    //메뉴 코드 조회 dto
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 생성자 생성
    public static class codeInfo {
        private String dirCdUpper;
        @Builder
        private codeInfo(String dirCdUpper){
            this.dirCdUpper=dirCdUpper;
        }
    }
}
