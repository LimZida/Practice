package practice.toyproject.shop.src.service;

import practice.toyproject.shop.src.dto.SrcDto;

import javax.servlet.http.HttpServletResponse;
/**
 * title : S3Service
 *
 * description : S3Uploader와 의존된 S3Service 인터페이스
 *
 * reference :
 *
 * author : 임현영
 * date : 2023.07.07
 **/
public interface S3SrcService {
    String uploadSrcService(SrcDto.upload upload);
    void getSrcService(SrcDto.download download, HttpServletResponse response);
}
