package MiniTelegramClient;

import com.google.gson.JsonObject;

public interface MessageCallback {
    void messageReceived(JsonObject messagesList);
    void messageNotReceived();
    void messageSent();
    void messageNotSent();
    void messageEdited();
    void messageNotEdited();
}
