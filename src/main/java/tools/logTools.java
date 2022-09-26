package tools;

public final class logTools {

    public static void printMessage(String strMessage){
        System.out.println(dateTimeTools.getDateTimeFormatted(null) + "  " + strMessage);
    }
    public static void printException(Exception e, String strMessage){
        System.err.println(dateTimeTools.getDateTimeFormatted(null) + " [EXCEPTION] ["+e.getMessage()+"] "+strMessage);
    }
}
