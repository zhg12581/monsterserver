package com.example.service;

import com.example.dto.backend.question.AdminDTO;
import com.example.entity.AdminDO;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/14 下午10:23
 */
public interface AdminService {

    /**
     * 根据传入的管理员对象下对管理员信息进行验证，并返回验证结果
     *
     * @param adminDO 所需验证的对象
     * @return boolean
     */
    boolean verification(AdminDO adminDO);

    /**
     * 根据DTO管理员对象转换为DO对象
     *
     * @param adminDTO 所需转换的DTO对象
     * @return org.gzmtu.monster.dto.backend.AdminDTO
     */
    AdminDO transformDTO(AdminDTO adminDTO);

    /**
     * 根据管理员账号吗向数据库中查询并生成对象返回
     *
     * @param userName 所需查询的管理员账号名
     * @return org.gzmtu.monster.entity.AdminDO
     */
    AdminDO getAdminByUserName(String userName);

    /**
     * 插入或更新新管理员记录
     *
     * @param adminDO 所需插入或更新的管理员对象
     * @return boolean
     */
    boolean saveOrUpdate(AdminDO adminDO);

}
