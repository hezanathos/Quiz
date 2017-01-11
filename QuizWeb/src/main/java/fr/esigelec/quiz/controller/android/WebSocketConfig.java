package fr.esigelec.quiz.controller.android;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 * 
 * @author wangxi
 * 	Configuration of the Spring websocket
 * @see https://spring.io/guides/gs/messaging-stomp-websocket/
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
 
	
    @Override
    // condifutation of the message broker
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
 
    // set register STOMP endpoint
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		 registry.addEndpoint("/choisir").withSockJS();
		
	}

}
