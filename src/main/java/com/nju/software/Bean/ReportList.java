package com.nju.software.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/04/09/下午4:37
 * @Description: 这是报目表的数据
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Document
public class ReportList {

    @Id
    private String topologyId;
    // 报目表数据
    private Object data;

    private String userId;
}
