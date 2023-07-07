package practice.toyproject.shop.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.toyproject.shop.main.model.GetSrcDto;
import practice.toyproject.shop.main.model.UploadSrcDto;
import practice.toyproject.shop.main.service.S3Service;

import javax.servlet.http.HttpServletResponse;

/**
 * title : MainController
 * description :
 *
 * reference : RESTful 설계 규칙 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *             @RequestBody란? : https://dev-coco.tistory.com/95 , https://cheershennah.tistory.com/179
 *             @RequestBody, @ModelAttribute, @RequestParam의 차이 : https://mangkyu.tistory.com/72
 *             form-data 형식 요청은 보통 파일 업로드나 파일 데이터 전송에 사용된다.
 *
 * author : 임현영
 * date : 2023.07.04
 **/
@RestController
@RequestMapping("/shop")
public class MainController {
    private final S3Service s3Service;
    @Autowired
    public MainController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    //로컬에서 s3로 업로드 (1장씩)
    @PostMapping(value="/src", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadSrc(@ModelAttribute UploadSrcDto uploadSrcDto) {
        try {
            String uploadResult = s3Service.uploadSrcService(uploadSrcDto);
            return ResponseEntity.ok(uploadResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST); }
    }

    //s3에서 로컬로 업로드 (1장씩)
    @GetMapping ("/src")
    public ResponseEntity<String> getSrc(@RequestBody GetSrcDto getSrcDto, HttpServletResponse response){
        try {
            s3Service.getSrcService(getSrcDto,response);
            return ResponseEntity.ok("good");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
