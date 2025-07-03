package myInstagram.service;

import myInstagram.entities.UserInfo;

public interface UserInfoService {
    void update(UserInfo userInfo, Long id);
}
