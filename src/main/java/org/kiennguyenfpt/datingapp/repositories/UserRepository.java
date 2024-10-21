package org.kiennguyenfpt.datingapp.repositories;

import jakarta.transaction.Transactional;
import org.kiennguyenfpt.datingapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    // Tìm kiếm người dùng theo email hoặc name chứa từ khóa
    @Query("SELECT u FROM User u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "WHERE r.roleName = 'User' " +
            "AND (:keyword IS NULL OR :keyword = '' OR u.email LIKE %:keyword% OR u.phone LIKE %:keyword%)")
    List<User> searchUsersByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Query("UPDATE User u SET u.status = CASE WHEN u.status = 'ACTIVE' THEN 'INACTIVE' ELSE 'ACTIVE' END WHERE u.userId = :id")
    int lockOrUnLockUser(@Param("id") Long id);
}
