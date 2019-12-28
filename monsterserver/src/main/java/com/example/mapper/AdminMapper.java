package com.example.mapper;

import com.example.entity.AdminDO;
import com.example.entity.RoleInfoDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

/**
 * 管理员Mapper接口
 * @author zhu
 * @version 1.0
 * @date 2019/12/14 下午10:27
 */
@Repository
public interface AdminMapper {
    /**
     * 根据账号从数据库中获取管理员信息信息并以对象的形式返回
     *
     * @param userName 管理员账号
     * @return org.gzmtu.monster.entity.AdminDO
     */
    AdminDO getAdminByUserName(@Param("userName") String userName);

    /**
     * 插入管理员信息
     *
     * @param adminDO 所需插入的管理员对象
     * @return int
     */
    int insert(AdminDO adminDO);

    /**
     * 更新管理员信息
     *
     * @param adminDO 所需更新的管理员对象
     * @return int
     */
    int update(AdminDO adminDO);

    /**
     * 根据ID从数据库中获取管理员信息信息并以对象的形式返回
     *
     * @param id 管理员ID
     * @return org.gzmtu.monster.entity.AdminDO
     */
    AdminDO getAdmin(@Param("id") Integer id);

}
