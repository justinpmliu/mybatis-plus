package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Justin Liu
 * @since 2022-07-22
 */
@Data
//@KeySequence(value = "seq_user_id") //H2
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "id", type = IdType.INPUT) //H2
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableLogic
    private Integer deleted;
}
