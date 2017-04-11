package com.company;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by dumindu on 09/04/2017.
 */
public class decryption {

    public void decrypt(){

        BufferedReader message = null;
        String left="";
        String right="";
        String message1;
        String[] key = {"key1hsjsjsjsjsjsjsjjsjsjs","this is the second secret key","this is awesome"};
        String swap;
        String Left_bin_last=null;
        String Right_bin_last = null;

        try {
            message =
                    new BufferedReader(new FileReader("src\\Messeges\\Encrypted\\encrypted_message1.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer complete_message = new StringBuffer();
        try {
            while((message1 = message.readLine())!= null){
                complete_message.append(message1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//       System.out.println(complete_message); // get the encrypted message from the file
        String[] messageList = complete_message.toString().split("BitStream");
        String Bits = messageList[1];

        left = Bits.substring(0,Bits.length()/2); // divide into left and right
        right = Bits.substring(Bits.length()/2);

//        System.out.println(left);
//        System.out.println(right);




        int loop_ends=key.length-1;

        int Right_len;

        while(loop_ends>-1){


            Right_len = right.length();
            String key1_bin = new BigInteger(key[loop_ends].getBytes()).toString(2);

            String eqlen_key = String.format("%0"+String.valueOf(Right_len)+"d",new BigInteger(key1_bin));


            StringBuilder rightxorkey = new StringBuilder();
            StringBuilder leftxorresult = new StringBuilder();
            rightxorkey.setLength(0);
            leftxorresult.setLength(0);

            for(int i = 0; i < right.length(); i++)
                rightxorkey.append((right.charAt(i) ^ eqlen_key.charAt(i)));

            String result = rightxorkey.toString();										//same procedure as encryption
            for(int i = 0; i < left.length(); i++)
                leftxorresult.append((left.charAt(i) ^ result.charAt(i)));
            String result2 = leftxorresult.toString();


            if(loop_ends<1){

                 Left_bin_last = result2;
                 Right_bin_last= right;
            }
            else{swap = right;
                right  = result2;
                left = swap;
                }


                loop_ends-=1;

        }
        System.out.println(Left_bin_last);
        System.out.println(Right_bin_last);

        String left1 = new String(new BigInteger(Left_bin_last,2).toByteArray());
        String right1 = new String(new BigInteger(Right_bin_last,2).toByteArray());

        System.out.println(left1);
        System.out.println(right1);
        String complete = left1+right1;
        String[] complete_list = complete.split(";;;;");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src\\Messeges\\Decrypted\\decrypted_message1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(String i:complete_list)
        writer.println(i);  /// write to a file



        writer.close();







    }

}
