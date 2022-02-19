package golabi.event;

import java.io.File;
import java.util.EventObject;

public class FileEvent extends EventObject {
    File file;
    public FileEvent(Object source, File file) {
        super(source);
        this.file = file;
    }
    public File getFile() {
        return file;
    }
}
