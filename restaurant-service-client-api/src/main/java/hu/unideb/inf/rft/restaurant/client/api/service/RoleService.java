package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;

import java.util.List;

public interface RoleService {

    RoleVo saveRole(RoleVo roleVo);

    RoleVo getRoleById(Long id);

    RoleVo getRoleByName(String name);

    List<RoleVo> getRolesByUserId(Long userId);

}
