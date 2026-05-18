# Configuration notes

Code:
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/ws")
                .setAllowedOrigins("*");
    }

}

- This configuration acts as a gatekeeper, opening the route for our chat handler to start accept the WS connections
- @EnableWebSocket, this annotation turns on WS feature inside our app, telling it to actively listen for WS handshake requests
- registry.addHandler(chatHandler, "/ws"), this maps a specific URI to your handler
  Example: ws://[yourdomain.com/ws](https://yourdomain.com/ws)
- setAllowedOrigins("*"), this handles CORS