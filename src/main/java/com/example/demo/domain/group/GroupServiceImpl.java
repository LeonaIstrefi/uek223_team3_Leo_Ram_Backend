package com.example.demo.domain.group;

import com.example.demo.core.generic.AbstractServiceImpl;
import com.example.demo.domain.group.dto.GroupDTO;
import com.example.demo.domain.group.dto.GroupMapper;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl extends AbstractServiceImpl<Group> implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserRepository userRepository;

    public GroupServiceImpl(GroupRepository repository) {
        super(repository);
    }

    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.toDTOs(groups);
    }

    public Optional<GroupDTO> getGroupById(UUID groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        return group.map(groupMapper::toDTO);
    }

    @Transactional
    public GroupDTO addGroup(GroupDTO groupDTO) {
        // If memberEmails is empty, this will return an empty list of users
        List<User> users = groupDTO.getMemberEmails().stream()
            .map(email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email)))
            .collect(Collectors.toList());

        Group group = groupMapper.fromDTO(groupDTO, users);

        group = groupRepository.save(group);

        return groupMapper.toDTO(group);
    }

    @Transactional
    public GroupDTO updateGroup(UUID groupId, GroupDTO groupDTO) {
        if (groupRepository.existsById(groupId)) {
            Group group = groupMapper.fromDTO(groupDTO);
            group = groupRepository.save(group);
            return groupMapper.toDTO(group);
        }
        return null;
    }

    @Transactional
    public void deleteGroup(UUID groupId) {
        groupRepository.deleteById(groupId);
    }

    @Transactional
    public Page<User> getGroupMembers(UUID groupId, Pageable pageable) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            List<User> members = group.get().getMembers();
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;

            List<User> paginatedMembers;
            if (members.size() < startItem) {
                paginatedMembers = List.of();
            } else {
                int toIndex = Math.min(startItem + pageSize, members.size());
                paginatedMembers = members.subList(startItem, toIndex);
            }
            return new PageImpl<>(paginatedMembers, pageable, members.size());
        }
        return Page.empty(pageable);
    }
}
