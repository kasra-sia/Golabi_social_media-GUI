package golabi.db;
import golabi.model.Tweet;
import golabi.model.account.Profile;
import golabi.model.account.User;
import golabi.model.chat.ChatRoom;
import golabi.model.chat.GroupChat;
import golabi.model.chat.Message;

public class Context {
    public DBSet<User> Users = new UserDB();
    public DBSet<Tweet> Tweets = new TweetDB();
    public DBSet<Message> Messages = new MessageDB();
    public DBSet<ChatRoom> ChatRooms = new ChatDB();
    public DBSet<GroupChat> GroupChats = new GroupChatDB();

}