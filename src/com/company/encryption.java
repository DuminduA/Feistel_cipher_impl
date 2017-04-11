package com.company;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by dumindu on 09/04/2017.
 */
public class encryption {

    public void encrypt() {
        BufferedReader message = null;
        String Left;
        String Right;
        String swap;
        String message1;
        String Left_bin_last = null;
        String Right_bin_last = null;
        String[] key = {"key1hsjsjsjsjsjsjsjjsjsjs","this is the second secret key","this is awesome"}; // longer the key better encryption. at least a length of 10 characters

        try {
            message =
                   new BufferedReader(new FileReader("src\\Messeges\\message1.txt")); // read the message from text
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {

            StringBuffer complete_message = new StringBuffer();
            while((message1 = message.readLine())!= null){                            //buffer the message
                complete_message.append(message1);
                complete_message.append(";;;;");
            }

            Left = complete_message.substring(0,complete_message.length()/2);
            Right = complete_message.substring(complete_message.length()/2);


            if(Left.length()!=Right.length()){
                complete_message.append("x");                           //make the message even length by adding additional string
            }
            Left = complete_message.substring(0,complete_message.length()/2);       //divide to left and right
            Right = complete_message.substring(complete_message.length()/2);



            String Left_bin = new BigInteger(Left.getBytes()).toString(2);
            int leadingzeros_left = 8-Left_bin.length()%8;                          //add leading zeros which removed by BigInteger
            String Left_bin_new = String.format("%0"+String.valueOf(Left_bin.length()+leadingzeros_left)+"d",new BigInteger(Left_bin));

            String Right_bin = new BigInteger(Right.getBytes()).toString(2);
            int leadingzeros_right = 8-Right_bin.length()%8;
            String Right_bin_new = String.format("%0"+String.valueOf(Right_bin.length()+leadingzeros_right)+"d",new BigInteger(Right_bin));

            System.out.println(Left_bin_new);
            System.out.println(Right_bin_new);


            int loop_ends=0;
            int Right_len;



            //loop for make feistel network
            while(loop_ends<key.length){

                Right_len = Right_bin_new.length();
                String key1_bin = new BigInteger(key[loop_ends].getBytes()).toString(2);

                String eqlen_key = String.format("%0"+String.valueOf(Right_len)+"d",new BigInteger(key1_bin)); // make the key as same length




                StringBuffer rightxorkey = new StringBuffer();
                StringBuffer leftxorresult = new StringBuffer();
                rightxorkey.setLength(0);
                leftxorresult.setLength(0);

                for(int i = 0; i < Right_bin_new.length(); i++){
                    rightxorkey.append((Right_bin_new.charAt(i) ^ eqlen_key.charAt(i))); }//feisietel function performed here

                String result = rightxorkey.toString();
                rightxorkey.setLength(0);



                for(int i = 0; i < Left_bin_new.length(); i++){
                    leftxorresult.append((Left_bin_new.charAt(i) ^ result.charAt(i)));} //xor the value from feistel functin and left
                String result2 = leftxorresult.toString();




                if(loop_ends<key.length-1){

                    swap = Right_bin_new;
                    Left_bin_new = swap;
                    Right_bin_new  = result2;
                    }
                else{
                    Left_bin_last = result2;
                    Right_bin_last = Right_bin_new;
                    }

                loop_ends+=1;
            }

//            System.out.println(Left_bin_last);
//            System.out.println(Right_bin_last);

            String Right_new = new String(new BigInteger(Left_bin_last).toByteArray());
            String Left_new = new String(new BigInteger(Right_bin_last).toByteArray());

            PrintWriter writer = new PrintWriter("src\\Messeges\\Encrypted\\encrypted_message1.txt");

            writer.println(Left_new);  /// write to a file
            writer.println(Right_new);
            writer.println("BitStream");
            writer.println(Left_bin_last);
            writer.println(Right_bin_last);

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
