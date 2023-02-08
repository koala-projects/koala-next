package cn.koala.system.apis;

import cn.koala.system.User;
import cn.koala.system.services.UserService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class UserApiImpl implements UserApi {
  protected final UserService service;

  @Override
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.findAll(parameters, pageable));
  }
}
