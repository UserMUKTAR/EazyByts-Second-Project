package EazyByts.Muktar.Restaurant.service;


import EazyByts.Muktar.Restaurant.model.UserPrincipal;
import EazyByts.Muktar.Restaurant.model.Users;
import EazyByts.Muktar.Restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);

        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}
