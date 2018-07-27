package hytera.storm.service;


import hytera.storm.model.User;

public interface UserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
