package cn.koala.mybatis.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis参数
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.mybatis")
@Data
public class MyBatisProperties {
  private List<String> typeHandlerPackages = new ArrayList<>();
}
