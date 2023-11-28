package user_api.service.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import user_api.model.User;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    public boolean checkAccess (Long id){
        if (getCurrentUser() != null){
            return Objects.equals(this.getCurrentUser().getId(), id);
        } return false;
    }
}
