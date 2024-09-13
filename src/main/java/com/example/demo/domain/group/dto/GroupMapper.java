package com.example.demo.domain.group.dto;


import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.group.Group;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper extends AbstractMapper<Group, GroupDTO> {
    default  Page<GroupDTO> toDTOs(Page<Group> groups){
        return groups.map(this::toDTO);
    };

    default  Page<Group> fromDTOs(Page<GroupDTO> groups){
        return groups.map(this::fromDTO);
    };

}
