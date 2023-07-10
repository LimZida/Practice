package practice.toyproject.shop.main.service;

import practice.toyproject.shop.main.dto.GetSrcDto;
import practice.toyproject.shop.main.dto.UploadSrcDto;

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
public interface S3Service {
    String uploadSrcService(UploadSrcDto uploadSrcDto);
    void getSrcService(GetSrcDto getSrcDto, HttpServletResponse response);
}
