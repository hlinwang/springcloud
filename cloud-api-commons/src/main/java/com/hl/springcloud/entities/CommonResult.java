package com.hl.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    //这里是当data=null时的情况，上面两个注解已经定义了无参和全参构造方法，这里类似定义半参
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }

}
