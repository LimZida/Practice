package practice.toyproject.util.AWS;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
/**
 * title : S3Uploader
 * description :
 *
 * reference : S3 연동하기 : https://velog.io/@_koiil/Springboot-AWS-S3%EB%A1%9C-%ED%8C%8C%EC%9D%BC-%EC%A0%80%EC%9E%A5%EC%86%8C-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0
 *             withCannedAcl(CannedAccessControlList.PublicRead) :  S3에 업로드되는 객체에 대해 퍼블릭 읽기 권한을 부여할 수 있습니다. 이렇게 설정된 객체는 인증되지 않은 사용자도 해당 객체를 읽을 수 있게 됩니다.
 *
 * author : 임현영
 * date : 2023.07.04
 **/
@Slf4j
@Component
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    private String bucket;

    @Autowired
    public S3Uploader(AmazonS3Client amazonS3Client,@Value("${cloud.aws.s3.bucket}") String bucket) {
        this.amazonS3Client = amazonS3Client;
        this.bucket=bucket;
    }
    
    // s3로 파일을 업로드하는 기능
    public String uploadFiles(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
        return upload(uploadFile, dirName);
    }
    // url 받아오기
    public String upload(File uploadFile, String filePath) {
        String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }
    // s3에 넣기
    private String putS3(File uploadFile, String fileName) {
//        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("로컬 저장 이미지 삭제 성공");
            return;
        }
        log.info("로컬 저장 이미지 삭제 실패");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/images/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성되어야만 변환 가능 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}