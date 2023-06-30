package practice.toyproject.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.toyproject.token.entity.Token;
import java.util.Optional;

/**
 * title : TokenRepository
 * description : save(token) => 토큰 저장 및 업데이트 (pk 존재하면 해당 pk에 해당하는 데이터를 update해줌)
 *               findTokenByUserId(userId) => ID를 통해 토큰 조회
 *
 * reference : 쿼리 직접 사용시 : https://sundries-in-myidea.tistory.com/91
 *             JPA 메서드 명령규칙 : https://zara49.tistory.com/130
 *                              https://ozofweird.tistory.com/entry/%EC%82%BD%EC%A7%88-%ED%94%BC%ED%95%98%EA%B8%B0-JpaRepository-%EA%B7%9C%EC%B9%99%EC%97%90-%EB%A7%9E%EB%8A%94-%EB%A9%94%EC%84%9C%EB%93%9C
 *
 *             @Modifying 어노테이션 사용: UPDATE 쿼리와 같은 DML 작업을 수행할 때는 해당 쿼리 메서드에 @Modifying 어노테이션을 추가해야 합니다.
 *                        이를 통해 스프링 데이터 JPA가 쿼리를 수정 작업으로 인식하고 실행할 수 있습니다.
 *                        + Modifying queries can only use void or int/Integer as return type.
 *                        ex) @Modifying
 *                            @Query("UPDATE T_TOKEN t SET t.accessJwt = ?2 WHERE t.userId = ?1")
 *                            void updateAccessJwtByUserIdAndAccessJwt(String userId, String accessJwt);
 *
 *             Optional : https://mangkyu.tistory.com/70
 *  *             메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 좋다.
 *  *             즉, Optional은 메소드의 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.
 * author : 임현영
 * date : 2023.06.30
 **/
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Optional<Token> findTokenByUserId(String userId);
}
