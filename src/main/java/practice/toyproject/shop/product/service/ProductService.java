package practice.toyproject.shop.product.service;

import practice.toyproject.shop.product.dto.ProductDto;
import practice.toyproject.shop.product.entity.Product;

import java.util.List;

/**
 * title : S3Service
 *
 * description : S3Uploader와 의존된 S3Service 인터페이스
 *
 * reference :
 *
 * author : 임현영
 * date : 2023.07.07
 **/
public interface ProductService {
    Product saveProductService(ProductDto productDto);
    Product selectSeqProductService(ProductDto productDto); // 매개변수 product로 해야하는지?
    List<Product> selectTypeProductService(ProductDto productDto);
    List<Product> selectAllService();
}
