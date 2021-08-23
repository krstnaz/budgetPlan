package org.budget.plan.service;

import org.budget.plan.entity.collection.AppUser;
import org.budget.plan.entity.collection.Role;
import org.budget.plan.entity.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository repository;
    private PasswordEncoder encoder;

    public UserDetailsServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        var optionalUser = repository.findByName(name);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with name " + name + " not found.");
        }
        AppUser user = optionalUser.get();
        var roleList = new ArrayList<String>();
        for(Role role : user.getRoles()) {
            roleList.add(role.name());
        }
        return User.builder()
                .username(user.getName())
                .password(encoder.encode(user.getPassword()))
                .roles(roleList.toArray(new String[0]))
                .build();
    }
}
