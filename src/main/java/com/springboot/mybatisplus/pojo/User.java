package com.springboot.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    //乐观锁注解
    @Version
    private Integer version;

    //逻辑删除注解
    @TableLogic
    private Integer deleted;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
