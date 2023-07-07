package practice.toyproject.shop.main.service;

import org.springframework.stereotype.Service;
import practice.toyproject.shop.main.model.GetSrcDto;
import practice.toyproject.shop.main.model.UploadSrcDto;

import javax.servlet.http.HttpServletResponse;
/**
 * title : S3ServiceImpl
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
@Service
public class S3ServiceImpl implements S3Service {
    @Override
    public void uploadSrcService(UploadSrcDto uploadSrcDto) {

    }

    @Override
    public void getSrcService(GetSrcDto getSrcDto, HttpServletResponse response) {

    }
}
