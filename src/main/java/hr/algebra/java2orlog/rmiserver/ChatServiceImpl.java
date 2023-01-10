package hr.algebra.java2orlog.rmiserver;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceImpl implements ChatService {
    private List<String> chatHistory;

    public ChatServiceImpl(){
        chatHistory = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, String user) {
        chatHistory.add(new StringBuilder().append(user).append(" > ").append(message).toString());
    }

    @Override
    public List<String> getChatHistory() {
        return chatHistory;
    }
}
