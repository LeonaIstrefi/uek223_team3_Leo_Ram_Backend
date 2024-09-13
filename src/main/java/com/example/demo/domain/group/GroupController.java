package com.example.demo.domain.group;

import com.example.demo.domain.group.dto.GroupDTO;
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
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @GetMapping()
    @Operation(description = "Get all Groups", summary = "Get all Groups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{groupId}")
    @Operation(description = "Get a Group by its Id", summary = "Get a Group by its Id")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable("groupId") Integer groupId) {
        Optional<GroupDTO> group = groupService.getGroupById(groupId);
        return group.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('CREATE')")
    @Operation(description = "Create a new Group", summary = "Create a new Group")
    public ResponseEntity<GroupDTO> addGroup(@Valid @RequestBody GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.addGroup(groupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @PutMapping("/{groupId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @Operation(description = "Update an existing Group", summary = "Update an existing Group")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable("groupId") Integer groupId, @Valid @RequestBody GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.updateGroup(groupId, groupDTO);
        if (updatedGroup != null) {
            return ResponseEntity.ok(updatedGroup);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAuthority('DELETE')")
    @Operation(description = "Delete an existing Group", summary = "Delete an existing Group")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/paginated")
    @Operation(description = "Get paginated list of Groups", summary = "Get paginated list of Groups")
    public ResponseEntity<Page<GroupDTO>> getGroupsPaginated(Pageable pageable) {
        Page<GroupDTO> groupPage = groupService.getGroupsPaginated(pageable);
        return ResponseEntity.ok(groupPage);
    }


}
