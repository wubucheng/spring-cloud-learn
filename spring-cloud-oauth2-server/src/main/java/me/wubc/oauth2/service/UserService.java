package me.wubc.oauth2.service;

import me.wubc.oauth2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wbc
 * @date 2020/05/27
 * @desc
 **/
@Service
public class UserService implements UserDetailsService {

    private List<User> userList;
    // 密码编码器
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();

        // 设置用户的拥有的权限
        userList.add(new User("wbc", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("guest", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    /**
     * 通过用户名查找用户
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> targeUserList = userList.stream()
                .filter(user -> s.equals(user.getUsername()))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(targeUserList)) {
            return targeUserList.get(0);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
