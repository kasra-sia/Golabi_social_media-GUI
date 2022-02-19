package golabi.event;

public class SetUserViewEvent {
   private String username;
   private String userImgPath;
   private String firstname;
   private String lastName;
   private String lastSeen;
   private String birthDay;
   private String bio;
   private boolean isFollowed;
   private boolean isBlocked;
    private boolean isMuted;
   private boolean isPrivate;

    public SetUserViewEvent(String username, String userImgPath, String firstname, String lastName, String lastSeen, String birthDay, String bio, boolean isFollowed, boolean isBlocked,boolean isMuted ,boolean isPrivate) {
        this.username = username;
        this.userImgPath = userImgPath;
        this.firstname = firstname;
        this.lastName = lastName;
        this.lastSeen = lastSeen;
        this.birthDay = birthDay;
        this.bio = bio;
        this.isFollowed = isFollowed;
        this.isBlocked = isBlocked;
        this.isMuted = isMuted;
        this.isPrivate = isPrivate;
    }

    public String getUsername() {
        return username;
    }

    public String getUserImgPath() {
        return userImgPath;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBio() {
        return bio;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
