package com.example.demo.domain.group.dto;

import com.example.demo.core.generic.AbstractDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class GroupDTO extends AbstractDTO {

    @NotNull
    @Size(max = 100)
    private String groupMotto;

    @NotNull
    @Size(min = 3, max = 20)
    private String groupName;

    @NotNull
    private String groupLogoUrl;

    private List<String> memberEmails;

    public GroupDTO(UUID id, String groupMotto, String groupName, String groupLogoUrl, List<String> memberEmails) {
        super(id);
        this.groupMotto = groupMotto;
        this.groupName = groupName;
        this.groupLogoUrl = groupLogoUrl;
        this.memberEmails = memberEmails;
    }
}
