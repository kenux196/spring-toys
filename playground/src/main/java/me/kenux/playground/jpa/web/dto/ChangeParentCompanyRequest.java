package me.kenux.playground.jpa.web.dto;

import lombok.Data;

@Data
public class ChangeParentCompanyRequest {

    private Long myCompanyId;

    private Long parentCompanyId;

    private Long userId;
}
