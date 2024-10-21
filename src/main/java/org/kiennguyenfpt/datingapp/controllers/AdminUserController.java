package org.kiennguyenfpt.datingapp.controllers;

import org.kiennguyenfpt.datingapp.dtos.mapper.UserMapper;
import org.kiennguyenfpt.datingapp.dtos.responses.AdminUserResponse;
import org.kiennguyenfpt.datingapp.entities.User;
import org.kiennguyenfpt.datingapp.entities.UserRole;
import org.kiennguyenfpt.datingapp.responses.CommonResponse;
import org.kiennguyenfpt.datingapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/user")
@CrossOrigin
public class AdminUserController {
    private final UserService userService;

    private final UserMapper userMapper;

    public AdminUserController(
            UserService userService,
            UserMapper userMapper
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // 1. Xem danh sách người dùng
    @GetMapping
    public ResponseEntity searchUsers(@RequestParam(value = "keyword", required = false) String keyword) {
        CommonResponse response = new CommonResponse<>();
        try {
            List<User> users = userService.searchUsers(keyword);
            List<AdminUserResponse> userResponse = users.stream().map(this::userToAdminUserResponse).toList();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Get list user successfully!");
            response.setData(userResponse);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 2. Khóa tài khoản người dùng
    @PutMapping("/{id}/lockOrUnLock")
    public ResponseEntity lockOrUnLockUser(@PathVariable Long id) {
        CommonResponse response = new CommonResponse<>();
        try {
            int result = userService.lockOrUnLockUser(id);
            if (result == 1) {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Update user successfully");
                User user = userService.findById(id);
                response.setData(userMapper.userToUserResponse(user));
                return ResponseEntity.ok(response);
            } else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setMessage("Failed to update user status");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private AdminUserResponse userToAdminUserResponse(User user) {
        AdminUserResponse adminUserResponse = new AdminUserResponse();
        adminUserResponse.setUserId(user.getUserId());
        adminUserResponse.setEmail(user.getEmail());
        adminUserResponse.setPhone(user.getPhone());
        adminUserResponse.setCreatedAt(user.getCreatedAt());
        if (user.getStatus() != null) {
            adminUserResponse.setStatus(user.getStatus().name());
        }
        List<UserRole> list = user.getUserRoles();
        if (list != null) {
            adminUserResponse.setRole(list.get(0).getRole());
        }

        return adminUserResponse;
    }

}
