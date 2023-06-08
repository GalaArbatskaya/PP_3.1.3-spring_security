package com.galinaarbatskaya.PP_313.spring_security.repositories;

import com.galinaarbatskaya.PP_313.spring_security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Long> {
}
