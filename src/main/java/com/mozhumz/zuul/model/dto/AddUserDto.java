package com.mozhumz.zuul.model.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

@Data
public class AddUserDto {
    private String username;
    private List<Long> roleIds;
    private String roleIdStr;

    public void setRoleIds(List<Long> roleIds) {
        if(!CollectionUtils.isEmpty(roleIds)){
            Collections.sort(roleIds);
            this.roleIds = roleIds;
//            StringJoiner stringJoiner=new StringJoiner(",");
            roleIdStr=",";
            for(Long id:roleIds){
                roleIdStr+=id+",";
            }
        }
    }

}
