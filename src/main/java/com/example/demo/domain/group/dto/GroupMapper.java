package com.example.demo.domain.group.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.group.Group;
import com.example.demo.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper extends AbstractMapper<Group, GroupDTO> {

    default GroupDTO toDTO(Group group) {
        List<String> memberEmails = group.getMembers().stream()
            .map(User::getEmail)
            .collect(Collectors.toList());

        return new GroupDTO(group.getId(), group.getGroupMotto(), group.getGroupName(), group.getGroupLogoUrl(), memberEmails);
    }

    default Group fromDTO(GroupDTO groupDTO) {
        return new Group(groupDTO.getId(), null, groupDTO.getGroupMotto(), groupDTO.getGroupName(), groupDTO.getGroupLogoUrl());
    }
}
