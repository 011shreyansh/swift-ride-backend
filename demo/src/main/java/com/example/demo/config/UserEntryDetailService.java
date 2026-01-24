package com.example.demo.config;
import com.example.demo.Entities.User;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserEntryDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> userEntry=userrepo.findByUsername(username);
       if(userEntry.isPresent()){
           User u=userEntry.get();
           return org.springframework.security.core.userdetails.User.builder()
                   .username(u.getUsername())
                   .password(u.getPassword())
                   .roles(u.getRoles().toArray(new String[0]))
                   .build();
       }
       throw new UsernameNotFoundException("user not found"+username);

    }
}

//
//package com.example.demo.config;
//
//import com.example.demo.Entities.User;
//import com.example.demo.Repos.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserEntryDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userEntry = userRepo.findByUsername(username);
//        if (userEntry.isPresent()) {
//            User u = userEntry.get();
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(u.getUsername())
//                    .password(u.getPassword())
//                    .roles(u.getRoles().toArray(new String[0]))
//                    .build();
//        }
//        throw new UsernameNotFoundException("User not found: " + username);
//    }
//}
