package unir.com.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import unir.com.users.dto.TaskDTO;

import java.util.List;

@FeignClient(name = "tasks")
public interface TaskClient {

    @GetMapping("api/task/user/{userId}")
    List<TaskDTO> getTasks(@PathVariable() Long userId);
}