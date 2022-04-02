package ec2Demo.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select * from Role")
    List<Role> getRole();

    @Insert("insert into Role(name) values(#{name})")
    void insertRDS(Role role);
}
