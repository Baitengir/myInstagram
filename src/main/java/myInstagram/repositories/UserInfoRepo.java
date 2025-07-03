package myInstagram.repositories;

import myInstagram.entities.UserInfo;

public interface UserInfoRepo {
    void update(UserInfo userInfo, Long id);
}
