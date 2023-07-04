package practice.toyproject.shop.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.toyproject.util.AWS.S3Uploader;

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


    @RequestMapping(value = "/src",method = RequestMethod.POST)
    public ResponseEntity<String> updateUserImage(@RequestParam("images") MultipartFile multipartFile, @RequestParam("type") String type) {
        try {
            s3Uploader.uploadFiles(multipartFile, "images/"+type);
            return ResponseEntity.ok("good");
        } catch (Exception e) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }
    }
}
