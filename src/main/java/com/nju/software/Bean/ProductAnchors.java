package com.nju.software.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "product_anchors")
//用来将组件注册到前端的类
public class ProductAnchors {

    @Id
    private String id;
    //组件的英文名字
    private String name;
    //组件需要的样式
    private int anchorsId;
}
