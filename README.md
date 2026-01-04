# Remember Chat – Spring AI (In-Memory Chat Memory)

This project demonstrates how to build a **context-aware conversational API** using **Spring Boot** and **Spring AI**.

The application uses:
- **In-memory chat memory (last 20 messages)**
- **Chat memory advisor**
- **Simple logger advisor**
- **Safeguard advisor**
- **OpenAI Chat Model**

The AI is able to **remember conversation context** across messages without manually resending previous user messages.

---

## 🧠 What this project does

- Exposes a REST API that accepts user messages
- Uses **Spring AI ChatClient** configured with multiple advisors
- Remembers the last **20 messages per conversation**
- Logs requests/responses
- Applies safety/guardrails before sending prompts to OpenAI
- Returns AI-generated responses while maintaining context

### Example:
1. User: *What is the price for Manchester to Islamabad flight?*  
2. User: *Give me the cheapest price*  

➡️ The AI understands that both messages are about **Manchester → Islamabad flights**, because context is preserved.

---

## 🧩 Architecture Overview
Client  
|  
| POST /remember/message  
v  
RememberChatController  
|  
v  
ChatClient  
├── MessageChatMemoryAdvisor (20 messages)  
├── SimpleLoggerAdvisor  
├── SafeguardAdvisor  
|  
v  
OpenAI Chat Model  
