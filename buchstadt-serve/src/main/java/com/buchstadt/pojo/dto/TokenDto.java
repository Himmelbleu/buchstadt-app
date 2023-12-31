package com.buchstadt.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @package: com.buchstadt.pojo.dto
 * @author: zheng
 * @date: 2023/9/3
 */
@Data
@NoArgsConstructor
public class TokenDto {

    private String value;
    private String type;
    private Integer id;
    private String avatar;
    private String username;
    private String authority;

    public TokenDto(String value, String type, Integer id, String avatar, String username) {
        this.value = value;
        this.type = type;
        this.id = id;
        this.avatar = avatar;
        this.username = username;
    }

}
