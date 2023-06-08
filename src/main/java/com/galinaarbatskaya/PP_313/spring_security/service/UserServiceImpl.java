package com.galinaarbatskaya.PP_313.spring_security.service;

import com.galinaarbatskaya.PP_313.spring_security.models.User;
import com.galinaarbatskaya.PP_313.spring_security.repositories.RoleRepository;
import com.galinaarbatskaya.PP_313.spring_security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addUserRole(roleRepository.getById(2L));
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Override
    @Transactional
    public void update(User updateUser) {
        Optional<User> user = userRepository.findById(updateUser.getId());
        String oldPassword = "";
        if (user.isPresent()){
            oldPassword= user.get().getPassword();
            updateUser.setRoles(user.get().getRoles());
        }
        if(!(oldPassword.equals(updateUser.getPassword()))){
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        userRepository.save(updateUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s doesn't exists", username)));
        return user;
    }
}