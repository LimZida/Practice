package practice.toyproject.shop.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.toyproject.shop.main.model.SrcDto;
import practice.toyproject.util.AWS.S3Uploader;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.List;

/**
 * title : MainController
 * description :
 *
 * reference : RESTful 설계 규칙 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *             @RequestBody란? : https://dev-coco.tistory.com/95 , https://cheershennah.tistory.com/179
 *             Jackson library object mapper ,JSON Serialize 알아보기
 *
 *
 * author : 임현영
 * date : 2023.07.04
 **/
@RestController
@RequestMapping("/shop")
public class MainController {
    private final S3Uploader s3Uploader;

    @Autowired
    public MainController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    //로컬에서 s3로 업로드
//    @RequestMapping(value = "/src",method = RequestMethod.POST)
    @PostMapping("/src")
    public ResponseEntity<String> uploadSrc(@RequestBody SrcDto srcDto) {
        try {
            String uploadResult = s3Uploader.uploadFiles(srcDto.getMultipartFile(), "image/" + srcDto.getType());
            return ResponseEntity.ok(uploadResult);
        } catch (Exception e) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }
    }

    //s3에서 로컬로 업로드
    @GetMapping ("/src")
    public void getSrc(HttpServletResponse response){
        try {
            s3Uploader.getFileList(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
