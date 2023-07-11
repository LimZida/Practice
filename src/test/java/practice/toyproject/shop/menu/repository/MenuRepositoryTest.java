package practice.toyproject.shop.menu.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.toyproject.shop.menu.entity.Menu;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class MenuRepositoryTest {
    private final MenuRepository menuRepository;
    @Autowired
    MenuRepositoryTest(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

//    @BeforeEach
//    void before(){
//        Menu menu= Menu.builder()
//                .dirDepth(1)
//                .dirCdLower("CD0002")
//                .dirCdUpper("CD0001")
//                .dirCdName("상품")
//                .dirUseYn("Y")
//                .build();
//    }


    @Test
    void findMenuByDirDepth() {
        Menu menu= Menu.builder()
                .dirDepth(1)
                .dirCdLower("CD0002")
                .dirCdUpper("CD0001")
                .dirCdName("상품")
                .dirUseYn("Y")
                .build();

        Menu menu2= Menu.builder()
                .dirDepth(1)
                .dirCdLower("CD0002")
                .dirCdUpper("CD0001")
                .dirCdName("상품")
                .dirUseYn("Y")
                .build();

        menuRepository.save(menu);
        menuRepository.save(menu2);

        List<Menu> menuByDirDepth = menuRepository.findMenuByDirDepth(1L);
        System.out.println(menuByDirDepth);

        assertThat(menuByDirDepth.size()).isEqualTo(2);
    }

    @Test
    void findMenuByDirCdUpper() {
        Menu menu= Menu.builder()
                .dirDepth(1)
                .dirCdLower("CD0002001")
                .dirCdUpper("CD0001")
                .dirCdName("상품")
                .dirUseYn("Y")
                .build();

        Menu menu2= Menu.builder()
                .dirDepth(1)
                .dirCdLower("CD0002002")
                .dirCdUpper("CD0001")
                .dirCdName("마이페이지")
                .dirUseYn("Y")
                .build();

        Menu menu3= Menu.builder()
                .dirDepth(2)
                .dirCdLower("CD0003001")
                .dirCdUpper("CD0002001")
                .dirCdName("신발")
                .dirUseYn("Y")
                .build();

        Menu menu4= Menu.builder()
                .dirDepth(2)
                .dirCdLower("CD0003002")
                .dirCdUpper("CD0002001")
                .dirCdName("의류")
                .dirUseYn("Y")
                .build();


        menuRepository.save(menu);
        menuRepository.save(menu2);
        menuRepository.save(menu3);
        menuRepository.save(menu4);

        List<Menu> result = menuRepository.findMenuByDirCdUpper(menu.getDirCdLower());
        System.out.println(result);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void save() {
        Menu menu= Menu.builder()
                .dirDepth(1)
                .dirCdLower("CD0002")
                .dirCdUpper("CD0001")
                .dirCdName("상품")
                .dirUseYn("Y")
                .build();

        Menu result = menuRepository.save(menu);
        System.out.println(result);

        assertThat(result.getDirDepth()).isEqualTo(menu.getDirDepth());
    }

//    @Disabled
    @Test
    void findAll() {
    }
}