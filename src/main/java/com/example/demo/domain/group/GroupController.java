package com.example.demo.domain.group;

import com.example.demo.domain.group.dto.GroupDTO;
import com.example.demo.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @GetMapping()
    @Operation(description = "Retrieve a list of all groups in the system.", summary = "Get all Groups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{groupId}")
    @Operation(description = "Fetch details of a specific group by its unique identifier.", summary = "Get a Group by its Id")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable("groupId") UUID groupId) {
        Optional<GroupDTO> group = groupService.getGroupById(groupId);
        return group.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('GROUP_CREATE')")
    @Operation(description = "Create a new group with the provided details.", summary = "Create a new Group")
    public ResponseEntity<GroupDTO> addGroup(@Valid @RequestBody GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.addGroup(groupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @PutMapping("/{groupId}")
    @PreAuthorize("hasAuthority('GROUP_MODIFY')")
    @Operation(description = "Update the details of an existing group identified by its unique ID.", summary = "Update an existing Group")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable("groupId") UUID groupId, @Valid @RequestBody GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.updateGroup(groupId, groupDTO);
        if (updatedGroup != null) {
            return ResponseEntity.ok(updatedGroup);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAuthority('GROUP_DELETE')")
    @Operation(description = "Remove a group from the system using its unique identifier.", summary = "Delete an existing Group")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{groupId}/members")
    @PreAuthorize("@groupSecurityService.isMemberOrAdmin(authentication, #groupId)")
    @Operation(description = "Retrieve a paginated list of all members belonging to a specific group.", summary = "Get all members of a Group")
    public ResponseEntity<Page<User>> getGroupMembers(@PathVariable("groupId") UUID groupId, Pageable pageable) {
        Page<User> members = groupService.getGroupMembers(groupId, pageable);
        return ResponseEntity.ok(members);
    }

    @PutMapping("/{groupId}/members")
    @PreAuthorize("hasAuthority('USER_MODIFY')")
    public ResponseEntity<GroupDTO> addUserToGroup(@PathVariable UUID groupId, @RequestBody Map<String, String> userIdMap) {
        String userId = userIdMap.get("userId");
        GroupDTO updatedGroup = groupService.addUserToGroup(groupId, userId);
        return ResponseEntity.ok(updatedGroup);
    }

}
