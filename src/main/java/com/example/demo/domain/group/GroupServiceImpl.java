package com.example.demo.domain.group;

import com.example.demo.domain.group.dto.GroupDTO;
import com.example.demo.domain.group.dto.GroupMapper;
import com.example.demo.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupServiceImpl {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Transactional
    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.toDTOs(groups);
    }

    @Transactional
    public Optional<GroupDTO> getGroupById(UUID groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        return group.map(groupMapper::toDTO);
    }

    @Transactional
    public GroupDTO addGroup(GroupDTO groupDTO) {
        Group group = groupMapper.fromDTO(groupDTO);
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
