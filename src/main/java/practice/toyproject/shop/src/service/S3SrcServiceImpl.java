package practice.toyproject.shop.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.shop.src.dto.SrcDto;
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
public class S3SrcServiceImpl implements S3SrcService {
    private final S3Uploader s3Uploader;
    @Autowired
    public S3SrcServiceImpl(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @Override
    public String uploadSrcService(SrcDto.uploadInfo uploadInfo) {
        try{
            String uploadResult = s3Uploader.uploadFiles(uploadInfo.getImage(), "image/" + uploadInfo.getType());
            return uploadResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getSrcService(SrcDto.downloadInfo downloadInfo, HttpServletResponse response) {
        try{
            s3Uploader.getFileInResponse(downloadInfo.getDirName(),response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
