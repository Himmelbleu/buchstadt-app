package com.buchstadt.pojo.vo;

import com.buchstadt.constant.ValidRegexVals;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @package: com.buchstadt.pojo.vo
 * @author: zheng
 * @date: 2023/9/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpVo {

    @Pattern(regexp = ValidRegexVals.UNAME, message = "用户名格式错误！")
    private String username;

    @Pattern(regexp = ValidRegexVals.PWD, message = "密码格式出错！")
    private String password;

    @Pattern(regexp = ValidRegexVals.PWD, message = "密码格式出错！")
    private String rePasswd;

    @Pattern(regexp = ValidRegexVals.PHONE, message = "手机号格式出错！")
    private String phone;

    @Max(value = 50, message = "个人简介最多 50 个字符！")
    private String profile;

}
