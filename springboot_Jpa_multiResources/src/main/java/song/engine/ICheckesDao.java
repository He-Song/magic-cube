package song.engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/7/22 18:13
 */
public interface ICheckesDao extends JpaRepository<Checkes, Long> {
}
