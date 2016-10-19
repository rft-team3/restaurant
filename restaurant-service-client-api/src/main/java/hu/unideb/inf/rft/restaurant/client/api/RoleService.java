package hu.unideb.inf.rft.restaurant.client.api;

import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;

public interface RoleService {

    RoleVo saveRole(RoleVo roleVo);

    RoleVo getRoleById(Long id);

    RoleVo getRoleByName(String name);

}
