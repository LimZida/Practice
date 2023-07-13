package practice.toyproject.util.AWS;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * title : S3Uploader
 * description :
 *
 * reference : S3 연동하기 : https://velog.io/@_koiil/Springboot-AWS-S3%EB%A1%9C-%ED%8C%8C%EC%9D%BC-%EC%A0%80%EC%9E%A5%EC%86%8C-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0
 *             withCannedAcl(CannedAccessControlList.PublicRead) :  S3에 업로드되는 객체에 대해 퍼블릭 읽기 권한을 부여할 수 있습니다. 이렇게 설정된 객체는 인증되지 않은 사용자도 해당 객체를 읽을 수 있게 됩니다.
 *             S3에서 로컬로 가져오는법 : https://velog.io/@galmegi/Spring-Amazon-S3-%EC%97%B0%EB%8F%99
 * author : 임현영
 * date : 2023.07.04
 **/
@Slf4j
@Component
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    private String bucket;

    @Autowired
    public S3Uploader(AmazonS3Client amazonS3Client,@Value("${aws.s3.bucket}") String bucket) {
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
    private String upload(File uploadFile, String filePath) {
        String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }
    // s3에 넣기
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile));//withCannedAcl(CannedAccessControlList.PublicRead)
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
        File convertFile = new File(System.getProperty("user.dir") + "/image/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성되어야만 변환 가능 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    // S3에서 사용자에게 이미지 리스트 응답
    public void getFileList(HttpServletResponse response)throws IOException {
        try{
            ListObjectsV2Result result = amazonS3Client.listObjectsV2(bucket,"image/clothes/temp.jpg");
            List<S3ObjectSummary> objects = result.getObjectSummaries();

            for (int i = 1; i < objects.size(); i++) {
                S3ObjectSummary objectSummary = objects.get(i);
                String fileName = objectSummary.getKey();

                //사진 응답
                getFileInResponse(fileName,response);
            }

        } catch (AmazonServiceException e) {
            System.out.println(e);
            // Amazon S3 서비스 예외 처리
            // 예외 처리 로직 추가
        }
    }
    
    //s3에서 로컬(현재 플젝 root 디렉토리)에 저장하기
    public void getFileInLocal(String fileName) throws IOException {
        S3ObjectInputStream file=null;
        FileOutputStream fos=null;
        try {
            //S3로부터 파일 가져오기
            S3Object object = amazonS3Client.getObject(bucket, fileName);

            //가져온 객체로부터 파일 내용 꺼내기
            file = object.getObjectContent();

            //서버에 File Stream 생성
            fos = new FileOutputStream(new File(fileName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            System.out.println(file.read(read_buf));
            //파일 저장
            while ((read_len = file.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
            if (file != null) {
                file.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
    //s3에서 사용자에게 응답하기
    public void getFileInResponse(String dirName, HttpServletResponse response) throws IOException {
            S3ObjectInputStream inputStream = null;

        try {
            // S3로부터 이미지 파일 가져오기
            S3Object object = amazonS3Client.getObject(bucket, dirName);
            inputStream = object.getObjectContent();
            // 이미지 파일의 MIME 타입 설정
            String contentType = object.getObjectMetadata().getContentType();
            response.setContentType(contentType);

            // 이미지 파일 스트림을 응답으로 전송
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();

        } catch (AmazonServiceException e) {
            // Amazon S3 서비스 예외 처리
            // 예외 처리 로직 추가
            System.out.println(e);
        } finally {
            inputStream.close();
            response.getOutputStream().close();
        }
    }


}