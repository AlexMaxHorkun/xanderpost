package xanderpost.repository;

import org.springframework.stereotype.Repository;
import xanderpost.entity.readonly.PostInfo;

import java.util.List;

@Repository
public interface PostInfoDaoInterface {
    public List<PostInfo> findAll(int limit, int offset);
}
