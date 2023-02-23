package gui.chat;

import entities.Chat;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ChatCRUD;

/**
 * FXML Controller class
 *
 */
public class ChatController implements Initializable {

    @FXML
    private VBox chatBox;

    @FXML
    private VBox messageBox;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private TextField messageInput;

    @FXML
    private Button sendButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // load previous messages
        loadMessages();

        // set up the send button event handler
        sendButton.setOnAction(event -> {
            // get the message text from the input field
            String messageText = messageInput.getText().trim();

            

            // create a new Chat message with the sender, receiver, and message text
            // in this example, we are assuming that the sender is always the logged-in user and the receiver is a fixed user
            String sender = "my_username";
            String receiver = "friend_username";
            Chat newMessage = new Chat(sender, receiver, messageText);

            // add the new message to the database
            ChatCRUD chatCRUD = new ChatCRUD();
            chatCRUD.add(newMessage);

            // add the new message to the chat box
            addMessageToChatBox(newMessage);

            // clear the message input field
            messageInput.clear();
        });
    }

    /**
     * Loads the previous messages from the database and adds them to the chat box.
     */
    private void loadMessages() {
        ChatCRUD chatCRUD = new ChatCRUD();
        List<Chat> messages = chatCRUD.getAll();

        for (Chat message : messages) {
            addMessageToChatBox(message);
        }
    }

    /**
     * Adds a message to the chat box.
     *
     * @param message the message to add to the chat box
     */
   /* private void addMessageToChatBox(Chat message) {
        // create a label to display the message text
        Label messageLabel = new Label(message.getMessage());

        // create an HBox to hold the label and set its alignment
        HBox messageHBox = new HBox(messageLabel);
        messageHBox.setAlignment(Pos.CENTER_LEFT);

        // add the HBox to the message box
        messageBox.getChildren().add(messageHBox);

        // scroll to the bottom of the chat box
        chatScrollPane.setVvalue(1.0);
    }*/
    private void addMessageToChatBox(Chat message) {
    if (chatBox == null) {
        System.err.println("Error: chatBox is null");
        return;
    }
    
    String username = message.getSender();
    String text = message.getMessage();
    Date time = message.getTimestamp();
    
    Label usernameLabel = new Label(username + ":");
    Label textLabel = new Label(text);
    Label timeLabel = new Label("(" + time + ")");
    
    chatBox.getChildren().addAll(usernameLabel, textLabel, timeLabel);
}

}
