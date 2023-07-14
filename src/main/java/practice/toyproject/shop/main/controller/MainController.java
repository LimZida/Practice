package practice.toyproject.shop.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.toyproject.shop.src.dto.SrcDto;
import practice.toyproject.shop.src.service.S3SrcService;

import javax.servlet.http.HttpServletResponse;

/**
 * title : MainController
 * description : uploadSrc(UploadSrcDto uploadSrcDto) => 로컬에서 s3로 업로드 (1장씩) 기능
 *               getSrc(GetSrcDto getSrcDto, HttpServletResponse response) => s3에서 로컬로 업로드 (1장씩) 기능
 *
 * reference : RESTful 설계 규칙 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *             @RequestBody란? : https://dev-coco.tistory.com/95 , https://cheershennah.tistory.com/179
 *             @RequestBody, @ModelAttribute, @RequestParam의 차이 : https://mangkyu.tistory.com/72
 *
 * author : 임현영
 * date : 2023.07.04
 **/
@RestController
@RequestMapping("/shop")
public class MainController {
    private final S3SrcService s3SrcService;
    @Autowired
    public MainController(S3SrcService s3SrcService) {
        this.s3SrcService = s3SrcService;
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // form-data 형식
    public ResponseEntity<String> uploadSrc(@ModelAttribute SrcDto.uploadInfo uploadInfo) {
        try {
            String uploadResult = s3SrcService.uploadSrcService(uploadInfo);
            return ResponseEntity.ok(uploadResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/download")
    public ResponseEntity<String> getSrc(@RequestBody SrcDto.downloadInfo downloadInfo, HttpServletResponse response){
        try {
            s3SrcService.getSrcService(downloadInfo,response);
            return ResponseEntity.ok("good");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
