package com.zzy.jdk8.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;



//    @Override
 //   public String toString() {
   //     return "[id:"+id+",name:"+name+",hashcode:"+this.hashCode()+"]";
   // }

}
