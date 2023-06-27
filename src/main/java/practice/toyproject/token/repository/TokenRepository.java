package practice.toyproject.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import practice.toyproject.token.entity.Token;
/**
 * title : TokenRepository
 * description : save(token) => 토큰 저장
 *               findTokenByUserId(userId) => ID를 통해 토큰 조회
 *               updateAccessJwtByUserIdAndAccessJwt(userId,accessJwt) => 엑세스 토큰 업데이트
 *               updateRefreshJwtByUserIdAndRefreshJwt(userId,refreshJwt) => 리프레시 토큰 업데이트
 *
 * reference : 쿼리 직접 사용시 : https://sundries-in-myidea.tistory.com/91
 *             JPA 메서드 명령규칙 : https://zara49.tistory.com/130
 *                              https://ozofweird.tistory.com/entry/%EC%82%BD%EC%A7%88-%ED%94%BC%ED%95%98%EA%B8%B0-JpaRepository-%EA%B7%9C%EC%B9%99%EC%97%90-%EB%A7%9E%EB%8A%94-%EB%A9%94%EC%84%9C%EB%93%9C
 *             @Modifying 어노테이션 사용: UPDATE 쿼리와 같은 DML 작업을 수행할 때는 해당 쿼리 메서드에 @Modifying 어노테이션을 추가해야 합니다.
 *                        이를 통해 스프링 데이터 JPA가 쿼리를 수정 작업으로 인식하고 실행할 수 있습니다.
 *                        + Modifying queries can only use void or int/Integer as return type.
 * author : 임현영
 * date : 2023.06.27
 **/
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Token findTokenByUserId(String userId);
    @Modifying
    @Query("UPDATE T_TOKEN t SET t.accessJwt = ?2 WHERE t.userId = ?1")
    void updateAccessJwtByUserIdAndAccessJwt(String userId, String accessJwt);
    @Modifying
    @Query("UPDATE T_TOKEN t SET t.refreshJwt = ?2 WHERE t.userId = ?1")
    void updateRefreshJwtByUserIdAndRefreshJwt(String userId,String refreshJwt);
}
