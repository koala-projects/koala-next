package cn.koala.system.autoconfigure;

import cn.koala.system.apis.DictionaryApi;
import cn.koala.system.apis.DictionaryApiImpl;
import cn.koala.system.apis.DictionaryItemApi;
import cn.koala.system.apis.DictionaryItemApiImpl;
import cn.koala.system.apis.UserApi;
import cn.koala.system.apis.UserApiImpl;
import cn.koala.system.repositories.DictionaryItemRepository;
import cn.koala.system.repositories.DictionaryRepository;
import cn.koala.system.repositories.UserRepository;
import cn.koala.system.services.DictionaryItemService;
import cn.koala.system.services.DictionaryItemServiceImpl;
import cn.koala.system.services.DictionaryService;
import cn.koala.system.services.DictionaryServiceImpl;
import cn.koala.system.services.UserService;
import cn.koala.system.services.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
  @ConditionalOnMissingBean
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return new UserServiceImpl(userRepository, passwordEncoder);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserApi userApi(UserService userService) {
    return new UserApiImpl(userService);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryService dictionaryService(DictionaryRepository dictionaryRepository) {
    return new DictionaryServiceImpl(dictionaryRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryApi dictionaryApi(DictionaryService dictionaryService) {
    return new DictionaryApiImpl(dictionaryService);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemService dictionaryItemService(DictionaryItemRepository dictionaryItemRepository) {
    return new DictionaryItemServiceImpl(dictionaryItemRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemApi dictionaryItemApi(DictionaryItemService dictionaryItemService) {
    return new DictionaryItemApiImpl(dictionaryItemService);
  }
}
