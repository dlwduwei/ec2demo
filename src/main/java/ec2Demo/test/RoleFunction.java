package ec2Demo.test;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class RoleFunction {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    String name = "test";
    int n = 1;


    @PostMapping("/getRole")
    String getRole(){
      String roleJSON = (String) redisTemplate.opsForValue().get("roleJason");
      if (roleJSON != null){
        return roleJSON;
      }

      List<Role> roleList = roleMapper.getRole();
      roleJSON = JSONArray.fromObject(roleList).toString();
      redisTemplate.opsForValue().set("roleJSON",roleJSON,10, TimeUnit.SECONDS);
      System.out.println("Error, create new KV");
      return roleJSON;
    }

    @GetMapping("/insertRDS")
    String insertRDS(){

        Role role = new Role();
        role.setName(name);
        roleMapper.insertRDS(role);
        return("insert RDS success");
    }

    @GetMapping("/insertRedis")
    String insertRedis(){
        Role role = new Role();
        role.setName(name);
        String r = role.toString();
        String key = "test"+ n;
        redisTemplate.opsForValue().set(key,r,10,TimeUnit.MINUTES);
        n++;
        return("insert Redis Success");
    }

}
