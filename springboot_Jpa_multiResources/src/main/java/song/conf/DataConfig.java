package song.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/7/22 17:38
 */
@Configuration
@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepository.class)
public class DataConfig {
}
