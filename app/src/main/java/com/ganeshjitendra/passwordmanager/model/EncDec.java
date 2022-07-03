package com.ganeshjitendra.passwordmanager.model;

import com.ganeshjitendra.passwordmanager.MainActivity;
import com.ganeshjitendra.passwordmanager.data.DataBaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static com.ganeshjitendra.passwordmanager.ui.RecyclerViewAdapter.keName;
import static com.ganeshjitendra.passwordmanager.ui.RecyclerViewAdapter.pName;

public class EncDec extends MainActivity {

    public static void enc()
    {
        Item item = new Item();
        String title = titleName.getText().toString().trim();
        String txt;
        String key;

        //Scanner sc = new Scanner(System.in);

        ArrayList<Character> a = new ArrayList<Character>(Arrays.asList(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'));

        //System.out.print("Enter Text to be Encrypted : ");
        //txt=sc.nextLine();

        txt = passName.getText().toString().trim();

        //System.out.print("Enter Password : ");
        //key=sc.nextLine();
        key = keyName.getText().toString().trim();

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

        MainActivity.dataBaseHandler.addItem(item);
        //sc.close();
    }
    public static void dec(final Item item)
    {
        String txt;
        String key;
        //Scanner sc = new Scanner(System.in);

        ArrayList<Character> a = new ArrayList<Character>(Arrays.asList(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'));

        //System.out.print("Enter Text to be Decrypted : ");
        //txt=sc.nextLine();

        //System.out.print("Enter Password : ");
        //key=sc.nextLine();
        key = keName.getText().toString().trim();
        txt = item.getPassword();

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

        int flag=1;
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
        pName.setText("Password : "+ s);

        //System.out.println("\nDecrypted text is : "+temp);
        //sc.close();
    }

    public static void enc1(String _title, String pass, String _key, DataBaseHandler db, Item _item)
    {
        Item item = _item;
        String title = _title;
        String txt;
        String key;
        DataBaseHandler handler = db;

        //Scanner sc = new Scanner(System.in);

        ArrayList<Character> a = new ArrayList<Character>(Arrays.asList(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'));

        //System.out.print("Enter Text to be Encrypted : ");
        //txt=sc.nextLine();

        txt = pass;

        //System.out.print("Enter Password : ");
        //key=sc.nextLine();
        key = _key;

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

        handler.updateItem(item);

        //sc.close();
    }
}
