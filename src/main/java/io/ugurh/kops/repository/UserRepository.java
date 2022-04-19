package io.ugurh.kops.repository;

import io.ugurh.kops.entity.Group;
import io.ugurh.kops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByGroups(Group groups);
}