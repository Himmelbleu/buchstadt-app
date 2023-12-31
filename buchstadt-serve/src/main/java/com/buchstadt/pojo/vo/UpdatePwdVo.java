package com.buchstadt.pojo.vo;

import com.buchstadt.constant.ValidRegexVals;
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
public class UpdatePwdVo {

    @Pattern(regexp = ValidRegexVals.PWD, message = "原始密码格式错误！")
    private String oldPasswd;

    @Pattern(regexp = ValidRegexVals.PWD, message = "新密码格式错误！")
    private String newPasswd;

}
