package practice.toyproject.shop.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.shop.product.dto.ProductDto;
import practice.toyproject.shop.product.entity.Product;
import practice.toyproject.shop.product.repository.ProductRepository;

import java.util.List;
/**
 * title : ProductServiceImpl
 *
 * description : productRepository와 의존된 ProductService 구현체
 *
 * reference :
 *
 * author : 임현영
 * date : 2023.07.14
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProductService(ProductDto.saveInfo saveInfo) {
        Product product=Product.builder()
                .productDescription(saveInfo.getProductDescription())
                .productType(saveInfo.getProductType())
                .productUseYn(saveInfo.getProductUseYn())
                .productPrice(saveInfo.getProductPrice())
                .productName(saveInfo.getProductName())
                .productImgUrl(saveInfo.getProductImgUrl())
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product selectSeqProductService(ProductDto.seqInfo seqInfo) {
        return  productRepository.findProductByProductSeq(seqInfo.getProductSeq());
    }

    @Override
    public List<Product> selectTypeProductService(ProductDto.typeInfo typeInfo) {
        return productRepository.findProductByProductType(typeInfo.getProductType());
    }

    @Override
    public List<Product> selectAllService() {
        return productRepository.findAll();
    }
}
