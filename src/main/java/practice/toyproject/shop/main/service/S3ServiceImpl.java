package practice.toyproject.shop.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.shop.main.dto.GetSrcDto;
import practice.toyproject.shop.main.dto.UploadSrcDto;
import practice.toyproject.util.AWS.S3Uploader;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * title : S3ServiceImpl
 *
 * description : S3Uploader와 의존된 S3Service 구현체
 *
 * reference :
 *
 * author : 임현영
 * date : 2023.07.07
 **/
@Service
public class S3ServiceImpl implements S3Service {
    private final S3Uploader s3Uploader;
    @Autowired
    public S3ServiceImpl(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @Override
    public String uploadSrcService(UploadSrcDto uploadSrcDto) {
        try{
            String uploadResult = s3Uploader.uploadFiles(uploadSrcDto.getImage(), "image/" + uploadSrcDto.getType());
            return uploadResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getSrcService(GetSrcDto getSrcDto, HttpServletResponse response) {
        try{
            s3Uploader.getFileInResponse(getSrcDto.getDirName(),response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
