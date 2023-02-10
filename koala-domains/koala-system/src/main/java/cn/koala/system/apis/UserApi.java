package cn.koala.system.apis;

import cn.koala.openapi.PageableAsQueryParam;
import cn.koala.system.User;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户管理接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/users")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "用户管理")
public interface UserApi {

  /**
   * 根据条件分页查询用户
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 用户列表
   */
  @PreAuthorize("hasAuthority('SCOPE_user.read')")
  @Operation(summary = "根据条件分页查询用户")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "username", description = "用户名", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "nickname", description = "用户昵称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "departmentId", description = "部门ID", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<User>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                @Parameter(hidden = true) Pageable pageable);


  class UserPageResult extends DataResponse<Page<User>> {

  }
}
