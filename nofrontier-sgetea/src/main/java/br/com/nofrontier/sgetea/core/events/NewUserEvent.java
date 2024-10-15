package br.com.nofrontier.sgetea.core.events;

import org.springframework.context.ApplicationEvent;

import br.com.nofrontier.sgetea.core.models.User;
import lombok.Getter;

@Getter
public class NewUserEvent extends ApplicationEvent {

    private static final long serialVersionUID= 1L;

    private User user;

    public NewUserEvent(Object source, User user) {
        super(source);
        this.user= user;
    }

}