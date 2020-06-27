package com.example.passwordmanager.model;

import com.example.passwordmanager.ListActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Enc extends ListActivity {
    public static void enc()
    {
        Item item = new Item();
        String title = app_n.getText().toString().trim();
        String txt;
        String key;

        //Scanner sc = new Scanner(System.in);

        ArrayList<Character> a = new ArrayList<Character>(Arrays.asList(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'));

        //System.out.print("Enter Text to be Encrypted : ");
        //txt=sc.nextLine();

        txt = pas.getText().toString().trim();

        //System.out.print("Enter Password : ");
        //key=sc.nextLine();
        key = ke.getText().toString().trim();

        char[] ch = new char[txt.length()];
        for (int i = 0; i < txt.length(); i++)
        {
            ch[i] = txt.charAt(i);
        }
        for(int i=0 ; i<key.length() ; i++)
        {
            int j = key.charAt(i);
            Collections.shuffle(a, new Random(j));
        }

        int inc = key.charAt(0)%95;
        int inc_factor=key.charAt(0)%95;
        for(int i=1 ; i<key.length() ; i++)
        {
            if(inc_factor==inc)
            {
                inc_factor=key.charAt(i)%95;
            }
            else break;
        }
        if(inc_factor==inc)
        {
            inc_factor = (inc+50)%95;
        }

        int flag=0;
        StringBuffer temp = new StringBuffer("");
        for (int i = 0; i < txt.length(); i++)
        {
            if(flag==0)
            {

                int ind = a.indexOf(txt.charAt(i));
                temp.append(a.get((ind+inc)%95));
                flag=1;
            }
            else
            {
                int ind = a.indexOf(txt.charAt(i));
                int abc=(ind-inc)%95;
                if(abc<0)
                    abc += 95;
                temp.append(a.get(abc));
                flag=0;
            }
            inc += inc_factor;
        }
        String s = temp.toString();
        //System.out.println("\nEncrypted text is : "+temp);
        item.setWebName(title);
        item.setPassword(s);
        //item.setKey(key);

        ListActivity.dataBaseHandler.addItem(item);
        //sc.close();
    }
}
