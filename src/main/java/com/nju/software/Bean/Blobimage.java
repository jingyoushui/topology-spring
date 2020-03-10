package com.nju.software.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/09/下午4:37
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "blobimage")
public class Blobimage {

    @Id
    private String path;
    //是否重命名
    private int randomName;
    //图片
    //@Lob 通常与@Basic同时使用，提高访问速度
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="file", columnDefinition="longblob", nullable=true)
    private byte[] file;
    //是否共享
    private boolean ispublic;

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
