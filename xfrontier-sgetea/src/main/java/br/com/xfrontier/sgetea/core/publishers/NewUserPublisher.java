package br.com.xfrontier.sgetea.core.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.xfrontier.sgetea.core.events.NewUserEvent;
import br.com.xfrontier.sgetea.core.models.User;

@Component
public class NewUserPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publish(User user) {
        var event= new NewUserEvent(this, user);
        eventPublisher.publishEvent(event);
    }

}