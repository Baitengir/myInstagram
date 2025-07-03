package myInstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myInstagram.entities.UserInfo;
import myInstagram.repositories.UserInfoRepo;
import myInstagram.repositories.UserRepo;
import myInstagram.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepo userInfoRepo;

    @Override
    public void update(UserInfo userInfo, Long id) {
        userInfoRepo.update(userInfo, id);
    }
}
