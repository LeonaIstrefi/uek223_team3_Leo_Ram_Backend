package com.example.demo.core.security;

import com.example.demo.domain.group.Group;
import com.example.demo.domain.group.GroupRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GroupSecurityService {

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private UserRepository userRepository;

  // Checks if the authenticated user is a member of the specified group
  public boolean isMember(Authentication authentication, UUID groupId) {
    String currentUserEmail = authentication.getName();
    Optional<User> currentUser = userRepository.findByEmail(currentUserEmail);

    if (currentUser.isEmpty()) {
      return false;
    }

    Optional<Group> group = groupRepository.findById(groupId);
    return group.isPresent() && group.get().getMembers().contains(currentUser.get());
  }

  // Checks if the authenticated user is an admin
  public boolean isAdmin(Authentication authentication) {
    String currentUserEmail = authentication.getName();
    Optional<User> currentUser = userRepository.findByEmail(currentUserEmail);

    return currentUser.map(user -> user.getRoles().stream()
        .anyMatch(role -> role.getName().equals("ADMIN"))).orElse(false);
  }

  // Checks if the user is either a member of the group or an admin
  public boolean isMemberOrAdmin(Authentication authentication, UUID groupId) {
    return isAdmin(authentication) || isMember(authentication, groupId);
  }
}