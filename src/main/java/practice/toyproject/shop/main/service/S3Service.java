package practice.toyproject.shop.main.service;

import practice.toyproject.shop.main.model.GetSrcDto;
import practice.toyproject.shop.main.model.UploadSrcDto;

import javax.servlet.http.HttpServletResponse;
/**
 * title : S3Service
 *
 * description :
 *
 * reference :
 *
 *
 *
 * author : 임현영
 * date : 2023.07.07
 **/
public interface S3Service {
    void uploadSrcService(UploadSrcDto uploadSrcDto);
    void getSrcService(GetSrcDto getSrcDto, HttpServletResponse response);
}
