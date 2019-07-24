package song.engine;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/7/22 17:46
 */
@Entity
@Data
public class Checkes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String formula;

    private String errInfo;

    private int status;

    private int type;

    private int tier;
}
