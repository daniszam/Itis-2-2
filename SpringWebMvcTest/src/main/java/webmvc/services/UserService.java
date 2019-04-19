package webmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webmvc.jparepo.UserJpaRepository;
import webmvc.models.UserDetailsImpl;
import webmvc.models.UserJpa;

@Service("customUserDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserJpa userOptional = userJpaRepository.findUserJpaByUserName(s);
        System.out.println(userOptional);
        if (userOptional != null) {
            return new UserDetailsImpl(userOptional);
        } else throw new SecurityException("User with email <" + s + "> not found");
    }
}
