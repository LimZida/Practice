package practice.toyproject.shop.menu.service;

import practice.toyproject.shop.menu.dto.MenuDto;
import practice.toyproject.shop.menu.entity.Menu;

import java.util.List;
/**
 * title : MenuService
 *
 * description : menuRepository 매핑용 Service 인터페이스
 *
 * reference :
 *
 *
 * author : 임현영
 * date : 2023.07.11
 **/
public interface MenuService {
    List<Menu> selectMenuDepthService(MenuDto.listInfo listInfo);
    List<Menu> selectMenuCodeService(MenuDto.listInfo listInfo);
    Menu saveMenuService(MenuDto.listInfo listInfo);
}
