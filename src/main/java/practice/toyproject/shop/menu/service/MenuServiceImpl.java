package practice.toyproject.shop.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.shop.menu.dto.MenuDto;
import practice.toyproject.shop.menu.entity.Menu;
import practice.toyproject.shop.menu.repository.MenuRepository;

import java.util.List;
/**
 * title : MenuServiceImpl
 *
 * description : menuRepository와 의존된 MenuService 구현체
 *
 * reference :
 *
 * author : 임현영
 * date : 2023.07.11
 **/
@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> selectMenuDepthService(MenuDto menuDto) {
        return menuRepository.findMenuByDirDepth(menuDto.getDirDepth());
    }

    @Override
    public List<Menu> selectMenuCodeService(MenuDto menuDto) {
        return menuRepository.findMenuByDirCdUpper(menuDto.getDirCd());
    }
}
