## TERMINOLOGY

1. Message Broker
- A message broker is a middleman that sits between senders and receivers of the messages.
- Instead of services talking directly to each other, they talk through the broker.
- In Spring context, /topic or /queue can be called as a message broker.
  Client A  ──sends──▶  /app/chat (your controller)
  │
  ▼
  Message Broker       ◀── Client B subscribes to /topic/messages
  (/topic/...)        ◀── Client C subscribes to /topic/messages
  │
  ├──▶ delivers to Client B
  └──▶ delivers to Client C