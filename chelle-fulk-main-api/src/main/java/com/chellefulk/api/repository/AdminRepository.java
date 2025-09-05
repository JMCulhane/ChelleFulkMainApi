
package com.chellefulk.api.repository;

import com.chellefulk.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByUsernameAndLockedFalse(String username);
}
