package com.example.demo.domain.group;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @GetMapping()
    @Operation(description = "Get all Groups", summary = "Get all Groups")
    public ResponseEntity<List<Group>> getAllGroups(){
        return ResponseEntity.ok().body(groupService.getAllGroups());
    }

    @GetMapping("/{groupId}")
    @Operation(description = "Get a Group by it's Id", summary = "Get a Group by it's Id")
    public ResponseEntity<Group> getGroupById(@PathVariable("groupId")Integer groupId){
        return ResponseEntity.ok().body(groupService.getGroupById(groupId));
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('CREATE')")
    @Operation(description = "Create a new Group", summary = "Create a new Group")
    public ResponseEntity<Group> addGroup(@Valid @RequestBody Group group){
        Group createdGroup = groupService.addGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @PutMapping("/{groupId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @Operation(description = "Update an existig Group", summary = "Update an Existing Group")
    public ResponseEntity<Group> updateGroup(@PathVariable ("groupId")Integer groupId, @Valid @RequestBody Group group){
        return ResponseEntity.ok().body(groupService.updateGroup(groupId, group));
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAuthority('DELETE')")
    @Operation(description = "Delete an existing Group", summary = "Delete an existing Group")
    public ResponseEntity<String> deleteGroup(@PathVariable Integer groupId){
        return ResponseEntity.ok().body(groupService.deleteGroup(groupId));
    }
}
