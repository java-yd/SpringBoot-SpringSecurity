package com.yd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.entity.RoleResource;
import com.yd.entity.vo.ResourceVO;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);

    /**
     * 根据权限名称，得到具有权限的url
     * @param roleList
     * @return
     */
	List<ResourceVO> selectRoleUrlByRoleNames(@Param("roleList")List<String> roleList);
}