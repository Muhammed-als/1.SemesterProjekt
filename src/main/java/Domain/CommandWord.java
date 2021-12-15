package Domain;

public enum CommandWord {
    GO("go"), QUIT("quit"),
    HELP("help"), GET("get"),
    DROP("drop"), UNKNOWN("?"),
    INVENTORY("inventory"), TALK("talk");


    private final String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}

