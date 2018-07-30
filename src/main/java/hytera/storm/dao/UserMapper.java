package hytera.storm.dao;


import hytera.storm.model.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    //@Select("SELECT * FROM t_user WHERE user_id = #{userId}")
    User selectByPrimaryKey(Integer userId);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}