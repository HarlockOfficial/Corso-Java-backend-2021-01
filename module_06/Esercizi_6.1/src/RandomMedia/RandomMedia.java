package RandomMedia;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.*;
import com.botticelli.bot.request.methods.types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomMedia extends Bot {
    private final List<String> messages, audio, video, voice, sticker, document, photo;

    public RandomMedia(String token)
    {
        super(token);
        messages = new ArrayList<>();
        audio = new ArrayList<>();
        video = new ArrayList<>();
        voice = new ArrayList<>();
        sticker = new ArrayList<>();
        document = new ArrayList<>();
        photo = new ArrayList<>();
    }
    private String action(List<String> arr, String fileId){
        arr.add(fileId);
        Collections.shuffle(arr);
        return arr.get(0);
    }
    @Override
    public void textMessage(Message message) {
        sendMessage(new MessageToSend(message.getFrom().getId(), action(messages, message.getText())));
    }

    @Override
    public void audioMessage(Message message) {
        sendAudiobyReference(new AudioReferenceToSend(message.getFrom().getId(), action(audio, message.getAudio().getFileID())));
    }

    @Override
    public void videoMessage(Message message) {
        sendVideobyReference(new VideoReferenceToSend(message.getFrom().getId(), action(video, message.getVideo().getFileID())));
    }

    @Override
    public void voiceMessage(Message message) {
        sendVoicebyReference(new VoiceReferenceToSend(message.getFrom().getId(), action(voice, message.getVoice().getFileID())));
    }

    @Override
    public void stickerMessage(Message message) {
        sendStickerbyReference(new StickerReferenceToSend(message.getFrom().getId(), action(sticker, message.getSticker().getFileID())));
    }

    @Override
    public void documentMessage(Message message) {
        sendDocumentbyReference(new DocumentReferenceToSend(message.getFrom().getId(), action(document, message.getDocument().getFileID())));
    }

    @Override
    public void photoMessage(Message message) {
        sendPhotobyReference(new PhotoReferenceToSend(message.getFrom().getId(), action(photo, message.getPhoto().get(0).getFileID())));
    }

    @Override
    public void contactMessage(Message message) {

    }

    @Override
    public void locationMessage(Message message) {

    }

    @Override
    public void venueMessage(Message message) {

    }

    @Override
    public void newChatMemberMessage(Message message) {

    }

    @Override
    public void newChatMembersMessage(Message message) {

    }

    @Override
    public void leftChatMemberMessage(Message message) {

    }

    @Override
    public void newChatTitleMessage(Message message) {

    }

    @Override
    public void newChatPhotoMessage(Message message) {

    }

    @Override
    public void groupChatPhotoDeleteMessage(Message message) {

    }

    @Override
    public void groupChatCreatedMessage(Message message) {

    }

    @Override
    public void inLineQuery(InlineQuery inlineQuery) {

    }

    @Override
    public void chose_inline_result(ChosenInlineResult chosenInlineResult) {

    }

    @Override
    public void callback_query(CallbackQuery callbackQuery) {

    }

    @Override
    public void gameMessage(Message message) {

    }

    @Override
    public void videoNoteMessage(Message message) {

    }

    @Override
    public void pinnedMessage(Message message) {

    }

    @Override
    public void preCheckOutQueryMessage(PreCheckoutQuery preCheckoutQuery) {

    }

    @Override
    public void shippingQueryMessage(ShippingQuery shippingQuery) {

    }

    @Override
    public void invoiceMessage(Message message) {

    }

    @Override
    public void successfulPaymentMessage(Message message) {

    }

    @Override
    public void routine() {

    }
}
