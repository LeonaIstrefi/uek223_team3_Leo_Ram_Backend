package com.example.demo.domain.group.dto;

import com.example.demo.core.generic.AbstractDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
public class GroupDTO extends AbstractDTO {


    @NotNull
    @Max(20)
    private String groupMotto;

    @NotNull
    @Size(min = 3, max = 20)
    private String groupName;

    @NotNull
    private String groupLogoUrl;

    private String memberName;


    public GroupDTO(UUID id, String groupMotto, String groupName, String groupLogoUrl, String memberName) {
        super(id);
        this.groupMotto = groupMotto;
        this.groupName = groupName;
        this.groupLogoUrl = groupLogoUrl;
        this.memberName = memberName;
    }
}
