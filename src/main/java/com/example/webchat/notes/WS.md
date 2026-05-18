# What is WebSocket?
- WebSocket is a persistent, full-duplex communication channel over a single TCP connection.
- A simple example would be WebSocket is an upgrade version of HTTP
  + While HTTP can only open the connection when it gets the request, WebSocket only receives once and it stays free
  + HTTP is like sending mails back and forth, while WS is like a phone call, the session keeps open

# Model for HTTP:
    Browser         Server
    ------ Request ------>
    <----- Response ------
    ------ Closed --------
    ---- New Request ---->
    <--- New Response ----
    ------ Closed --------
⇒ To get new data, browser must ask again, server cannot speak

# Model for WebSocket:
    Browser         Server
    --- HTTP Handshake -->
    -- Connection stays --
    ---- Send message --->
    <- Push notification --
    <---- Push again ------
    -- Browser Response -->
⇒ Both side can speak at any time, no new connection needed

# Three attributes about WebSocket:
- One handshake, and it's free: It starts with just a normal HTTP request. 
  The server agrees, and from that point the connection is no longer HTTP.
  It becomes a TCP pipe that both side can keep open indefinitely
- Full-duplex: Both sides can send at any time without waiting the other.
- Frames, not request: After the handshake, data travels as lightweight frames.
  No HTTP headers re-sent each time, no new TCP handshake per message.

# WebSocket in Spring Boot:
- Spring Boot needs two things: a config class that registers your endpoint, and a handler
  class that reacts to connection events
- @EnableWebSocket + WebSocketConfig -- This is the entry point
    + We implement registerWebSocketHandlers()and map a handler class to a URL path (/ws). Spring does the rest.
    + When a browser hits GET /ws with Upgrade: websocket, Spring completes the handshake.
- Model:

  Browser ---- GET /ws ----> WebSocketConfig ----> EchoHandler
  WebSocketConfig: tells Spring there's a WebSocket endpoint at /ws, and EchoHandler is responsible for that.
  EchoHandler: the actual logic, every connection event flows here

- WebSocketSession: connection cycle

  afterConnectionEstablished(session) - browser just connected, save session to a map
  handleTextMessage(session, message) - browser send a frame, read and reply
  afterConnectionClosed(session, status) - browser disconnected, remove from map 