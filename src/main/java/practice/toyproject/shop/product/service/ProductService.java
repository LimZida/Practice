package practice.toyproject.shop.product.service;

import practice.toyproject.shop.product.dto.ProductDto;
import practice.toyproject.shop.product.entity.Product;

import java.util.List;

/**
 * title : ProductService
 *
 * description : ProductService 인터페이스
 *
 * reference :
 *
 * author : 임현영
 * date : 2023.07.14
 **/
public interface ProductService {
    Product saveProductService(ProductDto.saveInfo saveInfo);
    Product selectSeqProductService(ProductDto.seqInfo seqInfo);
    List<Product> selectTypeProductService(ProductDto.typeInfo typeInfo);
    List<Product> selectAllService();
}
