package myInstagram.repositories.repositoriesImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.UserInfo;
import myInstagram.repositories.UserInfoRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserInfoRepoImpl implements UserInfoRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void update(UserInfo userInfo, Long id) {
        try {
            UserInfo userInfoInDb = entityManager.createQuery("select u from UserInfo u where u.user.id = :id", UserInfo.class)
                    .setParameter("id", id)
                    .getSingleResult();
            userInfoInDb.setFullName(userInfo.getFullName());
            userInfoInDb.setBiography(userInfo.getBiography());
            userInfoInDb.setGender(userInfo.getGender());
            userInfoInDb.setImage(userInfo.getImage());
            entityManager.merge(userInfoInDb);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
