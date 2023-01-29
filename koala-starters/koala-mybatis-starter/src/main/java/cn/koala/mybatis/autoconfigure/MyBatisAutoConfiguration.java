package cn.koala.mybatis.autoconfigure;

import cn.koala.mybatis.handlers.EnhancedEnumTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@EnableConfigurationProperties(MyBatisProperties.class)
@EnableTransactionManagement
public class MyBatisAutoConfiguration {
  /**
   * MyBatis中文枚举TypeHandler自定义配置
   *
   * @return 自定义配置
   */
  @Bean
  public ConfigurationCustomizer enhancedEnumTypeConfigurationCustomizer() {
    return configuration -> configuration.getTypeHandlerRegistry().register(EnhancedEnumTypeHandler.class);
  }

  /**
   * MyBatis TypeHandler自定义配置
   *
   * @param properties MyBatis配置
   * @return 自定义配置
   */
  @Bean
  public ConfigurationCustomizer typeHandlerConfigurationCustomizer(MyBatisProperties properties) {
    return configuration -> {
      TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
      properties.getTypeHandlerPackages().forEach(registry::register);
    };
  }
}
