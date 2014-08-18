package xanderpost.repository;

import xanderpost.entity.readonly.UserInfo;

public interface UserInfoDaoInterface {
    public UserInfo find(long id);
}
