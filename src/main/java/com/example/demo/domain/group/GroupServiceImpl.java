package com.example.demo.domain.group;

import com.example.demo.domain.group.dto.GroupDTO;
import com.example.demo.domain.group.dto.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<GroupDTO> getGroupById(Integer groupId) {
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
    public GroupDTO updateGroup(Integer groupId, GroupDTO groupDTO) {
        if (groupRepository.existsById(groupId)) {
            Group group = groupMapper.fromDTO(groupDTO);
            group.setGroup_id(groupId);
            group = groupRepository.save(group);
            return groupMapper.toDTO(group);
        }
        return null;
    }

    @Transactional
    public void deleteGroup(Integer groupId) {
        groupRepository.deleteById(groupId);
    }

    @Transactional
    public Page<GroupDTO> getGroupsPaginated(Pageable pageable) {
        Page<Group> groupPage = groupRepository.findAll(pageable);
        return groupPage.map(groupMapper::toDTO);
    }


}
