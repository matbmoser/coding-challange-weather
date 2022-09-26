package tools;

public final class logTools {

    public static void printMessage(String strMessage){
        System.out.println(strMessage);
    }
    public static void printException(Exception e, String strMessage){
        System.out.println("[EXCEPTION] ["+e.getMessage()+"] "+strMessage);
    }
}
