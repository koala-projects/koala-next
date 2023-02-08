package cn.koala.system.autoconfigure;

import cn.koala.system.apis.UserApi;
import cn.koala.system.apis.UserApiImpl;
import cn.koala.system.repositories.UserRepository;
import cn.koala.system.services.UserService;
import cn.koala.system.services.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 系统管理自动配置类
 *
 * @author Houtaroy
 */
@Import({SecurityAutoConfiguration.class, SwaggerAutoConfiguration.class})
@Configuration
@MapperScan(basePackages = "cn.koala.system.repositories")
public class SystemAutoConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return new UserServiceImpl(userRepository, passwordEncoder);
  }

  @Bean
  public UserApi userApi(UserService userService) {
    return new UserApiImpl(userService);
  }
}
