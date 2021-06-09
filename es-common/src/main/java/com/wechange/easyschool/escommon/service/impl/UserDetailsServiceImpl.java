package com.wechange.easyschool.escommon.service.impl;

import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.repository.UserDetailsRepository;
import com.wechange.easyschool.escommon.security.CustomUserDetails;
import com.wechange.easyschool.esmodel.entity.user.EnumAuthority;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userDetailsRepository.findByEmailOrUsername(username,username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + username)
                );

        return CustomUserDetails.create(user);
    }

    @Transactional
    public CustomUserDetails loadUserById(String id) throws DBItemNotFoundException {
        User user = userDetailsRepository.findById(id).orElseThrow( () -> new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND,"User", "id", id.toString())
        );
        return CustomUserDetails.create(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<EnumAuthority> roles) {
        return getGrantedAuthorities(getRoles(roles));
    }

    private List<String> getRoles(Collection<EnumAuthority> authorities) {
        return authorities.stream().map(authority -> authority.name()).collect(Collectors.toList());
    }

    private List<GrantedAuthority> getGrantedAuthorities( List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for ( String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }


}
