package practice.toyproject.shop.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.toyproject.shop.menu.entity.Menu;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findMenuByDirDepth(Long dirDepth);
    List<Menu> findMenuByDirCdUpper(String dirCdLower);
    Menu save(Menu menu);
    List<Menu> findAll();
}
