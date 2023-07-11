package practice.toyproject.shop.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.toyproject.shop.menu.entity.Menu;

import java.util.List;
import java.util.Optional;
/**
 * title : MenuRepository
 * description : findMenuByDirDepth(Long dirDepth) => 메뉴 뎁스에 해당하는 메뉴들 반환
 *               findMenuByDirCdUpper(String dirCdLower) =>
 *
 *
 *
 * reference :
 *
 * author : 임현영
 *
 * date : 2023.07.11
 **/
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findMenuByDirDepth(Long dirDepth);
    List<Menu> findMenuByDirCdUpper(String dirCdLower);
    Menu save(Menu menu);
    List<Menu> findAll();
}
