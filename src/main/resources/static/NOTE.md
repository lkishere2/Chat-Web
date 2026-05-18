The whole process would be like
1) When a user get into the page, they immediately connect to WS.
   In the index.html,
       const socket = new WebSocket('ws://localhost:8080/ws');
       socket.onopen = () => console.log('Connected to server');
    This will call the 'ws://localhost:8080/ws', and then open the session for user.
    ChatHandler will receive the signal, and execute afterConnectionEstablished(WebSocketSession session).
    Then, it calls the service, and put new session to sessions.
2) Now, imagine in the sessions, there are some users, a user type something in the chat.
   And they send that message, now JS will execute sendMessage(), take the input from the FE,
    which then calling to ChatHandler. Afterward, chat handler will execute handleTextMessage(WebSocketSession session, TextMessage message)
    ChatService will then be called and execute handleIncoming(), save new message to the DB, and broadcast to every open sessions that WS.
    FE will handle how the message handled, socket.onmessage
3) When user exit the page, or reload, or navigate to other, they simply disconnect to WS