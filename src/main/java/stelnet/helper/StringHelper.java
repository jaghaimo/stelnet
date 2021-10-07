package stelnet.helper;

public class StringHelper {

    /**
     * Guestimate size of the string.
     * 
     * @link https://stackoverflow.com/a/60643245
     */
    public static float getSize(String s) {
        String lookup = " .:,;'^`!|jl/\\i-()JfIt[]?{}sr*a\"ce_gFzxk+0123456789<=>~qvy$opnSbduEhTBXY#VRKZN%GUAHD@OQ&wmWLPCM";
        int result = 0;
        for (int i = 0; i < s.length(); ++i) {
            int c = lookup.indexOf(s.charAt(i));
            result += (c < 0 ? 60 : c) * 9 + 200;
        }
        return (float) result / 40;
    }
}