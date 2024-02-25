package de.aittr.g_27_security.repositories;

import de.aittr.g_27_security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
  Role findByName(String name);

}
