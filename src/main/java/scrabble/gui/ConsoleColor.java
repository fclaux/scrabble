package scrabble.gui;

public enum ConsoleColor {
	RESET("\u001B[0m"),  

    BLACK("\u001B[30m"),  
    RED("\u001B[31m"),    
    GREEN("\u001B[32m"),   
    YELLOW("\u001B[33m"), 
    BLUE("\u001B[34m"),    
    PURPLE("\u001B[35m"),  
    CYAN("\u001B[36m"),    
    WHITE("\u001B[37m"),   

    BLACK_BOLD("\u001B[1;30m"), 
    RED_BOLD("\u001B[1;31m"), 
    GREEN_BOLD("\u001B[1;32m"), 
    YELLOW_BOLD("\u001B[1;33m"), 
    BLUE_BOLD("\u001B[1;34m"), 
    PURPLE_BOLD("\u001B[1;35m"), 
    CYAN_BOLD("\u001B[1;36m"),
    WHITE_BOLD("\u001B[1;37m"), 

    BLACK_UNDERLINED("\u001B[4;30m"), 
    RED_UNDERLINED("\u001B[4;31m"),   
    GREEN_UNDERLINED("\u001B[4;32m"), 
    YELLOW_UNDERLINED("\u001B[4;33m"),
    BLUE_UNDERLINED("\u001B[4;34m"),  
    PURPLE_UNDERLINED("\u001B[4;35m"),
    CYAN_UNDERLINED("\u001B[4;36m"),  
    WHITE_UNDERLINED("\u001B[4;37m"), 

    BLACK_BACKGROUND("\u001B[40m"), 
    RED_BACKGROUND("\u001B[41m"), 
    GREEN_BACKGROUND("\u001B[42m"), 
    YELLOW_BACKGROUND("\u001B[43m"), 
    BLUE_BACKGROUND("\u001B[44m"), 
    PURPLE_BACKGROUND("\u001B[45m"), 
    CYAN_BACKGROUND("\u001B[46m"), 
    WHITE_BACKGROUND("\u001B[47m"); 

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
