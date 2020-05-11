package com.springboot.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.mybatisplus.mapper.UserMapper;
import com.springboot.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;
/**********************************************增加操作*******************************************************************/
    //增加用户
    @Test
    public void insert(){
        User user = new User();
        user.setName("张三111");
        user.setAge(1);
        user.setEmail("111@qq.com");
        int result = userMapper.insert(user);

        System.out.println(result);
        System.out.println(user);
    }
/**********************************************删除操作*****************************************************************/
    //根据id删除用户
    @Test
    public void deleteById(){
        userMapper.deleteById(1259007806086889474L);
    }
    //根据map构建条件删除用户
    @Test
    public void deleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三111");
        userMapper.deleteByMap(map);
    }

    //根据wrapper包装器构建条件删除用户
    @Test
    public void delete(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("id",5).eq("name","张三").lt("age",10);
        userMapper.delete(wrapper);
    }

    //根据id集合批量删除用户
    @Test
    public void deleteBatchIds(){
        List<Long> list = new ArrayList<>();
        list.add(7L);
        list.add(8L);
        list.add(9L);
        userMapper.deleteBatchIds(list);
    }
/**********************************************更新操作*****************************************************************/
    //根据id更新用户(与根据id删除不同的是，传入的参数是pojo而不是单独的id,注意pojo的id是不允许为空的，其余属性可以为空)
    //优点：数据库中的字段是否更新完全取决于参数pojo的属性是否为空，为空则不变，不为空则更新，非常灵活
    @Test
    public void updateById(){
        User user = new User();
        user.setId(6L);
        user.setName("张三");
        userMapper.updateById(user);
    }

    //与updateById方法类似，只是添加了wrapper参数先进行条件判断，再更新数据
    @Test
    public void update(){
        User user = new User();
        user.setId(6L);
        user.setAge(3);
        user.setEmail("zs@163.com");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","张三");
        userMapper.update(user,wrapper);
    }
/**********************************************查询操作*****************************************************************/
    //根据id查询用户
    @Test
    public void selectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //根据id集合批量查询用户
    @Test
    public void selectBatchIds(){
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        List<User> userList = userMapper.selectBatchIds(list);
        userList.forEach(System.out::println);
    }

    //查询全部用户
    @Test
    public void testSelectAll(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //分页查询用户
    @Test
    public void selectPage(){
        Page<User> page = new Page<>(2,3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("id",1);
        userMapper.selectPage(page, wrapper);
        page.getRecords().forEach(System.out::println);
    }
}
