package practice.toyproject.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.toyproject.shop.product.entity.Product;

import java.util.List;

/**
 * title : ProductRepository
 *
 * description : save(Product product) => 상품 저장
 *               findProductByProductSeq(Long seq) => SEQ를 통해 샹품 조회
 *               findProductByProductType(String ProductType) => 상품별 목록조회
 *               findAll() => 모든 상품 조회
 *
 * reference : 쿼리 직접 사용시 https://sundries-in-myidea.tistory.com/91
 *             JPA 메서드 명령규칙 https://zara49.tistory.com/130
 *                              https://ozofweird.tistory.com/entry/%EC%82%BD%EC%A7%88-%ED%94%BC%ED%95%98%EA%B8%B0-JpaRepository-%EA%B7%9C%EC%B9%99%EC%97%90-%EB%A7%9E%EB%8A%94-%EB%A9%94%EC%84%9C%EB%93%9C
 *
 * author : 임현영
 * date : 2023.07.13
 **/
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    Product findProductByProductSeq(Long productSeq); // 매개변수 product로 해야하는지?
    List<Product> findProductByProductType(String ProductType);
    List<Product> findAll();

}
