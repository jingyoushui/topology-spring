package com.nju.software.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/08/下午2:42
 * @Description:用户上传的图片，是在节点里使用到的图片
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "image")
public class Image {

    @Id
    private String id;
    //图片地址
    private String image;
    //拥有者的id
    private String userId;
    //用户姓名
    private String username;

    private long createdAt;
    private long deletedAt;

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
