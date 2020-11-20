package com.hzw.learn.springboot.javabase.StringEscape;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class StringEscapeUtils {
    public static String escapeString(String x) throws UnsupportedEncodingException {
        if(isEscapeNeededForString(x,x.length())){
            return escapeString(x,null);
        }
        return x;
    }

    public static String escapeString(String x,String encoding) throws UnsupportedEncodingException {
        byte[] bytes;
        bytes = null == encoding ? x.getBytes() :  x.getBytes(encoding);

        bytes = escapeBytes(bytes);

        return null==encoding ? new String(bytes) : new String(bytes,encoding);
    }


    public static byte[] escapeBytes(byte[] x){
        int numBytes = x.length;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(numBytes);
        bOut.write(39);

        for(int i = 0; i < numBytes; ++i) {
            byte b = x[i];
            switch(b) {
                case 0:
                    bOut.write(92);
                    bOut.write(48);
                    break;
                case 10:
                    bOut.write(92);
                    bOut.write(110);
                    break;
                case 13:
                    bOut.write(92);
                    bOut.write(114);
                    break;
                case 26:
                    bOut.write(92);
                    bOut.write(90);
                    break;
                case 34:
                    bOut.write(92);
                    bOut.write(34);
                    break;
                case 39:
                    bOut.write(92);
                    bOut.write(39);
                    break;
                case 92:
                    bOut.write(92);
                    bOut.write(92);
                    break;
                default:
                    bOut.write(b);
            }
        }

        bOut.write(39);

        return bOut.toByteArray();
    }


    public static boolean isEscapeNeededForString(String x){
        return isEscapeNeededForString(x,x.length());
    }
    private static boolean isEscapeNeededForString(String x, int stringLength) {
        boolean needsHexEscape = false;

        for(int i = 0; i < stringLength; ++i) {
            char c = x.charAt(i);
            switch(c) {
                case '\u0000':
                    needsHexEscape = true;
                    break;
                case '\n':
                    needsHexEscape = true;
                    break;
                case '\r':
                    needsHexEscape = true;
                    break;
                case '\u001a':
                    needsHexEscape = true;
                    break;
                case '"':
                    needsHexEscape = true;
                    break;
                case '\'':
                    needsHexEscape = true;
                    break;
                case '\\':
                    needsHexEscape = true;
            }

            if (needsHexEscape) {
                break;
            }
        }

        return needsHexEscape;
    }

}
