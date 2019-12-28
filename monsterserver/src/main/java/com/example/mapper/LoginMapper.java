package com.example.mapper;

import com.example.entity.RoleInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/23 下午10:40
 */
@Repository
public interface LoginMapper {
    List<RoleInfoDO> getUserRolesByUid(@Param("id") Integer id);
}
