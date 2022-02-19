package golabi.controller;

import golabi.event.AddMemberEvent;
import golabi.event.CreatGroupEvent;
import golabi.event.EditMessageEvent;
import golabi.event.NewMessageEvent;
import golabi.model.Tweet;
import golabi.model.chat.ChatRoom;
import golabi.model.chat.GroupChat;
import golabi.model.chat.Message;

public class ChatController extends Controller{
    public void creatGroup(CreatGroupEvent event){
        if (event.getGroupName()!=null && !event.getGroupName().equals("")){
            logger.info("@"+owner.getUsername()+" has created a group : "+event.getGroupName());
        context.GroupChats.add(new GroupChat(event.getOwnerID(), event.getGroupName()));
        }
    }
    public void creatChatRooms(){
        owner.getProfile().getFollowing().forEach(following->{
            final boolean[] flag1 = new boolean[1];
            context.ChatRooms.all().forEach(chatRoom -> {
                flag1[0] = chatRoom.isExist(following,owner.getID());

            });
            if (!flag1[0]) {
                ChatRoom chatRoom = new ChatRoom(owner.getID(), following);
                context.ChatRooms.add(chatRoom);
            }});
        owner.getProfile().getFollowers().forEach(follower->{
            final boolean[] flag1 = new boolean[1];
            context.ChatRooms.all().forEach(chatRoom -> {
                flag1[0] = chatRoom.isExist(follower,owner.getID());

            });
            if (!flag1[0]) {
                ChatRoom chatRoom = new ChatRoom(owner.getID(), follower);
                context.ChatRooms.add(chatRoom);
            }});
    }
    public void newMessage(NewMessageEvent e){
        if (!e.getText().isEmpty() && e.getImageFilePath() == null){
            Message message = new Message(e.getText(),owner.getID(),e.getChatRoomID());
            logger.info("@"+owner.getUsername()+" sent a message ");
            context.Messages.add(message);
        }
        if (e.getImageFilePath() != null && !e.getText().isEmpty()){
            Message message = new Message(e.getText(),owner.getID(),e.getChatRoomID());
            logger.info("@"+owner.getUsername()+" sent a message ");
            context.Messages.add(message);
            context.Messages.updatePhoto(message,e.getImageFilePath());
        }
        }
    public void editMessage(EditMessageEvent e) {
        if (!e.getMessage().equals("")) {
            Message message = context.Messages.get(e.getId());
            message.setText(e.getMessage());
            logger.info("@"+owner.getUsername()+" edited a message ");
            context.Messages.update(message);
        }
    }
    public void deleteMessage(Message message){
        logger.info("@"+owner.getUsername()+" deleted a message ");
        context.Messages.remove(message);
    }
    public void addMemberToGroup(AddMemberEvent e){
        GroupChat groupChat =context.GroupChats.get(e.getGroupID());
        groupChat.getUsersID().add(e.getUserID());
        logger.info("@"+owner.getUsername()+" add new member to groupChat ");
        context.GroupChats.update(groupChat);
    }
    }
