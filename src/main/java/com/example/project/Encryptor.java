package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor 
{
    
    public static int determineColumns(int messageLen, int rows)
    {
        if (messageLen == 0)
        {
            return 1;
        }

        return (int)Math.ceil((double)messageLen / rows);
    }
    
    public static String[][] generateEncryptArray(String message, int rows) 
    {
        char[] messageArray = message.toCharArray();
        int column = determineColumns(message.length(), rows);
        String[][] encryptArray = new String[rows][column];
        int increment = 0;

        for (int i = 0; i < encryptArray.length; i++)
        {
            for (int j = 0; j < encryptArray[0].length; j++)
            {
                if (increment < messageArray.length)
                {
                    encryptArray[i][j] = String.valueOf(messageArray[increment]);
                }
                else
                {
                    encryptArray[i][j] = "=";
                }

                increment++;
            }
        }

        return encryptArray;
    }

    public static String encryptMessage(String message, int rows)
    {
        String[][] nonEncryptedMessage = generateEncryptArray(message, rows);
        String encryptedMessage = "";
        
        for (int i = nonEncryptedMessage[0].length - 1; i >= 0; i--)
        {
            for (int j = 0; j < nonEncryptedMessage.length; j++)
            {
                encryptedMessage += nonEncryptedMessage[j][i];
            }
        }
        return encryptedMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) 
    {
        int column = determineColumns(encryptedMessage.length(), rows);
        
        while (rows * column > encryptedMessage.length()) 
        {
            rows--;
            column = determineColumns(encryptedMessage.length(), rows);
        }

        String[][] decryptedMessage = new String[rows][column];
        int increment = 0;
        String trueMessage = "";
        char[] array = encryptedMessage.toCharArray();

        for (int i = decryptedMessage[0].length - 1; i >= 0; i--)
        {
            for (int j = 0; j < decryptedMessage.length; j++)
            {
                if (increment < array.length)
                {
                    decryptedMessage[j][i] = String.valueOf(array[increment]);
                    increment++;
                }
            }
        }
        
        for (String[] row : decryptedMessage)
        {
            for (String string : row)
            {
                if (!string.equals("="))
                {
                    trueMessage += string;
                }
            }
        }

        return trueMessage;
    }
}